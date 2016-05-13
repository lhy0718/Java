import java.io.File;
import java.util.Scanner;

class ReadFile {
	private String[] lines;
	private int count = 0;
	ReadFile(String fileName, int max){
		lines = new String[max];
		Scanner input = null;
		try {
			input = new Scanner(new File(fileName));
			}
			catch(Exception e) {
				System.out.println("*INVALID_FILE*");
				System.exit(0);
			}
			while (input.hasNext()) {
			lines[count++] = input.nextLine();
			}
	}
	
	String[] returnString(){
		return lines;
	}
}
