package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {
/** Holder of a switchable pane. */
	// poopypop
    // I AM GOING TO SPANK JORDAN'S HINEY
    // THERE IS NOTHING HE CAN DO TO STOP THIS SPANKING
    // HAHA
    @FXML
    private StackPane viewHolder;

    /**
     * Replaces the vista displayed in the pane holder with a new vista.
     *
     * @param node the pane node to be swapped in.
     */
    public void setView(Node node) {
        viewHolder.getChildren().setAll(node);
    }
}
