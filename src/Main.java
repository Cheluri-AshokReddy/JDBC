import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String username="root";
    private static final String password="Ashok@4849";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  // mysql driver name this will contain in mysql connector j
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection =DriverManager.getConnection(url,username,password);
            String query="INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            while(true){
                Scanner sc=new Scanner(System.in);
                System.out.print("Enter Name :");
                String name=sc.next();
                System.out.print("Enter Age :");
                int age=sc.nextInt();
                System.out.print("Enter Marks :");
                double marks=sc.nextDouble();
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);
                System.out.println("Enter More Details(Y/N)");
                String choice=sc.next();
                preparedStatement.addBatch();
                if(choice.toUpperCase().equals("Y")){
                    break;
                }
            }
            int arr[]=preparedStatement.executeBatch();
            for(int i=0;i<arr.length;i++){
                if(arr[i]==1)
                    System.out.println(i+"th statement is exicuted successfully");
                else
                    System.out.println(i+"th statement is not exicuted successfully");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
