package csv;

import model.IndicatorType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Parses CSV file and puts respective data into array of countries, years labels, and 2D array of values .
 * @author Foothill College, Juntao Ren
 */
public class CSVParser {

    private String[] countryNames;
    private int[] yearLabels;
    private double[][] dataTable;
    private int numCountries;
    private int numYears;
    private IndicatorType indicatorType;

    /**
     * Parses CSV file and initializes instance variables
     * @param fileName String name of the CSV file
     * @throws InvalidFileFormatException if the CSV file is not formatted exception
     * @throws FileNotFoundException if the CSV file given by fileName cannot be found
     */
    public CSVParser(String fileName) throws InvalidFileFormatException, FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        int line  = 0;      // keeps track of scanned line
        int updateRow = 0;      //keeps track of next empty row in countryNames and dataTable

        while (sc.hasNext()) {
            String newLine = formatLine(sc.nextLine());     //puts spaces behind empty entries or a series of commas
            String[] tokens = newLine.split(",");

            if (tokens.length == 0)
                throw new InvalidFileFormatException(fileName, "The CSV file is not formatted correctly.");

            if (line < 3) {
                checkHeaderFormat(tokens, fileName, line);      //checks that the first three lines of csv file is formatted correctly
            }
            else if (line == 3){
                numCountries = getNumCountries(tokens, fileName);
                countryNames = new String[numCountries];
            } else if (line == 4){
                yearLabels = new int[tokens.length-1];
                updateYearLabels(tokens, fileName);
                numYears = yearLabels.length;
                dataTable = new double[numCountries][numYears];
            } else {
                updateArrays(tokens, fileName, updateRow);
                updateRow ++;
            }
            line++;
        }
    }


    /**
     * Formats each line of CSV file to be effectively split into individual tokens
     * @param line String variable of one line of CSV file
     * @return String concatenation of formatted line of CSV file
     */
    private String formatLine(String line) {
        while (true){
            if (line.charAt(0) == '\"') {
                int indexComma = line.indexOf(',');
                int indexQuotes = line.substring(1).indexOf('\"');
                line = line.substring(1, indexComma) + "//" + line.substring(indexComma+1, indexQuotes+1) + line.substring(indexQuotes + 2);        //replaces comma with special character to be replaced later
            } else if(line.contains(",,")){
                int indexCommas = line.indexOf(",,");
                line = line.substring(0, indexCommas+1) + " " + line.substring(indexCommas+1);
            } else if (line.charAt(line.length()-1) == ','){
                return line + " ";
            } else {
                return line;
            }
        }
    }


    /**
     * Checks the first three lines of CSV file to follow format requirements
     * @param tokens String[] of each input separated by commas in CSV file
     * @param fileName String name of CSV file
     * @param line int variable of scanned line in CSV file
     * @throws InvalidFileFormatException if CSV file does not contain expected Strings in first 3 lines
     */
    private void checkHeaderFormat(String[] tokens, String fileName, int line) throws InvalidFileFormatException {
        if (tokens.length < 2){
            throw new InvalidFileFormatException(fileName, "The CSV file is not formatted correctly.");
        }

        if (line == 0 && tokens[0].equals("Data Source") && tokens[1].equals("World Development Indicators")) {
            return;
        } else if (line == 1 && tokens[0].equals("Indicator")){
            if (tokens[1].equals("School Enrollment In Primary (% net)")){
                indicatorType = IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
            } else if (tokens[1].equals("School Enrollment In Secondary (% net)")){
                indicatorType = IndicatorType.SCHOOL_ENROLLMENT_SECONDARY;
            } else if (tokens[1].equals("GDP per capita (current US$)")){
                indicatorType = IndicatorType.GDP_PER_CAPITA;
            } else {
                throw new InvalidFileFormatException(fileName, "The CSV file is not formatted correctly.");
            }
        } else if (line == 2 && tokens[0].equals("Last Updated Date")
                && ((tokens[1].indexOf("/") > 0) && (tokens[1].substring(tokens[1].indexOf("/")+1).indexOf("/") > 0))) {
            return;
        } else {
            throw new InvalidFileFormatException(fileName, "The CSV file is not formatted correctly.");
        }
    }


    /**
     * Checks the fourth line of CSV file to get number of countries
     * @param tokens String[] of each input separated by commas in CSV file
     * @param fileName String name of CSV file
     * @return int variable of number of countries
     * @throws InvalidFileFormatException if CSV file does not contain expected values in 4th line
     */
    private int getNumCountries(String[] tokens, String fileName) throws InvalidFileFormatException {
        if (tokens.length < 2){
            throw new InvalidFileFormatException(fileName, "The CSV file is not formatted correctly.");
        }

        int numCountries;
        if (tokens[0].equals("Number of Countries")){
            try {
                numCountries = Integer.parseInt(tokens[1]);
            } catch (NumberFormatException e){
                throw new InvalidFileFormatException(fileName, "The CSV file does not specify the number of Countries.");
            }
        } else {
            throw new InvalidFileFormatException(fileName, "The CSV file does not contain \"Number of Countries\" in line 4");
        }

        return numCountries;
    }


    /**
     * Checks the remaining line of CSV file to for country name and corresponding data points
     * @param tokens String[] of each input separated by commas in CSV file
     * @param fileName String name of CSV file
     * @throws InvalidFileFormatException if CSV file does not contain expected values in 4th line
     */
    private void updateYearLabels(String[] tokens, String fileName) throws InvalidFileFormatException {
        if (tokens.length<0){
            throw new InvalidFileFormatException(fileName, "The CSV file is not formatted correctly.");
        }

        for (int i=0; i<yearLabels.length; i++){
            try {
                yearLabels[i] = Integer.parseInt(tokens[i+1]);
            } catch (NumberFormatException e){}

        }
    }


    /**
     * Checks the remaining line of CSV file to for country name and corresponding data points
     * @param tokens String[] of each input separated by commas in CSV file
     * @param fileName String name of CSV file
     * @param updateRow int variable of next empty row in countryNames and dataTable
     * @throws InvalidFileFormatException if CSV file does not contain expected values in 4th line
     */
    private void updateArrays(String[] tokens, String fileName, int updateRow) throws InvalidFileFormatException {
        if (tokens.length < 0){
            throw new InvalidFileFormatException(fileName, "The CSV file is not formatted correctly.");
        }

        if (tokens[0].contains("//")){
            countryNames[updateRow] = tokens[0].substring(0, tokens[0].indexOf("//")) + "," + tokens[0].substring(tokens[0].indexOf("//")+2);
        } else {
            countryNames[updateRow] = tokens[0];
        }

        for (int i=0; i<dataTable[updateRow].length; i++){
            try {
                dataTable[updateRow][i] = Double.parseDouble(tokens[i+1]);
            } catch (NumberFormatException e){}
              catch (IndexOutOfBoundsException e) {}//in case there is String or empty entry at tokens[i]
        }
    }


    /**
     * accessor method for user to get number of years
     * @return int variable of number of years
     */
    public int getNumberOfYears() {
        return numYears;
    }


    /**
     * accessor method for user to get String[] of country names
     * @return String[] variable of country names
     */
    public String[] getCountryNames() {
        return countryNames;
    }


    /**
     * accessor method for user to get int[] of year labels
     * @return int[] variable of year labels
     */
    public int[] getYearLabels() {
        return yearLabels;
    }


    /**
     * accessor method for user to get 2-D array of data
     * @return double[][] variable of country data
     */
    public double[][] getParsedTable() {
        return dataTable;
    }

    /**
     * accessor method for user to get indicator type of data
     * @return IndicatorType enum indicatorType
     */
    public IndicatorType getIndicatorType() {
        return indicatorType;
    }
}
