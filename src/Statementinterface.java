import java.sql.*;
import java.util.*;
public class Statementinterface {
    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String username="root";
    private static final String password="Ashok@4849";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }try{
            Connection connection=DriverManager.getConnection(url,username,password);

            System.out.println("Statement to retrive records :-");
            Statement statement=connection.createStatement();
            String query="Select * from students";
            ResultSet resultSet=statement.executeQuery(query);
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

            System.out.println("Statement to Insert records :-");
            Statement statement1=connection.createStatement();
            String query1=String.format("INSERT INTO students(name, age, marks) VALUES('%s', %d, %f)", "Ashok Reddy", 22, 92.5);
            int rowsaffected=statement1.executeUpdate(query1);
            if(rowsaffected>0){
                System.out.println("Data Inserted successfully..");
            }else{
                System.out.println("Data Not Inserted..");
            }

            System.out.println("Statement to Delete records :-");
            Statement statement2=connection.createStatement();
            String query2=String.format("DELETE FROM students WHERE id=%d",9);
            int rowsaffected2=statement2.executeUpdate(query2);

            if(rowsaffected2 > 0){
                System.out.println("Data deleted successfully..");
            }else{
                System.out.println("Data not deleted..");
            }
            connection.close();
            resultSet.close();
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
