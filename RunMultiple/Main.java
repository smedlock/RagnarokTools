// Scott Medlock
// 10/4/2015
// Personal ragnarok related program
// This class runs multiple openkore clients through a simple gui or hardcoded
// file locations. it uses a list of different account infos in a text file.
// account infos should be per line, in the format of <username> <password> <char>, 
// separated by a single space.

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.event.*;

public class Main {

   public static final boolean useGUI = false;
   public static final String accountList = "C:\\Users\\Scott\\Desktop\\ajlkwejfd\\RAGNAROK STUFF\\botting\\RESTART\\openkore\\account_list.txt";
   public static final String config = "C:\\Users\\Scott\\Desktop\\ajlkwejfd\\RAGNAROK STUFF\\botting\\RESTART\\openkore\\controls\\control(quest_things)\\config.txt";
   public static final String start = "C:\\Users\\Scott\\Desktop\\ajlkwejfd\\RAGNAROK STUFF\\botting\\RESTART\\openkore\\starts\\start(quest_things).exe.lnk";
   
   public static void main(String[] args) {
      
      if (useGUI) {
         MyFrame myFrame = new MyFrame();
      } else {
         ArrayList<Account> accountInfos = RunMultiples.getAccountInfos(accountList);
         ArrayList<String> configLines = RunMultiples.getConfigLines(config);
         
         for (Account account : accountInfos) {
            try {
               Thread.sleep(15000);
            } catch (InterruptedException ex) {
               System.out.println("Thread interrupted");
            }
            
            RunMultiples.modifyConfig(account, config, configLines);
            
            try {
                  RunMultiples.runProgram(start);
            } catch (IOException ex) {}
         }
      }
   }
   
}
