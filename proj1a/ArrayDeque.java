/**
 * Created with IntelliJ IDEA
 * Description:
 * User: JeffRay
 * Date: 2022.10.18
 */
public class ArrayDeque<T> implements Deque {
    // treat my array as circular
    private T[] array;
    private int head;
    private int tail;
    // the size that array is used, not array's size (length)
    private int size;

    public ArrayDeque() {
        this.array = (T[]) new Object[8];
    }

    public ArrayDeque(ArrayDeque other) {
        this.array = (T[]) new Object[other.array.length];
        revise((T[])other.array, array);
    }

    @Override
    public void addFirst(Object item) {
        if (isFull()) {
            array = arrayExpansion(array);
        }
        head = (head + array.length - 1) % array.length;
        array[head] = (T) item;
        size++;
    }

    @Override
    public void addLast(Object item) {
        if (isFull()) {
            array = arrayExpansion(array);
        }
        tail = (tail + 1) % array.length;
        array[tail] = (T) item;
        size++;
    }

    private T[] arrayExpansion(T[] array) {
        T[] expansion = (T[]) new Object[(int) (size * 1.5)];
        revise((T[]) array, (T[]) expansion);
        return expansion;
    }

    private void revise(T[] array, T[] newArray) {
        if (head <= tail) {
            System.arraycopy(array, head, newArray, 0, tail - head + 1);
        } else {
            System.arraycopy(array, head, newArray, 0, size - head + 1);
            System.arraycopy(array, 0, newArray, size - head + 2, tail);
        }
        this.head = 0;
        this.tail = this.size;
        this.size = newArray.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private boolean isFull() {
        return this.size == array.length;
    }

    private boolean isLowUtilization() {
        return array.length >= 16 && array.length / 4 > size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        if (head < tail) {
            for (int i = head; i <= tail; i++) {
                System.out.print(array[i] + " ");
            }
        } else {
            for (int i = head; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            for (int i = 0; i <= tail; i++) {
                System.out.print(array[i] + " ");
            }
        }
    }

    @Override
    public Object removeFirst() {
        if (isEmpty()) return null;
        T ret = array[head];
        head = (head + 1) % array.length;
        this.size--;
        if (isLowUtilization()) {
            this.array = arrayShrink(array);
        }
        return ret;
    }

    @Override
    public Object removeLast() {
        if (isEmpty()) return null;
        T ret = array[tail];
        this.size--;
        tail = (tail - 1 + array.length) % array.length;
        if (isLowUtilization()) {
            this.array = arrayShrink(array);
        }
        return ret;
    }

    private T[] arrayShrink(T[] array) {
        T[] shrink = (T[]) new Object[array.length / 4];
        revise((T[]) array, (T[]) shrink);
        return shrink;
    }

    @Override
    public Object get(int index) {
        return array[index + head];
    }

}
