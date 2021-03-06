import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * An improved version of the popular game, "2048." Improvements include a reset button, a game title, motivational
 * support and diagonal movement.
 * @author Jacob Alspaw
 */

public class SlideGame1 extends JFrame implements ActionListener{
  
  /** The game. */
  private JFrame game;
  
  /** The grid of buttons. */
  private JPanel board;
  
  /** The buttons on the grid. */
  private JButton[][] buttonArray;
  
  /** The reset button. */
  private JButton resetButton = new JButton("Reset Game");
  
  /** The values on the current game. */
  private int[][] intArray;
  
  /** Did a the current row or column change? */
  private static boolean change = false;
  
  /** Did any row or column change? */
  private boolean totalChange = false;
  
  /** Was a new tile added? */
  private boolean added = false;
  
  /** Motivational text in the top of the screen. */
  private JTextArea textArea;
  
  /** Has the player won by acheiving a 2048 title? */
  private boolean won = false;
  
  /** Constructor for the SlideGame. This constructor will set up the game
    * to the designated amount of rows and columns and initialize the game.
    * @param rows the number rows in the game.
    * @param columns the number of columns in the game.
    */
  public SlideGame1 (int rows, int columns) {
    
    buttonArray = new JButton[rows][columns];
    intArray = new int[rows][columns];
    
    this.game = new JFrame();                                                 //creates the window
    game.setSize(columns * 150, rows * 150);                                  //sets the size of the game
    
    this.board = new JPanel(new GridLayout(rows, columns, 6, 6));             //makes the grid
    board.setBackground(Color.BLACK);

    this.board.add(resetButton);                                              //adds reset button
    resetButton.addActionListener(this);
    
    this.textArea = new JTextArea("START!");                                  //sets motivational text
    textArea.setFont(new Font("Arial", Font.BOLD, 30));                       //changes text style on motivational quotes
    
    game.getContentPane().add(textArea, "North");                             //lays out board on the window
    game.getContentPane().add(board, "Center");                               //lays out board on the window
    game.getContentPane().add(resetButton, "South");                          //lays out board on the window
    
    //nested for loops to assign JButtons to the board
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        buttonArray[i][j] = new JButton("");
        buttonArray[i][j].setFont(new Font("Arial", Font.BOLD, 35));
        buttonArray[i][j].addActionListener(this);
        this.board.add(buttonArray[i][j]);
      }
    }
    addNewTile();                                                             //adds a new tile to the board
    addNewTile();                                                             //adds a second new tile to the board
    addValueToJButtons();                                                     //updates grid
    game.setTitle("2048");                                                    //sets the title of the JFrame
    game.setVisible(true);                                                    //makes the game visible
  }
  
  /** Resets the game back to the original starting point of two tiles. Is
    * used when the reset button is activated. No parameters or return values
    * are used.
    */
  public void resetGame() {
    
    //sets all elements in the int[][] array to "0"
    for(int i = 0; i < intArray.length; i++) {
      for(int j = 0; j < intArray[0].length; j++) {
        intArray[i][j] = 0;
      }
    }
    addNewTile();                             //adds new tile to grid
    addNewTile();                             //adds new tile to grid
    addValueToJButtons();                     //updates grid
    won = false;                              //you have not won
  }                   
      
  /** This method rewrites the values on the JButton array. The values on the board 
    * will change as a result. Buttons with "0' as a value are changed to have no 
    * text in the grid. Different colors are assigned to buttons of different values.
    * No parameters or return values are used.
    */
  public void addValueToJButtons() {
    
    //runs through all the buttons to assign individual colors and values as labels
    for(int i = 0; i < intArray.length; i++) {
      for(int j = 0; j < intArray[0].length; j++) {
        buttonArray[i][j].setText(intArray[i][j] + "");
        if(buttonArray[i][j].getText().equals("0")) {
          buttonArray[i][j].setText("");                                     //0 tiles are set to blank
          buttonArray[i][j].setBackground(Color.decode("#FFFF99"));
        }
        if(buttonArray[i][j].getText().equals("1")) {
          buttonArray[i][j].setBackground(Color.WHITE);
        }
        if(buttonArray[i][j].getText().equals("2")) {
          buttonArray[i][j].setBackground(Color.LIGHT_GRAY);
        }
        if(buttonArray[i][j].getText().equals("4")) {
          buttonArray[i][j].setBackground(Color.ORANGE);
        }
        if(buttonArray[i][j].getText().equals("8")) {
          buttonArray[i][j].setBackground(Color.CYAN);
        }
        if(buttonArray[i][j].getText().equals("16")) {
          buttonArray[i][j].setBackground(Color.YELLOW);
        }
        if(buttonArray[i][j].getText().equals("32")) {
          buttonArray[i][j].setBackground(Color.BLUE);
        }
        if(buttonArray[i][j].getText().equals("64")) {
          buttonArray[i][j].setBackground(Color.decode("#669999"));
        }
        if(buttonArray[i][j].getText().equals("128")) {
          buttonArray[i][j].setBackground(Color.PINK);
        }
        if(buttonArray[i][j].getText().equals("256")) {
          buttonArray[i][j].setBackground(Color.GREEN);
        }
        if(buttonArray[i][j].getText().equals("512")) {
          buttonArray[i][j].setBackground(Color.RED);
        }
        if(buttonArray[i][j].getText().equals("1024")) {
          buttonArray[i][j].setBackground(Color.MAGENTA);
        }
        if(buttonArray[i][j].getText().equals("2048")) {
          buttonArray[i][j].setBackground(Color.decode("#FFCCFF"));
          won = true;                                                      //if 2048 is reached, you have won
          textArea.setText("MUCH CONGRATULATION! MANY WIN!");
        }
      }
    }
  }
  
  /** This method adds a new value to the int array at a randomly chosen location.
    * The location is the place of a current zero value and the tile added is always
    * of value "1". No parameters or return values are used.
    */
  public void addNewTile() {
    
    int count = 0;                                           //counts how many "0" values have currently been seen
    int spot = 0;                                            //designates the spot to place to put the new tile
    int blankTiles = 0;                                      //number of blank tiles currently on the board
    
    //counts the number of blank tiles
    for(int i = 0; i < intArray.length; i++) {
      for(int j = 0; j < intArray[0].length; j++) {
        if(intArray[i][j] == 0) {
          blankTiles++;
        }
      }
    }
    
    spot = (int)(Math.random() * blankTiles + 1);            //calculates the spot where a new tile will appear
    
    //places the value "1" in the occurence where "spot" is equal to the random value
    for(int i = 0; i < intArray.length; i++) {
      for(int j = 0; j < intArray[0].length; j++) {
        if(intArray[i][j] == 0) {
          count++;
          if(count == spot) {
            intArray[i][j] = 1;
            buttonArray[i][j].setText("1");
            buttonArray[i][j].setBackground(Color.RED);
          }
        }
      }
    }
  }
  
  /** This method listens for actions performed. Specifically, this method
    * will act on the press of the reset button or the press of designated
    * buttons on the grid. The press of the reset button completely resets
    * the game. The press of the grid buttons will manipulate the array in
    * the correct direction using the sliding methods. The motivational text
    * is changed and a new tile is added. If the game has been won, then the
    * buttons will not work. There is no return value.
    * @param e is the event performed.
    */
  public void actionPerformed(ActionEvent e) {
    
    //checks all the buttons for a matching action
    for(int i = 0; i < intArray.length; i++) {
      for(int j = 0; j < intArray[0].length; j++) {        
        if(resetButton == (JButton) e.getSource()) {                             //if the reset button is hit, reset game
          resetGame();
          this.textArea.setText("START!");                                       //changes motivational text
        }
        if(buttonArray[i][j] == (JButton) e.getSource() && won == false) {       //if the top row is clicked, excluding the corners, slide all rows left
          if(i > 0 && i < intArray.length - 1 && j > 0 && j < intArray[0].length - 1) {
            this.textArea.setText("NOT THAT BUTTON!");
          }
          if(i == 0 && j < intArray.length - 1 && j > 0) {
            for(int k = 0; k < intArray[0].length; k++) {
              slideUp(intArray, k);
              if(change == true) {
                this.totalChange = change;
              }
            }
            this.textArea.setText("MUCH WOW!");                                 //changes motivational text
          }          
          if(i == intArray.length - 1 && j < intArray[0].length - 1 && j > 0 && won == false) {       //if the bottom row is clicked, exclusing corners, slide all columns down
            for(int k = 0; k < intArray[0].length; k++) {
              slideDown(intArray, k);
              if(change == true) {
                this.totalChange = change;
              }
            }
            this.textArea.setText("SO AMAZE!");                                 //changes motivational text
          }          
          if(j == 0 && i < intArray.length - 1 && i > 0 && won == false) {                 //if the left column is clicked, exclusing corners, slide all columns down   
            for(int k = 0; k < intArray.length; k++) {
              slideLeft(intArray, k);
              if(change == true) {
                this.totalChange = change;
              }
            }
            this.textArea.setText("GREAT JOBE!");                               //changes motivational text
          }          
          if(j == intArray[0].length - 1 && i < intArray.length - 1 && i > 0 && won == false) {       //if the right column is clicked, exclusing corners, slide all columns down
            for(int k = 0; k < intArray.length; k++) {
              slideRight(intArray, k);
              if(change == true) {
                this.totalChange = change;
              }
            }
            this.textArea.setText("MANY POINTS!");                             //changes motivational text
          }      
          if(i == 0 && j == 0 && won == false) {                               //if the top left corner button is clicked, slide up-left
            for(int k = 0; k < intArray[0].length; k++) {
              slideUpLeft(intArray, 0, k);
              if(change == true) {
                  this.totalChange = change;
              }
            }
            for(int l = 1; l < intArray.length; l++) {
              slideUpLeft(intArray, l, 0);
              if(change == true) {
                this.totalChange = change;
              }
            }
            this.textArea.setText("MANY COOL!");                              //changes motivational text
          }
          if(i == intArray.length - 1 && j == intArray[0].length - 1 && won == false) {             //if the bottom right corner button is clicked, slide down-right
            for(int k = 0; k < intArray[0].length; k++) {
              slideDownRight(intArray, 0, k);
              if(change == true) {
                this.totalChange = change;
              }
            }
            for(int l = 1; l < intArray.length; l++) {
              slideDownRight(intArray, l, 0);
              if(change == true) {
                this.totalChange = change;
              }
            }
            this.textArea.setText("VERY PROUD!");                             //changes motivational text
          }
          if(i == 0 && j == intArray[0].length - 1 && won == false) {                    //if the top right corner button is clicked, slide up-right
            for(int k = 0; k < intArray[0].length; k++) {
              slideUpRight(intArray, 0, k);
              if(change == true) {
                this.totalChange = change;
              }
            }
            for(int l = 1; l < intArray.length; l++) {
              slideUpRight(intArray, l, intArray[0].length - 1);
              if(change == true) {
                this.totalChange = change;
              }
            }
            this.textArea.setText("MUCH SMART!");                           //changes motivational text
          }
          if(i == intArray.length - 1 && j == 0 && won == false) {                     //if the bottom left corner button is clicked, slide down-left
            for(int k = 0; k < intArray[0].length; k++) {
              slideDownLeft(intArray, 0, k);
              if(change == true) {
                this.totalChange = change;
              }
            }
            for(int l = 1; l < intArray.length; l++) {
              slideDownLeft(intArray, l, intArray[0].length - 1);
              if(change == true) {
                this.totalChange = change;
              }
            }
            this.textArea.setText("SUCH AWESOME!");                       //changes motivational text
          }
          if(totalChange == true && added == false) {                     //if the values on the board were at all changed, add a new tile
            addNewTile();
            added = true;
          }
          this.totalChange = false;                                      //resets the board to no change has occured
          added = false;                                                 //the new tile has been reset to not added to the board yet
          addValueToJButtons();                                          //updates grid
        }     
      }
    }
  }
  
  /** Tests if the current tile is on the grid.
    * @param array is being tested.
    * @param a is the row of the tile.
    * @param b is the column of the tile.
    * @return boolean
    */
  public static boolean isOnGrid(int[][] array, int a, int b) {
    if(a < array.length && b < array[0].length && a >= 0 && b >= 0) {       //if the row and column are within the board, then the tile is on the grid
      return true;
    }
    else{
      return false;
    }
  }
  
  /** Tests if the tile is the top left or bottom right tile.
    * @param array is being tested.
    * @param a is the row of the tile.
    * @param b is the column of the tile.
    * @return boolean
    */
  public static boolean isCorner(int[][] array, int a, int b) {
    if((a == 0 && b == 0) || (a == array.length - 1 && b == array[0].length - 1)) {    //if the tile is the top left or bottom right corner, then yes
      return true;
    }
    else {
      return false;
    }
  }
  
  /** This method manipulates an array in the desiganted row. The values in the row are moved
    * all the way left of the row as possible. If the next value is equal to the current value, 
    * the two combine into double their previous value. If the next value isn't equal to the
    * current vlue, the value before acts as a wall and stops the sliding value. 
    * @param array stores the values for the game.
    * @param row is the designated row to slide.
    * @return boolean
    */
  public static boolean slideLeft(int[][] array, int row) {

   change = false;                                                             //has not changed
   int spot = 0;                                                               //second incremented value
   
   //loop that goes through a row
   for(int i = spot; i < array[0].length; i++) {
     if(array[row][i] != 0 && i != 0) {                                        //if current spot isnt equal to zero
       if(array[row][i] != array[row][spot] && array[row][spot] != 0) {
         spot += 1;
       }
       if(i != spot) {                                                         //if i isnt spot
         array[row][spot] += array[row][i];                                    //double the value
         array[row][i] = 0;                                                    //current spot is 0
         change = true;                                                        //has changed
       }
     }
   }
   return change;
  }
  
  /** This method manipulates an array in the desiganted row. The values in the row are moved
    * all the way right of the row as possible. If the next value is equal to the current value, 
    * the two combine into double their previous value. If the next value isn't equal to the
    * current vlue, the value before acts as a wall and stops the sliding value. 
    * @param array stores the values for the game.
    * @param row is the designated row to slide.
    * @return boolean
    */
  public static boolean slideRight(int[][] array, int row) {
    
    change = false;                                                         //has not changed
    int spot = array[0].length - 1;                                         //second incremented value
   
    for(int i = spot; i >= 0; i--) {
      if(array[row][i] != 0 && i != array[0].length - 1) {                  //if current spot isnt equal to zero
        if(array[row][i] != array[row][spot] && array[row][spot] != 0) {
          spot -= 1;
        }
        if(i != spot) {                                                     //if i isnt spot
          array[row][spot] += array[row][i];                                //double the value
          array[row][i] = 0;                                                //current spot is 0
          change = true;                                                    //has changed
        }
      }
    }
    return change;
  }
  
  /** This method manipulates an array in the desiganted column. The values in the column are moved
    * all the way up the row as possible. If the next value is equal to the current value, 
    * the two combine into double their previous value. If the next value isn't equal to the
    * current vlue, the value before acts as a wall and stops the sliding value. 
    * @param array stores the values for the game.
    * @param column is the designated column to slide.
    * @return boolean
    */
  public static boolean slideUp(int[][] array, int column) {
    
    change = false;                                                                 //has not changed
    int spot = 0;                                                                   //second incremented value
    
    for(int i = spot; i < array.length; i++) {
      if(array[i][column] != 0 && i != 0) {                                         //if current spot isnt equal to zero
        if(array[i][column] != array[spot][column] && array[spot][column] != 0) {
          spot += 1;
        }
        if(i != spot) {                                                             //if i isnt spot
          array[spot][column] += array[i][column];                                  //double the value
          array[i][column] = 0;                                                     //current spot is 0
          change = true;                                                            //has changed
        }
      }
    }
    return change;
  }
    
  
  /** This method manipulates an array in the desiganted column. The values in the column are moved
    * all the way down the row as possible. If the next value is equal to the current value, 
    * the two combine into double their previous value. If the next value isn't equal to the
    * current vlue, the value before acts as a wall and stops the sliding value. 
    * @param array stores the values for the game.
    * @param column is the designated column to slide.
    * @return boolean 
    */
  public static boolean slideDown(int[][] array, int column) {
    
    change = false;                                                                   //has not changed
    int spot = array.length - 1;                                                      //second incremented value
   
    for(int i = spot; i >= 0; i--) {
      if(array[i][column] != 0 && i != array.length - 1) {                            //if current spot isnt equal to zero
        if(array[i][column] != array[spot][column] && array[spot][column] != 0) {
          spot -= 1;
        }
        if(i != spot) {                                                               //if i isnt spot
          array[spot][column] += array[i][column];                                    //double the value
          array[i][column] = 0;                                                       //current spot is 0
          change = true;                                                              //has changed
        }
      }
    }
    return change;
  }
  
  /** This method manipulates an array in the desiganted column. The values in the column are moved
    * all the way across the diagonal as possible. If the next value is equal to the current value, 
    * the two combine into double their previous value. If the next value isn't equal to the
    * current vlue, the value before acts as a wall and stops the sliding value. 
    * @param array stores the values for the game.
    * @param row is the designated row to slide.
    * @param column is the designated column to slide.
    * @return boolean
    */
  public static boolean slideUpLeft(int[][] array, int row, int column) {    
    int startColumn = 0;                                                  //column which calculations start at
    int startRow = 0;                                                     //row which calculations start at
    change = false;                                                       //has not changed
    
    //finds the starting row and starting column
    for(int i = 0; isOnGrid(array, row - i, column - i); i++) {          
        startRow = row - i;
        startColumn = column - i;
    }
    
    int previousC = startColumn - 1;                                       //finds the previous column 
    
    //slides left and up through a diagonal of the array
    for(int i = startRow; i < array.length; i++) {
      for(int j = startColumn; j < array[row].length; j++) {
        if(array[i][j] != 0 && j == previousC + 1) {
          if(i > 0 && j > 0) {
            if(array[i - 1][j - 1] == array[i][j]) {                        //if the next position is the same as the current, combine the tiles 
              array[i - 1][j - 1] *= 2;
              array[i][j] = 0;
              change = true;
            }
          }
          if(i > 0 && j > 0) {
            if(array[i - 1][j - 1] == 0) {                                  //if the next position is 0, replace the next value with the current
              array[i - 1][j - 1] = array[i][j];
              array[i][j] = 0;
              i = startRow;
              j = startColumn;
              previousC = startColumn - 1;
              change = true;
            }
          }
        }
      }
      previousC++;
    }
    return change;
  } 
  
  /** This method manipulates an array in the desiganted column. The values in the column are moved
    * all the way across the diagonal as possible. If the next value is equal to the current value, 
    * the two combine into double their previous value. If the next value isn't equal to the
    * current vlue, the value before acts as a wall and stops the sliding value. 
    * @param array stores the values for the game.
    * @param row is the designated row to slide.
    * @param column is the designated column to slide.
    * @return boolean
    */
  public static boolean slideDownRight(int[][] array, int row, int column) {
    int startColumn = 0;                                                        //column which calculations start at
    int startRow = 0;                                                           //row which calculations start at
    change = false;                                                             //has not changed
    
    //finds the starting row and starting column
    for(int i = 0; isOnGrid(array, row + i, column + i); i++) {
        startRow = i + row;
        startColumn = i + column;
    }
    
    int previousC = startColumn + 1;                                            //finds the previous column 
    
    //slides down and right through a diagonal of the array
    for(int i = startRow; i >= 0; i--) {
      for(int j = startColumn; j >= 0; j--) {
        if(array[i][j] != 0 && j == previousC - 1) {
          if(i < array.length - 1 && j < array[row].length - 1) {
            if(array[i + 1][j + 1] == array[i][j]) {                            //if the next position is the same as the current, combine the tiles 
              array[i + 1][j + 1] *= 2;
              array[i][j] = 0;
              change = true;
            }
          }
          if(i < array.length - 1 && j < array[row].length - 1) {
            if(array[i + 1][j + 1] == 0) {                                     //if the next position is 0, replace the next value with the current
              array[i + 1][j + 1] = array[i][j];
              array[i][j] = 0;
              i = startRow;
              j = startColumn;
              previousC = startColumn + 1;
              change = true;
            }
          }
        }
      }
      previousC--;
    }
    return change;
  } 
  
  /** This method manipulates an array in the desiganted column. The values in the column are moved
    * all the way across the diagonal as possible. If the next value is equal to the current value, 
    * the two combine into double their previous value. If the next value isn't equal to the
    * current vlue, the value before acts as a wall and stops the sliding value. 
    * @param array stores the values for the game.
    * @param row is the designated row to slide.
    * @param column is the designated column to slide.
    * @return boolean
    */
  public static boolean slideUpRight(int[][] array, int row, int column) {
    int startColumn = 0;                                                  //column which calculations start at
    int startRow = 0;                                                     //row which calculations start at
    change = false;                                                       //has not changed
    
    //finds the starting row and starting column
    for(int i = 0; isOnGrid(array, row - i, column + i); i++) {
        startRow = row - i;
        startColumn = column + i;
    }
    
    int j = startColumn;                                                  //is a variable that helps go diagonal
    
    //slides right and up through a diagonal of the array
    for(int i = startRow; i < array.length && !isCorner(array, row , column); i++) {
      if(j >= 0) {
        if(array[i][j] != 0) {
          if(i > 0 && j < array[row].length - 1) {
            if(array[i - 1][j + 1] == array[i][j]) {
              array[i - 1][j + 1] *= 2;                                  //if the next position is the same as the current, combine the tiles 
              array[i][j] = 0;
              change = true;
            }
          }
          if(i > 0 && j < array[row].length - 1) {
            if(array[i - 1][j + 1] == 0) {                               //if the next position is 0, replace the next value with the current
              array[i - 1][j + 1] = array[i][j];
              array[i][j] = 0;
              i = startRow;
              j = startColumn;
              j = startColumn;
              change = true;
            }
          }
        }
      }
      j--;
    }
    return change;
  } 

  /** This method manipulates an array in the desiganted column. The values in the column are moved
    * all the way across the diagonal as possible. If the next value is equal to the current value, 
    * the two combine into double their previous value. If the next value isn't equal to the
    * current vlue, the value before acts as a wall and stops the sliding value. 
    * @param array stores the values for the game.
    * @param row is the designated row to slide.
    * @param column is the designated column to slide.
    * @return boolean
    */
  public static boolean slideDownLeft(int[][] array, int row, int column) {
    int startColumn = 0;                                                     //column which calculations start at
    int startRow = 0;                                                        //row which calculations start at
    change = false;                                                          //has changed
    
    //finds the starting row and starting column
    for(int i = 0; isOnGrid(array, row + i, column - i); i++) {
        startRow = row + i;
        startColumn = column - i;
    }
    
    int j = startColumn;                                                    //is a variable that helps go diagonal
    
    //slides left and down through a diagonal of the array
    for(int i = startRow; i >= 0 && !isCorner(array, row , column); i--) {
      if(j < array[0].length) {
        if(array[i][j] != 0) {
          if(i < array.length - 1 && j > 0) {
            if(array[i + 1][j - 1] == array[i][j]) {
              array[i + 1][j - 1] *= 2;                                     //if the next position is the same as the current, combine the tiles 
              array[i][j] = 0;
              change = true;
            }
          }
          if(i < array.length - 1 && j > 0) {
            if(array[i + 1][j - 1] == 0) {                                  //if the next position is 0, replace the next value with the current
              array[i + 1][j - 1] = array[i][j];
              array[i][j] = 0;
              i = startRow;
              j = startColumn;
              change = true;
            }
          }
        }
      }
      j++;
    }
    return change;
  } 
  
  /** This is the main method. It quickly initializes the game. The default
    * is set to a game of 4 rows and 4 columns. Throwing two numbers will 
    * give a different number of rows and columns. There is no return value.
    * @param args is a string array that holds the place of the dimensions.
    */
  public static void main(String[] args) {
    if(args.length == 0) {
      SlideGame1 g = new SlideGame1(4, 4);                               //make a default game of 4 x 4
    }
    if(args.length == 2) {
      if(Integer.parseInt(args[0]) > 5 || Integer.parseInt(args[1]) > 5 || Integer.parseInt(args[0]) < 3 || Integer.parseInt(args[1]) < 3 || Integer.parseInt(args[0]) - Integer.parseInt(args[1]) > 2) {        //if the game is given weird parameters, then dont accept them and tell the user to try again
        System.out.println("Try a set of columns and rows that are more reasonable!");
      }
      else{                                                                                          //otherwise, start a game with these parameters
        SlideGame1 g = new SlideGame1(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
      }
    }
  }
}