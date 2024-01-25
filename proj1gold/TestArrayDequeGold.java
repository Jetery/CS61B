/**
 * Created with IntelliJ IDEA
 * Description:
 * User: JeffRay
 * Date: 2024.01.24
 */

import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {


    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> stdArray = new ArrayDequeSolution<>();
        String log = "";
        for (int i = 0; i < 200; i++) {
            if (stdArray.isEmpty()) {
                int addNumber = StdRandom.uniform(200);
                int headOrBack = StdRandom.uniform(2);
                if (headOrBack == 0) {
                    log = log + "addFirst(" + addNumber + ")\n";
                    testArray.addFirst(addNumber);
                    stdArray.addFirst(addNumber);
                } else {
                    log = log + "addLast(" + addNumber + ")\n";
                    testArray.addLast(addNumber);
                    stdArray.addLast(addNumber);
                }
            } else {
                int x = StdRandom.uniform(4);
                int addNumber = StdRandom.uniform(200);
                Integer testRemoveNumber = 1;
                Integer stdRemoveNumber = 1;
                switch (x) {
                    case 0:
                        log = log + "addFirst(" + addNumber + ")\n";
                        testArray.addFirst(addNumber);
                        stdArray.addFirst(addNumber);
                        break;
                    case 1:
                        log = log + "addLast(" + addNumber + ")\n";
                        testArray.addLast(addNumber);
                        stdArray.addLast(addNumber);
                        break;
                    case 2:
                        log = log + "removeFirst()\n";
                        testRemoveNumber = testArray.removeFirst();
                        stdRemoveNumber = stdArray.removeFirst();
                        break;
                    case 3:
                        log = log + "removeLast()\n";
                        testRemoveNumber = testArray.removeLast();
                        stdRemoveNumber = stdArray.removeLast();
                        break;
                    default:
                }
                assertEquals(log, stdRemoveNumber, testRemoveNumber);
            }
        }

    }
}
