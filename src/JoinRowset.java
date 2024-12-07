import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class JoinRowset {
    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String user="root";
    private static final String password="Ashok@4849";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        try{
            RowSetFactory rsf= RowSetProvider.newFactory();
            CachedRowSet crs1=rsf.createCachedRowSet();
            crs1.setUrl(url);
            crs1.setUsername(user);
            crs1.setPassword(password);
            String Query="SELECT id,name,marks FROM students";
            crs1.setCommand(Query);
            crs1.execute();
            CachedRowSet crs2=rsf.createCachedRowSet();
            crs2.setUrl(url);
            crs2.setUsername(user);
            crs2.setPassword(password);
            String Query2="SELECT id,name,age,marks FROM students";
            crs2.setCommand(Query2);
            crs2.execute();
            JoinRowSet jrs=rsf.createJoinRowSet();
            jrs.addRowSet(crs1,"id");  // commad column on both tables
            jrs.addRowSet(crs2,"id");
            System.out.printf("%-5s %-20s %-5s %-10s%n", "ID", "Name", "Age", "Marks");
            System.out.println("----------------------------------------------------");
            while (jrs.next()) {
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
