
class ParsingFile {
	int max;
	private String[][] parsedString;
	ParsingFile(String[] fileString){
		max = fileString.length;
		parsedString = new String[max][5];
		for(int i = 0; i < fileString.length; i++){
			if (fileString[i] != null && !fileString[i].startsWith("//") && !fileString[i].trim().equals("")){
				String temp[] = fileString[i].split(":");
				if(temp.length<4){
					new ErrorReciever("CODE:0001_@_line_NO_" + (i + 1));
					continue;
				}
				for (int j = 0; j < temp.length && j < 5; j++){
					parsedString[i][j] = temp[j].trim();
				}
			}
		}
	}
	
	ReservationRecord[] returnArray(){
		ReservationRecord[] array = new ReservationRecord[max];
		for(int i = 0; i<max; i++){
			array[i] = new ReservationRecord();
			if (parsedString[i][0] == null)
				continue;
			array[i].roomName = parsedString[i][0];
			array[i].day = parsedString[i][1];
			try{
				Integer.parseInt(parsedString[i][2]);
			}
			catch(Exception e){
				new ErrorReciever("CODE:0003_@_line_NO_" + (i + 1));
				array[i].roomName = null;
				continue;
			}
			if(Integer.parseInt(parsedString[i][2]) <= 8 && Integer.parseInt(parsedString[i][2]) >= 1)
				array[i].time = Integer.parseInt(parsedString[i][2]);
			else{
				new ErrorReciever("CODE:0003_@_line_NO_" + (i + 1));
				array[i].roomName = null;
			}
			array[i].personName = parsedString[i][3];
			array[i].memo = parsedString[i][4];
		}
		return array;
	}
}
