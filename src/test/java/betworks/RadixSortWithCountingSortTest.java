package betworks;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

class RadixSortWithCountingSortTest {

    private RadixSortWithCountingSort radixSortWithCountingSort;

    @BeforeEach
    public void setup() {
         radixSortWithCountingSort = new RadixSortWithCountingSort(new int[]{});
    }

    @Test
    public void getPrefixSums_successful() {
        Assert.assertArrayEquals(new int[]{0,2,3,5,6,8,10,13,15,16},radixSortWithCountingSort.getPrefixSums(new int[]{0,2,1,2,1,2,2,3,2,1}));
    }
}