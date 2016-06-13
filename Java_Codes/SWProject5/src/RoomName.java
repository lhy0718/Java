
enum RoomName {
	ROOM514(0), ROOM515(1), ROOM527(2);
	private int num;
	
	private RoomName(int num) {
		this.num = num;
	}
	
	int getNum(){
		return num;
	}
}