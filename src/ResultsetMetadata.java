import java.sql.*;
public class ResultsetMetadata {
    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String user="root";
    private static final String password="Ashok@4849";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection=DriverManager.getConnection(url,user,password);
            String query2="SELECT * FROM students";
            PreparedStatement preparedStatement1=connection.prepareStatement(query2);
            ResultSet resultSet=preparedStatement1.executeQuery();
            ResultSetMetaData rsmd=resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.println("Number of columns: " + columnCount);

            System.out.println("Loop through columns and fetch metadata");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column " + i + " Name: " + rsmd.getColumnName(i));
                System.out.println("Column " + i + " Label: " + rsmd.getColumnLabel(i));
                System.out.println("Column " + i + " Type: " + rsmd.getColumnType(i));
                System.out.println("Column " + i + " Type Name: " + rsmd.getColumnTypeName(i));
                System.out.println("Column " + i + " Is Nullable: " + rsmd.isNullable(i));
                System.out.println("Column " + i + " Is Auto Increment: " + rsmd.isAutoIncrement(i));
                System.out.println("-----------------------------------");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
