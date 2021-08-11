package csv;

import model.Country;
import model.LinkedList;

/**
 * Manages singly linked list of CountryNode objects
 * @author Foothill College, Juntao Ren
 */
public class CountryList extends LinkedList {

    private CountryNode head;
    private int size;

    /**
     * Constructor method to initialize instance variables
     */
    public CountryList(){
        super();
    }

    /**
     * Method to add new object to end of list
     * @param countryToAdd to be added to end of list
     */
    public void add(Country countryToAdd) {
        if (head == null){
            head = new CountryNode(countryToAdd);
            size++;
        } else {
            CountryNode runner = head;
            while (runner.getNext() != null){
                runner = runner.getNext();
            }
            runner.setNext(new CountryNode(countryToAdd));
            size++;
        }
    }

    /**
     * Method to check whether list contains data of interest
     * @param check to be checked in list
     * @return boolean of whether checked data is in instance
     */
    public Country contains(Country check){
        CountryNode runner = head;
        while(runner != null) {
            if (runner.getData().equals(check)) {
                return runner.getData();
            }
            runner = runner.getNext();
        }

        return null;
    }

    /**
     * Method that returns data at requested index
     * @param requestedIndex index of requested data
     * @return Country of requested index
     * @throws IndexOutOfBoundsException if index out of range
     */
    public Country getIndex(int requestedIndex) throws IndexOutOfBoundsException{
        if (requestedIndex < 0){
            throw new IndexOutOfBoundsException("Must be a positive index");
        } else if (requestedIndex >= size){
            throw new IndexOutOfBoundsException("The requested index is out of bounds.");
        }

        CountryNode runner = head;
        int counter = 0;
        while (counter < requestedIndex) {
            runner = runner.getNext();
            counter++;
        }

        return runner.getData();
    }

    /**
     * Accessor method to get length of list
     * @return int of linked list size
     */
    public int size(){
        return size;
    }

    /**
     * Concatenates String representation of list
     * @return String representation of list
     */
    public String toString(){
        String listString = "";

        CountryNode runner = head;

        while (runner.getNext() != null) {
            listString = listString + runner.getData().getName() + ", ";
            runner = runner.getNext();
        }
        listString = listString + runner.getData().getName();
        return listString;
    }

}
