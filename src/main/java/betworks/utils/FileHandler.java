package betworks.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public final class FileHandler {

  private final String fileName;
  private final int[] data;

  public FileHandler(final String fileName, final int[] data) {
    this.fileName = fileName;
    this.data = data;

  }

  public int[] readFile() {
    int[] array = new int[data.length];
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));) {
      String line; // don't like this string
      for (int i = 0; i < data.length; i++) {
        String string = br.readLine();
        if (string != null) {
          try {array[i] = Integer.parseInt(string); } catch (NumberFormatException ex) {
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return array;
  }

  public void writeFileBuffered() throws IOException {
    try (FileOutputStream out = new FileOutputStream(fileName); OutputStreamWriter writer = new OutputStreamWriter(out)) {
      for (Integer integer : data) {
        writer.write(integer + System.lineSeparator());
      }
    }
  }

}
