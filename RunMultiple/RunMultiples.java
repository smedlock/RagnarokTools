import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.IOException;
import java.util.ArrayList;

public class RunMultiples {
   
   public static ArrayList<Account> getAccountInfos(String accountList) {
      ArrayList<Account> accountInfos = new ArrayList<>();
      Scanner input;
      String line = "";
      String[] accountSplit;
      
      try {
         input = new Scanner(new File(accountList));
         
         while (input.hasNextLine()) {
            if (!line.startsWith("#")) {
               line = input.nextLine();
               accountSplit = line.split(" ");
               accountInfos.add(new Account(accountSplit[0],
                                            accountSplit[1],
                                            accountSplit[2]));
            }
         }
         
         input.close();
         
      } catch (FileNotFoundException e) {
         System.out.println("File not found");
      }
      
      return accountInfos;
   }
   
   public static ArrayList<String> getConfigLines(String config) {
      ArrayList<String> configLines = new ArrayList<>();
      Scanner input;
      
      try {
         input = new Scanner(new File(config));
         
         while (input.hasNextLine()) {
            configLines.add(input.nextLine());
         }
         
         input.close();
         
      } catch (FileNotFoundException e) {
         System.out.println("File not found");
      }
      
      return configLines;
   }
   
   public static void modifyConfig(Account accountInfo, String config, ArrayList<String> configLines) {
      try {
         PrintStream output = new PrintStream(new File(config));
         for (String line : configLines) {
            if (line.startsWith("username")) {
               output.println("username " + accountInfo.getUsername());
            } else if (line.startsWith("password")) {
               output.println("password " + accountInfo.getPassword());
            } else if (line.startsWith("char")) {
               output.println("char " + accountInfo.getCharNo());
            } else {
               output.println(line);
            }
         }
         output.close();
      } catch (FileNotFoundException e) {
         System.out.println("File not found");
      }
   }
   
   // runs start.exe
   public static void runProgram(String fileName) throws IOException {
      String[] commands = {"cmd", "/c", "start", "\"DummyTitle\"", fileName};
      Process process = Runtime.getRuntime().exec(commands);
   }
}