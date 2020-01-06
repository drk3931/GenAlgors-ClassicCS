import java.util.Random;
import java.util.logging.Logger;

abstract class Genetic {

    private int popSize;
    private float mutRate, crossoverRate;
    private Population population;
    private float ACCEPTABLE_TERMINATION_FITNESS;
    private Random rGen;
    private int numIterations; 
    private int maxAllowableIterations; 
    private int elitistCount;

    public enum TerminationCondition {
        NUM_GENS, ACCEPTABLE_FITNESS
    }

    public enum SelectionStrategy {
        ELITISM
    }

    TerminationCondition terminationCondition;
    SelectionStrategy selectionStrategy;

    public Genetic(TerminationCondition tCon, SelectionStrategy sStrat) {


        this.terminationCondition = tCon;
        this.selectionStrategy = sStrat;
        this.numIterations = 0; 

    }

    public Genetic setCrossoverRate(float crossRate)
    {
        this.crossoverRate = crossRate;
        return this; 
    }

    public Genetic setMutRate(float mutRate)
    {
        this.mutRate = mutRate; 
        return this; 
    }

    public Genetic setPopSize(int popSize)
    {
        this.popSize = popSize;
        return this; 
    }

    
    public Genetic setMaxIterations(int iterations)
    {
        this.maxAllowableIterations = iterations; 
        return this;
    }

    public Genetic setAcceptableTerminationFitness(float fitness)
    {
        this.ACCEPTABLE_TERMINATION_FITNESS = fitness; 
        return this;
    }

    public Genetic setElitistCount(int elitistCount)
    {
        this.elitistCount = elitistCount;
        return this;
    }

    public Genetic setTerminationCondition(TerminationCondition t)
    {
        this.terminationCondition = t; 
        return this; 
    }

    public Genetic setSelectionStrategy(SelectionStrategy s)
    {
        this.selectionStrategy = s; 
        return this; 
    }

    public Population getPopulation()
    {
        return this.population;
    }


    public void initialize()
    {
        this.population = new Population(this.popSize, 8);
        rGen = new Random();

        System.out.println("Running Genetic Algorithm with Parameters: \n");
        System.out.println("Population Size: " + this.popSize);
        System.out.println("Mutation Rate: " + this.mutRate);
        System.out.println("Crossover Rate: " + this.crossoverRate);

        System.out.println();
        System.out.println("Population Members:");
    }




    public void routine() throws Exception {
        for (int i = 0; i < population.getNumOrganisms(); i++) {
            Organism o = population.getMember(i);
            calcFitness(o);
        }

        population.sortByFitness();

        while (!shouldTerminate(population)) {
            crossoverPop(population);
            population.sortByFitness();
            for (int i = 0; i < population.getNumOrganisms(); i++) {
                Organism o = population.getMember(i);
                calcFitness(o);
            }
            incrementNumIterations();

        }



    }

    public boolean shouldTerminate(Population p) throws Exception {
        if (terminationCondition == TerminationCondition.ACCEPTABLE_FITNESS) {

            if (p.getFittest(0).getFitness() == this.ACCEPTABLE_TERMINATION_FITNESS) {
                return true;
            } else {
                return false;
            }

        }

        return false;
    }

    

    public Organism rouletteSpin() throws Exception {
        Organism[] organisms = population.getPop();
        float popFitness = genPopulationFitness(population);

        // we do this backwards from how a roulette wheel would work.
        // we get the random position first and work backwards to find out which
        // organism it belongs to
        // given the cumulative population fitness we pick a random point on it
        float randomPos = (float) Math.random() * popFitness;

        float currentSearchPos = 0;
        for (int i = 0; i < organisms.length; i++) {

            Organism o = organisms[i];
            currentSearchPos += calcFitness(o);
            if (currentSearchPos >= randomPos) {
                return o;
            }
        }
        throw new Exception("Error: failed to perform roulette wheel selection");
    }

    // each gene has a 50 percent chance of coming from either parent.
    public Organism uniformCrossover(Organism a, Organism b) {
        Organism offspring = new Organism(a.getChromosomes().length);
        Random r = new Random();

        for (int gene = 0; gene < offspring.getChromosomes().length; gene++) {
            boolean swapGene = r.nextFloat() * 100 < 50 ? true : false;
            if (swapGene) {
                offspring.getChromosomes()[gene] = a.getChromosomes()[gene];
            } else {
                offspring.getChromosomes()[gene] = b.getChromosomes()[gene];
            }

        }

        return offspring;

    }

    public abstract float calcFitness(Organism o);

    public float genPopulationFitness(Population p) {

        Organism[] population = p.getPop();
        float totalFitness = 0;
        for (int i = 0; i < population.length; i++) {
            totalFitness += calcFitness(population[i]);
        }

        return totalFitness;
    }

    public Population crossoverPop(Population p) throws Exception {

        Population newGen = new Population(p.getNumOrganisms(), p.getChromosomeLen());

        for (int nthFittestIndex = 0; nthFittestIndex < p.getNumOrganisms(); nthFittestIndex++) {
            Organism nthFittestOrganism = p.getPop()[nthFittestIndex];
            if (canCrossover()) {
                if (this.selectionStrategy == SelectionStrategy.ELITISM) {
                    if (nthFittestIndex > elitistCount) {
                        Organism offSpring = uniformCrossover(nthFittestOrganism, rouletteSpin());
                        mutateOrganism(offSpring);
                        p.setMember(nthFittestIndex, offSpring);
                    }
                }

            } else {
                p.setMember(nthFittestIndex, nthFittestOrganism);
            }

        }
        return newGen;
    }

    private boolean canCrossover() {
        return (float) Math.random() < this.crossoverRate;
    }

    private Organism mutateOrganism(Organism o) throws Exception {
       
        float guess = (float)Math.random();

        if(guess <= mutRate)
        {
            return mutateFunction(o);

        }else{
            return o;
        }


    }

    private Organism mutateFunction(Organism o) throws Exception {
        int geneIndex = rGen.nextInt(o.getNumChromosomes());
        o.setGene(geneIndex, o.getGene(geneIndex) == 0 ? 1 : 0);
        return o;
    }


    public int getNumIterations()
    {
        return this.numIterations;
    }

    public void incrementNumIterations()
    {
        this.numIterations++;
    }
}