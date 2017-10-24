import java.util.*;
import java.io.*;

public class ReadTextFile {
   public static void main(String[] args) throws FileNotFoundException {
      PrintStream output = new PrintStream(new File("output.txt"));
      Scanner input = new Scanner(new File("prontera.dist"));
      String line = "";
      while (input.hasNext()) {
         line = input.next();
         System.out.println(line);
         System.out.println(line.charAt(0));
         //output.println(line);
      }
   }
}