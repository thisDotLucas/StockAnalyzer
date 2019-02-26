package prog3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
   // private LineChart<?, ?> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

    @Override
    public void initialize(URL url, ResourceBundle rb){

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data(1, 2));

        //lineChart.getData().addAll(series);

    }
}
