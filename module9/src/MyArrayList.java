public class MyArrayList<T> {
    int size = 0;
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
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialSize);
        }
    }

    public void add(T value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = value;
                size++;
                break;
            }
        }
    }
    public void remove(int index) {
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
    }
    public int size() {
        return size;
    }
    public T get(int index) {
        return (T) arr[index];
    }
}