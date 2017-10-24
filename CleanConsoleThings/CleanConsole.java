import java.util.*;
import java.io.*;

public class CleanConsole {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("console.txt"));
      PrintStream cleanConsole = new PrintStream(new File("cleanconsole.txt"));
      String searchString = "changed to angeling";
      
      removeLines(input, cleanConsole, searchString);
   }
   
   // removes undesired lines to create a cleaner console log. Also counts the number of lines and searchStrings.
   public static void removeLines(Scanner input, PrintStream cleanConsole, String searchString) {
      int lines = 0;
      int searchCount = 0;
      while (input.hasNextLine()) {
         String nextLine = input.nextLine().toLowerCase();
         if (nextLine.contains(searchString)) {
            cleanConsole.println(nextLine);
            searchCount++;
         }
         lines++;
      }
      System.out.println("search string line count: " + searchCount);
      System.out.println("total lines: " + lines);
      input.close();
      cleanConsole.close();
   }
}