
class ReservationRecord {
	
	private String roomName;
	private int day;
	private int time;
	private String personName;
	private String memo;
	
	ReservationRecord(String roomName, int day, int time, String personName, String memo){
		this.roomName = roomName;
		this.day = day;
		this.time = time;
		this.personName = personName;
		this.memo = memo;
	}
	ReservationRecord(){
		this.roomName = null;
		this.day = -1;
		this.time = -1;
		this.personName = "";
		this.memo = null;
	}
	ReservationRecord(String line){
		this();
		
		String[] parsedStrings = line.split(":");
		
		if(parsedStrings.length<4){
			memo = "//"+line;
			roomName = null;
			return;
		}
	
		for(RoomName room_ : RoomName.values()){
			if(parsedStrings[0].trim().toUpperCase().equals(room_.name())){
				roomName = room_.name();
			}
		}
		if(roomName == null){
			memo = "//"+line;
			return;
		}

		for(Day day_ : Day.values())
			if(parsedStrings[1].trim().toUpperCase().equals(day_.name())){
				day = day_.getNum();
			}
		if(day<0 || day>6){
			memo = "//"+line;
			roomName = null;
			return;
		}
		
		try{
			time = Integer.parseInt(parsedStrings[2].trim());
			if(time<0 || time>8){
				memo = "//"+line;
				roomName = null;
				return;
			}
		}
		catch (Exception e){
			memo = "//"+line;
			roomName = null;
			return;
		}

		personName = parsedStrings[3].trim();
		if(personName.equals("")){
			memo = "//"+line;
			roomName = null;
			return;
		}
		
		try{
			memo = parsedStrings[4];
		}
		catch (Exception e){
			memo = null;
		}
	}

	String getRoomName(){
		return this.roomName;
	}
	int getDay(){
		return this.day;
	}
	int getTime(){
		return this.time;
	}
	String getPersonName(){
		return this.personName;
	}
	String getMemo(){
		return this.memo;
	}
	
	boolean conflict(ReservationRecord r){
		if(this.roomName.equals(r.roomName) && this.day == r.day && this.time == r.time)
			return true;
		return false;
	}
	
	public String toString(){
		if(memo == null)
			memo = "";
		if(roomName == null)
			return null;
		return (roomName + ":" + Day.values()[day] + ":" + time + ":" + personName + ":" + memo);
	}
}
