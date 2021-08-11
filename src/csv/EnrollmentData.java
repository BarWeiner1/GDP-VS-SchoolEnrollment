package csv;

import model.Indicator;

/**
 * Contains array of countries, years, and 2D array of values corresponding to both.
 * @author Foothill College, Juntao Ren
 */

public class EnrollmentData {

    private String[] countryNames;
    private int[] yearLabels;
    private Indicator[][] table;
    private int lastUsedCountryIndex = 0;                       // keeps track of the last filled index in countryNames
    private int lastUsedTableIndex = 0;                         // keeps track of the Last filled row in table

    /**
     * Initializes all instance variables
     @param numRows the number of rows in 2D array table table and length of array countryNames
     @param numColumns the number of columns in 2D array table table and length of array yearLabels
     @param startingYear the first entry in array yearLabels, where each index increments consecutively
     */
    public EnrollmentData(int numRows, int numColumns, int startingYear) {
        table = new Indicator[numRows][numColumns];
        countryNames = new String[numRows];
        yearLabels = new int[numColumns];
        for (int i=0; i<yearLabels.length; i++){
            yearLabels[i] = startingYear + i;
        }

    }

    /**
     * Add entry to array CountryNames and 2D array table
     @throws IndexOutOfBoundsException if array countryNames is already full
     @param countryName to be added to next open index in array countryName
     @param partialPrimaryEnrollment01 array of variables corresponding to countryName to be added to next open index in 2D array table
     */
    public void addCountry(String countryName, Indicator[] partialPrimaryEnrollment01) throws IndexOutOfBoundsException{
        if (lastUsedCountryIndex >= table.length){
            throw new IndexOutOfBoundsException();
        }
        countryNames[lastUsedCountryIndex] = countryName;
        for (int i=0; i<table[lastUsedTableIndex].length; i++){
            table[lastUsedTableIndex][i] = partialPrimaryEnrollment01[i];
        }
        lastUsedTableIndex++;
        lastUsedCountryIndex++;
    }

    /**
     * Makes formatted table of all variables
     @return String formatted into table with corresponding countryNames, yearLabels, and table
     */
    @Override
    public String toString(){
        String returnedStr = String.format("%-30s", "Country Name");
        for (int year : yearLabels){
            returnedStr += String.format("%-20s", year);
        }
        returnedStr += "\n";
        for (int i=0; i<table.length; i++){
            if (countryNames[i] != null)
                returnedStr += String.format("%-30s", countryNames[i]);
            for (int j=0; j<table[i].length; j++){
                if (table[i][j] != null) {
                    returnedStr += String.format("%-20s", table[i][j].toString());
                }
                else {
                    returnedStr += String.format("%-20s", "");
                }
            }
            if (countryNames[i] != null)
                returnedStr += "\n";
        }

        return returnedStr;
    }

    /**
     * Given stated range, returns data values for specified country
     @param countryName specified name of country to get enrollment values for
     @param requestedStartYear int value of starting year of wanted enrollment period
     @param requestedEndYear int value of ending year of wanted enrollment period
     @throws IllegalArgumentException if both ranges are outside of set period or if request country is not in array
     @return Indicator[] array of enrollment data for specific country in identified range
     */
    public Indicator[] getEnrollmentInCountryForPeriod(String countryName, int requestedStartYear, int requestedEndYear) throws IllegalArgumentException{
        if (requestedStartYear < yearLabels[0] && requestedEndYear < yearLabels[0]){
            throw new IllegalArgumentException("The requested range is before the earliest year.");
        }
        if (requestedStartYear > yearLabels[yearLabels.length-1] && requestedEndYear > yearLabels[yearLabels.length-1]) {
            throw new IllegalArgumentException("The requested range is after the latest year.");
        }

        if (requestedStartYear < yearLabels[0]){
            System.out.println("Adjusting year " + requestedStartYear + " to " + yearLabels[0]);
            requestedStartYear = yearLabels[0];
        }
        if (requestedEndYear > yearLabels[yearLabels.length-1]){
            System.out.println("Adjusting year " + requestedEndYear + " to " + yearLabels[yearLabels.length-1]);
            requestedEndYear = yearLabels[yearLabels.length-1];
        }

        Indicator[] singleCountry = new Indicator[requestedEndYear-requestedStartYear+1];
        int indexOfIdentifiedCountry = -1; //stores the index in the 2D array that contains the data for the specified countryName
        for (int i=0; i<countryNames.length; i++){
            if (countryNames[i].equals(countryName)){
                indexOfIdentifiedCountry = i;
                break;
            }
        }
        if (indexOfIdentifiedCountry == -1){
            throw new IllegalArgumentException("The country request is invalid. There is no data for that country.");
        }
        if (singleCountry.length >= 0){
            for (int i=0; i<singleCountry.length; i++){
                singleCountry[i] = table[indexOfIdentifiedCountry][i];
            }
        }

        return singleCountry;
    }
}
