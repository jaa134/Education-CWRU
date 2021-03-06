import java.util.*;

/**
 * A class that will represent a sequence in the form of a linked list.
 * @author Jacob Alspaw
 */
public class NumLinkedList implements NumList {
  
  /**
   * The first node of the list.
   */
  private LLNode<Double> front;
  
  /**
   * The size of the sequence.
   */
  private int sequenceSize;
  
  /**
   * The constructor method that creates a starting node for the linked list.
   * @param start The list head value.
   */
  public NumLinkedList() {
    //set the sequence size to 0
    sequenceSize = 0;
  }
  
  /**
   * A necessary method that will return an iterator to transverse a linked list.
   * @return Iterator<Double> The requested iterator.
   */
  public Iterator<Double> iterator() {
    return new Iterator<Double>(){
      /**
       * This node stores the current position for the iterator.
       */
      private LLNode<Double> nodeptr = front;
      
      /**
       * A method to test if the linked list has a value in the next consecutive location;
       * @return boolean Does the sequence have a next value?
       */
      public boolean hasNext() {
        return (nodeptr != null);
      }
      
      /**
       * A method to return the value of the current location and then increment the current position.
       * @return Double The value of the current location.
       */
      public Double next() {
        double node = nodeptr.getValue();
        nodeptr = nodeptr.getNext();
        return node;
      }
      
      /** 
       * This method will not and should not be used but still must be created.
       */
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
  
  /**
   * This helper method returns the first node of the list.
   * @return front The first node of the list.
   */
  public LLNode<Double> getFront() {
    return front;
  }

  /**
   * This helper method changes the first node to the newly designated node.
   * @param first  the first node of the new linked list
   */
  public void setFront(LLNode<Double> front) {
    //setting the front
    this.front = front;
  }

  /**
   * This helper method inserts an element to the front of the linked list.
   * @param element The value of the inserted element.
   */
  public void addToFront(double element) {
    //setting the front to a new node with the value inputted
    setFront(new LLNode<Double>(element, getFront()));
  }
  
  /**
   * This method returns the length of the linked list. The worst case running time is one operation.
   * In terms of big O, it's O(1).
   * @return size The number of nodes in the list.
   */
  public int size() {
    return sequenceSize;
  }
  
  /**
   * This method inserts a node with the designated value into the list in the designated location.
   * The worst case running time is one cycle through the sequence. In terms of big O, it's O(n).
   * @param int The location of the designated item.
   * @param double The value of the added item.
   */
  public void insert(int i, double value){
    //if the user enters a number less than one, produce an error.
    if(i < 0) {
      throw new IllegalArgumentException("Numbers less than 0 are not permitted!");
    }
    //if the list is zero elements long, or the user wishes to input into the first position
    if(sequenceSize == 0 || i == 0) {
      //add the element to the front of the list
      this.addToFront(value);
    }
    else {
      //spot to mark current positon in the list
      int spot = 1;
      //a pointer to transverse the list
      LLNode<Double> nodeptr = getFront();
      //loop that will run until either inserted is true or the next node is not null
      while(spot != i && spot < sequenceSize) {
        //increment pointer 
        nodeptr = nodeptr.getNext();
        //increment the spot
        spot++;
      }
      //set the node to be the value
      nodeptr.setNext(new LLNode<Double>(value, nodeptr.getNext()));
    }
    //increasing the sequence size 1
    sequenceSize = sequenceSize + 1;
  }
  
  /**
   * This method deletes the node of the list at a specified position in the list. The worst case running 
   * time is one cycle through the sequence. In terms of big O, it's O(n).
   * @param element The list's position number to remove.
   */  
  public void remove(int i) {
    //if the user enters a number less than one, produce an error.
    if(i < 0) {
      throw new IllegalArgumentException("Numbers less than 0 are not permitted!");
    }
    //marks the spot in the list the method currently lies
    int spot = 0;
    //if the user input is within the sequence size
    if(i < sequenceSize){
      //make a pointer to transverse the list
      LLNode<Double> nodeptr = getFront();
      //if the user wishes to remove the first positon in the list
      if(i == 0){
        //set the front to the next node
        setFront(nodeptr.getNext());
      }
      else{
        //boolean to represent if an item has been removed. will improve efficiency in most cases
        boolean removed = false;
        //while the element in the list hasnt been removed
        while(removed == false && nodeptr.getNext() != null) {
          //increment spot
          spot++;
          //if the spot has reached the ser input
          if(spot == i) {
            //the element will be removed
            removed = true;
            //deleting the next node in the list
            nodeptr.deleteNext();
          }
          else{
            //incrementing pointer
            nodeptr = nodeptr.getNext();
          }
        }
      }
    }
    else{
    }
    //decreasing the sequence size by one
    sequenceSize = sequenceSize - 1;
  }
  
  /**
   * This method will lookup the value of a location in the list. The worst case running time is
   * one cycle through the sequence. In terms of big O, it's O(n).
   * @param i The designated position in the list.
   * @return double The value of the requested position.
   */
  public double lookup(int i) {
    //produces an error when the user has gone outside the array size
    if(i < 0 || i >= sequenceSize) {
      throw new IllegalArgumentException("Postion outside sequence size!");
    }
    //marks the spot the pointer is at
    int spot = 0;
    //makes a new pointer to transverse the list
    LLNode<Double> nodeptr = getFront();
    //loop to fnd the right spot to lookup in the list. Stops before pointer is in the correct position
    while(spot != i) {
      //incrementing pointer
      nodeptr = nodeptr.getNext();
      //incrementing spot
      spot++;
    }
    return nodeptr.getValue();
  }
}