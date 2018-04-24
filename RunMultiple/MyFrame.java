import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.SpringLayout;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextComponent;
import java.util.ArrayList;
import java.lang.InterruptedException;
import java.io.IOException;

public class MyFrame extends JFrame {
   
   private SpringLayout layout;
   
   private ArrayList<Account> accountInfos;
   private String accountList;
   private JLabel accountListLabel;
   private JButton accountListButton;
   
   private ArrayList<String> configLines;
   private String config;
   private JLabel configLabel;
   private JButton configButton;
   
   private String start;
   private JLabel startLabel;
   private JButton startButton;
   
   private JButton runAllButton;
   
   private JFileChooser chooser;
   
   public MyFrame() {
      super("HIIII");
      
      chooser = new JFileChooser();
      
      layout = new SpringLayout();
      
      setLayout(layout);
      setSize(800, 400);
      setResizable(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      
      Container contentPane = getContentPane();
      
      accountListLabel = new JLabel("Account List: ");
      accountListButton = new JButton("Choose account list");
      contentPane.add(accountListLabel);
      contentPane.add(accountListButton);
      
      accountListButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            selectAccountList();
         }
      });

      
      layout.putConstraint(SpringLayout.WEST, accountListLabel, 10, SpringLayout.WEST, contentPane);
      layout.putConstraint(SpringLayout.NORTH, accountListLabel, 10, SpringLayout.NORTH, contentPane);
      
      layout.putConstraint(SpringLayout.WEST, accountListButton, 10, SpringLayout.WEST, contentPane);
      layout.putConstraint(SpringLayout.NORTH, accountListButton, 20, SpringLayout.NORTH, accountListLabel);
      
      // select config to edit
      
      configLabel = new JLabel("Config: ");
      configButton = new JButton("Choose config");
      contentPane.add(configLabel);
      contentPane.add(configButton);
      
      configButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            selectConfig();
         }
      });
      
      layout.putConstraint(SpringLayout.WEST, configLabel, 10, SpringLayout.WEST, contentPane);
      layout.putConstraint(SpringLayout.NORTH, configLabel, 40, SpringLayout.NORTH, accountListButton);
      
      layout.putConstraint(SpringLayout.WEST, configButton, 10, SpringLayout.WEST, contentPane);
      layout.putConstraint(SpringLayout.NORTH, configButton, 20, SpringLayout.NORTH, configLabel);   
         
      // select openkore's start.exe
      
      startLabel = new JLabel("Start.exe: ");
      startButton = new JButton("Choose start.exe");
      contentPane.add(startLabel);
      contentPane.add(startButton);
      
      startButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            selectStart();
         }
      });
      
      layout.putConstraint(SpringLayout.WEST, startLabel, 10, SpringLayout.WEST, contentPane);
      layout.putConstraint(SpringLayout.NORTH, startLabel, 40, SpringLayout.NORTH, configButton);
      
      layout.putConstraint(SpringLayout.WEST, startButton, 10, SpringLayout.WEST, contentPane);
      layout.putConstraint(SpringLayout.NORTH, startButton, 20, SpringLayout.NORTH, startLabel);
      
      // run all
      
      runAllButton = new JButton("Run all");
      contentPane.add(runAllButton);
      
      runAllButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            for (Account accountInfo : accountInfos) {
               RunMultiples.modifyConfig(accountInfo, config, configLines);
               
               try {
                  RunMultiples.runProgram(start);
               } catch (IOException ex) {}
               try {
                  Thread.sleep(15000);
               } catch (InterruptedException ex) {
                  System.out.println("Thread interrupted");
               }
            }
         }
      });
      
      layout.putConstraint(SpringLayout.WEST, runAllButton, 40, SpringLayout.WEST, contentPane);
      layout.putConstraint(SpringLayout.NORTH, runAllButton, 40, SpringLayout.NORTH, startButton);
      
      // choose, from a list of loaded account infos, which account to run

      
   }
   
   public void selectAccountList() {
      
      int returnVal = chooser.showOpenDialog(accountListButton);
      
      if(returnVal == JFileChooser.APPROVE_OPTION) {
         accountList = chooser.getSelectedFile().toString();
         accountListLabel.setText("Account List: " + accountList);
      }
      
      accountInfos = RunMultiples.getAccountInfos(accountList);
   }
   
   public void selectConfig() {
      
      int returnVal = chooser.showOpenDialog(configButton);
      
      if(returnVal == JFileChooser.APPROVE_OPTION) {
         config = chooser.getSelectedFile().toString();
         configLabel.setText("Config: " + config);
      }
      
      configLines = RunMultiples.getConfigLines(config);

   }
   
   public void selectStart() {
      
      int returnVal = chooser.showOpenDialog(startButton);
      
      if(returnVal == JFileChooser.APPROVE_OPTION) {
         start = chooser.getSelectedFile().toString();
         startLabel.setText("Start: " + start);
      }
   }

}