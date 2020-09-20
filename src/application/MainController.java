package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {
/** Holder of a switchable pane. */
	// poopypop
    // jordan makes me so butt
    // jesus why don't jordan shutup he so sassy
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
