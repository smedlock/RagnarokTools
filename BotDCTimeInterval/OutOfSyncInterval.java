import java.util.*;
import java.io.*;

public class OutOfSyncInterval {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("console.txt"));
      ArrayList<Date> dates = readLines(input);
      
      
      for (int i = 0; i < dates.size() - 1; i++) {
         System.out.println((dates.get(i + 1).getTime() - dates.get(i).getTime()) / 1000);
      }
      
      // Code below added to get the next 500 out of sync dates from the last one that was recorded in the input
      Date myDate = dates.get(dates.size() - 1);
      PrintStream output = new PrintStream(new File("OutOfSyncTimes.txt"));
      for (int i = 0; i < 500; i++) {
         myDate.setTime(myDate.getTime() + 100000000);
         output.println(myDate);
      }
   }
   
   
   public static ArrayList<Date> readLines(Scanner input) {
      ArrayList<Date> dates = new ArrayList<Date>();
      int year;
      int month = 0;
      String monthString;
      String timeString;
      int day;
      int hour;
      int minute;
      int second;
      String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
      
      Scanner lineReader;
      String line = "";
      while (input.hasNextLine()) {
         line = input.nextLine();
         if (line.contains("sync")) {
            lineReader = new Scanner(line);
            monthString = lineReader.next().substring(1);
            for (int i = 0; i < months.length; i++) {
               if (monthString.equals(months[i])) {
                  month = i;
               }
            }
            day = lineReader.nextInt();
            timeString = lineReader.next();
            year = Integer.parseInt(lineReader.next().substring(0, 4));
            dates.add(new Date(year, month, day, Integer.parseInt(timeString.substring(0, 2)),
                                                     Integer.parseInt(timeString.substring(3, 5)),
                                                     Integer.parseInt(timeString.substring(6))));
         }
      }
      return dates;
   }
}