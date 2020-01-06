
public class ThreeDMatchingProblem {
	
	
	//@Author Deepak Khemraj deepak.khemraj37@qmail.cuny.edu

	public final int[] SET1,SET2,SET3;
	
	/**
	 * 
	 * @param set1 set of ints, assure it is the same size as sets 2,3
	 * @param set2 set of ints, assure it is the same size as sets 1,3
	 * @param set3set of ints, assure it is the same size as sets 1,2
	 */
	
	public ThreeDMatchingProblem(int[] set1, int[] set2, int[] set3)
	{
		
		SET1 = set1; 
		SET2 = set2;
		SET3 = set3;
		//check the same size invariant
		assert(SET1.length == SET2.length && SET2.length == SET3.length );
		
		
	}
	
	public void printProblem()
	{
		
		System.out.println("3D MATCHING SETS ");
		System.out.println("********************* ");

		for(int i = 0; i < 3; i++)
		{
			int[] targetSet = null;
			if(i == 0)
			{
				targetSet = SET1;
			}
			if(i == 1)
			{
				targetSet = SET2;
			}
			if(i == 2)
			{
				targetSet = SET3;
			}
			
			for(int j= 0; j < targetSet.length; j++)
			{
				System.out.print(targetSet[j] + " ");
			}
			System.out.println();
		}
		System.out.println("********************* ");

	}

}
