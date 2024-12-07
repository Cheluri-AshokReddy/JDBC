import java.sql.*;
import java.util.*;
public class ResultsetInterface {
    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String username="root";
    private static final String password="Ashok@4849";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection=DriverManager.getConnection(url,username,password);
            String query="SELECT * FROM students";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id"); // column name
                String name=resultSet.getString("name");
                int age=resultSet.getInt("age");
                Double marks=resultSet.getDouble("Marks");
                System.out.println("ID :"+id);
                System.out.println("NAME :"+name);
                System.out.println("AGE "+age);
                System.out.println("MARKS "+marks);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
