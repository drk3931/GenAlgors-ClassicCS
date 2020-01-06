class RunGenAlg {

    public static void main(String[] args) throws Exception {
        AllOnesGenAlg gen = new AllOnesGenAlg(Genetic.TerminationCondition.ACCEPTABLE_FITNESS, Genetic.SelectionStrategy.ELITISM);
        gen.setAcceptableTerminationFitness(1.0f);
        gen.setCrossoverRate(0.95f);
        gen.setPopSize(10);
        gen.setElitistCount(2);
        gen.setMutRate(0.03f);
        gen.initialize();
        gen.routine();
        System.out.println(gen.getPopulation().toString());
        System.out.println("Total iteration count: " + gen.getNumIterations());
    }

}
