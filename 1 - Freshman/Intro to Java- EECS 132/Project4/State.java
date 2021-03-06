import java.util.Hashtable;

/**
 * A class that will be part of each variable and will represent their contents
 * so their values and characteristics can be accessile to other parts of the program.
 * @author Jacob Alspaw
 */
public class State {
  
  /**
   * A hastable containing the names and values of variables stored.
   */
  private Hashtable<String, Integer> hashTable = new Hashtable<String, Integer>();
  
  /**
   * Adds a variable to the hashtable and a value of the variable to the hashtable.
   * @param name Is a string representing the variable name.
   * @param value Is an integer representing the value belonging to a certain variable.
   */
  public void update(String name, int value) {
    hashTable.put(name, value);
  }
  
  /**
   * Checks if the variable name is stored as part of the hashTable. If the variable is
   * then the value of the variable is returned.
   * @param name Is a string representing the variable name.
   * @return value Is an integer representation for value of the variable
   */
  public int lookup(String name) {
    if(hashTable.containsKey(name)) {   //if the hash table contains the name, then return the variables value
      return hashTable.get(name);
    }
    else {                             //otherwise, return 0 as the stored value.
      return 0;
    }
  }
}