import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class CoordenadasPolaresECartesianas extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Coordenadas Polares e Cartesianas");

        // Polar - gráfico
        LineChart<Number, Number> polarChart = createPolarChart();
        Scene polarScene = new Scene(polarChart, 800, 600);

        // Cartesiano - gráfico
        LineChart<Number, Number> cartesianChart = createCartesianChart();
        cartesianChart.getData().add(createCartesianSeries());
        Scene cartesianScene = new Scene(cartesianChart, 800, 600);

        // Configurar a View
        stage.setScene(polarScene);
        stage.show();

        // Trocar os gráficos com o mouse
        polarScene.setOnMouseClicked(event -> stage.setScene(cartesianScene));
        cartesianScene.setOnMouseClicked(event -> stage.setScene(polarScene));

    }
    
    private XYChart.Series<Number, Number> createPolarSeries() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (double theta = 0; theta <= 2 * Math.PI; theta += 1) {
            double r = -2 + 3 * Math.cos(theta);
            series.getData().add(new XYChart.Data<>(r * Math.cos(theta), r * Math.sin(theta)));
        }
        series.setName("r = -2 + 3cos(theta)");
        return series;
    }

    private XYChart.Series<Number, Number> createCartesianSeries() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (double x = -10; x <= 10; x += 0.1) {
            double y = -2 + 3 * Math.cos(Math.acos(x / Math.sqrt(x * x + 1)));
            series.getData().add(new XYChart.Data<>(x, y));
        }
        series.setName("r = -2 + 3cos(theta) em Cartesianas");
        return series;
    }

    private LineChart<Number, Number> createPolarChart() {
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        x.setAutoRanging(false);
        y.setAutoRanging(false);

        LineChart<Number, Number> lineChart = new LineChart<>(x, y);
        lineChart.getData().add(createPolarSeries());
        return lineChart;
    }

    private LineChart<Number, Number> createCartesianChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.getData().add(createCartesianSeries());
        return chart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
