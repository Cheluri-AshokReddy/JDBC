import java.sql.*;
public class CallableStatementForFunctionCalling {
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
            CallableStatement callableStatement= connection.prepareCall("{? = CALL GetGrade(?)}");
            callableStatement.registerOutParameter(1,Types.VARCHAR);
            callableStatement.setInt(2,96);
            callableStatement.execute();
            String grade=callableStatement.getNString(1);
            System.out.println("GRADE IS:"+grade);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
