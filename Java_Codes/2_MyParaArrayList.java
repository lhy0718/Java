
public class MyParaArrayList<E> {
	
	private final int maxNum = 2;
	private E[] array;
	private int count;
	
	public MyParaArrayList(){
		array = (E[]) new Object[maxNum];
		count = 0;
	}
	public int size(){
		return count;
	}
	public boolean isEmpty(){
		return (count == 0);
	}
	public E get(int index){
		if(index >= count || index < 0){
			System.out.println("ERROR::CHECK_INDEX_INPUT");
			count--;
			return null;
		}
		return array[index];
	}
	public void add(int index, E o){
		if(index > count || index < 0){
			System.out.println("ERROR::CHECK_INDEX_INPUT");
			return;
		}
		count++;
		if (count > array.length){
			int arraylength = array.length;
			E[] temp = array;
			array = (E[]) new Object[2 * arraylength];
			for(int i = 0; i < temp.length; i++)
				array[i] = temp[i];
		}
		for(int i = array.length - 2; i >= index; i--)
			array[i + 1] = array[i];
		array[index] = o;
	}
	
	public E remove(int index){
		if(index >= count || index < 0){
			System.out.println("ERROR::CHECK_INDEX_INPUT");
			return null;
		}
		if(isEmpty()){
			System.out.println("ERROR::LIST_IS_EMPTY");
			return null;
		}
		E o = array[index];
		count--;
		for(int i = index; i < count - 1; i++)
			array[i] = array[i + 1];
		if (count <= array.length / 2){
			int arraylength = array.length;
			E[] temp = array;
			array = (E[]) new Object[arraylength / 2];
			for(int i = 0; i < array.length; i++)
				array[i] = temp[i];
		}
		return o;
	}
}
