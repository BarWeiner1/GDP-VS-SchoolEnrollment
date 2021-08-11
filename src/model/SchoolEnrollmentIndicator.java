package model;


/**
 * Extends class Indicator
 * Keeps track of School Enrollment data points
 * @author Foothill College, Juntao Ren
 */
public class SchoolEnrollmentIndicator extends Indicator {

    private double netPrimary;
    private double netSecondary;

    /**
     * Constructor method to initialize variables
     * @param yearLabel to be initialized in parent class
     */
    public SchoolEnrollmentIndicator(int yearLabel) {
        super(yearLabel);
        netPrimary = super.INVALID_DATA;
    }

    /**
     * Constructor method to initialize variables
     * @param yearLabel to be initialized in parent class
     * @param netPrimary  to be initialized as global double
     * @param netSecondary  to be initialized as global double
     */
    public SchoolEnrollmentIndicator(int yearLabel, double netPrimary, double netSecondary){
        super(yearLabel);
        this.netPrimary = netPrimary;
        this.netSecondary = netSecondary;
    }

    /**
     * Mutator method to set netPrimary
     * @param netPrimary double variable for netPrimary
     */
    public void setPrimaryEnrollment(double netPrimary){
        this.netPrimary = netPrimary;
    }

    /**
     * Accessor method to get netPrimary
     * @return double variable with data from netPrimary
     */
    public double getPrimaryEnrollment(){
        return netPrimary;
    }

    /**
     * Mutator method to set netSecondary
     * @param netSecondary double variable for netSecondary
     */
    public void setSecondaryEnrollment(double netSecondary){
        this.netSecondary = netSecondary;
    }

    /**
     * Accessor method to get netPrimary
     * @return double variable with data from netPrimary
     */
    public double getSecondaryEnrollment(){
        return netSecondary;
    }

    /**
     * Mutator method to set data
     * @param data single array of type double for instance variables
     */
    @Override
    public void setData(double[] data) {
        netPrimary = data[0];
    }

    /**
     * Accessor method to get data
     * @return double[] with data entries
     */
    @Override
    public double[] getData() {
        return new double[] {netPrimary, netSecondary};
    }

    /**
     * Returns String representation of object
     * @return String with SchoolEnrollment
     */
    @Override
    public String toString() {
        return "" + "P:" + String.format("%.2f", netPrimary) + "  " + "S:" + String.format("%.2f", netSecondary);
    }

    public IndicatorType getIndicatorType(){
        return IndicatorType.SCHOOL_ENROLLMENT;
    }

}
