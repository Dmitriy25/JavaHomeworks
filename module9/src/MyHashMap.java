import java.util.Objects;

public class MyHashMap<K, V> {
    private int size = 0;
    private Node<K, V> first = null;
    private Node<K, V> last = null;

    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value, null);
        Node<K, V> currentNode = last;
        last = newNode;
        if (first == null) {
            first = newNode;
        } else {
            currentNode.next = newNode;
        }
        size++;
    }
            public void remove(int index) throws IndexOutOfBoundsException {
                Objects.checkIndex(index, size);
                Node<K, V> currentElement = first;
                Node<K, V> afterRemovingElement = null;
                Node<K, V> beforeRemovingElement = null;
                    if (size == 1) {
                        first = null;
                        last = null;
                        size = 0;
                    } else if (size == 2) {
                        if (index == 1) {
                            first = null;
                        } else {
                            last = null;
                        }
                        size--;
                    } else {
                        for (int i = 0; i <= size; i++) {
                            if (i == index + 1) {
                                afterRemovingElement = currentElement;
                                break;
                            } else if (i == index - 1) {
                                beforeRemovingElement = currentElement;
                            }
                            currentElement = currentElement.next;
                    }
                        beforeRemovingElement.next = afterRemovingElement;
                }
                    size--;
            }
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
    public int size() {
        return size;
    }
    public V get(K key) {
        Node<K, V> currentElement = first;
        for (int i = 0; i < size; i++) {
            if (currentElement.key == key) {
                break;
            } else {
                currentElement = currentElement.next;
            }
        }
        return currentElement.value;
    }
}
