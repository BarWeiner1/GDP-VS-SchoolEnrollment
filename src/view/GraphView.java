package view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Country;
import model.DataModel;
import model.IndicatorType;

public class GraphView<T> extends LineChart {
    private NumberAxis xAxis, yAxis;
    private DataModel model;
    private IndicatorType type;
    private final int TOTAL_COUNTRIES = 5;


    /**
     *Constructor for creating a graph view
     * Instantiates the Private Variable
     * Sets the xAxis and the yAxis
     */
    public GraphView(DataModel model, IndicatorType type){
        super(new NumberAxis(), new NumberAxis());
        this.model = model;
        this.type = type;
        super.setTitle("DATA POINTS FOR "+ type.getLabel());

        xAxis = (NumberAxis) super.getXAxis();
        xAxis.setLabel(model.getXAxisName());
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(model.getLowestYear() - 1);
        xAxis.setUpperBound(model.getHighestYear() + 1);

        yAxis = (NumberAxis) super.getYAxis();
        yAxis.setLabel(model.getYAxisName());
    }

    /**
     * Creates a series for a country
     * Adds the series to the graph
     * Loops through all of the countries
     */
    public Series<Number, Number> seriesFromCountry(Country currentCountry, IndicatorType passedType) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(currentCountry.getName());
        for (int i = currentCountry.getStartYear(); i < currentCountry.getEndYear(); i++) {
            if (passedType == IndicatorType.GDP_PER_CAPITA) {
                double value = Double.parseDouble(String.valueOf(currentCountry.getIndicatorForYear(i)));
                series.getData().add(new XYChart.Data<>(i, value));
            } else if (passedType == IndicatorType.SCHOOL_ENROLLMENT_PRIMARY){
                double primaryValue = Double.parseDouble(String.valueOf(currentCountry.getIndicatorForYear(i).getData()[0]));
                series.getData().add(new XYChart.Data<>(i, primaryValue));
                series.setName(currentCountry.getName() + " Primary");
            } else if (passedType == IndicatorType.SCHOOL_ENROLLMENT_SECONDARY){
                double secondaryValue = Double.parseDouble(String.valueOf(currentCountry.getIndicatorForYear(i).getData()[1]));
                series.getData().add(new XYChart.Data<>(i, secondaryValue));
                series.setName(currentCountry.getName() + " Secondary");
            }
        }
        return series;
    }
    /**
     *Updates the data for each of the countries in the series
     */
    public void update(){
        int countriesAdded = (int) (model.getModel().length * Math.random());

        for (int i=countriesAdded; i<countriesAdded+TOTAL_COUNTRIES; i++) {
            if (type == IndicatorType.GDP_PER_CAPITA) {
                XYChart.Series<Number, Number> gdpSeries = this.seriesFromCountry(model.getModel()[i], IndicatorType.GDP_PER_CAPITA);
                this.getData().add(gdpSeries);
            } else {
                XYChart.Series<Number, Number> primarySeries = this.seriesFromCountry(model.getModel()[i], IndicatorType.SCHOOL_ENROLLMENT_PRIMARY);
                XYChart.Series<Number, Number> secondarySeries = this.seriesFromCountry(model.getModel()[i], IndicatorType.SCHOOL_ENROLLMENT_SECONDARY);
                this.getData().add(primarySeries);
                this.getData().add(secondarySeries);
            }
        }
    }
}
