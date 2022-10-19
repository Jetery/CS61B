/**
 * Created with IntelliJ IDEA
 * Description:
 * User: JeffRay
 * Date: 2022.10.18
 */
public class ArrayDeque<T>{

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


    public void addFirst(T item) {
        if (isFull()) {
            array = arrayExpansion(array);
        }
        if (isEmpty()) {
            firstTime(item);
            size++;
            return;
        }
        head = (head + array.length - 1) % array.length;
        array[head] = (T) item;
        size++;
    }

    public void addLast(T item) {
        if (isFull()) {
            array = arrayExpansion(array);
        }
        if (isEmpty()) {
            firstTime(item);
            size++;
            return;
        }
        tail = (tail + 1) % array.length;
        array[tail] = (T) item;
        size++;
    }

    private void firstTime(Object item) {
        array[0] = (T) item;
        head = 0;
        tail = 0;
    }

    private T[] arrayExpansion(T[] array) {
        T[] expansion = (T[]) new Object[(int) (size * 1.5)];
        array = revise((T[]) array, (T[]) expansion);
        return array;
    }

    private T[] revise(T[] array, T[] newArray) {
        if (head <= tail) {
            System.arraycopy(array, head, newArray, 0, tail - head + 1);
        } else {
            System.arraycopy(array, head, newArray, 0, size - head);
            System.arraycopy(array, 0, newArray, size - head, tail + 1);
        }
        this.head = 0;
        this.tail = this.size - 1;
        return newArray;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private boolean isFull() {
        return this.size == array.length;
    }

    private boolean isLowUtilization() {
        return array.length >= 16 && array.length / 4 > size;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        int used = size;
        if (head < tail) {
            for (int i = head; i <= tail && used > 0; i++) {
                System.out.print(array[i] + " ");
                used--;
            }
        } else {
            for (int i = head; i < array.length && used > 0; i++) {
                System.out.print(array[i] + " ");
                used--;
            }
            for (int i = 0; i <= tail && used > 0; i++) {
                System.out.print(array[i] + " ");
                used--;
            }
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T ret = array[head];
        head = (head + 1) % array.length;
        this.size--;

        if (isLowUtilization()) {
            this.array = arrayShrink(array);
        }
        return ret;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
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
        array = revise((T[]) array, (T[]) shrink);
        return array;
    }

    public T get(int index) {
        if (index > this.size - 1) {
            return null;
        }
        return array[(index + head) % array.length];
    }

}
