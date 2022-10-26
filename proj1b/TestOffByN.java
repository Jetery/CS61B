import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: JeffRay
 * Date: 2022.10.26
 */
public class TestOffByN {

    @Test
    public void test() {
        OffByN offBy6 = new OffByN(6);
        assertTrue(offBy6.equalChars('b', 'h'));
        assertTrue(offBy6.equalChars('h', 'b'));
        assertFalse(offBy6.equalChars('a', 'z'));
    }


}
