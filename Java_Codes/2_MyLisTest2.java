
public class Test2 {

	public static void main(String[] args) {
		MyArrayList l2 = new MyArrayList();
		System.out.println(l2.isEmpty() + "\t" + l2.size());
		l2.add(0, 'D');
		l2.add(1, 6);
		l2.add(1, "E");
		l2.add(0, 'A');
		l2.add(1, 2);
		l2.add(2, "C");
		System.out.println(l2.isEmpty() + "\t" + l2.size());
		for(int i = 0; i < l2.size(); i++){
			System.out.println(l2.get(i));
		}
		for(int i = 0; i < 10; i++){
			l2.remove(1);
		}
		l2.add(1, "ava");
		System.out.println(l2.isEmpty() + "\t" + l2.size());
		for(int i = 0; i < l2.size(); i++){
			System.out.println(l2.get(i));
		}
	}
}
