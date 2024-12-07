import java.sql.*;
import java.util.*;
public class PreparedStatementinterface {
    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String user="root";
    private static final String password="Ashok@4849";
    public static void main(String[] args) {
        Scanner sc=new Scanner((System.in));
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection=DriverManager.getConnection(url,user,password);
            System.out.println("PreparedStatement to insert values");
            String query="INSERT INTO students(name,age,marks) VALUES(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,"raj");
            preparedStatement.setInt(2,21);
            preparedStatement.setDouble(3,96.5);
            int rowseffected=preparedStatement.executeUpdate();
            if(rowseffected >0){
                System.out.println("Successfully inserted data into students table");
            }else{
                System.out.println("Data is not inserted .there happen some error:");
            }
            System.out.println("PreparedStatement to retrive data");
            String query2="SELECT * FROM students";
            PreparedStatement preparedStatement1=connection.prepareStatement(query2);
            ResultSet resultSet=preparedStatement1.executeQuery();
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
            System.out.println("PreparedStatement to delete records");
            String query3="DELETE FROM students WHERE id=?";
            PreparedStatement preparedStatement2=connection.prepareStatement(query3);
            preparedStatement2.setInt(1,11);
            int rowseffected2=preparedStatement2.executeUpdate();
            if(rowseffected2 > 0){
                System.out.println("ID 11 records are deleted successfully :-");
            }else{
                System.out.println("ID 11 records are not deleted successfully");
            }
            connection.close();
            resultSet.close();
            preparedStatement.close();
            preparedStatement1.close();
            preparedStatement2.close();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}


