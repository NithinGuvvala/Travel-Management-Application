package application;

import edu.northeastern.tableModels.ItineraryTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AdminDelUpController extends Controllers{

    @FXML
    private TableColumn<ItineraryTable, String> col_arrive;

    @FXML
    private TableColumn<ItineraryTable, String> col_depart;

    @FXML
    private TableColumn<ItineraryTable, String> col_from;

    @FXML
    private TableColumn<ItineraryTable, String> col_id;

    @FXML
    private TableColumn<ItineraryTable, String> col_to;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField filterField;

    @FXML
    private TableColumn<ItineraryTable, String> mode;

    @FXML
    private TableView<ItineraryTable> table_users;

    @FXML
    private TextField travel_arrive;

    @FXML
    private TextField travel_depart;

    @FXML
    private TextField travel_from;

    @FXML
    private TextField travel_id;

    @FXML
    private TextField travel_mode;

    @FXML
    private TextField travel_to;

    @FXML
    private Button updateButton;

    @FXML
    void delete_trip(ActionEvent event) {

    }

    @FXML
    void getSelected(MouseEvent event) {

    }

    @FXML
    void search_mode(KeyEvent event) {

    }

    @FXML
    void update_trip(ActionEvent event) {

    }

	@Override
	public void changeScreen() {
		
	}

}
