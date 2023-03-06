import java.util.Objects;

public class MyLinkedList<T> {
    private int size = 0;
    private Node<T> first = null;
    private Node<T> last = null;
    public static class Node <T> {
        T element;
        Node<T> next;
        Node<T> prev;

        Node (Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(last, element, null);
        Node<T> currentNode = last;
        last = newNode;
        if (first == null) {
            first = newNode;
        } else {
            currentNode.next = newNode;
        }
        size++;
    }
    public T remove(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        Node<T> currentNode = first;
        if (index == 0) {
            first = currentNode.next;
            if (first == null) {
                last = null;
            }
        } else if (index == size) {
            last = currentNode.prev;
            last.next = null;
        } else {
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
        }
        size--;
        return currentNode.element;
    }
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
    public int size() {
        return size;
    }
    public Node<T> get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        Node<T> currentNode = first;
       for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }
       return currentNode;
    }
}