// Scott Medlock
// 1/16/2015
//
// This program will analyze ragnarok maps that are png images in an attempt to find tile configurations
// that will be referred to as "accessible corners".  An accessible corner is a walkable tile with adjacent
// north, south, east, and west tiles being walls, with at least one tile diagonal being walkable.
//
// -6710887 (tile)
// -13421773 (walkable tile)
// -14277082 (also a walkable tile)

import java.io.*;
import java.util.*;
import java.awt.*;
//import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
//import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class longestStraightPath {
   public static void main(String[] args) {
      File folder = new File("/Users/scottmedlock/Documents/MYSTUFF/JAVA/onmyown/ragnarokMapsAnalyzing/accessibleCornerEvaluation/maps");
      File[] listOfFiles = folder.listFiles();
            
      BufferedImage img = null;
      try {
         img = ImageIO.read(new File("/Users/scottmedlock/Documents/MYSTUFF/JAVA/onmyown/ragnarokMapsAnalyzing/accessibleCornerEvaluation/maps/moc_pryd04.png"));
      } catch (IOException e) {
      
      }
      System.out.println(img.getRGB(40, 10));
      
      System.out.println("map | X end coord | X length | Y end coord | Y length");
      //ArrayList<File> fileList = makeFileList();
      for (int i = 0; i < listOfFiles.length; i++) {
         findLongestStraightPath(listOfFiles[i]);
      }
   }
   /*
   public static ArrayList<File> makeFileList() {
      
      return fileList;
   }
   */
   public static void findLongestStraightPath(File image) {
      BufferedImage img = null;
      try {
         img = ImageIO.read(image);
      } catch (IOException e) {
      
      }
      
      int clr;
      String name;
      int lineLength = 1;
      Point endCoordX = new Point();
      int longestLineX = 0;
      Point endCoordY = new Point();
      int longestLineY = 0;
      
      
      // loop for lines in Y direction
      for (int i = 4; i < img.getWidth() - 4; i += 2) {
         for (int j = 4; j < img.getHeight() - 4; j += 2) {
            clr = img.getRGB(i, j);
            if ((clr == -14277082) || (clr == -13421773)) {
               if (!isWall(img.getRGB(i, j + 2))) {
                  lineLength++;
               } else if (lineLength > longestLineY) {
                  longestLineY = lineLength;
                  endCoordY.setLocation(i / 2, (img.getHeight() - j) / 2);
                  lineLength = 1;
               } else {
                  lineLength = 1;
               }
            }
         }
      }
      
      lineLength = 1;
      // loop for lines in X direction
      for (int i = 4; i < img.getHeight() - 4; i += 2) {
         for (int j = 4; j < img.getWidth() - 4; j += 2) {
            clr = img.getRGB(j, i);
            if ((clr == -14277082) || (clr == -13421773)) {
               if (!isWall(img.getRGB(j + 2, i))) {
                  lineLength++;
               } else if (lineLength > longestLineX) {
                  longestLineX = lineLength;
                  endCoordX.setLocation(j / 2, (img.getHeight() - i) / 2);
                  lineLength = 1;
               } else {
                  lineLength = 1;
               }
            }
         }
      }
      
      name = image.toString();
      System.out.println(name.substring(name.lastIndexOf('/') + 1) + " " + endCoordX + " " + longestLineX + " " + endCoordY + " " + longestLineY);
      
      
      
      /*
      for (int i = 4; i < img.getWidth() - 4; i += 2) {
         for (int j = 4; j < img.getHeight() - 4; j += 2) {
            clr = img.getRGB(i, j);
            
            if ((clr == -14277082) || (clr == -13421773)) {
               if (((isWall(img.getRGB(i + 2, j))) && (isWall(img.getRGB(i - 2, j))) &&
                   (isWall(img.getRGB(i, j + 2))) && (isWall(img.getRGB(i, j - 2)))) &&
                   ((clr == img.getRGB(i + 2, j + 2)) || (clr == img.getRGB(i + 2, j - 2)) ||
                   (clr == img.getRGB(i - 2, j + 2)) || (clr == img.getRGB(i - 2, j - 2)))) {
                   //i /= 2;
                   //j /= 2;
                   x = i / 2;
                   y = (img.getHeight() - j) / 2;
                   System.out.println(image);
                   System.out.println("RGB value: " + clr);
                   System.out.println("Pixel coordinates: " + i + ", " + j);
                   
                   System.out.println("Ragnarok map coordinates: " + x + " " + y);
                   
                   name = image.toString();
                   System.out.println(name.substring(name.lastIndexOf('/') + 1) + " " + x + " " + y);
               }
            }
         }
      }*/
   }
   
   public static boolean isWall(int RGBValue) {
      return ((RGBValue == -6710887) || (RGBValue == -10066330));
   }
}