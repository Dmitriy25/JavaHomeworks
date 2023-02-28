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
        Node<T> currentElement = last;
        Node<T> afterRemovingElement = null;
        Node<T> beforeRemovingElement = null;
        if (size == 1) {
            first = null;
            last = null;
            size = 0;
            return null;
        } else if (size == 2) {
            if (index == 1) {
                first = null;
                size--;
                return currentElement.element;
            } else {
                last = null;
                size--;
                return currentElement.prev.element;
            }
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
            currentElement = afterRemovingElement.prev;
            beforeRemovingElement.next = afterRemovingElement;
        }
        size--;
        return currentElement.element;
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