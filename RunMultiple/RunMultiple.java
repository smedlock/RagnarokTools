// Scott Medlock
// 10/4/2015
// Personal ragnarok related program
// This program will open multiple openkore clients from the same openkore bot, using a list of different account infos.
// account infos should be per line, in the format of <username> <password>, separated by a single space.

import java.util.*;
import java.io.*;

public class RunMultiple {
   public static void main(String[] args) throws IOException {
      ArrayList<String> accountInfos = getAccountInfos();
      modifyAndRunPrograms(accountInfos);
   }
   
   // obtains a list of account infos from "account_list.txt" and returns them as an ArrayList
   public static ArrayList<String> getAccountInfos() throws FileNotFoundException {
      Scanner input = new Scanner(new File("account_list.txt"));
      ArrayList<String> accountInfos = new ArrayList<String>();
      while (input.hasNextLine()) {
         accountInfos.add(input.nextLine());
      }
      for (String accountInfo: accountInfos) {
         System.out.println(accountInfo);
      }
      return accountInfos;
   }
   
   // using the accountInfos list, modifies the config file to run an openkore client using that account info
   public static void modifyAndRunPrograms(ArrayList<String> accountInfos) throws FileNotFoundException, IOException {
      for (String accountInfo : accountInfos) {
         if (!accountInfo.startsWith("#")) {
            File dooops = new File("control\\config.txt");
            File doopa = new File("control\\config1.txt");
            dooops.renameTo(doopa);
            Scanner input = new Scanner(doopa);
            PrintStream output = new PrintStream(dooops);
            String line = "";
            while (input.hasNextLine()) {
               line = input.nextLine();
               if (line.startsWith("username")) {
                  output.println("username " + accountInfo.split(" ")[0]);
               } else if (line.startsWith("password")) {
                  output.println("password " + accountInfo.split(" ")[1]);
               } else if (line.startsWith("char")) {
                  output.println("char " + accountInfo.split(" ")[2]);
               } else {
                  output.println(line);
               }
            }
            runProgram();
            try {
               Thread.sleep(3000);
            } catch (InterruptedException e) {
         
            }
         }
      }
   }
   
   // runs start.exe
   public static void runProgram() throws IOException {
      //String fileName = "C:\\Users\\Scott\\Desktop\\ajlkwejfd\\RAGNAROK STUFF\\botting\\STORAGE MANAGEMENT\\openkore_ready(sellOrDropLoot)\\start.exe";
      String fileName = "start.exe";
      String[] commands = {"cmd", "/c", "start", "\"DummyTitle\"", fileName};
      Process process = Runtime.getRuntime().exec(commands);
   }
}
