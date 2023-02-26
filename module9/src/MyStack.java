public class MyStack<T> {
        public int size = 0;
        private Node<T> first = null;
        private Node<T> last = null;

        public static class Node<T> {
            T element;
            Node<T> prev;

            Node(Node<T> prev, T element) {
                this.element = element;
                this.prev = prev;
            }
        }

        public void push(T element) {
            Node<T> newNode = new Node<>(null, element);
            Node<T> currentNode = last;
            last = newNode;
            if (first == null) {
                first = newNode;
            } else {
                newNode.prev = currentNode;
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
        public Node<T> peek() {
            return last;
        }
        public Node<T> pop() {
            Node<T> currentElement = last;
            last = last.prev;
            return currentElement;
        }
}
