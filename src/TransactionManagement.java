import com.mysql.cj.exceptions.ConnectionIsClosedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionManagement {
    private static final String url="jdbc:mysql://127.0.0.1:3306/banking_system";
    private static final String user="root";
    private static final String password="Ashok@4849";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            String withdraw="UPDATE accounts SET balance=balance-? WHERE account_number=?";
            String deposit="UPDATE accounts SET balance=balance+? WHERE account_number=?";
            Connection connection= DriverManager.getConnection(url,user,password);
            connection.setAutoCommit(false);
            System.out.println("Transaction management started");
            try{
                PreparedStatement withdrawpreparedStatement =connection.prepareStatement(withdraw);
                PreparedStatement depositpreparedStatement=connection.prepareStatement(deposit);
                withdrawpreparedStatement.setInt(1,500);
                withdrawpreparedStatement.setInt(2,100100002);
                depositpreparedStatement.setInt(1,750);
                depositpreparedStatement.setInt(2,100100001);
                int withdrawresult=withdrawpreparedStatement.executeUpdate();
                int depositresult=depositpreparedStatement.executeUpdate();
                if(withdrawresult ==1 && depositresult==1)
                {
                    connection.commit();
                    System.out.println("Transaction Successful");
                }

            }catch (SQLException e){
                System.out.println("Transaction Failed:");
                System.out.println(e.getMessage());
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
