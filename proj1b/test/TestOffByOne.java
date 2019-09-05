import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestOffByOne {
    static OffByOne offByOne = new OffByOne();

    @Test
    public void equalCharstest() {
        assertTrue(offByOne.equalChars('1','2'));
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('r','q'));
        assertTrue(offByOne.equalChars('&','%'));
        assertFalse(offByOne.equalChars('a','e'));
        assertFalse(offByOne.equalChars('z','a'));
        assertFalse(offByOne.equalChars('a','a'));
        assertFalse(offByOne.equalChars('a','B'));
    }
}