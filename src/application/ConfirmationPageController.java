package application;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.northeastern.models.BusTicket;
import edu.northeastern.models.Customer;
import edu.northeastern.models.Itinerary;
import edu.northeastern.models.TrainTickets;
import edu.northeastern.models.TravelTickets;
import edu.northeastern.tableModels.ItineraryTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ConfirmationPageController extends Controllers implements Initializable{
    @FXML
    private TableColumn<ItineraryTable, String> arrivCol;

    @FXML
    private TableColumn<ItineraryTable, Double> costCol;

    @FXML
    private TableColumn<ItineraryTable, String> departCol;

    @FXML
    private TableColumn<ItineraryTable, String> fromCol;

    @FXML
    private TableView<ItineraryTable> itineraryTable;

    @FXML
    private TableColumn<ItineraryTable, String> toCol;

//    @FXML
//    private Text toDate;
//
//    @FXML
//    private Text toText;
//
//    @FXML
//    private Text totalCost;

    @FXML
    private TableColumn<ItineraryTable, String> typeCol;
    
    @FXML
    private Button back;
	
	Itinerary itinerary;
	ArrayList<TravelTickets> routes;
	Customer customer;

	private ArrayList<ArrayList<TravelTickets>> allTickets;
	
//	public ConfirmationPageController(Itinerary itinerary, ArrayList<TravelTickets> routes, Customer customer, ArrayList<ArrayList<TravelTickets>> allTickets) {
//		
//	}
//	

	public ConfirmationPageController(Itinerary itinerary, ArrayList<TravelTickets> routes, Customer customer,
			ArrayList<ArrayList<TravelTickets>> allTickets) {
		this.routes=routes;
		this.itinerary=itinerary;
		this.allTickets=allTickets;
		this.customer=customer;
	}


	@FXML
    void goBack(ActionEvent event) throws IOException {
		Main main = new Main();
		SearchResultsController searchResultsController=new SearchResultsController(allTickets, customer);
		main.changeScene("SearchResult.fxml", searchResultsController);
    }

	@FXML
    void goToPayment(ActionEvent event) throws IOException {
		Main main = new Main();
		PaymentController paymentController = new PaymentController(customer, routes, itinerary);
		main.changeScene("PaymentPage.fxml", paymentController);
    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		fromText.setText(itinerary.getOrigin().getLocationName());
//		toText.setText(itinerary.getDestination().getLocationName());
//		fromDate.setText(this.routes.get(0).getDepartureTime().toString());
//		toDate.setText(this.routes.get(this.routes.size()-1).getArrivalTime().toString());
		
		arrivCol.setCellValueFactory(new PropertyValueFactory<>("arrival"));
		costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		departCol.setCellValueFactory(new PropertyValueFactory<>("departure"));
		arrivCol.setCellValueFactory(new PropertyValueFactory<>("arrival"));
		fromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
		toCol.setCellValueFactory(new PropertyValueFactory<>("to"));
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
			
		for(TravelTickets ticket:this.routes) {
			ItineraryTable itinerary = new ItineraryTable();
			itinerary.setFrom(ticket.getFromLocation().getLocationName());
			itinerary.setTo(ticket.getToLocation().getLocationName());
			itinerary.setDeparture(ticket.getDepartureTime().toString());
			itinerary.setArrival(ticket.getArrivalTime().toString());
			itinerary.setCost(ticket.getCost());
			if(ticket instanceof BusTicket)
				itinerary.setType("Bus");
			else if(ticket instanceof TrainTickets)
				itinerary.setType("Train");
			else
				itinerary.setType("Flight");
			itineraryTable.getItems().add(itinerary);
		}
		
	}


	@Override
	public void changeScreen() {
		
	}

}
