package betworks.utils;

import betworks.RadixSortWithCountingSort;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class NumberUtilsTest {

    public static final int STREAM_SIZE = 1000000;
    public static final int[] TEST_PREFIX = new int[]{0, 2, 1, 2, 1, 2, 2, 3, 2, 1};
    public static final int[] RESULTANT_PREFIX = new int[]{0, 2, 3, 5, 6, 8, 10, 13, 15, 16};

    @Before
    public void setup() {
        }

    @After
    public void tearDown() {

    }

    @Test
    public void getRandomIntegers_checkArraySize_true() {
        assertTrue(NumberUtils.getRandomIntegers(STREAM_SIZE).length  == STREAM_SIZE);
    }

    @Test
    public void getPrefixSums_successful() {
        assertArrayEquals(RESULTANT_PREFIX,
                NumberUtils.getPrefixSums(TEST_PREFIX));
    }

    @Test(expected = InvalidParameterException.class)
    public void getPrefixSums_ifArrayEmpty_throwException() {
        assertArrayEquals(new int[]{}, NumberUtils.getPrefixSums(new int[]{}));
    }

    @Test(expected = InvalidParameterException.class)
    public void getPrefixSums_ifArraySizeIsNotEqualTo10_throwException() {
        NumberUtils.getPrefixSums(new int[]{1, 2});
    }

}
