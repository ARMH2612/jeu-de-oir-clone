package Util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuestionBox {
    public static boolean display(String title, String question, String answer){
        final boolean[] retVal = {false};
        final  String[] displayString = {""};
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(350);
        window.setHeight(200);

        Label label = new Label();
        label.setText(question);

        TextField tempAnswer = new TextField();

        Button answerBtn = new Button("valider");
        Text t = new Text(displayString[0]);

        answerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(answer.equals(tempAnswer.getText())){
                    t.setText("vous avez entré la bonne réponse,\nvous obtenez 20 points et avancez de 4 étapes.");
                    retVal[0] = true;
                }else{
                    t.setText("Vous avez entré une mauvaise réponse,\nvous perdez 10 points et reculez de 4 étapes.\nla bonne réponse est: "+answer);
                }
            }
        });

        Button closeBtn = new Button("Fermer");
        closeBtn.setOnAction(e -> window.close());


        VBox layout = new VBox(10);
        HBox btns = new HBox(20);
        btns.getChildren().addAll(answerBtn,closeBtn);
        layout.getChildren().addAll(label,tempAnswer,t,btns);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return retVal[0];

    }
}
