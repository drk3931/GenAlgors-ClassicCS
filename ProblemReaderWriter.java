import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//@Author Deepak Khemraj deepak.khemraj37@qmail.cuny.edu


	public class ProblemReaderWriter {
			
		 

		public ProblemReaderWriter()
		{

		}
		 /**
			 * 
			 * @param 
			 * fileHandle needs a file with a set of numbers on each line separated only by a space 
			 * after each one. all sets must have the same cardinality
			 *             
			 */
		public ThreeDMatchingProblem load3DmatchingProb(String problemFileHandle)
		{
			
                //store the parsed sets here
                int[][] sets = new int[3][];

				try
				{
					   BufferedReader bfReader = new BufferedReader(new FileReader(problemFileHandle));
			            String line;
			            
			            
			            int setCounter = 0; 
			            
			            while ((line = bfReader.readLine()) != null) {
			                
			            	//parse each set line by  line 
			            	String[] setNumbersTmp = line.split("\\s");
			            	int[] setNumbers = new int[setNumbersTmp.length];
			            	for(int i = 0; i < setNumbersTmp.length; i++)
			            	{
			            		setNumbers[i] = Integer.parseInt(setNumbersTmp[i]);
			            	}
			            	
			            	sets[setCounter] = setNumbers; 
			            	setCounter++;
			            	
			            	
			        
			            }
			            bfReader.close();
			            
			            
			    
				}	
			            
		        catch (IOException e) {
		        	//log errors
		            e.printStackTrace(System.err);
		        }
				return new ThreeDMatchingProblem(sets[0], sets[1], sets[2]);
		
		}
	
		
	}