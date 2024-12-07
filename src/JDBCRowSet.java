
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class JDBCRowSet {
    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String user="root";
    private static final String password="Ashok@4849";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try{
            RowSetFactory rsf= RowSetProvider.newFactory();
            JdbcRowSet jrs=rsf.createJdbcRowSet();
            jrs.setUrl(url);
            jrs.setUsername(user);
            jrs.setPassword(password);
            String Query="SELECT * FROM  students";
            jrs.setCommand(Query);
            jrs.execute();
            System.out.printf("%-5s %-20s %-5s %-10s%n", "ID", "Name", "Age", "Marks");
            System.out.println("----------------------------------------------------");


            while(jrs.next()) {
                System.out.printf("%-5d %-20s %-5d %-10.2f%n",
                        jrs.getInt("id"),
                        jrs.getString("name"),
                        jrs.getInt("age"),
                        jrs.getDouble("marks"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
