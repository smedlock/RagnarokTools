import java.util.*;
import java.io.*;
import java.awt.*;

public class AmberniteMovement {
   public static final int MORTAR = 2; // space between cells
   public static final int CELL_SIZE = 50; // size of each cell
   
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("amberniteMovement.txt"));
      ArrayList<Point> points = getPoints(input);
      printStatistics(points);
      DrawingPanel p = new DrawingPanel(CELL_SIZE * 15 + MORTAR * 14, CELL_SIZE * 15 + MORTAR * 14);
      p.setBackground(Color.GRAY);
      Graphics g = p.getGraphics();
      g.setColor(new Color(0, 0, 0));
      for (int i = 0; i < 15; i++) {
         for (int j = 0; j < 15; j++) {
            g.fillRect(i * (CELL_SIZE + MORTAR), j * (CELL_SIZE + MORTAR), CELL_SIZE, CELL_SIZE);
         }
      }
      drawGrid(points, p, g);
   }
   
   public static ArrayList<Point> getPoints(Scanner input) {
      String inputNext = "";
      while (!inputNext.startsWith("START")) {
         inputNext = input.nextLine();
         System.out.println(inputNext);
      }
      ArrayList<Point> result = new ArrayList<Point>();
      int i = 0;
      while (input.hasNextLine()) {
         result.add(new Point(Integer.parseInt(input.next()), Integer.parseInt(input.next())));
      }
      return result;
   }
   
   public static void printStatistics(ArrayList<Point> points) {
      TreeMap<Integer, Integer> xTally = new TreeMap<Integer, Integer>();
      TreeMap<Integer, Integer> yTally = new TreeMap<Integer, Integer>();
      int trials = points.size();
      System.out.println("Trials = " + trials);
      for (int i = 0; i < points.size(); i++) {
         if (xTally.containsKey((int) points.get(i).getX())) {
            xTally.put((int) points.get(i).getX(), xTally.get((int) points.get(i).getX()) + 1);
         } else {
            xTally.put((int) points.get(i).getX(), 1);
         }
      }
      for (int x : xTally.keySet()) {
         System.out.println("X = " + x + ", " + xTally.get(x) + " times " + Math.round(xTally.get(x) * 1.0 / trials * 10000) / 100.0 + "%");
      }
      for (int i = 0; i < points.size(); i++) {
         if (yTally.containsKey((int) points.get(i).getY())) {
            yTally.put((int) points.get(i).getY(), yTally.get((int) points.get(i).getY()) + 1);
         } else {
            yTally.put((int) points.get(i).getY(), 1);
         }
      }
      for (int x : yTally.keySet()) {
         System.out.println("Y = " + x + ", " + yTally.get(x) + " times " + Math.round(yTally.get(x) * 1.0 / trials * 10000) / 100.0 + "%");
      }
   }
   
   public static void drawGrid(ArrayList<Point> points, DrawingPanel p, Graphics g) {
      TreeMap<String, Double> pointTally = new TreeMap<String, Double>();
      int trials = points.size();
      String[] pointStrings = new String[points.size()];
      for (int i = 0; i < points.size(); i++) {
         pointStrings[i] = "" + points.get(i).getX() + " " + points.get(i).getY();
      }
      
      for (String i : pointStrings) {
         if (pointTally.containsKey(i)) {
            pointTally.put(i, pointTally.get(i) + 1.0);
         } else {
            pointTally.put(i, 1.0);
         }
      }
      
      double max = 0;
      for (String i : pointTally.keySet()) {
         if (pointTally.get(i) > max) {
            max = pointTally.get(i);
         }
      }
      
      for (String i : pointTally.keySet()) {
         Scanner line = new Scanner(i);
         int x = (int) Double.parseDouble(line.next());
         int y = (int) Double.parseDouble(line.next());
         int GBIntensity = 200 - (int) Math.round(pointTally.get(i) / max * 200);
         g.setColor(new Color(255, GBIntensity, GBIntensity));
         g.fillRect((7 + x) * (CELL_SIZE + MORTAR), (7 - y) * (CELL_SIZE + MORTAR), CELL_SIZE, CELL_SIZE);
         g.setColor(new Color(0, 0, 255));
         g.drawString("" + Math.round(pointTally.get(i)), (7 + x) * (CELL_SIZE + MORTAR) + CELL_SIZE / 3, (7 - y) * (CELL_SIZE + MORTAR) + 2 * CELL_SIZE / 3);
      }
   }
}