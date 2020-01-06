import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.lang.Exception;
import java.lang.StringBuilder;

public class Population {

    private Organism[] population;
    private int chromosomeLen;
    private boolean needsSorting = true;

    public Population(int numMembers, int chromosomeLen) {
        this.population = new Organism[numMembers];
        for (int i = 0; i < population.length; i++) {
            this.population[i] = new Organism(chromosomeLen);
        }
        this.chromosomeLen = chromosomeLen;

    }

    public Organism getFittest(int nthFittest) throws Exception {
        if (this.needsSorting) {
            throw new Exception("Error: this population has not been sorted by fitness.");
        } else {
            return this.population[nthFittest];

        }

    }

    public void shuffle() {
        Random rnd = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            // i+1 because nextInt is 0 to exclusive
            int swapIndex = rnd.nextInt(i + 1);
            Organism tmp = population[swapIndex];
            population[swapIndex] = population[i];
            population[i] = tmp;
        }
        this.needsSorting = true; 
    }

    public void sortByFitness() {
        Arrays.sort(population, new Comparator<Organism>() {

            @Override
            public int compare(Organism a, Organism b) {
                if (a.getFitness() < b.getFitness()) {
                    return 1;
                } else if (a.getFitness() > b.getFitness()) {
                    return -1;
                } else {
                    return 0;
                }
            }

        });
        this.needsSorting = false;
    }

    public Organism[] getPop() {
        return this.population;
    }

    public int getNumOrganisms() {
        return this.population.length;
    }

    public int getChromosomeLen() {
        return this.chromosomeLen;
    }

    public void setMember(int index, Organism o)
    {
        this.needsSorting = true; 
        this.population[index] = o;
    }

    public Organism getMember(int index)
    {
        return population[index];
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < population.length;i++)
        {
            Organism o = population[i];
            s.append(o.toString());
            s.append(System.getProperty("line.separator"));
        }
        return s.toString();
    }
}