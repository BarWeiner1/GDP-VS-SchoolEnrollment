package model;

import java.util.Iterator;

/**
 * Class to hold array of Indicator objects
 * @author Foothill College, Juntao Ren
 */
public class Country {

    private String name;
    private LinkedList<Indicator> indicators;
    private int minYear, maxYear;


    /**
     * Constructor method to initialize instance variable name
     * @param name to be initialized as String
     */
    public Country(String name){
        this.name = name;
        indicators = new LinkedList<Indicator>();
    }

    //TODO javadocs
    public void addIndicator(Indicator newData){
        indicators.add(newData);
    }

    /**
     * Returns true if argument matches name of instance
     * @param countryName name of country to be searched
     * @return boolean of whether country is in instance
     */
    public boolean equals(String countryName){
        return name.equals(countryName);
    }

    /**
     * Accessor method to get name of instance
     * @return String name of instance
     */
    public String getName(){
        return name;
    }

    /**
     * Accessor method returning int for first year
     * @return int of first indicator entry
     */
    public int getStartYear(){
        return indicators.getIndex(0).getYear();
    }

    /**
     * Accessor method return int for last entry
     * @return int of last indicator entry
     */
    public int getEndYear(){
        return indicators.getIndex(0).getYear() + indicators.size();
    }

    /**
     * Returns data if requested year is valid
     * @param year requested year
     * @return Indicator if year exists in indicator instance variable
     * @throws IllegalArgumentException when year does not exist
     */
    public Indicator getIndicatorForYear(int year) throws IllegalArgumentException{
        if (indicators.size() <= 0){
            return null;
        }
        if (indicators.getIndex(0) == null){
            return null;
        }

        if (year < indicators.getIndex(0).getYear()){        //if year is too low
            throw new IllegalArgumentException("Year is too low.");
        }
        if (year > indicators.getIndex(0).getYear() + indicators.size()){        //if year is too high
            throw new IllegalArgumentException("Year is too high");
        }

        return indicators.getIndex(year - indicators.getIndex(0).getYear());
    }

    /**
     * Updates array indicators with new data
     * @param requestedYear year to update
     * @param newIndicator to be set to year in array indicators
     * @throws IllegalArgumentException if year does not exist
     */
    public void setIndicatorForYear(int requestedYear, Indicator newIndicator) throws IllegalArgumentException{
        if (indicators.size() <= 0){
            return;
        }
        if (indicators.getIndex(0) == null)
        {
            indicators.insertAtIndex(newIndicator, 0);
            return;
        }
        if (requestedYear < indicators.getIndex(0).getYear()){        //if year is too low
            throw new IllegalArgumentException("Year is too low.");
        }
        if (requestedYear > indicators.getIndex(0).getYear() + indicators.size()){        //if year is too high
            throw new IllegalArgumentException("Year is too high");
        }

        indicators.insertAtIndex(newIndicator, requestedYear-indicators.getIndex(0).getYear());
    }

    /**
     * Determines if requested year range is valid
     * @param requestedStartYear The year at which returned Indicators array starts
     * @param requestedEndYear The years at which array returned Indicators array ends
     * @return Inidcator[] containing data from start to end year
     */
    public Indicator[] getIndicatorForPeriod(int requestedStartYear, int requestedEndYear){
        if (requestedStartYear > requestedEndYear) {
            throw new IllegalArgumentException("The requested range is inverted.");
        }
        if (requestedStartYear < indicators.getIndex(0).getYear() && requestedEndYear < indicators.getIndex(0).getYear()){
            throw new IllegalArgumentException("The requested range is before the earliest year.");
        }
        if (requestedStartYear > indicators.getIndex(indicators.size()-1).getYear() && requestedEndYear > indicators.getIndex(indicators.size()-1).getYear()) {
            throw new IllegalArgumentException("The requested range is after the latest year.");
        }

        if (requestedStartYear < indicators.getIndex(0).getYear()){
            System.out.println("Adjusting year " + requestedStartYear + " to " + indicators.getIndex(0).getYear());
            requestedStartYear = indicators.getIndex(0).getYear();
        }
        if (requestedEndYear > indicators.getIndex(indicators.size()-1).getYear()){
            System.out.println("Adjusting year " + requestedEndYear + " to " + indicators.getIndex(indicators.size()-1).getYear());
            requestedEndYear = indicators.getIndex(indicators.size()-1).getYear();
        }

        Indicator[] singleCountry = new Indicator[requestedEndYear-requestedStartYear+1];

        if (singleCountry.length >= 0){
            for (int i=0; i<singleCountry.length; i++){
                singleCountry[i] = indicators.getIndex(i+requestedStartYear-indicators.getIndex(0).getYear());
            }
        }

        return singleCountry;
    }

    /**
     * Returns string object representing instance
     * @return string object representing instance
     */
    public String toString(){
        String finalStr = "Country Name: " + String.format("%-20s", name);
        if (indicators != null){
            for (int i=0; i<indicators.size(); i++){
                finalStr += String.format("%-20s", indicators.getIndex(i).toString());
            }
        }
        return finalStr + "\n";
    }

    /**
     *Over-rides equals function
     * @param check takes in object and checks if they are the same object
     * @return Boolean indicating if the objects are equal
     */
    public boolean equals(Object check){
        if (check instanceof String){
            return this.getName().equals((String)check);
        } else if (check instanceof Country){
            return this.getName().equals(((Country) check).getName());
        } else{
            return false;
        }
    }
}