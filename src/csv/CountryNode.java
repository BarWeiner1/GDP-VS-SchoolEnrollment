package csv;

import model.Country;

/**
 * Stores Country object as data and reference to next CountryNode
 * @author Foothill College, Juntao Ren
 */
public class CountryNode {

    private Country data;
    private CountryNode next;

    /**
     * Constructor method to initialize Country data
     * @param data to be initialized as instance variable
     */
    public CountryNode(Country data){
        this.data = data;
        this.next= null;
    }

    /**
     * Constructor method to initialize data and next
     * @param data to be initialized as instance variable
     * @param nextNode to be initialized as instance variable
     */
    public CountryNode(Country data, CountryNode nextNode){
        this.data = data;
        this.next = nextNode;
    }

    /**
     * Accessor method to return Node data
     * @return Country data
     */
    public Country getData(){
        return data;
    }

    /**
     * Accessor method to return next Node
     * @return CountryNode next
     */
    public CountryNode getNext(){
        return next;
    }

    /**
     * Mutator method to set CountryNode next
     * @param set to be set to CountryNode next
     */
    public void setNext(CountryNode set){
        this.next = set;
    }

    /**
     * Concatenates String representation of data instance variable
     * @return String representation of Country data
     */
    public String toString(){
        return data.toString();
    }

    /**
     * To check whether node equal to requested Country
     * @param check request Country to be checked
     * @return boolean if equal
     */
    public boolean equals(Country check) {
        return data.equals(check);
    }
}
