import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestOffByN {

    @Test
    public void equalCharstest() {
        OffByN obn = new OffByN(5);
        assertTrue(obn.equalChars('a', 'f'));
        assertTrue(obn.equalChars('f', 'a'));
        assertTrue(obn.equalChars('1','6'));
        assertFalse(obn.equalChars('h', 'f'));
    }
}