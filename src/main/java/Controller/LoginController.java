package Controller;
import View.Login;
import Model.LoginModel;

import java.sql.SQLException;

public class LoginController {
    private final Login loginView;
    private final LoginModel loginModel;

    private void Login(){
        String Email = loginView.getEmail();
        String password = loginView.getPassword();
        StringBuilder errors = new StringBuilder("The Following Concideration must be checked: \n");
        boolean f = true;
        if(!InputValidator.isEmailValid(Email)){
            errors.append("- Invalid Email Format \n");
            f = false;
        }
        if(!InputValidator.isPasswordValid(password)){
            errors.append("- Password Should be at least 8 characters, with uppercase, lowercase and a digit \n");
            f = false;
        }
        if(!f){
            loginView.noticeErrors(errors.toString());
        }
        else {
            boolean Auth = false;
            try {
                Auth = loginModel.Authenticate(Email, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (Auth) {
                loginView.noticeSuccess("Authentication Successed.");
            } else loginView.noticeErrors("Authentication Failed.");
        }
    }
    public LoginController(Login login , LoginModel loginModel){
        this.loginView = login;
        this.loginModel = loginModel;
        this.loginView.addLoginListener(_ -> Login());
    }
}
