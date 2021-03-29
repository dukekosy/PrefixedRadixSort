package betworks;

import betworks.utils.FileHandler;
import betworks.utils.NumberUtils;
import java.io.IOException;

public class RunMe {

  private static FileHandler fileHandler;
  private static int STREAM_SIZE = 1000000;

  RunMe() {

  }

  public static void main(String[] args) {
    fileHandler = new FileHandler(System.getProperty("user.dir") + "\\1.txt", STREAM_SIZE);

    System.out.println();
    long startTime = System.nanoTime();
    /*try {
      fileHandler.writeFile(1000000);
    } catch (IOException e) {
      e.printStackTrace();
    }*/
    /*    try {
      fileHandler.writeFileBuffered(1000000);
    } catch (IOException e) {
      e.printStackTrace();
    }*/
         try {
      fileHandler.concurrentWriteBuffered(4);
    } catch (IOException e) {
      e.printStackTrace();
    }
    /*try {
      fileHandler.concurrentWrite(4);
    } catch (IOException e) {
      e.printStackTrace();
    }*/
    long endTime = System.nanoTime();
    long durationMS = (endTime - startTime)/1000000;
    System.out.println("write "+durationMS);

    startTime = System.nanoTime();
    int[] array = fileHandler.readFile();
    //String str = boss.runTasks();
    endTime = System.nanoTime();
    durationMS = (endTime - startTime)/1000000;
    System.out.println("read " + durationMS);

    startTime = System.nanoTime();
    array = new RadixSortWithCountingSort(array).radixSort();
    System.out.println("correctly sorted? " + NumberUtils.checkNumbersAscending(array));
    endTime = System.nanoTime();
    durationMS = (endTime - startTime)/1000000;
    System.out.println("sort " + durationMS);

  }

}
