class AllOnesGenAlg extends Genetic
{

    public AllOnesGenAlg(TerminationCondition t, SelectionStrategy s)
    {
        super(t,s);
    }

    @Override
    public float calcFitness(Organism o)
    {
        int[] chromosomes = o.getChromosomes();
        int numChromosomes = o.getNumChromosomes();

        int passedChromosomes = 0;

        for (int i = 0; i < numChromosomes; i++) {
            if (chromosomes[i] == 1) {
                passedChromosomes++;
            }
        }
        float fitness = (float) passedChromosomes / numChromosomes;
        o.setFitness(fitness);
        return o.getFitness();
    }

}