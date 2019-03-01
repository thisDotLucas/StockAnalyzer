package prog3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class getChartData {



    public static LineChart getData(LineChart lineChart,String data){

        ObservableList<String> dates;
        ArrayList<String> listOfDates = new ArrayList<>();
        BufferedReader bufReader = new BufferedReader(new StringReader(data));
        XYChart.Series series = new XYChart.Series();
        String line;
        String date;
        Number value;
        int counter = 0;

        try {

            while ((line = bufReader.readLine()) != null) {

                if ((counter % 10) == 1) {

                    line = line.replaceAll(":", "").replace("Date", "");
                    System.out.println(line);
                    if(line.length() < 23)
                        date = line.substring(0, 11);
                    else
                        date = line.substring(0, 18);
                    System.out.println(date);
                    line = line.replace(date, "");
                    value = Double.parseDouble(line);
                    series.getData().add(new XYChart.Data(date, value));
                    lineChart.getData().add(series);

                }

                counter++;

            }

            dates = FXCollections.observableArrayList(listOfDates);
            print(dates);

            return lineChart;

        } catch (Exception e){

            System.out.println(e);

        }

        return lineChart;

    }

    public static void print(ObservableList x){

        for(int i = 0; i < x.size(); i++){

            System.out.println(x.get(i));

        }

    }


}
