package betworks.utils;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.stream.IntStream;

/*
* 1. Using Java 8, generate a million random numbers and output those numbers to a file.
2. Create a program which sorts the output of your random number generator and sends the results to another file.

This question is meant to allow us to gauge your level of skill and understanding of programming best practices. Clearly let us know what
* assumptions you are making and why. As a hint, think about the types of things you would expect a Senior Developer to do in their answer, and go
* into the question with a level of depth and detail you’re comfortable with. Creating code that compiles and directly answers the questions asked
* is the minimum requirement.
* */
public final class NumberUtils {

  public static int[] getRandomIntegers(final int streamSize) {
    Random r = new Random();
    IntStream instream = r.ints(streamSize, 0, Integer.MAX_VALUE);
    return instream.toArray();
  }

  public static int[] getPrefixSums(final int[] count) {
    if (count.length != 10) { throw new InvalidParameterException("message"); }
    for (int i = 0; i < 9; i++) {
      count[i + 1] = count[i] + count[i + 1];
    }
    return count;
  }

  public static boolean checkNumbersAscending(final int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) { return false; }
    }
    return true;
  }

}
