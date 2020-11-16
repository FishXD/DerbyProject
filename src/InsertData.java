import org.apache.derby.jdbc.ClientDataSource;

import java.sql.*;

/**
 * @author sanling
 * @date 2020/11/15
 */
public class InsertData {
    public static void main(String[] args) {
        ClientDataSource clientDS = new ClientDataSource();
        clientDS.setServerName("localhost");
        clientDS.setDatabaseName("studentdb");

        //Scanner sc = new Scanner(System.in);
        //String cmd = sc.nextLine();
        String cmd = "insert into STUDENT(SId, SName, MajorId, GradYear) values (12, '', , 2022)";
        try (Connection conn = clientDS.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(cmd);
             CheckAllTables checkAllTables = new CheckAllTables("STUDENT");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        //sc.close();

    }

}
