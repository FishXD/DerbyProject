import java.sql.*;
import java.util.Scanner;
import org.apache.derby.jdbc.ClientDataSource;

/*	执行一个连接STUDENT和MAJOR的SQL查询。
 * 	假设您知道执行一个join可能很耗时。
 * 	经过认真思考，您意识到无需使用连接就可以获得所需的数据
 * 
 * 	见CleverFindMajors
 * 		使用两个单表查询。
 * 		第一个查询扫描DEPT表，查找具有指定主名称的记录并返回其DIdvalue。
 * 		第二个查询然后使用该值搜索学生记录的MajorID值。
 * 
 * 	要运行这个类必须先启动DerbyTest项目中的DerbyServer
 * 		run config->DerbyServer->run
 * 	还需要有StudentDB(有就不用创建，没有需要创建)
 * 		运行CreateStudentDB
 * 			运行CreateStudentDB的基础也是先启动DerbyTest项目中的DerbyServer
 * */

public class FindMajors {
   public static void main(String[] args) {
      System.out.print("Enter a department name: ");
      Scanner sc = new Scanner(System.in);
      String major = sc.next();
      sc.close();
      String qry = "select sname, gradyear "
            + "from student, dept "
            + "where did = majorid "
            + "and dname = '" + major + "'";

      ClientDataSource ds = new ClientDataSource();
      ds.setServerName("localhost");
      ds.setDatabaseName("studentdb");
      try (Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry)) {

         System.out.println("Here are the " + major + " majors");
         System.out.println("Name\tGradYear");
         while (rs.next()) {
            String sname = rs.getString("sname");
            int gradyear = rs.getInt("gradyear");
            System.out.println(sname + "\t" + gradyear);
         }
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }
}
