// Scott Medlock
// 10/2/2015
// Personal ragnarok related program
// This program will organize storage logs that are created by openkore. The output will be a table with
// accounts on top, and a list of every single item that occurs at least once on the left. The number of
// each item owned is listed under the account, and the total number of each item is also listed.

import java.util.*;
import java.io.*;

public class StorageMaps {
   public static final boolean PRINTACCOUNTS = true;

   public static void main(String[] args) throws FileNotFoundException {
      introduction();
      TreeMap<String, TreeMap<String, Integer>> superTable = new TreeMap<String, TreeMap<String, Integer>>();
      ArrayList<String> accounts = getAccounts();
      superTable = addAccounts(superTable, accounts);
      makeTextDocument2(superTable, accounts);
   }
   
   // introduction and description of this program
   public static void introduction() {
      System.out.println("This program creates a table that keeps track of the");
      System.out.println("amount of every item that is in each account's storage.");
   }
   
   // returns an ArrayList that contains all of the names of files in the current directory that begin with "storage_"
   public static ArrayList<String> getAccounts() {
      File folder = new File("C:\\Users\\Scott\\Desktop\\temp\\storageMaps\\");
      File[] listOfFiles = folder.listFiles();
      ArrayList<String> accounts = new ArrayList<String>();
      for (File file : listOfFiles) {
         if (file.isFile() && file.getName().startsWith("storage_")) {
            accounts.add(file.getName());
         }
      }
      return accounts;
   }
   
   // Returns a TreeMap that maps accounts to item types to amounts of that item type.
   public static TreeMap<String, TreeMap<String, Integer>> addAccounts(TreeMap<String, TreeMap<String, Integer>> superTable, ArrayList<String> accounts) throws FileNotFoundException {
      for (String file : accounts) {
         superTable.put(file.split("_")[1], new TreeMap<String, Integer>());
         Scanner input = new Scanner(new File(file));
         String line = "a";
         while (input.hasNextLine() && !line.isEmpty()) {
            line = input.nextLine();
            String item = "";
            int amount = 0;
            if (!(line.startsWith("-") || line.isEmpty())) {
               int separator = line.lastIndexOf('x');
               item = line.substring(line.indexOf(' ', 1) + 1, separator - 1);
               if (line.endsWith("d") || line.endsWith("n")) {
                  amount = Integer.parseInt(line.substring(separator + 2, line.indexOf(' ', separator + 2)));
               } else {
                  amount = Integer.parseInt(line.substring(separator + 2));
               }
               //Use this if you want to see the items as well as amounts printed out as the program is run
               //System.out.println("item = " + item + ", " + "amount = " + amount);
               
               if (superTable.get(file.split("_")[1]).containsKey(item)) {
                  superTable.get(file.split("_")[1]).put(item, amount + superTable.get(file.split("_")[1]).get(item));
               } else {
                  superTable.get(file.split("_")[1]).put(item, amount);
               }
            }
         }
      }
      return superTable;
   }
   
   // makes a text document in the form of a table with items on the left and accounts on the top.  This
   // version will print the TOTAL column on the right
   public static void makeTextDocument(TreeMap<String, TreeMap<String, Integer>> superTable, ArrayList<String> accounts) throws FileNotFoundException {
      // make the tableName
      String tableName = "";
      Date today = new Date();
      int day = today.getDate();
      int month = today.getMonth() + 1;
      int year = today.getYear() + 1900;
      if (PRINTACCOUNTS) {
         tableName += "superTable" + "(" + month + "-" + day + "-" + year + ").txt";
      } else {
         tableName += "superTable" + "(" + month + "-" + day + "-" + year + "clean).txt";
      }
      PrintStream superTableFile = new PrintStream(new File(tableName));
      
      // begin filling in the table
      printHeader(accounts, superTableFile);
      Set<String> recordedItems = new TreeSet<String>();
      int lineCount = 0;
      int itemCount = 0;
      int total = 0;
      String totalString = "";
      for (String account1 : accounts) {
         for (String item : superTable.get(account1.split("_")[1]).keySet()) {
            if (!recordedItems.contains(item)) {
               for (int i = 0; i < 54 - item.length(); i++) {
                  superTableFile.print(" ");
               }
               superTableFile.print(item);
               for (String account2 : accounts) {
                  if (superTable.get(account2.split("_")[1]).containsKey(item)) {
                     itemCount = superTable.get(account2.split("_")[1]).get(item);
                     total += itemCount;
                     String amount = superTable.get(account2.split("_")[1]).get(item) + "";
                     for (int i = 0; i < 13 - amount.length(); i++) {
                        superTableFile.print(" ");
                     }
                     superTableFile.print(amount);
                  } else {
                     superTableFile.print("             ");
                  }
               }
               totalString = total + "";
               if (PRINTACCOUNTS) {
                  for (int i = 0; i < 13 - totalString.length(); i++) {
                     superTableFile.print(" ");
                  }
                  superTableFile.print(total);
               }
               superTableFile.println();
               total = 0;
               recordedItems.add(item);
               lineCount++;
               if (lineCount == 30) {
                  lineCount = 0;
                  superTableFile.println();
                  printHeader(accounts, superTableFile);
               }
            }
         }
      }
   }
   
