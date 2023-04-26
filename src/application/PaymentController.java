package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.northeastern.models.Customer;
import edu.northeastern.models.Itinerary;
import edu.northeastern.models.TravelTickets;
import edu.northeastern.service.BookingService;
import edu.northeastern.service.CustomerService;
import edu.northeastern.tableModels.UpcomingTrips;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class PaymentController extends Controllers implements Initializable{
	
	Customer customer;
	
   @FXML
    private Button payButton;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txt_depart;

    @FXML
    private TextField txt_from;

    @FXML
    private TextField txt_mode;

    @FXML
    private TextField txt_to;
    
    @FXML
    private Text fromText;
    
    @FXML
    private Text toText;
    
    @FXML
    private Text totalText;
    


	private ArrayList<TravelTickets> routes;

	private Itinerary itinerary;
	
	private double totalCost;
    

    @FXML
    void pay(ActionEvent event) throws IOException, SQLException {
    	LAB3_P1 l3 = new LAB3_P1();
    	long cardNumber = Long.valueOf(txtId.getText());
    	if(l3.isValid(cardNumber)) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Confirmation");
    		alert.setContentText("Your booking has been confirmed!");
    		alert.setHeaderText("Success");
    		alert.showAndWait();
        	Main main = new Main();
        	UpcomingTrips upcomingTrips = new UpcomingTrips();
        	upcomingTrips.setFrom(routes.get(0).getFromLocation().getLocationName());
        	upcomingTrips.setDepart(routes.get(0).getDepartureTime().toString());
        	upcomingTrips.setArrive(routes.get(routes.size()-1).getArrivalTime().toString());
        	upcomingTrips.setTo(routes.get(routes.size()-1).getToLocation().getLocationName());
        	ArrayList<UpcomingTrips> tripList = new ArrayList<>();
        	tripList.add(upcomingTrips);
    		WelcomeController welcomeController = new WelcomeController(customer,tripList);
    		main.changeScene("WelcomePage.fxml",welcomeController);
    	}else {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Confirmation");
    		alert.setContentText("Invalid Card!");
    		alert.setHeaderText("Sorry!");
    		alert.showAndWait();
    		
    	}

    }
    

    @FXML
    void search_mode(KeyEvent event) {

    }
    
    public PaymentController(Customer customer, ArrayList<TravelTickets> routes, Itinerary itinerary) {
    	this.customer = customer;
    	this.routes = routes;
    	this.itinerary = itinerary;
    	
    	for(TravelTickets route: routes) {
    		totalCost+=route.getCost();
    	}
    }
    private void updateTexts() {
    	totalText.setText(String.valueOf(this.totalCost));
		fromText.setText(this.routes.get(0).getFromLocation().getLocationName());
		toText.setText(this.routes.get(routes.size()-1).getToLocation().getLocationName());
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTexts();
		
	}


	@Override
	public void changeScreen() {
		
	}
}
