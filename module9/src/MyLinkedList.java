public class MyLinkedList<T> {
    public int size = 0;
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
    public void remove(int index) {
        Node<T> currentElement = last;
        Node<T> afterRemovingElement = null;
        Node<T> beforeRemovingElement = null;
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
            for (int i = size; i > 0; i--) {
                if (i == index + 1) {
                    afterRemovingElement = currentElement;
                } else if (i == index - 1) {
                    beforeRemovingElement = currentElement;
                    break;
                }
                currentElement = currentElement.prev;
            }
            afterRemovingElement.prev = beforeRemovingElement;
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
    public Node<T> get(int index) {
        Node<T> currentNode = first;
       for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }
       return currentNode;
    }
}