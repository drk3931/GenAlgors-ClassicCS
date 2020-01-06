import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/*
 * Contains utility functions used across all 3 solutions
 */

//@Author Deepak Khemraj deepak.khemraj37@qmail.cuny.edu


public class Utils<T> {

	
	public ArrayList<String> getCombinations(Integer3Tuple[] objects) {

		ArrayList<String> results = new ArrayList<>();

		for (int i = 0; i < objects.length; i++) {

			int resLen = results.size();
			for (int j = 0; j < resLen; j++) {

				String toAdd = "";
				toAdd += objects[i].x + " " + objects[i].y + " " + objects[i].z;

				String resArr = results.get(j);
				toAdd += ",";
				toAdd += resArr;

				results.add(toAdd);

			}
			results.add(objects[i].x + " " + objects[i].y + " " + objects[i].z);
		}

		return results;

	}
	
	public ArrayList<Integer3Tuple[]> filterTuples(ArrayList<String> tuples, int len) {
		Integer3Tuple[][] ret = new Integer3Tuple[tuples.size()][];

		for (int i = 0; i < ret.length; i++) {
			ret[i] = getTuples(tuples.get(i));

		}

		ArrayList<Integer3Tuple[]> filtered = new ArrayList<>();
		for (int i = 0; i < ret.length; i++) {
			if (ret[i].length == len) {
				filtered.add(ret[i]);
			}

		}

		return filtered;

	}

	public Integer3Tuple[] getTuples(String str) {
		String[] split = str.split(",");
		Integer3Tuple[] tuples = new Integer3Tuple[split.length];

		for (int i = 0; i < tuples.length; i++) {
			String[] nums = split[i].split("\\s");

			tuples[i] = new Integer3Tuple(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]),
					Integer.parseInt(nums[2]));

		}
		return tuples;
	}
	
	public static <T> boolean hasDuplicates(T[] array)
	{
		return array.length != Stream.of(array).distinct().count();
	}
	
	
	
	
}
