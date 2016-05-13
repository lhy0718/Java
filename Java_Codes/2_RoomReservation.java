
public class RoomReservation {
	public void showReservation(String reservationFileName){
		int maxNum = 100;
		new ErrorReciever("START");
		ReadFile readFile = new ReadFile(reservationFileName, maxNum);
		ParsingFile parsingFile = new ParsingFile(readFile.returnString());
		RoomReservationTable[] tables = new InsertToTable().insert(parsingFile.returnArray());
		new PrintTable(tables);
		ErrorReciever errorEnd = new ErrorReciever("END");
		errorEnd.ErrorPrinter();
	}
}