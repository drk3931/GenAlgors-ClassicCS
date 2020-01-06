//@Author Deepak Khemraj deepak.khemraj37@qmail.cuny.edu

class Integer3Tuple {
	public Integer x, y, z;

	public Integer3Tuple(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Integer3Tuple() {
	}

	public static void printTupleArray(Integer3Tuple[] arr) {
		for (int i = 0; i < arr.length; i++) {
			Integer3Tuple tup = arr[i];

			printTuple(tup);

		}
		System.out.println();
	}

	public static void printTuple(Integer3Tuple tup) {

		System.out.print("( " + tup.x + " ," + tup.y + " ," + tup.z + " )");

	}
}
