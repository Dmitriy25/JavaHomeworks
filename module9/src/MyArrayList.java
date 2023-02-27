public class MyArrayList<T> {
    int size = 0;
    int pointer = 0;
    public Object[] arr;

    public MyArrayList() {
        arr = new Object[10];
    }

    public MyArrayList(int initialSize) {
        if (initialSize > 0) {
            arr = new Object[initialSize];
        } else if (initialSize == 0) {
            arr = new Object[]{};
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialSize);
        }
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

    public void remove(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= 0 && index <= size) {
            for (int i = index; i < size; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
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

    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= 0 && index <= size) {
            return (T) arr[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}