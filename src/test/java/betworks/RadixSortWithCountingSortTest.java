package betworks;

import java.security.InvalidParameterException;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RadixSortWithCountingSortTest {

  private RadixSortWithCountingSort radixSortWithCountingSort;

  @Before
  public void setup() {
    radixSortWithCountingSort = new RadixSortWithCountingSort(
        new int[]{277, 806, 681, 462, 787, 163, 284, 166, 905, 518, 263, 395, 988, 307, 779, 721});
  }

  @After
  public void tearDown() {

  }

  @Test
  public void getPrefixSums_successful() {
    Assert.assertArrayEquals(new int[]{0, 2, 3, 5, 6, 8, 10, 13, 15, 16},
                             radixSortWithCountingSort.getPrefixSums(new int[]{0, 2, 1, 2, 1, 2, 2, 3, 2, 1}));
  }

  @Test(expected = InvalidParameterException.class)
  public void getPrefixSums_ifArrayEmpty_throwException() {
    Assert.assertArrayEquals(new int[]{}, radixSortWithCountingSort.getPrefixSums(new int[]{}));
  }

  @Test(expected = InvalidParameterException.class)
  public void getPrefixSums_ifArraySizeIsNotEqualTo10_throwException() {
    radixSortWithCountingSort.getPrefixSums(new int[]{1, 2});
  }

  @Test
  public void rebuildArraywithPrefixSum_successful() {
    int[] count = radixSortWithCountingSort.getPrefixSums(new int[]{0, 2, 1, 2, 1, 2, 2, 3, 2, 1});
    Assert.assertArrayEquals(new int[]{681, 721, 462, 163, 263, 284, 905, 395, 806, 166, 277, 787, 307, 518, 988, 779},
                             radixSortWithCountingSort.rebuildArraywithPrefixSum(count,
                                                                                 new int[]{277, 806, 681, 462, 787, 163, 284, 166, 905, 518, 263, 395,
                                                                                           988, 307, 779, 721}, 1));
  }

  @Test
  public void radixSort_successful() {
    Assert.assertArrayEquals(new int[]{163, 166, 263, 277, 284, 307, 395, 462, 518, 681, 721, 779, 787, 806, 905, 988},
                             radixSortWithCountingSort.radixSort());
  }

  @Test
  public void radixSort_testfor1M_successful() {
    int[] array = Arrays.stream(SortingAMillion.getRandomIntegers(1000000)).toArray();
    radixSortWithCountingSort = new RadixSortWithCountingSort(array);
    Assert.assertTrue(checkAscending(radixSortWithCountingSort.radixSort()));
  }

  private boolean checkAscending(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) { return false; }
    }
    return true;
  }

}