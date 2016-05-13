
public class Test3 {

	public static void main(String[] args) {
		MyParaArrayList<String> l3 = new MyParaArrayList<String>();
		System.out.println(l3.isEmpty() + "\t" + l3.size());
		l3.add(0, "D");
		l3.add(1, "F");
		l3.add(1, "E");
		l3.add(0, "A");
		l3.add(1, "B");
		l3.add(2, "C");
		System.out.println(l3.isEmpty() + "\t" + l3.size());
		for(int i = 0; i < l3.size(); i++){
			System.out.println(l3.get(i));
		}
		for(int i = 0; i < 10; i++){
			l3.remove(1);
		}
		l3.add(1, "ava");
		System.out.println(l3.isEmpty() + "\t" + l3.size());
		for(int i = 0; i < l3.size(); i++){
			System.out.println(l3.get(i));
		}
	}
}
