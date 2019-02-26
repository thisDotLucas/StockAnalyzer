package prog3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

public class GraphController {

    @FXML
    LineChart<String, Number> lineChart;
    AnchorPane content;

    public void  show(){



    }

    public void addData(String date, int value) {

        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        lineChart.getData().add(series);

    }
}

