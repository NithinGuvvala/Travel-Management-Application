package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.northeastern.models.Customer;
import edu.northeastern.service.CustomerService;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controllers implements Initializable{

	@FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    private String uname;
    private String pw;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	@FXML
    void goToSignIn(ActionEvent event) throws IOException {
		Main m = new Main();
		SignUpController signUpController = new SignUpController();
		m.changeScene("SignUp.fxml", signUpController);
    }
	
	@FXML
    void signIn(ActionEvent event) {
		this.uname = username.getText();
		this.pw = password.getText();
		if(event.getSource()==button) {
			CustomerService customerService = new CustomerService();
			this.uname = username.getText();
			this.pw = password.getText();
			try {
				Customer customer=customerService.login(this.uname, this.pw);
				Main m= new Main();
				WelcomeController welcomeController = new WelcomeController(customer,null);
		    	m.changeScene("WelcomePage.fxml",welcomeController);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
	@Override
	public void changeScreen() {
		
	}

	
}
