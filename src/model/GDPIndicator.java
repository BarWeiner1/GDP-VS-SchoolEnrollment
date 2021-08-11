package model;

/**
 * Extends class Indicator
 * Keeps track of GDP data points
 * @author Foothill College, Juntao Ren
 */
public class GDPIndicator extends Indicator {

    private double gdpPerCapita;

    /**
     * Constructor method to initialize variables
     * @param yearLabel to be initialized in parent class
     */
    public GDPIndicator(int yearLabel) {
        super(yearLabel);
        gdpPerCapita = super.INVALID_DATA;
    }

    /**
     * Constructor method to initialize variables
     * @param yearLabel to be initialized in parent class
     * @param gdpPerCapita  to be initialized as global int
     */
    public GDPIndicator(int yearLabel, double gdpPerCapita){
        super(yearLabel);
        this.gdpPerCapita = gdpPerCapita;
    }

    /**
     * Mutator method to set instance data variables
     * @param data single array of type double for data
     */
    @Override
    public void setData(double[] data) {
        gdpPerCapita = data[0];
    }

    /**
     * Accessor method to get gdpPerCapita
     * @return double[] with single entry gdpPerCapita
     */
    @Override
    public double[] getData() {
        return new double[] {gdpPerCapita};
    }

    /**
     * Returns String representation of object
     * @return String with gdpPerCapita
     */
    @Override
    public String toString() {
        return String.format("%.2f", gdpPerCapita);
    }

}
