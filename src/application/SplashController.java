package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SplashController extends Controllers{

    @FXML
    private Button button;

    @FXML
    private Label wrongLogIn;

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
    	Main m= new Main();
    	LoginController loginController = new LoginController();
    	m.changeScene("Login.fxml",loginController);
		
    }
    
    @FXML
    void goToAdminLogin(ActionEvent event) throws IOException {
    	Main m= new Main();
    	AdminLoginController adminLoginController = new AdminLoginController();
    	m.changeScene("AdminLogin.fxml", adminLoginController);
    }

	@Override
	public void changeScreen() {
		
	}


}
