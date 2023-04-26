package application;

import java.io.IOException;
import java.sql.SQLException;

import edu.northeastern.models.Customer;
import edu.northeastern.service.CustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController extends Controllers{

    @FXML
    private Button button;

    @FXML
    private Button button1;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password11;

    @FXML
    private Label wrongLogIn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Main m = new Main();
    	LoginController loginController = new LoginController();
    	m.changeScene("Login.fxml", loginController);
    }

    @FXML
    void signUp(ActionEvent event) throws Exception {
    	String fn = firstName.getText();
    	String ln = lastName.getText();
    	String un = email.getText();
    	String pw = password.getText();
    	String pw1 = password11.getText();
    	if(pw.equals(pw1)) {
    		CustomerService cs = new CustomerService();
    		cs.createCustomer(ln, fn, un, pw, 0, 0);
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Confirmation");
    		alert.setContentText("You have successfully signed up!");
    		alert.setHeaderText("Success");
    		alert.showAndWait();
    		Customer c = cs.login(un, pw1);
    		Main m = new Main();
    		WelcomeController welcomeController = new WelcomeController(c, null);
    		m.changeScene("WelcomePage.fxml", welcomeController);
    	}
    	else {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Sorry");
    		alert.setContentText("Passwords do not match!");
    		alert.setHeaderText("Sorry!");
    		alert.showAndWait();
    	}
    }

	@Override
	public void changeScreen() {
		
	}

}

