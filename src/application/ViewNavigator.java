package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import application.ImportController;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;

/**
 * Utility class for controlling navigation between views.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class ViewNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String MAIN    = "/application/Main.fxml";
    public static final String IMPORT = "/application/Import.fxml";
    
    public static String url;
    public static String pname;




    /** The main application layout controller. */
    private static MainController mainController;
    private static ImportController importController;


    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param mainController the main application layout controller.
     */
    public static void setMainController(MainController mainController) {
        ViewNavigator.mainController = mainController;
    }
    public static void setLoginController(ImportController loginController) {
        ViewNavigator.importController = loginController;
    }
	public static void setUrl(String url) {
		ViewNavigator.url = url;
	}
	public static void setPname(String pname) {
		ViewNavigator.pname = pname;
	}
	/**
     * Loads the view specified by the fxml file into the
     * viewHolder pane of the main application layout.
     *
     * Previously loaded view for the same fxml file are not cached.
     * The fxml is loaded anew and a new view node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded view nodes, so they can be recalled or reused
     *   allow a user to specify view node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadView(String fxml) {
        try {
            mainController.setView(FXMLLoader.load(ViewNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
