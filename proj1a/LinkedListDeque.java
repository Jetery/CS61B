/**
 * Created with IntelliJ IDEA
 * Description:
 * User: JeffRay
 * Date: 2022.10.16
 */


public class LinkedListDeque<T> implements Deque {

    public static class Node<T> {
        public T val;
        public Node<T> next;
        public Node<T> pre;

        public Node(T val) {
            this.val = val;
        }

    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public LinkedListDeque() {
        Node<T> sentinel = new Node<>(null);
        this.head = sentinel;
        this.tail = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        Node<T> sentinel = new Node<>(null);
        this.head = sentinel;
        this.tail = sentinel;
        Node otherHead = other.head.next;
        while (otherHead != null) {
            this.addLast(otherHead.val);
            otherHead = otherHead.next;
        }
    }

    @Override
    public T get(int index) {
        if (index > this.size - 1) return null;
        Node<T> cur = head.next;
        while (index-- > 0) {
            cur = cur.next;
        }
        return cur.val;
    }

    public T getRecursive(int index) {
        Node<T> help = this.head.next;
        return getRecursiveHelper(index, help);
    }

    public T getRecursiveHelper(int index, Node<T> node) {
        if (index == 0) return node.val;
        return getRecursiveHelper(index - 1, node.next);
    }

    @Override
    public void addFirst(Object val) {
        // 当前头节点
        Node<T> cur = head.next;
        // 添加的节点
        Node<T> node = new Node<T>((T)val);
        // 修改指针
        node.pre = this.head;
        node.next = cur;
        head.next = node;
        // head 后已有节点要修改后节点的前指针
        if (node.next != null) {
            node.next.pre = node;
        }
        // 第一次插入要修改尾指针
        if (this.isEmpty()) {
            this.tail = node;
        }
        this.size++;
    }

    @Override
    public void addLast(Object val) {
        if (this.isEmpty()) {
            addFirst(val);
            return;
        }
        Node<T> node = new Node<T>((T)val);
        this.tail.next = node;
        node.pre = this.tail;
        tail = node;
        this.size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        T ret = this.head.next.val;
        head.next.pre = null;
        head.next = head.next.next;
        this.size--;
        if (this.size > 0) {
            head.next.pre = head;
        }
        return ret;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        T ret = tail.val;
        this.tail = tail.pre;
        this.tail.next.pre = null;
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        Node<T> cur = head.next;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}









