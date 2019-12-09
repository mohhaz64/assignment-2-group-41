import java.io.IOException;
import java.util.ConcurrentModificationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class WinController {

    @FXML
    private GridPane gridPane;
    
    private GameController parentController;

    @FXML
    void clickQuit(ActionEvent event) {
    	Stage stage = (Stage) gridPane.getScene().getWindow();
    	parentController.clickQuit(event);
	    stage.close();
    }
    
    public void setParentController(GameController parentController) {
        this.parentController = parentController;
    }

}
