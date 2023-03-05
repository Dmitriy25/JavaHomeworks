import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> {
    private Node<K, V>[] hashMap;
    private int size;
    private final int DEFAULT_CAPACITY = 16;

    public MyHashMap() {
        hashMap = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private static class Node<K, V> {
        private final int hash;
        private final K key;
        private V value;
        Node<K, V> next;

        public Node(int hash,K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public int size() {
        return size;
    }

    public void put(K key, V value){
        if (size == hashMap.length) {
            resize();
        }
        putValue(key, value);
        size++;
    }

    private void putValue(K key, V value) {
        Node<K, V> node = new Node<>(hashCode(key), key, value, null);
        int index = Math.abs(node.hash % hashMap.length);
        Node<K, V> tmpNode = hashMap[index];
        if (tmpNode == null) {
            hashMap[index] = node;
        } else {
            while (tmpNode.next != null) {
                if (tmpNode.key.equals(key)) {
                    tmpNode.value = value;
                    return;
                }
                tmpNode = tmpNode.next;
            }
            if (tmpNode.key.equals(key)) {
                tmpNode.value = value;
            } else {
                tmpNode.next = node;
            }
        }
    }

    private void resize() {
        Node<K, V>[] newArrayNode = Arrays.copyOf(hashMap, hashMap.length);
        hashMap = new Node[hashMap.length * 2];
        for (Node<K, V> kvNode : newArrayNode) {
            if (kvNode != null) {
                putValue(kvNode.key, kvNode.value);
            }
        }
    }

    public V get(K key) {
        int index = Math.abs(hashCode(key) % hashMap.length);
        Node<K, V> tmpNode = hashMap[index];
        V returnValue = tmpNode.value;
        while (tmpNode != null) {
            if ((key == null) || (key != null && key.equals(tmpNode.key))) {
                returnValue = tmpNode.value;
            }
            tmpNode = tmpNode.next;
        }
        return returnValue;
    }

    public V remove(K key) {
        Node<K, V> tmpNode = new Node<>(hashCode(key), key, null, null);
        Node<K, V> previousNode, nextNode;
        int index = Math.abs(tmpNode.hash % hashMap.length);
        V result;
        if (hashMap[index] != null) {
            do {
                previousNode = hashMap[index];
                nextNode = hashMap[index].next;
                if ((key == null && tmpNode.key == null) ||
                        (previousNode.key != null && tmpNode.key != null
                                && previousNode.key.equals(tmpNode.key))) {
                    result = previousNode.value;
                    hashMap[index] = nextNode;
                    size--;
                    return result;
                }
                if (nextNode != null && previousNode.key != null && tmpNode.key != null) {
                    if (nextNode.key.equals(tmpNode.key)) {
                        result = nextNode.value;
                        previousNode.next = nextNode.next;
                        hashMap[index] = previousNode;
                        size--;
                        return result;
                    }
                }
            } while (nextNode != null);
        }
        throw new RuntimeException("Value not found");
    }

    public void clear() {
        hashMap = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public int hashCode(K key) {
        return Objects.hash(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHashMap<?, ?> myHashMap = (MyHashMap<?, ?>) o;
        return size == myHashMap.size && Arrays.equals(hashMap, myHashMap.hashMap);
    }
}