import java.lang.Exception;

public class Organism {
    private int chromosomes[];
    private float fitness = Globals.UNEVALUATED;

    public Organism(int bitLen) {
        this.genRandomChromosomes(bitLen);
    }

    public Organism(int[] chromosomes)
    {
        this.chromosomes = chromosomes;
    }

    public void genRandomChromosomes(int numGenes) {
        chromosomes = new int[numGenes];
        for (int gene = 0; gene < numGenes; gene++) {
            chromosomes[gene] = Math.random() * 100 < 50 ? 0 : 1;
        }
    }

    public int[] getChromosomes()
    {
        return this.chromosomes;
    }

    public int getNumChromosomes()
    {
        return this.chromosomes.length;
    }


    private boolean validGeneValue(int val) {
        return val == 0 || val == 1 ? true : false;
    }

    public void setGene(int pos, int val) throws Exception {
        if (validGeneValue(val) && pos <= chromosomes.length && pos >= 0) {
            chromosomes[pos] = val;
        } else {
            throw new Exception("Error: invalid gene value: " + val);
        }
    }

    public int getGene(int pos) {
        return chromosomes[pos];
    }

    public float getFitness() {
        return this.fitness;
    }

    public void setFitness(float fitness)
    {
        this.fitness = fitness;
    }

    public String toString() {

        StringBuilder strRep = new StringBuilder();

        for(int gene = 0; gene < this.chromosomes.length; gene++) {
            strRep.append(this.chromosomes[gene]);
        }
        return strRep.toString();
    }
}