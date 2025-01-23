package Model;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    private final Connection connection = DatabaseConnect.getConnection();
    public boolean Authenticate(String Email , String Password) throws SQLException {
        String Query = "select * from User where Email = ?";
        PreparedStatement login = connection.prepareStatement(Query);
        login.setString(1,Email);
        ResultSet rs = login.executeQuery();
        if(rs.next()){
            String pass = rs.getString("Password");
            return BCrypt.checkpw(Password,pass);
        }
        return false;
    }
}
