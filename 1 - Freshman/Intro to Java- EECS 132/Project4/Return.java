/**
 * A class that represents the "return" statement in normal java.
 * @author Jacob Alspaw
 */
public class Return implements Statement {
  
  /**
   * "expression" represent the returned expression for the statement.
   */
  private Expression expression;
  
  /**
   * A constructor that initializes the field "expression".
   * @param expression Is type Expression and represents the expression of the statement.
   */
  public Return(Expression expression) {
    this.expression = expression;
  }
  
  /**
   * A method that will update the state using the expresion
   * @param hashState Represents a State variable and the hashTable it contains.
   */
  public void execute(State hashState) {
    hashState.update("return", expression.value(hashState));
  }
  
  /**
   * A method that will return a string with all values involved.
   */
  public String toString() {
    return ("return " + expression.toString() + ";");
  }
  
  /**
   * A method that returns the same string as toString(), but this method will
   * also add in a set of indentations equal to the amount declared by the user.
   * @param indents Is an integer that represents the wanted number of iindentations.
   */
  public String toStringTabbed(int indents) {
    StringBuilder newString = new StringBuilder();    //is the string representation for the Return
    //Adds the declared number of indentations before the toString() part
    for(int i = 0; i < indents; i++) {
      newString.append("\t");
    }
    newString.append("return" + " " + (expression + "") + ";");
    return newString.toString();
  }
}