// Scott Medlock
// 1/16/2015
//
// This program will analyze ragnarok maps that are png images in an attempt to find tile configurations
// that will be referred to as "accessible corners".  An accessible corner is a walkable tile with adjacent
// north, south, east, and west tiles being walls, with at least one tile diagonal being walkable.
//
// A quick illustration is probably a good idea.
//
// X = wall, A = accessible corner, O = one of the 4 tiles that must be walkable.
//
// OXO
// XAX
// OXO
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

public class AccessibleCorner {
   public static void main(String[] args) {
      File folder = new File("/Users/scottmedlock/Documents/MYSTUFF/JAVA/onmyown/ragnarokMapsAnalyzing/accessibleCornerEvaluation/maps");
      File[] listOfFiles = folder.listFiles();
            
      BufferedImage img = null;
      try {
         img = ImageIO.read(new File("/Users/scottmedlock/Documents/MYSTUFF/JAVA/onmyown/ragnarokMapsAnalyzing/accessibleCornerEvaluation/maps/yuno_fild04.png"));
      } catch (IOException e) {
      
      }
      System.out.println(img.getRGB(400, 60));
      
      //ArrayList<File> fileList = makeFileList();
      for (int i = 0; i < listOfFiles.length; i++) {
         findAccessibleCorners(listOfFiles[i]);
      }
   }
   /*
   public static ArrayList<File> makeFileList() {
      
      return fileList;
   }
   */
   public static void findAccessibleCorners(File image) {
      BufferedImage img = null;
      try {
         img = ImageIO.read(image);
      } catch (IOException e) {
      
      }
      
      int clr;
      int x;
      int y;
      String name;
      /*for (int i = 4; i < img.getWidth() - 4; i += 2) {
         for (int j = 4; j < img.getHeight() - 4; j+= 2) {
            clr = img.getRGB(i, j);
            
            if ((clr == -14277082) || (clr == -13421773)) {
               if (((clr != img.getRGB(i + 2, j)) && (clr != img.getRGB(i - 2, j)) &&
                   (clr != img.getRGB(i, j + 2)) && (clr != img.getRGB(i, j - 2))) &&
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
               }
            }
         }
      }*/
      for (int i = 4; i < img.getWidth() - 4; i += 2) {
         for (int j = 4; j < img.getHeight() - 4; j+= 2) {
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
                   /*System.out.println(image);
                   System.out.println("RGB value: " + clr);
                   System.out.println("Pixel coordinates: " + i + ", " + j);
                   
                   System.out.println("Ragnarok map coordinates: " + x + " " + y);
                   */
                   name = image.toString();
                   System.out.println(name.substring(name.lastIndexOf('/') + 1) + " " + x + " " + y);
               }
            }
         }
      }
   }
   
   public static boolean isWall(int RGBValue) {
      return ((RGBValue == -6710887) || (RGBValue == -10066330));
   }
}