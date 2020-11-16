import org.apache.derby.jdbc.ClientDataSource;

import java.sql.*;

/**
 * @author Edward Sciore
 * @date XXX/XX/XX
 * @updateAuthor sanling
 * @update 2020/11/15
 */
public class StudentMajor {
    public static void main(String[] args) {
        String qry = "select SName, DName "
                + "from DEPT, STUDENT "
                + "where MajorId = DId";
        ClientDataSource clientDS = new ClientDataSource();
        clientDS.setServerName("localhost");
        clientDS.setDatabaseName("studentdb");
        try (Connection conn = clientDS.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(qry)) {
            /*
             * 	当处理一条记录时，客户端使用方法 getInt 和 getString 来检索其字段的值。
             * 	每个方法都接受一个字段名作为参数，并返回该字段的值。
             * 	代码检索并打印每个记录的字段 SName 和 DName 的值。
             * */
            System.out.println("Name\tMajor");
            String sname = "";
            String dname = "";
            while (rs.next()) {
                // 学生姓名可能为空

                sname = rs.getString("SName");
               if (rs.wasNull()) {
                  sname = "NULL";
                   System.out.println("--------------");
               }
                dname = rs.getString("DName");

                System.out.println(sname + "\t" + dname);
            }
            //printSchema(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printSchema(ResultSet rs) throws SQLException {
        System.out.println("\nHere is the schema:");

        /*
         * 	程序可以调用查询结果集上的getMetaData方法，
         * 	该方法返回一个ResultSetMetaData类型的对象。
         * 	然后，它可以调用这个对象的方法来确定输出表的模式。
         *
         * */

        ResultSetMetaData md = rs.getMetaData();
        // md 从ResultSetMetaData的很多方法中调用四个方法
        // 调用getColumnCount方法来返回结果集中的字段数
        for (int i = 1; i <= md.getColumnCount(); i++) {
            // 调用方法getColumnName、getColumnType和getColumnDisplaySize
            // 来确定每个列中的字段的名称、类型和大小
            String name = md.getColumnName(i);
            int size = md.getColumnDisplaySize(i);
            // getColumnType方法返回一个对字段类型进行编码的整数。
            int typecode = md.getColumnType(i);
            String type;
            if (typecode == Types.INTEGER) {
                type = "int";
            } else if (typecode == Types.VARCHAR) {
                type = "string";
            } else {
                type = "other";
            }
            System.out.println("  " + name + "\t" + type + "\t" + size);
        }
    }
}
