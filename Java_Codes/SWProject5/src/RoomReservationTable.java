
class RoomReservationTable {
	private String roomName = null;
	private ReservationState[][] table;
	
	RoomReservationTable(RoomName roomName){
		this();
		this.roomName = roomName.name();
	}
	
	RoomReservationTable(){
		this.table = new ReservationState[7][8];
		for(int i = 0; i<7; i++){
			for(int j=0; j<8; j++){
				table[i][j] = new ReservationState();
			}
		}
	}
	
	ReservationState[][] getTable(){
		return table;
	}
	
	String getRoomName(){
		return this.roomName;
	}

	void insert(ReservationRecord r, int listIndex){
		if (r.getRoomName() == null || r.getDay()==-1 || r.getTime()==-1)
			return;
		if(this.roomName.equals(r.getRoomName()) && !this.table[r.getDay()][r.getTime()-1].isReserved){
			this.table[r.getDay()][r.getTime()-1].isReserved = true;
			this.table[r.getDay()][r.getTime()-1].personName = r.getPersonName();
			this.table[r.getDay()][r.getTime()-1].listIndex = listIndex;
		}
	}
	void insert(ReservationRecord r){
		if (r.getRoomName() == null || r.getDay()==-1 || r.getTime()==-1)
			return;
		if(this.roomName.equals(r.getRoomName()) && !this.table[r.getDay()][r.getTime()-1].isReserved){
			this.table[r.getDay()][r.getTime()-1].isReserved = true;
			this.table[r.getDay()][r.getTime()-1].personName = r.getPersonName();
		}
	}
	class ReservationState{
		boolean isReserved;
		String personName;
		int listIndex;
		
		public ReservationState() {
			isReserved = false;
			personName = "";
			listIndex = -1;
		}
		ReservationState(String personName, boolean isReserved, int listIndex){
			this.personName = personName;
			this.isReserved = isReserved;
			this.listIndex = listIndex;
		}

		ReservationState(String personName, boolean isReserved){
			this.personName = personName;
			this.isReserved = isReserved;
		}
	}
}

