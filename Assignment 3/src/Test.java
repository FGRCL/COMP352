import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<Integer>();
		System.out.println("Building Tree");
		Random rand = new Random(1337);
		for(int i=0; i<100; i++) {
			int nb = rand.nextInt(100);
			System.out.println(nb);
			tree.insert(nb);
		}
		tree.printTree();
		System.out.println("Done");
	}

}
