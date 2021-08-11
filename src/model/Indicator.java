package model;

/**
 * Abstract class to manage different data from different input files
 * @author Foothill College, Juntao Ren
 */

public abstract class Indicator {

    private int year;
    protected final int INVALID_DATA = -1;

    /**
     * Constructor method to initialize variable
     * @param year initialize global variable type int
     */
    public Indicator (int year){
        this.year = year;
    }

    /**
     * Accessor method for int year
     * @return int variable year
     */
    public int getYear(){
        return year;
    }

    /**
     * Abstract mutator method to set double[] data
     * @param data doube[] to set to global vairable
     */
    public abstract void setData(double[] data);

    /**
     * Abstract accessor method for double[] data
     * @return double[] of data
     */
    public abstract double[] getData();

    /**
     * Abstract method returning String of instance
     * @return String containing contents of instance
     */
    public abstract String toString();
}
