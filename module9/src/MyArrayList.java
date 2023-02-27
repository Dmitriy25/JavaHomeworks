import java.util.Objects;

public class MyArrayList<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private int pointer = 0;
    private Object[] arr;

    public MyArrayList() {
        size = 0;
        arr = new Object[DEFAULT_CAPACITY];
    }

    public void add(T value) {
        if (pointer == arr.length - 1) {
            Object[] newArr = new Object[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, pointer);
            arr = newArr;
        }
        arr[pointer++] = value;
        size++;
    }

    public Object remove(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        for (int i = index; i < size; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
        return arr[index];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) throws IndexOutOfBoundsException {
            Objects.checkIndex(index, size);
            return (T) arr[index];
    }
}