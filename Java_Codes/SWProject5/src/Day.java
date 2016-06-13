
enum Day {
	MON(0), TUE(1), WED(2), THU(3), FRI(4), SAT(5), SUN(6);
	private int num;
	
	private Day(int num){
		this.num = num;
	}
	
	int getNum(){
		return this.num;
	}
}
