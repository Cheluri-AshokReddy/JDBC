import java.sql.*;
import java.util.Scanner;

public class BatchUpdatesORBatchProcessing {
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
            Scanner sc=new Scanner(System.in);
            Connection connection=DriverManager.getConnection(url,user,password);
            String query="INSERT INTO students(name,age,marks) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            while(true){
                System.out.println("Enter details that you need to insert into table--");
                System.out.print("Enter Name:-");
                String name=sc.next();
                sc.nextLine();
                System.out.print("Enter Age:-");
                int age=sc.nextInt();
                System.out.print("Enter Marks:-");
                double marks=sc.nextDouble();
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);
                preparedStatement.addBatch();
                System.out.println("Enter more details(Y/N)");
                String more=sc.next();
                if(more.toUpperCase().equals("N")){
                    break;
                }
            }
            int arr[]=preparedStatement.executeBatch();
            for(int i=0;i<arr.length;i++){
                if(arr[i]==1)
                    System.out.println(i+" th statement is exicuted successfully");
                else
                    System.out.println(i+" th statement is not exicuted successfully");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
