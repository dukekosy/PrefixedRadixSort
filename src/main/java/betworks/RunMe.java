package betworks;

import betworks.utils.FileHandler;
import betworks.utils.NumberUtils;
import java.io.IOException;

public final class RunMe {

  private final int streamSize;
  private FileHandler fileHandler;

  RunMe(final int streamSize) {
    this.streamSize = streamSize;
  }

  public static void main(String[] args) {
    RunMe runMe = new RunMe(1000000);
    runMe.sort();
  }

  void sort() {

    fileHandler = new FileHandler(System.getProperty("user.dir") + "\\unsorted.txt", NumberUtils.getRandomIntegers(streamSize));

    long startTime = System.nanoTime();
    try {
      fileHandler.writeFileBuffered();
    } catch (IOException e) {
      e.printStackTrace();
    }
    long endTime = System.nanoTime();
    long durationMS = (endTime - startTime) / 1000000;
    System.out.println("write unsorted " + durationMS);

    startTime = System.nanoTime();
    int[] array = fileHandler.readFile();
    endTime = System.nanoTime();
    durationMS = (endTime - startTime) / 1000000;
    System.out.println("read " + durationMS);

    startTime = System.nanoTime();
    array = new RadixSortWithCountingSort(array).radixSort();
    System.out.println("correctly sorted? " + NumberUtils.checkNumbersAscending(array));
    endTime = System.nanoTime();
    durationMS = (endTime - startTime) / 1000000;
    System.out.println("sort " + durationMS);

    FileHandler fileHandler2 = new FileHandler(System.getProperty("user.dir") + "\\sorted.txt", array);
    startTime = System.nanoTime();
    try {
      fileHandler2.writeFileBuffered();
    } catch (IOException e) {
      e.printStackTrace();
    }
    endTime = System.nanoTime();
    durationMS = (endTime - startTime) / 1000000;
    System.out.println("write sorted " + durationMS);
  }

}
