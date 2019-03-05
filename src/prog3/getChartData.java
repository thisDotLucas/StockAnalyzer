package prog3;


import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import java.io.BufferedReader;
import java.io.StringReader;


public class getChartData {


    //Tar all data som finns i textarean och gör det kompabelt med grafen
    public static LineChart getData(LineChart lineChart,String data, int interval){

        BufferedReader bufReader = new BufferedReader(new StringReader(data));
        XYChart.Series series = new XYChart.Series();
        String line;
        String date;
        Number value;
        int counter = 0;
        int gap;

        try {
            if(interval < 15 && interval != 0) {
                gap = 40;
                lineChart.setCreateSymbols(false);
            } else {
                gap = 10;
                lineChart.setCreateSymbols(true);
            }

            lineChart.getData().add(series);

            while ((line = bufReader.readLine()) != null) {

                //Tar endast var tionde värde så grafen hålls läsbar :)
                if ((counter % gap) == 1) {

                    line = line.replaceAll(":", "").replace("Date", "");
                    if(line.length() < 23)
                        date = line.substring(0, 11);
                    else
                        date = line.substring(0, 18);
                    line = line.replace(date, "");
                    value = Double.parseDouble(line);
                    series.getData().add(new XYChart.Data(date, value));

                }

                series.getData().removeAll();
                counter++;

            }

            return lineChart;

        } catch (Exception e){

            System.out.println(e);

        }

        return lineChart;

    }


    //print funktion
    public static void print(ObservableList x){

        for(int i = 0; i < x.size(); i++){

            System.out.println(x.get(i));

        }

    }


}
