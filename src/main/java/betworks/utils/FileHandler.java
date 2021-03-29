package betworks.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileHandler {

  private String fileName;
  private int streamSize;

  public FileHandler(String fileName, int streamSize) {
    this.fileName = fileName;
    this.streamSize = streamSize;

  }

  public int[] readFile() {
    int[] array = new int[streamSize];
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
      String line; // don't like this string
      for (int i = 0; i < streamSize; i++) {
        String string = br.readLine();
        if (string != null) {
          try {array[i] = Integer.parseInt(string); } catch (Exception ex) {
            //System.out.println(string);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return array;
  }

  public void writeFile(int streamSize) throws IOException {
    int[] data = NumberUtils.getRandomIntegers(streamSize);
    FileWriter writer = new FileWriter(fileName);
    for (Integer integer : data) {
      writer.write(integer + System.lineSeparator());
    }
    writer.close();

  }

  public void writeFile(int streamSize, FileWriter writer) throws IOException {
    int[] data = NumberUtils.getRandomIntegers(streamSize);
    for (Integer integer : data) {
      writer.write(integer + System.lineSeparator());
    }

  }

  public void writeFileBuffered(int streamSize) throws IOException {
    int[] data = NumberUtils.getRandomIntegers(streamSize);
    try (FileOutputStream out = new FileOutputStream(fileName); OutputStreamWriter writer = new OutputStreamWriter(out)) {
      for (Integer integer : data) {
        writer.write(integer + System.lineSeparator());
      }
    }
  }

  public void writeFileBuffered(int streamSize, OutputStreamWriter writer) throws IOException {
    int[] data = NumberUtils.getRandomIntegers(streamSize);
    for (Integer integer : data) {
      writer.write(integer + System.lineSeparator());
    }
  }

  public void concurrentWriteBuffered(int threadCount) throws IOException {
    Thread[] runners = new Thread[threadCount];
    try (FileOutputStream out = new FileOutputStream(fileName); OutputStreamWriter writer = new OutputStreamWriter(out)) {

      for (int i = 0; i < threadCount; i++) {

        runners[i] = new Thread(() -> {
          //need synchronized block to prevent
          //race condition between isEmpty and remove
          try {
            writeFileBuffered(streamSize / threadCount, writer);
          } catch (IOException e) {
            e.printStackTrace();
          }

        });
        runners[i].start();
      }
      for (int i = 0; i < threadCount; i++) {
        try {
          runners[i].join();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void concurrentWrite(int threadCount) throws IOException {
    Thread[] runners = new Thread[threadCount];
    FileWriter writer = new FileWriter(fileName);
    for (int i = 0; i < threadCount; i++) {

      runners[i] = new Thread(() -> {
        //need synchronized block to prevent
        //race condition between isEmpty and remove
        try {
          writeFile(streamSize / threadCount, writer);
        } catch (IOException e) {
          e.printStackTrace();
        }

      });
      runners[i].start();
    }
    for (int i = 0; i < threadCount; i++) {
      try {
        runners[i].join();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    writer.close();
  }

}
