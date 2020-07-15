package application;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import application.MainController;
import application.ViewNavigator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	public static FileChooser fc;
	private static Desktop desktop = Desktop.getDesktop();
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JIRA Stepper");
		primaryStage.setScene(createScene(loadMainPane()));
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			try {
				handleExit();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
	}

	private void handleExit() throws SQLException {
		// handle disposing of the timestamp thread
		Platform.exit();
		System.exit(0);
	}

	/**
	 * @return the loaded pane.
	 * @throws IOException if the pane could not be loaded.
	 */
	private Pane loadMainPane() throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader();

		Pane mainPane = (VBox) loader.load(getClass().getResourceAsStream(ViewNavigator.MAIN));

		MainController mainController = loader.getController();

		ViewNavigator.setMainController(mainController);
		ViewNavigator.loadView(ViewNavigator.IMPORT);

		return mainPane;
	}

	/**
	 * Creates the main application scene.
	 *
	 * @param mainPane the main application layout.
	 *
	 * @return the created scene.
	 */
	private Scene createScene(Pane mainPane) {
		Scene scene = new Scene(mainPane);
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}
