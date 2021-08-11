package model;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

    private Node head;
    private int size;
    /**
     * Constructor to create a LinkedList
     * Sets the head of the list
     * Sets the size of the list for accessibility
     */
    public LinkedList(){
        head = null;
        size = 0;
    }
    /**
     * Generic method to add data
     * Checks the data to make sure it fits the data
     */
    public void add(T data){
        size++;
        if (head == null){
            head = new Node(data);
        } else {
            Node<T> runner = head;
            while (runner.getNext() != null){
                runner = runner.getNext();
            }
            runner.setNext(new Node(data));
        }

    }
    /**
     * Takes in generic T object
     * Generic method to check if the data is contained in the lists
     * Checks the data to make sure it fits the data
     * Returns the data object if the objects data matches the passed in data
     */
    public T contains(T object){
        Node<T> runner = head;
        while(runner != null) {
            if (runner.getData().equals(object)) {
                return runner.getData();
            }
            runner = runner.getNext();
        }

        return null;
    }
    /**
     * Gets index as parameter
     * Generic method to get data at index
     * Checks the data to make sure it fits the data
     */
    public T getIndex(int requestedIndex) throws IndexOutOfBoundsException{
        if (requestedIndex < 0){
            throw new IndexOutOfBoundsException("Must be a positive index");
        } else if (requestedIndex > size){
            throw new IndexOutOfBoundsException("The requested index is out of bounds.");
        }

        Node<T> runner = head;

        for (int i=0; i<requestedIndex; i++){
            if (runner.getNext() != null){
                runner = runner.getNext();
            } else {
                break;
            }
        }

        return runner.getData();
    }
    /**
     * Generic method to add data at a certain index
     * Parameters take in a T data to add, and an int index
     * Checks the data to make sure it fits the data
     * Adds the data at the specified index
     */
    public void insertAtIndex(T data, int index) throws IndexOutOfBoundsException {
        if (index<0)
            throw new IndexOutOfBoundsException();
        if (index>size)
            index=size;

        size++;
        Node<T> add = new Node<T>(data);
        if (index == 0) {
            add.next = head;
            head = add;
            return;
        }


        Node runner = head;
        int counter = 0;
        while (counter < index-1) {
            runner = runner.next;
            counter++;
        }

        add.next = runner.next;
        runner.next = add;
    }
    /**
     *Creates the Iterator
     */
    @Override
    public ListIterator iterator(){
        return new ListIterator();
    }
    /**
     *Accessor method to get size
     */
    public int size(){
        return size;
    }
    /**
     * Returns string representation of data
     */
    public String toString(){
        String listString = "";

        Node<T> runner = head;

        while (runner.getNext() != null) {
            listString = listString + runner.toString() + ", ";
            runner = runner.getNext();
        }
        listString = listString + runner.toString();
        return listString;
    }
    /**
     * Nested generic template to create a Node
     */
    public class Node<T>{

        private T data;
        private Node next;

        /**
         * Constructor for Node
         * Initialized the data, and takes in a T newData
         * Initializes node next
         */
        public Node(T newData){
            this.data = newData;
            this.next = null;
        }
        /**
         * Constructor for Node
         * Initialized the data, and takes in a T newData
         * Initializes node next
         */
        public Node(T newData, Node next){
            this.data = newData;
            this.next = next;
        }
        /**
         * Accessor method to get Data T
         */
        public T getData(){
            return data;
        }
        /**
         * Accessor method to get Next Node
         */
        public Node getNext(){
            return next;
        }
        /**
         * Setter method, takes in a Node and sets it to Node next
         */
        public void setNext(Node next){
            this.next = next;
        }
        /**
         * Returns string representation of data
         */
        public String toString(){
            return this.data.toString();
        }
        /**
         * Over-rides the equals feature for the node object
         * Tests if the data is the same
         * Returns boolean: True if equals, False otherwise
         */
        public boolean equals(Node test){
            return this.data.equals(test.getData());
        }

    }
    /**
     * Nested generic List Iterator
     */
    public class ListIterator implements Iterator{

        private Node<T> current;

        public ListIterator(){
            current = head;
        }
        /**
         * Boolean has next
         * Checks if the node has next
         * Returns
         */
        @Override
        public boolean hasNext() {
            if (current.next != null)
                return true;

            return false;
        }
        /**
         * Returns data of next node
         * NO parameters
         */
        @Override
        public T next() {
            T data = current.getData();
            current = current.next;
            return data;
        }
        /**
         * Method to remove a node
         * Throws exception because this function is not supported
         */
        public void remove() throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
    }

}