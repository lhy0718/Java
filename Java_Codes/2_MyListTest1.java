
public class Test1 {

	public static void main(String[] args) {
		MyArrayListLimitedCapacity l1 = new MyArrayListLimitedCapacity();
		System.out.println(l1.isEmpty() + "\t" + l1.size());
		l1.add(0, 'J');
		l1.add(1, 123);
		System.out.println(l1.isEmpty() + "\t" + l1.size());
		for(int i = 0; i < l1.size(); i++){
			System.out.println(l1.get(i));
		}
		l1.remove(1);
		l1.add(1, "ava");
		System.out.println(l1.isEmpty() + "\t" + l1.size());
		for(int i = 0; i < l1.size(); i++){
			System.out.println(l1.get(i));
		}
	}
}
