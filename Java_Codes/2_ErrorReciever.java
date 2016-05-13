
class ErrorReciever {
	static String[] errors = new String[100];
	static int index;
	ErrorReciever(String msg){
		if(msg.equals("START")){
			index = 0;
			for(int i = 0; i < 100; i++)
				errors[i] = null;
		}
		errors[index++] = msg;
	}
	
	void ErrorPrinter(){
		if(index > 2){
			for(int i = 0; i < index; i++)
				System.out.println("** ERROR_" + errors[i]);
		}
	}
}
