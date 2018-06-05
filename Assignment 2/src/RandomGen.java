import java.util.Random;

public class RandomGen {
	
	public static int[] getInput(int n, int seed) {
		int[] randoms;
		Random rnd = new Random(seed);
		randoms = new int[n];
		for(int i=0; i<randoms.length; i++) {
			randoms[i]=rnd.nextInt();
		}
		return randoms;
	}
	
	public static int[] getInput(int n) {
		int[] randoms;
		Random rnd = new Random();
		randoms = new int[n];
		for(int i=0; i<randoms.length; i++) {
			randoms[i]=rnd.nextInt();
		}
		return randoms;
	}
}
