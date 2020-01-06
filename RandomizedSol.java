
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


//@Author Deepak Khemraj deepak.khemraj37@qmail.cuny.edu
public class RandomizedSol {
	
	

	private ThreeDMatchingProblem prob;
	
	//this represents how many times to run the random trial
	final int NUM_RANDOM_TRIALS = 500;

	public RandomizedSol() {
		prob = new ProblemReaderWriter().load3DmatchingProb("problem.txt");
		prob.printProblem();
	}


	public void solve(int q) {

		// sets tuple represents a tuple of len=setsize that has
		// one element from each set in each of the len positions
		// we generate all of them.

		int setSize = prob.SET1.length;
		int[] setTuple = new int[3];

		// m represents the set that contains all of the possilbe tuples of the 3 input
		// sets

		
		ArrayList<Integer[]> m = new ArrayList<>();

		//there are setLen^3 number of triples
		for (int i = 0; i < setSize; i++) {

			for (int j = 0; j < setSize; j++) {

				for (int k = 0; k < setSize; k++) {

					m.add(new Integer[] { prob.SET1[i], prob.SET2[j], prob.SET3[k] });

				}

			}

		}
		
		Utils<Integer3Tuple> u = new Utils<>();
		
		Integer3Tuple[] tupleStore = new Integer3Tuple[m.size()];
		for(int i = 0; i < m.size(); i++)
		{
			
		    tupleStore[i] = new Integer3Tuple();
			tupleStore[i].x = m.get(i)[0];
			tupleStore[i].y = m.get(i)[1];
			tupleStore[i].z = m.get(i)[2];


		}
		
		//ONLY GET THE COMBINATIONS OF LEN Q
		ArrayList<Integer3Tuple[]> combos = u.filterTuples(u.getCombinations(tupleStore), q);
		ArrayList<Integer3Tuple[]> answers = new ArrayList<>();

		
		
		/*
		 *  THE FOLLOWING IS THE RANDOM PART
		 */
		Random r = new Random(System.currentTimeMillis());
		int randomCounter = 0;
		
		while(randomCounter <= NUM_RANDOM_TRIALS)
		{
			//GET A RANDOM COMBINATION INSTEAD OF GOING THROUGH EVERY  SINGLE ONE
			Integer3Tuple[] combo = combos.get(
					r.nextInt(combos.size())
			);
			
			Integer[] asIntArray = new Integer[3 * combo.length];
			
			
			for(int j = 0; j < combo.length; j++)
			{	
				int offset = j* 3; 
				Integer3Tuple tuple = combo[j];
				asIntArray[0 + offset] = tuple.x;
				asIntArray[1 + offset] = tuple.y;
				asIntArray[2 + offset] = tuple.z;

			}
			
			
			if(!Utils.hasDuplicates(asIntArray))
			{
		
				Integer3Tuple[] f = null;
				
				//a check to make sure not adding duplicates
				for(Integer3Tuple[] t: answers)
				{
					if(t == combo)
					{
						f = t;
					}
				}
				if(f == null)
				{
					answers.add(combo);
				}
			}
			randomCounter++;
		}
		
		System.out.println("Q = " + q); 
		System.out.println("NUM RANDOM TRIALS = " + NUM_RANDOM_TRIALS);
		System.out.println("THE ANSWERS FOR THE RANDOMIZED SOLUTION ARE");
		for(Integer3Tuple[] tup: answers)
		{
			Integer3Tuple.printTupleArray(tup);
		}

	}
	
	

	public static void main(String[] args) {
		RandomizedSol rsSol = new RandomizedSol();
		rsSol.solve(2);

	}

}
