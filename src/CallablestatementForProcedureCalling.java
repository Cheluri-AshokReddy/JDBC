import java.sql.*;
public class CallablestatementForProcedureCalling {
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
            Connection connection=DriverManager.getConnection(url,user,password);
            CallableStatement callablestatement=connection.prepareCall("{call GetStudentDetails(?)}");
            callablestatement.setInt(1,18);
            ResultSet rs=callablestatement.executeQuery();
            while(rs.next()){
                 int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double marks = rs.getDouble("marks");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Marks: " + marks);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
