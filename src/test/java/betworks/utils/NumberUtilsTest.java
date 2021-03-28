package betworks.utils;

import betworks.RadixSortWithCountingSort;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class NumberUtilsTest {


    @Before
    public void setup() {
        }

    @After
    public void tearDown() {

    }

    @Test
    public void getPrefixSums_successful() {
        Assert.assertArrayEquals(new int[]{0, 2, 3, 5, 6, 8, 10, 13, 15, 16},
                NumberUtils.getPrefixSums(new int[]{0, 2, 1, 2, 1, 2, 2, 3, 2, 1}));
    }

    @Test(expected = InvalidParameterException.class)
    public void getPrefixSums_ifArrayEmpty_throwException() {
        Assert.assertArrayEquals(new int[]{}, NumberUtils.getPrefixSums(new int[]{}));
    }

    @Test(expected = InvalidParameterException.class)
    public void getPrefixSums_ifArraySizeIsNotEqualTo10_throwException() {
        NumberUtils.getPrefixSums(new int[]{1, 2});
    }

}
