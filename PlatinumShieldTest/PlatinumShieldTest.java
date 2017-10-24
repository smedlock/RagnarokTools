// This program is used to find the success rate of any random experiment
// that has two outcomes (refer to these as success/failure).  The program
// takes in a text file file with the following format, and prints the
// success rate to System.out.
//
// FORMAT OF TEXT FILE ON NEXT LINE
// <free line to describe text file (only one)>
// <trials until success>
// <trials until success>
// <trials until success>
// <trials until success>
// .
// .
// .
//
// EXAMPLE
// this is the number of times magic hits a target until platinum shield procs
// 5
// 10
// 32
// 2
// 1
// 2

import java.util.*;
import java.io.*;

public class platinumshieldtest {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("platinumshieldtest.txt"));
      statistics(input);
   }
   
   public static void statistics(Scanner input) {
      double nextNum = 0;
      double successes = 0;
      double failures = 0;
      input.nextLine();
      while (input.hasNextLine()) {
         try {
            nextNum = input.nextDouble();
            successes += 1;
            failures += nextNum - 1;
         } catch (NoSuchElementException e) {
            
         }
      }
      System.out.println("successes = " + successes);
      System.out.println("failures = " + failures);
      System.out.println("success percentage = " + successes / (successes + failures) * 100 + "%");
   }
}