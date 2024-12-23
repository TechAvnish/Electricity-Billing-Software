package electricity.billing.system;


import java.sql.*;

public class database {
    Connection connection;
    Statement statement;
    database(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Bill_System","root","Avnish@2511");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
