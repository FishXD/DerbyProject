import java.sql.*;
import org.apache.derby.jdbc.ClientDriver;
/**
 *    想要创建数据库首先需要运行Derby Server
 *     参数 ：
 *        主类： org.apache.derby.drda.NetworkServerControl、
 *        程序参数： start -h localhost
 *        工作目录： D:\CodeApps\ideaCodeRepositories\DerbyProject
 *
 * */
public class CreateTestDB {
   public static void main(String[] args) {
      String url = "jdbc:derby://localhost/testdb;create=true";

      Driver d = new ClientDriver();
      /*
       * 	让Java通过其try-with-resources语法自动关闭连接。
       * 	要使用它，您需要在try关键字后面的圆括号中创建连接对象。
       * 	当try块结束时(通常或通过异常)，Java将隐式调用对象的close方法
       * 
       * */
      try (Connection conn = d.connect(url, null)) {
         System.out.println("Database Created");
      }
      catch(SQLException e) {
         e.printStackTrace();
      }
   }
}

