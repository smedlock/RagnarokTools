// Scott Medlock
//
// This is an alchemist potting simulator for Ragnarok Online. It will spit out the points, and
// the percentage of the maximum attainable points for success rates ranging from 1 to 100 when
// potting 30000 of something.


import java.util.*;
import java.io.*;

public class PottingPointsSimulator {
   public static void main(String[] args) throws FileNotFoundException {
      int trials = 30000 * 27;
      //int successRate = 0;
      Random r = new Random();
      int counter = 0;
      int chance = 0;
      int[] points = new int[100];
      
      for (int successRate = 1; successRate < 101; successRate++) {
         for (int i = 0; i < trials; i++) {
            chance = r.nextInt(100);
            if (chance + 1 > successRate) {
               counter = 0;
            } else {
               counter++;
               if (counter == 3) {
                  points[successRate - 1]++;
               } else if (counter == 5) {
                  points[successRate - 1] += 3;
               } else if (counter == 7) {
                  points[successRate - 1] += 10;
               } else if (counter == 10) {
                  counter = 0;
                  points[successRate - 1] += 50;
               }
            }
         }
      }//192000
      for (int i = 0; i < 100; i++) {
         System.out.print("(" + (i + 1) + ": " + points[i] + ")");
      }
      System.out.println();
      for (int i = 0; i < 100; i++) {
         System.out.print("(" + (i + 1) + ": " + points[i] / 192000.0 + ")");
      }
   }
}