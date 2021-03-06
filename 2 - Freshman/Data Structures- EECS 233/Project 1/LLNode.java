/**
 * This class represents a single node of a linked list.
 * @author Jacob Alspaw
 */
public class LLNode<T> {
  
  /** 
   * The value stored in the node.
   */
  private T value;
  
  /** 
   * The next node of the list.
   */
  private LLNode<T> next;
  
  /**
   * This constructor method initializes the fields.
   * @param element The element to store in the node.
   * @param next A reference to the next node of the list.
   */
  public LLNode(T value, LLNode<T> next) {
    //setting the value of the node
    this.value = value;
    //setting the next node
    this.next = next;
  }
  
  /**
   * This helper method returns the element stored in the node.
   * @return T The value stored in the node.
   */
  public T getValue() {
    return value;
  }
  
  /**
   * This helper method returns the next node of the list.
   * @return LLNode<T> The next node of the list.
   */
  public LLNode<T> getNext() {
    return next;
  }
  
  /**
   * This helper method changes the node that comes after this node in the list.
   * @param next The node that should come after this node in the list. It can be null.
   */
  public void setNext(LLNode<T> next) {
    //setting the next null
    this.next = next;
  }
  
  
  /**
   * This helper method removes the node that occurs immediately after this node in the list.
   */
  public void deleteNext() {
    //checks to see if the next node is null
    if(this.getNext() != null){
      //setting the next node to the node after the next node
      this.setNext(this.getNext().getNext());
    }
  }
}