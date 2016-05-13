
public class MyArrayListLimitedCapacity {
	
	protected final int maxNum = 2;
	protected Object[] array;
	protected int count;
	
	public MyArrayListLimitedCapacity(){
		array = new Object[maxNum];
		count = 0;
	}
	
	public int size(){
		return count;
	}
	public boolean isEmpty(){
		return (count == 0);
	}
	public Object get(int index){
		if(index >= count || index < 0){
			System.out.println("ERROR::CHECK_INDEX_INPUT");
			count--;
			return null;
		}
		return array[index];
	}
	public void add(int index, Object o){
		count++;
		if(index >= count || index < 0){
			System.out.println("ERROR::CHECK_INDEX_INPUT");
			count--;
			return;
		}
		for(int i = index; i < array.length - 1; i++)
			array[i + 1] = array[i];
		array[index] = o;
	}
	public Object remove(int index){
		if(index >= count || index < 0){
			System.out.println("ERROR::CHECK_INDEX_INPUT");
			return null;
		}
		if(isEmpty()){
			System.out.println("ERROR::LIST_IS_EMPTY");
			return null;
		}
		count--;
		Object temp = array[index];
		for(int i = index; i<array.length - 1; i++)
			array[i] = array[i + 1];
		return temp;
	}
}
