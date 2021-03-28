package betworks;

import java.security.InvalidParameterException;

public final class RadixSortWithCountingSort {

  private int[] unsorted;

  RadixSortWithCountingSort(final int[] unsorted) {
    this.unsorted = unsorted;
  }

  int[] radixSort() {
//?? figure out how to set the iterations here later
    for(int i = 0; i < 12; i++) {
      int[] count = new int[10];
      int power = (int) Math.pow(10, i);
      for(int j = 0; j < unsorted.length; j++) {
        count[(unsorted[j] / (power)) % 10]++;
      }
      count = getPrefixSums(count);
      unsorted = rebuildArraywithPrefixSum(count, unsorted, power);
    }
    return unsorted;
  }

  int[] getPrefixSums(int[] count) {
    if (count.length != 10)
      throw new InvalidParameterException("message");
    for(int i = 0; i < 9; i++) {
       count[i+1] = count[i] + count[i+1];
    }
    return count;
  }

  int[] rebuildArraywithPrefixSum(int[] count, int array[], int power) {
    int[] result = new int[array.length];
    for(int i = array.length - 1; i >= 0; i--) {
      int index = --count[(array[i] / (power)) % 10];
      result[index] = array[i];
    }
    return result;//chk that nothing is zero
  }

}
