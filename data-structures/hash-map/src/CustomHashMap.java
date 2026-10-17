import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Node<K, V> {
    K key;
    V val;
    Node next;
    Node prev;

    Node(K key, V val){
        this.key = key;
        this.val = val;
    }
}

//hashmap = {(key1, val1), (key2, val2)}
//hashset = { key1, key2 }

public class CustomHashMap<K, V> {
    private final int INITIAL_SIZE = 4;
    private final int MAX_CAPACITY = 1 << 30;
    private final float LOAD_FACTOR = 0.75f;
    private int countOfNodes = 0;

    private Node[] map;

    CustomHashMap(){
        map = new Node[INITIAL_SIZE];
        for (int i = 0; i < INITIAL_SIZE; i++) {
            map[i] = new Node<>(null, null);
            map[i].next = new Node<>(null, null);
            map[i].next.prev = map[i];
        }
    }

    public V get(K key){
        Node node = findNode(key);
        return node == null ? null : (V) node.val;
    }

    public void put(K key, V val){
        Node node = findNode(key);
        if(node != null){
            node.val = val;
            return;
        }

        int bucket = key.hashCode() % map.length;
        Node head = map[bucket];

        Node newNode = new Node(key, val);
        Node old_first = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = old_first;
        old_first.prev = newNode;

        countOfNodes++;

        if(countOfNodes > LOAD_FACTOR * map.length){
            rehash(map.length * 2);
        }
    }

    public void remove(K key){
        Node nodeToRemove = findNode(key);

        if (nodeToRemove == null) return;

        Node prevNode = nodeToRemove.prev;
        Node nextNode = nodeToRemove.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        countOfNodes--;
    }

    public int getSize(){
        return countOfNodes;
    }

    private void rehash(int newSize) {
        if(newSize > MAX_CAPACITY){
            System.out.println("Hashmap is exceeding max capacity");
            return;
        }

        Node[] newMap = new Node[newSize];
        for (int i = 0; i < newSize; i++) {
            newMap[i] = new Node<>(null, null);
            newMap[i].next = new Node<>(null, null);
            newMap[i].next.prev = newMap[i];
        }

        for (Node<K, V> curr : map) {
            while (curr != null) {
                //ignore head and tail
                if (curr.key == null){
                    curr = curr.next;
                    continue;
                }

                int newBucket = curr.key.hashCode() % newSize;
                Node newHead = newMap[newBucket];
                Node oldFirst = newHead.next;

                // note down curr's next
                Node nextNode = curr.next;

                newHead.next = curr;
                curr.prev = newHead;
                curr.next = oldFirst;
                oldFirst.prev = curr;

                curr = nextNode;
            }
        }

        map = newMap;
    }

    public Node findNode(K key){
        int bucket = key.hashCode() % map.length;
        Node head = map[bucket];

        while(head != null){
            if(head.key != null && head.key.equals(key)){
                return head;
            }
            head = head.next;
        }
        return null;
    }
}