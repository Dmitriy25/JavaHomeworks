import java.util.Objects;

public class MyArrayList<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] arr;

    public MyArrayList() {
        size = 0;
        arr = new Object[DEFAULT_CAPACITY];
    }

    public void add(T value) {
        if (size < arr.length) {
            Object[] newArr = new Object[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }
        arr[size++] = value;
    }

    public T remove(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        T tmp = (T) arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        arr[size] = null;
        return tmp;
    }

    public void clear() {
        arr = new Object[DEFAULT_CAPACITY];
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