   // makes a text document in the form of a table with items on the left and accounts on the top.  This
   // version will print the TOTAL column next to the ITEM column.
   public static void makeTextDocument2(TreeMap<String, TreeMap<String, Integer>> superTable, ArrayList<String> accounts) throws FileNotFoundException {
      // make the tableName
      String tableName = "";
      Date today = new Date();
      int day = today.getDate();
      int month = today.getMonth() + 1;
      int year = today.getYear() + 1900;
      if (PRINTACCOUNTS) {
         tableName += "logs\\superTable" + "(" + month + "-" + day + "-" + year + ").txt";
      } else {
         tableName += "logs\\superTable" + "(" + month + "-" + day + "-" + year + "clean).txt";
      }
      PrintStream superTableFile = new PrintStream(new File(tableName));
      
      // begin filling in the table
      printHeader2(accounts, superTableFile);
      Set<String> recordedItems = new TreeSet<String>();
      String[] values = new String[accounts.size()];
      int iter = 0;
      int lineCount = 0;
      int itemCount = 0;
      int total = 0;
      String totalString = "";
      for (String account1 : accounts) {
         for (String item : superTable.get(account1.split("_")[1]).keySet()) {
            if (!recordedItems.contains(item)) {
               for (int i = 0; i < 54 - item.length(); i++) {
                  superTableFile.print(" ");
               }
               superTableFile.print(item);
               for (String account2 : accounts) {
                  if (superTable.get(account2.split("_")[1]).containsKey(item)) {
                     total += superTable.get(account2.split("_")[1]).get(item);
                     values[iter] = superTable.get(account2.split("_")[1]).get(item) + "";
                  } else {
                     values[iter] = "0";
                  }
                  iter++;
               }
               totalString = total + "";
               for (int i = 0; i < 13 - totalString.length(); i++) {
                  superTableFile.print(" ");
               }
               superTableFile.print(total);
               
               // prints individual account values
               if (PRINTACCOUNTS) {
                  for (String value : values) {
                     for (int i = 0; i < 13 - value.length(); i++) {
                        superTableFile.print(" ");
                     }
                     superTableFile.print(value);
                  }
               }
               
               superTableFile.println();
               iter = 0;
               total = 0;
               recordedItems.add(item);
               lineCount++;
               if (lineCount == 30) {
                  lineCount = 0;
                  superTableFile.println();
                  printHeader2(accounts, superTableFile);
               }
            }
         }
      }
   }

   // Prints the header of the table with the TOTAL column on the end
   public static void printHeader(ArrayList<String> accounts, PrintStream superTableFile) {
      superTableFile.print("                                                  ITEM");
      for (String account : accounts) {
         //superTableFile.print(account.split("_")[1]);
         for (int i = 0; i < 13 - account.split("_")[1].length(); i++) {
            superTableFile.print(" ");
         }
         superTableFile.print(account.split("_")[1]);
      }
      superTableFile.println("        TOTAL");
   }
   
   // Prints the header of the table with the TOTAL column next to the ITEM column
   public static void printHeader2(ArrayList<String> accounts, PrintStream superTableFile) {
      superTableFile.print("                                                  ITEM        TOTAL");
      
      // prints all account names to head columns
      if (PRINTACCOUNTS) {
         for (String account : accounts) {
            for (int i = 0; i < 13 - account.split("_")[1].length(); i++) {
               superTableFile.print(" ");
            }
            superTableFile.print(account.split("_")[1]);
         }
      }
      
      superTableFile.println();
   }

}