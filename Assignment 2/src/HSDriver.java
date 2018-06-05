
public class HSDriver {

	public static void main(String[] args) {
		if(args[0].equals("RandomGen")) {
			if(args.length>3) {
				HeapSort.sort(RandomGen.getInput(Integer.parseInt(args[1]), Integer.parseInt(args[3])), Integer.parseInt(args[2]));
			}else{
				HeapSort.sort(RandomGen.getInput(Integer.parseInt(args[1])), Integer.parseInt(args[2]));
			}
			
		}else if(args[0].equalsIgnoreCase("FixedGen")) {
			HeapSort.sort(FixedGen.getInput(Integer.parseInt(args[1])), Integer.parseInt(args[2]));
		}

	}

}
