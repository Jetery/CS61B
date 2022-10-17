/**
 * Created with IntelliJ IDEA
 * Description:
 * User: JeffRay
 * Date: 2022.10.16
 */


public class LinkedListDeque<T> {

    public static class Node<T> {
        public T val;
        public Node<T> next;

        public Node(T val) {
            this.val = val;
        }

    }

    private final Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public LinkedListDeque() {
        Node<T> sentinel = new Node<>(null);
        this.head = sentinel;
        this.tail = sentinel;
    }

    public void addFirst(T val) {
        head.next = new Node<>(val);
        if (this.isEmpty()) tail = head.next;
        this.size++;
    }

    public void addLast(T val) {
        tail.next = new Node<>(val);
        tail = tail.next;
        this.size++;
    }

    public void removeFirst() {
        if (isEmpty()) return;
        head.next = head.next.next;
        this.size--;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        Node<T> cur = head.next;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}









