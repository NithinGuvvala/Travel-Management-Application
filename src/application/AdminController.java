package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.northeastern.dao.LocationDAO;
import edu.northeastern.models.Customer;
import edu.northeastern.models.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AdminController extends Controllers{
	
	Manager manager;

    @FXML
    private Button ViewTrip;

    @FXML
    private Button addTrip;

    @FXML
    private Label adminfName;

    @FXML
    private Label adminlName;

    @FXML
    private Button delupTrip;

    @FXML
    void add_Trip(ActionEvent event) throws IOException {
    	Main m= new Main();
    	AdminAddController adminAddController = new AdminAddController();
    	m.changeScene("AdminAdd.fxml",adminAddController);
    }

    @FXML
    void delup_Trip(ActionEvent event) throws IOException {
    	Main m= new Main();
    	AdminDelUpController adminDelUpController = new AdminDelUpController();
    	m.changeScene("AdminDelUp.fxml",adminDelUpController);
    }

    @FXML
    void view_Trip(ActionEvent event) {
    	
    	//need to create fxml page for this (admin to view all the trips booked)
    
    }
    
    private void adminUpdateTexts() {
    	adminfName.setText(manager.getFirstName());
		adminlName.setText(manager.getLastName());
    }
    

	public void initialize(URL arg0, ResourceBundle arg1) {
		
	
		adminUpdateTexts();
		
	}

	@Override
	public void changeScreen() {
		
	}

    
    


}
