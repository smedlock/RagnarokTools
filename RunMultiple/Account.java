// Scott Medlock
// 10/4/2015
// Personal ragnarok related program
// This class stores info to log into a specific character on an account. It includes username,
// password, and character number. 

import java.util.*;
import java.io.*;

public class Account {
   
   private String username;
   private String password;
   private String charNo;
   
   public Account() {
      this.username = "";
      this.password = "";
      this.charNo = "";
   }
   
   public Account(String username, String password, String charNo) {
      this.username = username;
      this.password = password;
      this.charNo = charNo;
   }
   
   public String getUsername() {
      return this.username;
   }
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   public String getPassword() {
      return this.password;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   
   public String getCharNo() {
      return this.charNo;
   }
   
   public void setCharNo(String charNo) {
      this.charNo = charNo;
   }
}
