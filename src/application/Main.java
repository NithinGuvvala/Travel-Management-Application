package application;
	

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import edu.northeastern.models.Manager;
import edu.northeastern.service.ManagerService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application{
	private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{    	
    	stg=primaryStage;
    	SplashController splashController = new SplashController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SplashPage.fxml"));
		fxmlLoader.setController(splashController);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root,1200,800);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    public void changeScene(String fxml, Controllers controller) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
        stg.getScene().setRoot(root);
    }


    public static void main(String[] args) throws Exception {
        launch(args);

    }
}
