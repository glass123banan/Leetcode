package lru_cache;
import java.util.*;

// class for nodes for doubly linked list of key/value pairs
class Node {
    int key;
    int value;
    Node next;
    Node prev;

    Node(int key, int value){
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

public class LRUCache {
    private int capacity;
    private Map<Integer, Node> cacheMap; // the cache itself
    private Node head;
    private Node tail;

    // constructor for creating a LRU cache with given capacity
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();

        // dummy head and tail nodes to simplify add/remove operations
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);

        // the next and previous nodes points to each other
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    // return value of key if it exists in cache
    public int get(int key) {
        // if map does not contain key, return -1
        if (!cacheMap.containsKey(key)){
            return -1;
        }
        // if contain key, return and put it in the front of the linked list
        Node node = cacheMap.get(key); // fetch value associated with key
        removeNode(node); // remove node from current position
        // cacheMap.remove(key);

        addNode(node); // add to most recently used position
        // cacheMap.put(key, node);

        return node.value;

    }
    // update value of key if key exists
    // if # of keys exceeds capacity: evict least recently used key-value pair
    public void put(int key, int value) {
        // if contains given node already -> remove from map and add to recent with new value
        if(cacheMap.containsKey(key)){
            removeNode(cacheMap.get(key)); // remove old position
        }
        Node node = new Node(key, value);
        addNode(node); // add to most recently used
        cacheMap.put(key, node); // add to cachemap

        // remove lru if exceeds capacity
        if (cacheMap.size() > capacity){
            cacheMap.remove(tail.prev.key); // remove from map
            removeNode(tail.prev); // remove lru
        }
    }

    // add a node to head of linked list
    /* Visualisation:
        Before: head <-> head.next <-> ... <-> tail.prev <-> tail
        After: head <-> node <-> head.next <-> ... <-> tail.prev <-> tail
     */
    private void addNode(Node node){
        Node nextNode = head.next; // fetch node after head
        head.next = node; // set new node after head
        node.prev = head; // set new previous (head) to parameter node
        node.next = nextNode; // set new next node (nextNode) to parameter node
        nextNode.prev = node; // set new previous node (param node) to nextNode
    }

    // remove a node from the doubly linked list of key/value pairs
    /* Visualisation:
        Before: head <-> ... node.prev <-> node <-> node.next ... <-> tail 
        After: head <-> ... node.prev <-> node.next ... <-> tail
     */
    private void removeNode(Node node){
        // extract current previous and next nodes (key/value pair)
        Node prevNode = node.prev;
        Node nextNode = node.next;

        // before: prev <-> X <-> next 
        // after: prev <-> next
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */