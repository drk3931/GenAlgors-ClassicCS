import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BruteForceSol {
	
	
	//@Author Deepak Khemraj deepak.khemraj37@qmail.cuny.edu


	private ThreeDMatchingProblem prob;

	public BruteForceSol() {
		prob = new ProblemReaderWriter().load3DmatchingProb("problem.txt");
		prob.printProblem();
	}

	public void solve(int q) {

		// sets tuple represents a tuple of len=setsize that has
		// one element from each set in each of the len positions
		// we generate all of them.

		int setSize = prob.SET1.length;
		int[] setTuple = new int[3];

		// m represents the set that contains all of the possible tuples of the 3 input
		// sets there are n^3 total

		ArrayList<Integer[]> m = new ArrayList<>();

		for (int i = 0; i < setSize; i++) {

			for (int j = 0; j < setSize; j++) {

				for (int k = 0; k < setSize; k++) {

					m.add(new Integer[] { prob.SET1[i], prob.SET2[j], prob.SET3[k] });

				}

			}

		}
		
		//Integer3TupleStore is a helper class
		Utils<Integer3Tuple> u = new Utils<>();
		
		Integer3Tuple[] tupleStore = new Integer3Tuple[m.size()];
		for(int i = 0; i < m.size(); i++)
		{
			
		    tupleStore[i] = new Integer3Tuple();
			tupleStore[i].x = m.get(i)[0];
			tupleStore[i].y = m.get(i)[1];
			tupleStore[i].z = m.get(i)[2];


		}
		
		
		//we get all the possible combinations but filter them such that 
		//we only have those of length Q
		ArrayList<Integer3Tuple[]> combos = u.filterTuples(u.getCombinations(tupleStore), q);
		ArrayList<Integer3Tuple[]> answers = new ArrayList<>();

		
		//go through every possible combination, this is the brute forcing
		for(int i = 0; i < combos.size(); i++)
		{
			
			Integer3Tuple[] combo = combos.get(i);
			
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
				answers.add(combo);
			}
			
			
			
		}
		
		System.out.println("Q = " + q); 
		System.out.println("THE ANSWERS FOR THE BRUTE FORCE SOLUTION ARE");
		for(Integer3Tuple[] tup: answers)
		{
			Integer3Tuple.printTupleArray(tup);
		}

	}
	
	

	public static void main(String[] args) {
		BruteForceSol bfSol = new BruteForceSol();
		bfSol.solve(2);

	}

}
