/**
 * This class represents an integer number for a variable.
 * @author Jacob Alspaw
 */ 
public class Number implements Expression {
  
  /**
   * "variableValue" reperesents the value of this class object.
   */
  private int variableValue;
  
  /**
   * This method connects the variable's value to the Expression class.
   * @param variableValue Is an integer representing the value of the variable.
   */ 
  public Number(int variableValue) {
    this.variableValue = variableValue;
  }
  
  /**
   * A method that will return the value of a variable in a hashtable as an integer.
   * @param  hashState Represents a State variable and the hashTable it contains.
   */ 
  public int value(State hashState) {
    return variableValue;
  }
  
  /**
   * A method that will return the integer value associated with the variable as a string.
   */ 
  public String toString() {
    return (variableValue + "");
  }  
}