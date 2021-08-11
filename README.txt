// TODO: For every project create a README file at the top-level directory of 
src/csv/CSVParser.java
    -Contains Constructor for Creating new CSVParser objects and initializing an array of countryNames,yearLabels, and a 3d array of type Indicator
    -Gets input a FileName which it reads from using scanner
          - Throws FileNotFoundException and InvalidFileFormatException
          - Checks different exceptions in the input of file and handles them accordingly
    -Contains a fillArrays method which fills in the CountryNames Array with the proper value and fills in the dataTable with values for the particular year.
    -Contains a fillYearLabels method which fills in the yearLabel Array with the proper values.
    -Contains a fixCommas method which places spaces behind each comma so that they can be parsed into distinct tokens
    -Contains accessor methods to retrieve the private variables including the Indicatortype and parsed table

src/csv/InvalidFileFormatException.java
    -Is a template for creating an InvalidFileFormatException
    -Contains a constructor which passes in an output message and a fileName
    -Has an accessor method to retrieve the outPut message

src/model/Indicator.java
    -Is a template for Indicator classes but is solely abstract
    -Has Private variable year
    -Contains constructor given an int
    -Contains accessor methods to retrieve and alter private variables

src/model/GDPIndicator.java
    -Is a template for GDPIndicator classes and inherits from Indicator
    -Has two different constructors given either an int , or an int and a double
    - Contains accessor methods to retrieve and alter private variables

src/model/IndicatorType.java
   -Defines three different Enums being GDP_PER_CAPITA, SCHOOL_ENROLLMENT_PRIMARY, and SCHOOL_ENROLLMENT_SECONDARY

src/model/SchoolEnrollmentIndicator.java
    -Is a template for SchoolEnrollmentIndicator classes and inherits from Indicator
    - Has Private variables net primary and netSecondary
    - Has two different constructors given either an int , or an int and a double
    - Contains accessor methods to retrieve and alter private variables
    -Contains a toString which prints out the Indicator

src/model/Country.java
    - Has two different constructors given either an int , or an int and a String
    -Has a private String name, and a private array of Indicators
    - Has a method to over ride the .equals comparing only the objects names
    - Contains a getEnrollmentInCountryForPeriod() method which returns an array of Indicators for a given country only providing the values between a specified range of years passed into the constructor and checks if the range is correct, and throws apropriate exceptions.
    - Contains accessor methods to retrieve and alter private variables
    -Contains a toString which prints out the data
src/model/LinkedList.java
    -Constructor to create a Linked List Object with no parameters
    - Method to Add generic object Data to list
    -Contains method to check if the object's data matches the data of an object in the list
    -getIndex method returns data at one index
    -insertAtIndex method inserts country object at an index
    -iterator constructor returns ListIterator
    -Nested Node Class with accessor methods to get different nodes in the list
    - Contains an unsupported remove() method which throws an UnsupportedOperationsException()
    - Next() method gets the next general data object from the node
src/csv/CountryList.java
    -Is the template for creating a CountryList object
    - Contains private variables head and int size
    - Has method add with parameter country that adds the country to the start of the list
    - Has Contains method which returns the country object if it matches the name of the country that is passed in
    - Contains accessor methods to retrieve and alter private variables
    - Has getIndex method which returns an object at a certain index
    - Contains a toString which prints out the data


src/csv/TestCountry.java
    -Contains a main method to test out the different methods in the Country object
    -Tests edge cases and ensures that all of the indicators work for the files and for all of the methods

src/csv/TestCountryList.java
    -Contains a main method to test out the different methods in the CountryList object
    - Individually calls each method for a certain fileType to make sure that they work
src/csv/TestGenericList.java
    -Contains a main method to test out the different methods in the Linked List object that was made generic
    - Individually calls each method for a certain fileType to make sure that they work
src/csv/TestIndicators.java
    -Contains a main method to test out the different methods in the Indicator object
    - Individually calls each method for a certain fileType to make sure that they work



src/view/ChartGraph.java
    -Instantiates an JavaFX application which creates a model of the data. Uses the model to instantiate an object of type  javafx.scene.chart.LineChart
    -via the GraphView class. Then sets up the scene with basic modification to the stage.
    -Start method which sets the stage for the graph and holds a GraphView Object
    -Has a main method
src/view/CountrySelector.java
    -Has a constructor which is passed in a list of countries
    -Sets the linked list of countries to a random selection of all the countries
    -Accessor method to get Linked List of selectedCountries
src/view/GraphView.java
    -Contains a constructor for a GraphView
        - Sets the number axes
    -Contains a constructor for a series for a single country
    -Contains a void update() which add the data from the series to the graph