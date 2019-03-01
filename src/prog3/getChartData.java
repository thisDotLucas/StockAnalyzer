package prog3;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import java.io.BufferedReader;
import java.io.StringReader;


public class getChartData {

    public static LineChart getData(LineChart lineChart, String data){

        BufferedReader bufReader = new BufferedReader(new StringReader(data));
        XYChart.Series series = new XYChart.Series();
        String line;
        String date;
        Number value;
        int counter = 0;

        try {

            while ((line = bufReader.readLine()) != null) {

                if (counter != 0) {

                    System.out.println(line);
                    line = line.replaceAll(":", "").replace("Date", "");
                    date = line.substring(0, 11);
                    line = line.replace(date, "");
                    value = Double.parseDouble(line);
                    series.getData().add(new XYChart.Data(date, value));
                    lineChart.getData().add(series);

                }

                counter++;

            }

            return lineChart;

        } catch (Exception e){

            System.out.println(e);

        }

        return lineChart;

    }

}
