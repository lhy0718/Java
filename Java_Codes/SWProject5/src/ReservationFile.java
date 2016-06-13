import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

@SuppressWarnings("serial")
public class ReservationFile extends File {
	private ArrayList<ReservationRecord> lines;
	private ArrayList<String> comments;
	private File tempFile;
	
	ReservationFile(final String fileName) {
		super(fileName);
		lines = new ArrayList<ReservationRecord>();
		comments = new ArrayList<String>();
		tempFile = newTempFile();
		int lineNum = 0;
		ArrayList<Integer> errorNum = new ArrayList<Integer>();
		Scanner input = null;
		try {
			input = new Scanner(this);
		}
		catch(Exception e) {
			System.out.println("*INVALID_FILE*");
			input.close();
			return;
		}
		while (input.hasNext()) {
			lineNum++;
			String line = input.nextLine();
			
			if (line == null || line.startsWith("//")){
				comments.add(line);
				continue;
			}
			ReservationRecord r = new ReservationRecord(line);
			if(r.getMemo()==null){
				lines.add(r);
				continue;
			}
			if(r.getMemo().startsWith("//")){
				errorNum.add(lineNum);
				comments.add(r.getMemo());
				continue;
			}
			if(r.getRoomName()==null)
				continue;
			lines.add(r);
		}
		checkOverlap();
		String errorNums = "";
		for(int i : errorNum)
			errorNums += i+"줄, "; 
		if (errorNum.size()>0)
			new Notice(errorNums+"\b\b은 잘못된 입력입니다.");
		reWrite(tempFile);
		
		input.close();
	}

	ArrayList<ReservationRecord> getList(){
		return lines;
	}
	
	private boolean checkOverlap(){
		boolean b = false;
		ArrayList<ReservationRecord> temp = new ArrayList<ReservationRecord>();
		if (lines.size()!= 0){
			for(ReservationRecord r1 : lines.subList(0, lines.size()-1)){
				for(ReservationRecord r2 : lines.subList(lines.indexOf(r1)+1, lines.size())){
					if(r1.conflict(r2)){
						new Notice("시간겹침 : "+r1.toString()+" & "+r2.toString());
						temp.add(r2);
						b = true;
						break;
					}
				}
			}
			for(ReservationRecord r : temp)
				lines.remove(lines.indexOf(r));
			return b;
		}
		return false;
	}
	boolean reWrite(File name){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(name));
			for(String ss : comments){
				out.write(ss); out.newLine();
			}
			for(ReservationRecord r : lines){
				String s = r.toString();
				out.write(s); out.newLine();
			}
			out.close();
		return true;	
		}
		catch(Exception e){
			System.out.println("reWrite_error");
			return false;
		}
	}
	private File newTempFile(){
		Calendar calendar = Calendar.getInstance();
		String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(calendar.getTime());
		System.out.println(name);
		return new File("temp"+name+".txt");
	}
	
	String getTempFileName(){
		return tempFile.getName();
	}
	
	boolean remove(int index){
		lines.remove(index);
		reWrite(tempFile);
		return true;
	}
	
	boolean add(ReservationRecord r){
		lines.add(r);
		if(checkOverlap())
			return false;
		reWrite(tempFile);
		return true;
	}

	void save() {
		System.out.println(this.delete());
		tempFile.renameTo(this);
	}
}
