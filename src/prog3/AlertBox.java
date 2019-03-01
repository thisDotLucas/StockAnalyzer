package prog3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;

public class AlertBox {

    public static void display(String title, String message){

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        Label label = new Label(message + "                           ");
        Button okButton = new Button("  Ok  ");
        okButton.setOnAction(event -> stage.close());

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);
        layout.getChildren().addAll(label, okButton);
        layout.setAlignment(Pos.CENTER);

        GridPane.setConstraints(label, 0, 1);
        GridPane.setConstraints(okButton, 0, 3);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.showAndWait();


    }

}
