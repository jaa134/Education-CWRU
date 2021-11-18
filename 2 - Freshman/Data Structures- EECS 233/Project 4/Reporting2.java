import java.io.*;

/**
 * The second of two classes that will help report results of our research.
 * This class will be used to read from an input file, sort it, and fix it.
 * @author Jacob Alspaw
 */
public class Reporting2 {
  
  /**
   * A method that will convert ".txt" file into a String.
   * @param file The name of the file.
   * @return String The file contents represented as a string.
   * @throws IOException Exception to make sure file exsists.
   */
  public static String convertFile(String file) throws IOException {
    //creating a new buffered reader from a file reader. Chosen for speed.
    BufferedReader reader = new BufferedReader(new FileReader (file));
    //creating a new String builder to store the read characters.
    StringBuilder text = new StringBuilder();
    //reading the text file by lines
    String line = reader.readLine();
    //loop until the text file has been completly read
    while(line != null) {
      //adding the line to the new string and starting a new line
      text.append(line + "\n");
      //iterate to the next line
      line = reader.readLine();
    }
    //closing the reader
    reader.close();
    //deleting the extra new line character at the end of the string
    text.deleteCharAt(text.length() - 1);
    //return text file as a string
    return text.toString();
  }
  
  /**
   * A method that will convert a string into an arrray of integers.
   * @param text The string of integers passed to the method.
   * @return array The array of integers formed from the string.
   */
  public static int[] convertString(String text) {
    //creating an array to hold our values
    int[] array =  new int[getSize(text)];
    //a string builder for individual numbers
    StringBuilder number = new StringBuilder();
    //the location it should be put in the array
    int location = 0;
    //loop through entire string
    for(int i = 0; i < text.length(); i++) {
      //if the character is a number
      if(text.charAt(i) >= 48 && text.charAt(i) <= 57) {
        //append it to our number builder
        number.append(text.charAt(i));
      }
      //if the character isnt a number and our number builder isnt null, or its the last iteration of the string
      if(((text.charAt(i) < 48 || text.charAt(i) > 57) && !number.toString().equals("")) || i == text.length() - 1) {
        //add our our number builder to the array
        array[location] = Integer.parseInt(number.toString());
        //delete the contents of our number builder
        number.delete(0, number.length());
        //increment location on array where next number should go
        location++;
      }
    }
    return array;
  }
  
  /**
   * A method that will determine the supposed size of an array
   * for the conversion of String to integer array.
   * @param text The text from the file.
   * @return The size of the resulting array.
   */
  public static int getSize(String text) {
    //number of new line characters
    int count = 0;
    //loop through entire string
    for(int i = 0; i < text.length(); i++) {
      //if the character is a new line
      if(text.charAt(i) == '\n') {
        //increment
        count++;
      }
    }
    return count + 1;
  }
  
  /**
   * A method that will convert an array of ints to a vertical string.
   * @param array The array to be converted.
   * @return String The array represented as a string.
   */
  public static String arrayToString(int[] array) {
    //object to add to
    StringBuilder convert = new StringBuilder();
    //loop through entire array
    for(int i = 0; i < array.length; i++) {
      //add the number
      convert.append(array[i]);
      //add the new line if it isnt the last number to be added
      if(i != array.length - 1) {
        convert.append(System.getProperty("line.separator"));
      }
    }
    return convert.toString();
  }
  
  /**
   * A method that will find the median of three values.
   * @param v1 The first value.
   * @param v2 The second value.
   * @param v3 The third value.
   * @return long The median value.
   */
  public static long findMedian(long v1, long v2, long v3) {
    //combinations of possible medians
    if(v1 <= v2 && v2 <= v3) return v2;
    if(v3 <= v2 && v2 <= v1) return v2;
    if(v1 <= v3 && v3 <= v2) return v3;
    if(v2 <= v3 && v3 <= v1) return v3;
    if(v2 <= v1 && v1 <= v3) return v1;
    else return v1;
  }
  
  /**
   * A method that will write to a file the sorted array.
   * @param text The array represented as a string.
   * @throws IOException Exception to make sure file exsists.
   */
  public static void makeFiles(String text) throws IOException {
    //creating the file to dump the heap sort trial information into
    PrintWriter writerHS = new PrintWriter("jaa134HS.txt", "UTF-8");
    //arrray representation of the file
    int[] arrayHS1 = convertString(text);
    int[] arrayHS2 = convertString(text);
    int[] arrayHS3 = convertString(text);
    //sorting it and recording the elapsed time
    long timeHS1 = Sorting.heapSort(arrayHS1);
    long timeHS2 = Sorting.heapSort(arrayHS2);
    long timeHS3 = Sorting.heapSort(arrayHS3);
    //sorted array as String
    String sortedHS1 = arrayToString(arrayHS1);
    //adding string to the file
    writerHS.println(sortedHS1);
    writerHS.close();
    //creating the file to dump the quick sort trial information into
    PrintWriter writerQS = new PrintWriter("jaa134QS.txt", "UTF-8");
    //arrray representation of the file
    int[] arrayQS1 = convertString(text);
    int[] arrayQS2 = convertString(text);
    int[] arrayQS3 = convertString(text);
    //sorting it and recording the elapsed time
    long timeQS1 = Sorting.quickSort(arrayQS1);
    long timeQS2 = Sorting.quickSort(arrayQS2);
    long timeQS3 = Sorting.quickSort(arrayQS3);
    //sorted array as String
    String sortedQS1 = arrayToString(arrayQS1);
    //adding string to the file
    writerQS.println(sortedQS1);
    writerQS.close();
    //creating the file to dump the merge sort trial information into
    PrintWriter writerMS = new PrintWriter("jaa134MS.txt", "UTF-8");
    //arrray representation of the file
    int[] arrayMS1 = convertString(text);
    int[] arrayMS2 = convertString(text);
    int[] arrayMS3 = convertString(text);
    //sorting it and recording the elapsed time
    long timeMS1 = Sorting.mergeSort(arrayMS1);
    long timeMS2 = Sorting.mergeSort(arrayMS2);
    long timeMS3 = Sorting.mergeSort(arrayMS3);
    //sorted array as String
    String sortedMS1 = arrayToString(arrayMS1);
    //adding string to the file
    writerMS.println(sortedMS1);
    writerMS.close();
    //print coding for test name and median trial duration of three runs
    System.out.println("HSjaa134 = " + findMedian(timeHS1, timeHS2, timeHS3) + "ns; QSjaa134 = " + findMedian(timeQS1, timeQS2, timeQS3) + "ns; MSjaa134 = " + findMedian(timeMS1, timeMS2, timeMS3) + "ns");
  }
  
  /**
   * The main method for this class.
   * @param args The arguments given for this method.
   */
  public static void main(String[] args) {
    //produce error if incorrect amount of arguments
    if(args.length != 1) {
      throw new IllegalArgumentException("Only one argument is allowed!\nThis argument must be a valid file name ending in .txt!");
    }
    else {
      try {
        //make the files from the file name
        makeFiles(convertFile(args[0]));
      }
      //produce error if IO error
      catch (IOException e) {
        System.out.println("In/Out error! Use a valid file name.");
      }
    }
  }
}