import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;
import java.sql.SQLException;

public class WebRowset {
    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String user="root";
    private static final String password="Ashok@4849";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            RowSetFactory rsf= RowSetProvider.newFactory();
            WebRowSet wrs=rsf.createWebRowSet();
            wrs.setUrl(url);
            wrs.setUsername(user);
            wrs.setPassword(password);
            String Query="SELECT * FROM students";
            wrs.setCommand(Query);
            wrs.execute();
            System.out.printf("%-5s %-20s %-5s %-10s%n", "ID", "Name", "Age", "Marks");
            System.out.println("----------------------------------------------------");
            while (wrs.next()) {
                System.out.printf("%-5d %-20s %-5d %-10.2f%n",
                        wrs.getInt("id"),
                        wrs.getString("name"),
                        wrs.getInt("age"),
                        wrs.getDouble("marks"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
