import java.sql.*;
import java.util.Scanner;
import org.apache.derby.jdbc.ClientDataSource;

/*
 * 	使用两个单表查询。
 * 	第一个查询扫描DEPT表，查找具有指定主名称的记录并返回其DIdvalue。
 * 	第二个查询然后使用该值搜索学生记录的MajorID值。
 * 
 * */


public class CleverFindMajors {
   public static void main(String[] args) {
      System.out.print("Enter a department name: ");
      Scanner sc = new Scanner(System.in);
      String major = sc.next();
      sc.close();
      System.out.println("Here are the " + major + " majors");
      System.out.println("Name\tGradYear");

      ClientDataSource ds = new ClientDataSource();
      ds.setServerName("localhost");
      ds.setDatabaseName("studentdb");
      try ( Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement()) {
         // Execute two single-table queries
         String qry = "select DId from DEPT where DName = '" + major + "'"; 
         ResultSet rs = stmt.executeQuery(qry);
         rs.next();
         // get the major's ID
         int deptid = rs.getInt("DId");
         rs.close();

         qry = "select SName, GradYear from STUDENT where MajorId = " + deptid;
         rs = stmt.executeQuery(qry);
         while (rs.next()) {
            String sname = rs.getString("sname");
            int gradyear = rs.getInt("gradyear");
            System.out.println(sname + "\t" + gradyear);
         }
         rs.close();
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }
}

