public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this;
		int totalSize = 0;
		while (p != null) {
			totalSize += 1;
			p = p.rest;
		}
		return totalSize;
	}

	/** Returns the ith item of this IntList. */
	public int get(int i) {
		if (i == 0) {
			return first;
		}
		return rest.get(i - 1);
	}

	/** Square the list, then add x to the end of the list.
	 *  e.g. 1 => 2, after adding 5 to the list, the list should be 
	 * 		 1 => 1 => 2 => 4 => 5
	 */
	public void addAdjacent(int x) {
		if (rest == null) {
			rest = new IntList(first * first, new IntList(x, null));
			return ;
		}

		rest = new IntList(first * first, rest);
		rest.rest.addAdjacent(x);
	}
	public static void main(String[] args) {
		// IntList L = new IntList(15, null);
		// L = new IntList(10, L);
		// L = new IntList(5, L);

		// System.out.println(L.get(100));

		// Test addAdjacent
		IntList L = new IntList(2, null);
		L = new IntList(1, L);

		L.addAdjacent(5);
		System.out.println(L.get(3)); // should print out 4
		System.out.println(L.get(4)); // should print out 5
		L.addAdjacent(7);
		System.out.println(L.get(10)); // should print out 7
	}
} 