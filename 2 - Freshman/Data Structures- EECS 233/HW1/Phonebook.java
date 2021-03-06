import java.util.*;

/*
 * This class will represent a phonebook that can easily add, find, and delete contacts.
 */
public class Phonebook <AnyType>{
  
  /*
   * This field can hold all identifying factors of a certain person.
   */
  private ArrayList<AnyType> identification;
  
  /*
   * This field can hold phone numbers in the form of Integer objects.
   */
  private ArrayList<String> numbers;
  
  /*
   * This constructor initializes the arrayLists that store contact information.
   */
  public Phonebook() {
    numbers = new ArrayList<String>();
    identification = new ArrayList<AnyType>();
  }
  
  /*
   * This method will find a person in the phonebook and return the appropriate value.
   * @param personsID The identifying factor for a certain person.
   * @return Integer The phone number of the person.
   */
  public String findPerson(AnyType personsID) {
    int index = identification.indexOf(personsID);
    if(index != 1) {
      return numbers.get(index);
    }
    else {
      return "No Listing";
    }
  }
  
  /*
   * This method will add a person into the phonebook with the inputted identyfying factors.
   * @param personsID The identification of a person.
   * @param personsNumber The social security number of a person.
   */
  public void addPerson(AnyType personsID, String personsNumber) {
    identification.add(personsID);
    numbers.add(personsNumber);
  }
  
  /*
   * This method will delete a person from the phonebook using the inputted indentyfying factors.
   * @param personsID The identification of a person.
   */
  public void deletePerson(AnyType personsID) {
    int index = identification.indexOf(personsID);
    if (index != 1) {
      identification.remove(index);
      numbers.remove(index);
    }
    else {
      System.out.println("This person does not exist.");
    }
  }
  
  public static void main(String[] args) {
    Phonebook<String> people = new Phonebook<String>();
    Phonebook<Integer> numbers = new Phonebook<Integer>();
    people.addPerson("Jacob", "6366997344");
    numbers.addPerson(345634567, "6366997344");
    System.out.println(people.findPerson("Jacob"));
    System.out.println(numbers.findPerson(345634567));
  }
}
    
    