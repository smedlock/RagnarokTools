import java.util.*;
import java.io.*;

public class TrapFormulaStats {
   public static void main(String[] args) throws FileNotFoundException {
      int intelligence = 1;
      int dexterity = 1;
      int lvl = 1;
      int statusPoints = 45;
      int increment = 0;
      double max = 0;
      double current = 0;
      String maxString = "";
      String[] maxes = new String[99];
      while (lvl < 100) {
         statusPoints = 45;
         dexterity = 1;
         intelligence = 1;
         for (int i = 0; i < lvl; i++) {
            increment = 3 + i / 5;
            statusPoints += increment;
         }
         while ((statusPoints > 1 + (dexterity - 1) / 10) && (dexterity < 99)) {
            statusPoints -= 2 + (dexterity - 1) / 10;
            dexterity++;
         }
         while ((statusPoints > 1 + (intelligence - 1) / 10) && (intelligence < 99)) {
            statusPoints -= 2 + (intelligence - 1) / 10;
            intelligence++;
         }
         while (dexterity > 0) {
            current = (dexterity + 38) * (1.0 + (intelligence + 14) / 35.0);
            if ((current > max) && (dexterity < 100) && (intelligence < 100)) {
               max = current;
               maxString = "lvl " + lvl + " dex " + dexterity + " int " + intelligence;
            }
            statusPoints += 2 + (dexterity - 2) / 10;
            dexterity--;
            while ((statusPoints > 1 + (intelligence - 1) / 10) && (intelligence < 99)) {
               statusPoints -= 2 + (intelligence - 1) / 10;
               intelligence++;
            }
         }
         
         maxes[lvl - 1] = maxString;
         
         lvl++;
      }
      for (int i = 0; i < maxes.length; i++) {
         System.out.println(maxes[i]);
      }
   }
}