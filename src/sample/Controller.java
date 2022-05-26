package sample;

import Util.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private TextField playerName;

    public void commencerCLicked() throws IOException {
        if (playerName.getText().isEmpty()) {
            AlertBox.display("Erreur", "Vous dever entrer votre nom svp!");
        }else{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Board.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Game window");
                stage.setScene(new Scene(root1));
                stage.show();
            }
        }

    }
