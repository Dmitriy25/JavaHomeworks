public class MyQueue<T> {
    private int size = 0;
    private Node<T> first = null;
    private Node<T> last = null;

    public static class Node<T> {
        T element;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element, null);
        Node<T> currentNode = last;
        last = newNode;
        if (first == null) {
            first = newNode;
        } else {
            currentNode.next = newNode;
        }
        size++;
    }
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
    public int size() {
        return size;
    }
    public Node<T> peek() {
        return first;
    }
    public Node<T> poll() {
        Node<T> result = first;
        first = first.next;
        return result;
    }
}