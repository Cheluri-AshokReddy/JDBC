import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class CachedRowset {
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
            CachedRowSet crs=rsf.createCachedRowSet();
            crs.setUrl(url);
            crs.setUsername(user);
            crs.setPassword(password);
            String Query="SELECT * FROM  students";
            crs.setCommand(Query);
            crs.execute();
            System.out.printf("%-5s %-20s %-5s %-10s%n", "ID", "Name", "Age", "Marks");
            System.out.println("----------------------------------------------------");

            while (crs.next()) {
                System.out.printf("%-5d %-20s %-5d %-10.2f%n",
                        crs.getInt("id"),
                        crs.getString("name"),
                        crs.getInt("age"),
                        crs.getDouble("marks"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
