import java.util.*;
import java.io.*;

public class SumSoldItems {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("shop_log.txt"));
      String searchString = "sold".toLowerCase();
      sumSoldItems(input, searchString);
   }
   
   public static void sumSoldItems(Scanner input, String searchString) {
      double sum = 0;
      int count = 0;
      String line = "";
      String whatImLookingFor = "";
      while (input.hasNextLine()) {
         line = input.nextLine().toLowerCase();
         if (line.contains(searchString)) {
            count++;
            whatImLookingFor = line.substring(line.lastIndexOf(' ') + 1, line.length() - 1);
            System.out.println(sum);
            sum += Double.parseDouble(whatImLookingFor);
         }
      }
      System.out.println("YOU HAVE SOLD " + count + " LINES OF " + searchString.toUpperCase());
      System.out.println("AND YOU HAVE GAINED...");
      System.out.println(sum + "z");
   }
}