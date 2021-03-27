package betworks;

public final class RadixSortWithCountingSort {

  private int[] unsorted;
  private int[] count;

  RadixSortWithCountingSort(final int[] unsorted) {
    this.unsorted = unsorted;
  }

  int[] radixSort() {

    for(int i = 0; i < 10; i++) {
      count = new int[10];
      int power = 10 * i;
      for(int j = 0; i < unsorted.length; i++) {
        count[(unsorted[j] / (power)) % 10]++;
      }
      getPrefixSums(count);
      unsorted = rebuildArray(unsorted, power);
    }
    return null;
  }

  int[] getPrefixSums(int[] count) {
    for(int i = 0; i < 10; i++) {
       count[i+1] = count[i] + count[i+1];
    }
    return count;
  }

  int[] rebuildArray(int array[], int power) {
    int[] result = new int[array.length];
    for(int i = array.length - 1; i < 0; i--) {
      int index = count[(array[i] / (power)) % 10]--;
      result[index] = array[i];
    }
    return result;
  }

}
