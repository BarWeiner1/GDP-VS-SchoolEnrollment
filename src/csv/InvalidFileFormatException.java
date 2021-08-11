package csv;


/**
 * Extends java.io.IOException and can be thrown to
 * indicate that the format of the file is not valid
 * @author Foothill College, Juntao Ren
 */
public class InvalidFileFormatException extends java.io.IOException {

    String message;
    String fileName;

    /**
     * Initializes instance variables
     * @param nameOfFile   String name of CSV file
     * @param outputMessage     String message to be outputted
     */
    public InvalidFileFormatException(String nameOfFile, String outputMessage){
        fileName = nameOfFile;
        message = outputMessage;
    }


    /**
     * Returns String variable of file name and error message
     * @return String variable of file name and error message
     */
    public String getMessage(){
        return fileName + " has invalid file format exception: \n" + message;
    }

}
