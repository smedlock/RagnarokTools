import java.util.*;
import java.io.*;

public class GSBItemLogs {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("GSBItemLog.txt"));
      averageItems(input);
   }
   
   public static void averageItems(Scanner input) throws FileNotFoundException {
      TreeMap<String, Double> itemList = new TreeMap<String, Double>();
      String line = "";
      String name = "";
      String amount = "";
      int lines = 0;
      while (input.hasNextLine()) {
         line = input.nextLine().substring(45);
         name = line.substring(line.indexOf(':') + 2, line.indexOf('(') - 1);
         amount = line.substring(line.indexOf(')') + 4, line.indexOf('-', line.indexOf(')')) - 1);
         if (!itemList.containsKey(name)) {
            itemList.put(name, Double.parseDouble(amount));
         } else {
            itemList.put(name, itemList.get(name) + Double.parseDouble(amount));
         }
         lines++;
      }
      System.out.println(itemList);
      System.out.println("Number of GSB = " + lines);
      for (String item : itemList.keySet()) {
         itemList.put(item, itemList.get(item) / lines);
      }
      System.out.println(itemList);
      PrintStream output = new PrintStream(new File("averageItemsPerGSB.txt"));
      output.println("Averages obtained with " + lines + " boxes.");
      output.println();
      for (String item : itemList.keySet()) {
         output.println(item + ": " + itemList.get(item));
      }
   }
}