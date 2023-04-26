package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import edu.northeastern.dao.LocationDAO;
import edu.northeastern.dao.ManagerDAO;
import edu.northeastern.dao.TravelDAO;
import edu.northeastern.models.BusTicket;
import edu.northeastern.models.Location;
import edu.northeastern.models.TrainTickets;
import edu.northeastern.models.TravelTickets;
import edu.northeastern.service.ManagerService;
import edu.northeastern.tableModels.Trips;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AdminAddController extends Controllers implements Initializable{

	@FXML
    private TextField carrier;

    @FXML
    private TableColumn<Trips, String> col_arrive;

    @FXML
    private TableColumn<Trips, Double> col_cost;

    @FXML
    private TableColumn<Trips, String> col_depart;

    @FXML
    private TableColumn<Trips, String> col_from;

    @FXML
    private TableColumn<Trips, String> col_id;

    @FXML
    private TableColumn<Trips, String> col_to;

    @FXML
    private TextField filterField;
    
    @FXML
    private TableColumn<Trips, String> carrier_col;

    @FXML
    private ComboBox<String> fromCombo;

    @FXML
    private TableColumn<Trips, String> mode;

    @FXML
    private ComboBox<String> modeCombo;

    @FXML
    private TextField mode_num;

    @FXML
    private TableView<Trips> table_users;

    @FXML
    private TextField ticketCount;

    @FXML
    private ComboBox<String> toCombo;

    @FXML
    private DatePicker arrivDatePicker;
    
    @FXML
    private DatePicker departDatePicker;

    @FXML
    private TextField txt_id;
    
    @FXML
    private TextField ticketCost;
    


    @FXML
    void Add_trips(ActionEvent event) throws NumberFormatException, SQLException {
    	String from = fromCombo.getValue();
    	String to = toCombo.getValue();
    	String tCount = ticketCount.getText();
    	LocalDate depart = departDatePicker.getValue();
    	LocalDate arriv = arrivDatePicker.getValue();
    	ZoneId defaultZoneId = ZoneId.systemDefault();
    	String tavelId = txt_id.getText();
    	String travelNumber = mode_num.getText();
    	String carr = carrier.getText(); 
    	ManagerDAO managerDAO = new ManagerDAO();
    	double cost = Double.valueOf(ticketCost.getText()); 
    	LocationDAO locationDAO = new LocationDAO();
    	Location fromLocation=locationDAO.getLocation(from);
    	Location toLocation = locationDAO.getLocation(to);
    	String airlines = carrier.getText();
    	if(modeCombo.getValue().equals("Train")) {
    		managerDAO.addTicket(fromLocation, toLocation, tavelId, Integer.valueOf(tCount), null, airlines, null, travelNumber, cost, Date.from(depart.atStartOfDay(defaultZoneId).toInstant()), Date.from(arriv.atStartOfDay(defaultZoneId).toInstant()));
    	}
    	else if(modeCombo.getValue().equals("Bus")) {
    		managerDAO.addTicket(fromLocation, toLocation, tavelId, Integer.valueOf(tCount), null, airlines, travelNumber, null, cost, Date.from(depart.atStartOfDay(defaultZoneId).toInstant()), Date.from(arriv.atStartOfDay(defaultZoneId).toInstant()));
    	}
    	else {
    		managerDAO.addTicket(fromLocation, toLocation, tavelId, Integer.valueOf(tCount), travelNumber, airlines, null, null, cost, Date.from(depart.atStartOfDay(defaultZoneId).toInstant()), Date.from(arriv.atStartOfDay(defaultZoneId).toInstant()));
    	}
    	populateTable();
    	
    }
    @FXML
    void signOut(ActionEvent event) throws IOException {
    	changeScreen();
    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Edit(ActionEvent event) {

    }

    @FXML
    void getSelected(MouseEvent event) {

    }

    @FXML
    void search_mode(KeyEvent event) {

    }
    
    void populateTable() throws SQLException {
    	table_users.getItems().removeAll(table_users.getItems());
    	TravelDAO travelDAO = new TravelDAO();
    	ArrayList<TravelTickets> travelTickets = travelDAO.findAllTicket();
    	mode.setCellValueFactory(new PropertyValueFactory<>("mode"));
    	col_arrive.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
		col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		col_depart.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
		col_from.setCellValueFactory(new PropertyValueFactory<>("fromLocation"));
		col_to.setCellValueFactory(new PropertyValueFactory<>("toLocation"));
		carrier_col.setCellValueFactory(new PropertyValueFactory<>("carrier"));
    	for(TravelTickets travelTicket: travelTickets) {
    		Trips trip = new Trips();
    		trip.setArrivalDate(travelTicket.getArrivalTime().toString());
    		trip.setDepartureDate(travelTicket.getDepartureTime().toString());
    		trip.setCost(travelTicket.getCost());
    		trip.setFromLocation(travelTicket.getFromLocation().getLocationName());
    		trip.setToLocation(travelTicket.getToLocation().getLocationName());
    		trip.setCarrier(travelTicket.getCarrier());
    		if(travelTicket instanceof TrainTickets) {
    			trip.setMode("Train");
    		}
    		else if(travelTicket instanceof BusTicket) {
    			trip.setMode("Bus");
    		}
    		else {
    			trip.setMode("Flight");
    		}
    		table_users.getItems().add(trip);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			populateTable();
			LocationDAO locationDAO = new LocationDAO();
			ArrayList<String> locations= locationDAO.getAllLocations();
			fromCombo.getItems().addAll(locations);
			toCombo.getItems().addAll(locations);
			modeCombo.getItems().add("Bus");
			modeCombo.getItems().add("Train");
			modeCombo.getItems().add("Flight");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void changeScreen() {
		Main m = new Main();
    	SplashController splashController = new SplashController();
    	try {
			m.changeScene("SplashPage.fxml", splashController);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
