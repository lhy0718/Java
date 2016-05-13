
class InsertToTable {
	static int tableNum;
	private static RoomReservationTable[] tables;
	RoomReservationTable[] insert(ReservationRecord[] array){
		tableNum = 0;
		tables = new RoomReservationTable[3];
		for(int i = 0; i < tables.length; i++){
			tables[i] = new RoomReservationTable("");
		}
		for (int i = 0; i < array.length; i++){
			boolean b = false;
			for (int j = 0; j < tables.length; j++){
				if(tables[j].roomName.equals(array[i].roomName)){
					b = true;
				}
			}
			if(!b && array[i].roomName != null && tableNum < tables.length){
				tables[tableNum++] = new RoomReservationTable(array[i].roomName);
			}
		}
		for (int i = 0; i < array.length; i++){
			for (int j = 0; j < tables.length; j++){
				if (array[i].roomName != null && array[i].roomName.equals(tables[j].roomName)){
					switch(array[i].day.toUpperCase()){
					case "SUNDAY" : 
					case "SUN":
						if (tables[j].reservationer[0][array[i].time - 1] == null)
							tables[j].reservationer[0][array[i].time - 1] = array[i].personName;
						else{
							new ErrorReciever("CODE:0004_@_line_NO_" + (i + 1));
							tables[j].reservationer[0][array[i].time - 1] = "*ERROR*";
						}
						break;
					case "MONDAY" : 
					case "MON":
						if (tables[j].reservationer[1][array[i].time - 1] == null)
							tables[j].reservationer[1][array[i].time - 1] = array[i].personName;
						else{
							new ErrorReciever("CODE:0004_@_line_NO_" + (i + 1));
							tables[j].reservationer[1][array[i].time - 1] = "*ERROR*";
						}
						break;
					case "TUESDAY" : 
					case "TUE":
						if (tables[j].reservationer[2][array[i].time - 1] == null)
							tables[j].reservationer[2][array[i].time - 1] = array[i].personName;
						else{
							new ErrorReciever("CODE:0004_@_line_NO_" + (i + 1));
							tables[j].reservationer[2][array[i].time - 1] = "*ERROR*";
						}
						break;
					case "WEDNESDAY" : 
					case "WED":
						if (tables[j].reservationer[3][array[i].time - 1] == null)
							tables[j].reservationer[3][array[i].time - 1] = array[i].personName;
						else{
							new ErrorReciever("CODE:0004_@_line_NO_" + (i + 1));
							tables[j].reservationer[3][array[i].time - 1] = "*ERROR*";
						}
						break;
					case "THURSDAY" : 
					case "THR":
						if (tables[j].reservationer[4][array[i].time - 1] == null)
							tables[j].reservationer[4][array[i].time - 1] = array[i].personName;
						else{
							new ErrorReciever("CODE:0004_@_line_NO_" + (i + 1));
							tables[j].reservationer[4][array[i].time - 1] = "*ERROR*";
						}
						break;
					case "FRIDAY" : 
					case "FRI":
						if (tables[j].reservationer[5][array[i].time - 1] == null)
							tables[j].reservationer[5][array[i].time - 1] = array[i].personName;
						else{
							new ErrorReciever("CODE:0004_@_line_NO_" + (i + 1));
							tables[j].reservationer[5][array[i].time - 1] = "*ERROR*";
						}
						break;
					case "SATURDAY" : 
					case "SAT":
						if (tables[j].reservationer[6][array[i].time - 1] == null)
							tables[j].reservationer[6][array[i].time - 1] = array[i].personName;
						else{
							new ErrorReciever("CODE:0004_@_line_NO_" + (i + 1));
							tables[j].reservationer[6][array[i].time - 1] = "*ERROR*";
						}
						break;
					default : 
						new ErrorReciever("CODE:0002_@_line_NO_" + (i + 1));
						break;
					}
				}
			}
		}
		return tables;
	}
}
