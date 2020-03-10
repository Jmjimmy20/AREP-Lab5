package edu.escuelaing.arep.app.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DataBase
 */
public class DataBase {

    public static void main(String[] args){
        
        System.out.println(getData());
    }

  public static String getData() {
    String res = "";
    try {
      Class.forName("org.postgresql.Driver");
      String host = "ec2-35-172-85-250.compute-1.amazonaws.com";
      String db = "d9dfpgdk2p7j1c";
      String port = "5432";
      String user = "hdequjdjitaosq";
      String passwd = "7d9f608c7e8f8ac5fbc75f99dd73ce98ca44cf648081a5b17e5f5ea95993c345";
      Connection con = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db, user, passwd);
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from Reto2");
      int i = 0;
      while (rs.next()){
        res += "Line" + i + ": " + rs.getInt(1) + "  " + rs.getString(2) + "</br>";
        i++;
      }
      con.close();
    } catch (Exception e) {
      res = "Can't Connect to database";
      System.out.println(e);
    }
    return res;
  }
}