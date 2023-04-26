package application;

import java.net.URL;
import java.util.ResourceBundle;

import edu.northeastern.tableModels.PreviousTrips;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TravelHistoryController extends Controllers implements Initializable{

    @FXML
    private TableColumn<PreviousTrips, String> col_arrive;

    @FXML
    private TableColumn<PreviousTrips, String> col_depart;

    @FXML
    private TableColumn<PreviousTrips, String> col_from;

    @FXML
    private TableColumn<PreviousTrips, String> col_to;

    @FXML
    private TableColumn<PreviousTrips, String> mode;

    @FXML
    private TableView<PreviousTrips> table_users;

    @FXML
    private TableColumn<PreviousTrips, Double> cost;


    @FXML
    void Add_trips(ActionEvent event) {

    }

    @FXML
    void getSelected(MouseEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		col_arrive.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
		col_depart.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
		col_from.setCellValueFactory(new PropertyValueFactory<>("from"));
		col_to.setCellValueFactory(new PropertyValueFactory<>("to"));
		mode.setCellValueFactory(new PropertyValueFactory<>("status"));
		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		PreviousTrips previousTrips1 = new PreviousTrips("T1", "Boston", "Seattle", "25-11-2022", "27-11-2022", "Completed",350);
		PreviousTrips previousTrips2 = new PreviousTrips("T1", "Boston", "San Francisco", "20-11-2022", "22-11-2022", "Completed",400);
		table_users.getItems().add(previousTrips1);
		table_users.getItems().add(previousTrips2);
		
	}

	@Override
	public void changeScreen() {
		
	}

}
