package application;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.northeastern.dao.LocationDAO;
import edu.northeastern.models.Customer;
import edu.northeastern.models.Itinerary;
import edu.northeastern.models.TravelTickets;
import edu.northeastern.tableModels.Trips;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchResultsController extends Controllers implements Initializable{
	
	
	 @FXML
    private TableColumn<Trips, String> arrDate;

    @FXML
    private Button button1;

    @FXML
    private Button button11;

    @FXML
    private TableColumn<Trips, Double> costCol;

    @FXML
    private TableColumn<Trips, String> departDate;

    @FXML
    private TableColumn<Trips, String> fromCol;

    @FXML
    private TableColumn<Trips, String> toCol;

    @FXML
    private TableView<Trips> tripsTable;
    
	private ArrayList<ArrayList<TravelTickets>> routes;
	
	Customer customer;
    
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	private String randomString(int len){
	   StringBuilder sb = new StringBuilder(len);
	   for(int i = 0; i < len; i++)
	      sb.append(AB.charAt(rnd.nextInt(AB.length())));
	   return sb.toString();
	}
    @FXML
    void gotoConfirm(ActionEvent event) throws IOException {
    	Main main = new Main();
    	int selectedRow = tripsTable.getSelectionModel().getSelectedIndex();
    	LocationDAO locationDAO = new LocationDAO();
    	ArrayList<TravelTickets> selectedRoute = routes.get(selectedRow);
    	Itinerary itinerary = new Itinerary(randomString(10),selectedRoute.get(0).getFromLocation(), selectedRoute.get(selectedRoute.size()-1).getToLocation());
    	ConfirmationPageController confirmationPageController = new ConfirmationPageController(itinerary, selectedRoute, this.customer, this.routes);
    	main.changeScene("Itinerary.fxml", confirmationPageController);
    	
    	
    }
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Main m = new Main();
    	WelcomeController welcomeController = new WelcomeController(customer, null);
    	m.changeScene("WelcomePage.fxml", welcomeController);
    }




	public SearchResultsController(ArrayList<ArrayList<TravelTickets>> routes, Customer customer) {
		this.routes = routes;
		this.customer = customer;
	}
	
	private double calculateTotalCost(ArrayList<TravelTickets> tickets) {
		double cost=0;
		for(TravelTickets ticket: tickets) {
			cost+=ticket.getCost();
		}
		return cost;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Trips> tripList = new ArrayList<>();
		arrDate.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
		departDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
    	fromCol.setCellValueFactory(new PropertyValueFactory<>("fromLocation"));
    	toCol.setCellValueFactory(new PropertyValueFactory<>("toLocation"));
    	costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		
		for(ArrayList<TravelTickets> ticket: routes) {
			Trips trip = new Trips();
			trip.setDepartureDate(ticket.get(0).getDepartureTime().toString());
			trip.setArrivalDate(ticket.get(ticket.size()-1).getArrivalTime().toString());
			trip.setCost(calculateTotalCost(ticket));
			trip.setToLocation(ticket.get(ticket.size()-1).getToLocation().getLocationName());
			trip.setFromLocation(ticket.get(0).getFromLocation().getLocationName());
			tripList.add(trip);
		}
		tripsTable.getItems().addAll(tripList);
	}
	
	@Override
	public void changeScreen() {
		
	}
	
	

}
