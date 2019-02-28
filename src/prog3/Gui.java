
package prog3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import json.AlphaVantage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Gui extends Application {

    TextArea text;
    Button queryButton;
    ChoiceBox dataSeries, timeSeries, symbol, timeInterval, outputSize;
    Label dataLabel, dataLabe2, dataLabe3, dataLabe4, dataLabe5;
    AlphaVantage data;
    int counter = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Layouts
        BorderPane main = new BorderPane();
        GridPane layout = new GridPane();
        GridPane graphLayout = new GridPane();

        layout.setPadding(new Insets(15, 15, 15, 15));
        layout.setVgap(8);
        layout.setHgap(10);

        graphLayout.setPadding(new Insets(15, 15, 15, 15));
        graphLayout.setVgap(8);
        graphLayout.setHgap(10);


        //Graph
        //FXMLDocumentController graph = new FXMLDocumentController();
        //GraphController graph = new GraphController();


        //Querybutton
        queryButton = new Button("Do query");
        queryButton.setOnAction(event -> {

            text.clear();
            String series = dataSeries.getValue().toString();
            String interval = "&interval=" + timeInterval.getValue().toString();
            String timeSer = timeSeries.getValue().toString();
            String symb = "&symbol=" + symbol.getValue().toString();
            String size = "&outputsize=" + outputSize.getValue().toString();

            data = new AlphaVantage();

            {

                try {

                        String out = data.getJson(series, interval, timeSer, symb, size);
                        text.appendText(out);
                        counter++;

                } catch (Exception e) {

                    System.out.println(e);

                }
            }

        });
        GridPane.setConstraints(queryButton, 1, 5);


        //Textarea
        text = new TextArea();
        text.setMinSize(500, 350);
        text.positionCaret(500);
        GridPane.setConstraints(text, 0, 7);
        GridPane.setColumnSpan(text, 5);


        //Labels
        dataLabel = new Label("Data Series");
        dataLabe2 = new Label("Time Series");
        dataLabe3 = new Label("Symbol");
        dataLabe4 = new Label("Time Interval");
        dataLabe5 = new Label("Output Size");


        //Choiceboxes
        dataSeries = new ChoiceBox(FXCollections.observableArrayList(
                "1. open", "2. high", "3. low", "4. close", "5. volume")
        );
        dataSeries.getStylesheets().add("prog3/ChoiceBoxes.css");

        timeSeries = new ChoiceBox(FXCollections.observableArrayList(
                "TIME_SERIES_INTRADAY", "TIME_SERIES_DAILY_ADJUSTED", "TIME_SERIES_WEEKLY_ADJUSTED", "TIME_SERIES_MONTHLY_ADJUSTED")
        );
        timeSeries.getStylesheets().add("prog3/ChoiceBoxes.css");

        symbol = new ChoiceBox(FXCollections.observableArrayList(
                "MSFT")
        );
        symbol.getStylesheets().add("prog3/ChoiceBoxes.css");

        timeInterval = new ChoiceBox(FXCollections.observableArrayList(
                "1min", "5min", "15min", "30min", "60min")
        );
        timeInterval.getStylesheets().add("prog3/ChoiceBoxes.css");

        outputSize = new ChoiceBox(FXCollections.observableArrayList(
                "full", "compact")
        );
        outputSize.getStylesheets().add("prog3/ChoiceBoxes.css");


        //Choicebox listeners
            dataSeries.getSelectionModel().selectedItemProperty().addListener((item, oldValue, newValue) -> {

                if (counter > 0) {

                    text.clear();
                    text.appendText(data.getUpdate(data.object, newValue.toString(), timeInterval.getValue().toString()));

                }
            });

        timeSeries.getSelectionModel().selectedItemProperty().addListener((item, oldValue, newValue) -> {

            if (newValue.toString().equals("TIME_SERIES_DAILY_ADJUSTED")){

                timeInterval.setDisable(true);
                outputSize.setDisable(true);
                timeInterval.setValue("Time Series (Daily)");
                outputSize.setValue("");

            } else if (newValue.toString().equals("TIME_SERIES_MONTHLY_ADJUSTED")){

                timeInterval.setDisable(true);
                outputSize.setDisable(true);
                timeInterval.setValue("Monthly Adjusted Time Series");
                outputSize.setValue("");

            } else if (newValue.toString().equals("TIME_SERIES_WEEKLY_ADJUSTED")){

                timeInterval.setDisable(true);
                outputSize.setDisable(true);
                timeInterval.setValue("Weekly Adjusted Time Series");
                outputSize.setValue("");

            } else {

                if (counter > 0) {

                    timeInterval.setDisable(false);
                    outputSize.setDisable(false);
                    timeInterval.setValue("15min");
                    outputSize.setValue("full");

                }
            }

        });


            //Placements
            GridPane.setConstraints(dataLabel, 0, 0);
            GridPane.setConstraints(dataLabe2, 0, 1);
            GridPane.setConstraints(dataLabe3, 0, 2);
            GridPane.setConstraints(dataLabe4, 0, 3);
            GridPane.setConstraints(dataLabe5, 0, 4);
            GridPane.setConstraints(dataSeries, 1, 0);
            GridPane.setConstraints(timeSeries, 1, 1);
            GridPane.setConstraints(symbol, 1, 2);
            GridPane.setConstraints(timeInterval, 1, 3);
            GridPane.setConstraints(outputSize, 1, 4);
            main.setLeft(layout);
            //main.setCenter(graph.initialize());


            //Scene
            layout.getChildren().addAll(dataLabel, dataLabe2, dataLabe3, dataLabe4, dataLabe5,
                    dataSeries, timeSeries, symbol, timeInterval, outputSize, queryButton, text);

            Scene scene = new Scene(main);


            //Stage
            primaryStage.setTitle("Stock Analyzer");
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();


        }
    }

