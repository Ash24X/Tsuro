import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets; 
import javafx.scene.paint.Color; 
import javafx.scene.canvas.GraphicsContext;
import java.util.Random;
/**
 * A javaFX application that models the Chinese Board Game Tsuro
 * @author Ashwin Saraswatula
 */
public class Tsuro extends Application
{
  /** Represents the 2d array of TsuroButtons used to make the board */
  private TsuroButton[][] butArray;
  /** Represents the 1d array of TsuroButtons used to make player 1 hand */
  private TsuroButton[] playerHand1; 
  /** Represents the 1d array of TsuroButtons used to make player 2 hand */
  private TsuroButton[] playerHand2; 
  /** Represents if it's currently player 1's turn  */
  public static boolean player1Turn = true; 
  /** Represents if it's currently player 2's turn  */
  private boolean player2Turn = false;
  /** Represents the number of times player 1 has moved  */
  private int player1MoveCounter = 0;
  /** Represents the number of times player 2 has moved  */
  private int player2MoveCounter = 0; 
  /** Represents the gridpane used to display the board  */
  private GridPane layout;
  /** Represents the row number of the last tile placed by player 1 */
  private int previousRow1;
  /** Represents the Column number of the last tile placed by player 1 */
  private int previousColumn1;
  /** Represents the row number of the current tile placed by player 1 */
  private int newRow1;
  /** Represents the column number of the current tile placed by player 1 */
  private int newColumn1;
  /** Represents the row number of the last tile placed by player 2 */
   private int previousRow2;
  /** Represents the column number of the last tile placed by player 2 */
  private int previousColumn2;
  /** Represents the row number of the current tile placed by player 2 */
  private int newRow2;
  /** Represents the column number of the current tile placed by player 2 */
  private int newColumn2;
  private static int rowSize = 6;
  private static int columnSize = 6;
  private static int handSize = 3;

  /**
   * Gets the rowSize for the TsuroButton Board
   * @return the int value of rowSize
   */
  public static int getRowSize()
  {
    return rowSize;
  }
  
  /**
   * sets the rowSize for the TsuroButton Board
   * @param the new int value of rowSize
   */
  public static void setRowSize(int rowSize)
  {
     rowSize = rowSize ;
  }
  
  /**
   * Gets the column Size for the TsuroButton Board
   * @return the int value of columnSize
   */
  public static int getColumnSize()
  {
    return columnSize ;
  }
  
  /**
   * Sets the columnSize for the TsuroButton Board
   * @param the new int value of columnSize
   */
  public static void setColumnSize(int columnSize)
  {
    columnSize = columnSize;
  }
  
  /**
   * Gets the hand Size for the player hands
   * @return the int value of handSize
   */
  public static int getHandSize()
  {
    return handSize;
  }
  
  /**
   * Sets the hand Size for the Player Hands
   * @param the new int value of handSize
   */
  public static void setHandSize(int handSize)
  {
     handSize = handSize ;
  }
  
  /**
   * Gets the rotation of a button by a given value
   * @return the int value of rotation
   */
  public static int rotate(int rotation)
  {
    int initial = 180;
    int finRoatate = initial + rotation;
    return finRoatate;
  }
  
  /**
   * Determines wether or not it is player 1's turn
   * @return true if it's player 1's turn
   */
  public static boolean isPlayerOneTurn()
  {
    if(player1Turn == true)
    {
      return true;
    }
    return false; 
  }
  
  
  /**
   * Sets up the GUI
   * @param primaryStage the main window of the GUI
   */
  public void start(Stage primaryStage) throws Exception {
    
    EventHandler<ActionEvent> BoardHandler = new BoardHandler();
    butArray = new TsuroButton[rowSize][columnSize];
   /** for loop used to populate the butArray with TsuroButton objects for the gameboard  */
 for(int row = 0; row < butArray.length; row++) {
    for(int col = 0; col < butArray[row].length; col++) {
       TsuroButton button = new TsuroButton(100,100);
       butArray[row][col] = button;
       butArray[row][col].setOnAction(BoardHandler);
    }
 }
     
    /** Nested for loop used to add the TsuroButtons from the butArray into the appropriate positions on the gridpane */
    layout = new GridPane();
    for(int row = 0; row < butArray.length; row++) {
    for(int col = 0; col < butArray[row].length; col++) {
      layout.add(butArray[row][col], col, row); 
    }
 } 
    Scene scene = new Scene(layout);
    primaryStage.setScene(scene);
    primaryStage.show();
    

    EventHandler<ActionEvent> Playerhandler1 = new Playerhandler1();
    
    /** for loop used to populate the playerHand1 array with TsuroButton objects for the player 1 Hand  */
    playerHand1 = new TsuroButton[handSize];
    for(int i = 0; i<playerHand1.length;i++)
    {
      TsuroButton button1 = new TsuroButton(100,100);
      playerHand1[i] = button1; 
      playerHand1[i].setOnAction(Playerhandler1);
    }
    
    /** for loop used to add TsuroButton objects from the playerHand1 array to the hand1 GridPane   */
    GridPane hand1 = new GridPane();
    for(int i = 0; i<playerHand1.length;i++)
    {
      playerHand1[i].setConnections(playerHand1[i].makeRandomConnectionArray());
      playerHand1[i].addStone(Color.BLUE, 6);
      hand1.add(playerHand1[i], i, 0);
    }
    
    /** Creating a new stage, and setting the scene to display the Player 1 Hand */
    Stage stage1 = new Stage();
    stage1.setTitle("Player Hand 1");
    Scene scene1 = new Scene(hand1);
    stage1.setScene(scene1);
    stage1.show();
    
    /** for loop used to add TsuroButton objects to the playerHand2 array and set the TsuroButtons to the eventHandler */
    EventHandler<ActionEvent> Playerhandler2 = new Playerhandler2();
    playerHand2 = new TsuroButton[handSize];
    for(int i = 0; i<playerHand2.length;i++)
    {
      TsuroButton button2 = new TsuroButton(100,100);
      playerHand2[i] = button2;
      playerHand2[i].setOnAction(Playerhandler2);
    }
    
    /** for loop used to add TsuroButton objects from the playerHand2 array to the hand2 gridpane */
    GridPane hand2 = new GridPane();
    for(int i = 0; i<playerHand2.length;i++)
    {
      playerHand2[i].setConnections(playerHand2[i].makeRandomConnectionArray());
      playerHand2[i].addStone(Color.GREEN, 2);
      hand2.add(playerHand2[i], i, 0);
    }
    
    /** Creating a new stage, and setting the scene to display the Player 2 Hand */
    Stage stage2 = new Stage();
    stage2.setTitle("Player Hand 2");
    Scene scene2 = new Scene(hand2);
    stage2.setScene(scene2);
    stage2.show();
  }
  
/**
 * An eventhandler that responds when one of the TsuroButtons from player 1's hand is pressed
 */
  private class Playerhandler1 implements EventHandler<ActionEvent> {
/**
 * Override the handle method to indicate what to do if the event occurs
 * @param e the event data
 */   
    public void handle(ActionEvent e) {
      
      TsuroButton b = (TsuroButton)e.getSource();
      /** if loop to display message when it's not player 1's turn */
      if(player1Turn == false)
     {
       System.out.println("It's player two's turn"); 
     }
      /** if loop to check if it is player 1's turn */
      if(player1Turn == true)
      {
       /** if loop to rotate the button pressed if it's background color is yellow */
       if(b.getBackgroundColor() == Color.YELLOW)
      {
        b.setRotate(b.getRotate() + 90); 
      }
      /**if loop to highlight the clicked button if it's non-highlighted */
      if (b.getBackgroundColor() != Color.YELLOW)
      {
       b.setBackgroundColor(Color.YELLOW);
       for(int i = 0; i<playerHand1.length; i++)
       {
         if(playerHand1[i]!= b)
         {
           playerHand1[i].setBackgroundColor(Color.WHITE);
         }
       }
      } 
    }
    }
  }
  
/**
 * An eventhandler that responds when one of the TsuroButtons from player 1's hand is pressed
 */
  private class Playerhandler2 implements EventHandler<ActionEvent> {
/**
 * Override the handle method to indicate what to do if the event occurs
 * @param e2 the event data
 */ 
    public void handle(ActionEvent e2) {
      
      TsuroButton b = (TsuroButton)e2.getSource();
      /** if loop to display message when it's not player 2's turn */
      if(player2Turn == false)
     {
       System.out.println("It's player one's turn"); 
     }
      /** if loop to check if it is player 2's turn */
      if(player2Turn == true)
      {
        /** if loop to rotate the button pressed if it's background color is yellow */
        if(b.getBackgroundColor() == Color.YELLOW)
      {
        b.setRotate(b.getRotate() + 90);
      }
      {
      /**if loop to highlight the clicked button if it's non-highlighted */
      if (b.getBackgroundColor() != Color.YELLOW)
      {
       b.setBackgroundColor(Color.YELLOW);
       for(int i = 0; i<playerHand1.length; i++)
       {
         if(playerHand2[i]!= b)
         {
           playerHand2[i].setBackgroundColor(Color.WHITE);
         }
       }
      }
    }
      }
    }
  }
  
  
  /**
   * Finds out if any of the tiles in player 1's hand are highlighted
   * @return true if any one of the buttons in player 1's hand is highlighted
   */
  public boolean isHighlighted1()
  {
    for(int i = 0; i< playerHand1.length; i ++)
    {
      if(playerHand1[i].getBackgroundColor() == Color.YELLOW)
      {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Finds out if any of the tiles in player 2's hand are highlighted
   * @return true if any one of the buttons in player 2's hand is highlighted
   */
  public boolean isHighlighted2()
  {
    for(int i = 0; i< playerHand2.length; i ++)
    {
      if(playerHand2[i].getBackgroundColor() == Color.YELLOW)
      {
        return true;
      }
    }
    return false;
  }
  
/**
 * An eventhandler that responds when one of the TsuroButtons from the board is pressed
 */
   private class BoardHandler implements EventHandler<ActionEvent> {
/**
 * Override the handle method to indicate what to do if the event occurs
 * @param e2 the event data
 */ 
    public void handle(ActionEvent e2) { 
      TsuroButton b = (TsuroButton)e2.getSource();
     
     /** if statement to check if it's player 1's turn */
     if(player1Turn == true)
     {
      /** Nested For Loops to store the value of the row and column for the button pressed */
      for(int row = 0; row < butArray.length; row++) {
      for(int col = 0; col < butArray[row].length; col++) {
      if(butArray[row][col] == b)
      {
         newRow1 = row;
         newColumn1 = col; 
      } 
    }
 }
      /** if statement to check if it's player 1's first move */
      if(player1MoveCounter == 0)
      {
        /** for loop to check if any of the tiles are highlighted in player 1's hand */
        for(int i = 0; i< playerHand1.length; i++)
        {
          /** if loop to replace the selected board tile with the player's highlighted tile*/
          if(playerHand1[i].getBackgroundColor()== Color.YELLOW)
          {
             butArray[newRow1][newColumn1].setConnections(playerHand1[i].getConnections());
             previousRow1 = newRow1;
             previousColumn1 = newColumn1; 
             b.addStone(Color.BLUE, playerHand1[i].getConnections()[6]);
             b.setBackgroundColor(Color.GHOSTWHITE);
             playerHand1[i].setBackgroundColor(Color.WHITE);
             playerHand1[i].setConnections(playerHand1[i].makeRandomConnectionArray());
           
          }
          
        }
        player1MoveCounter++; 
        player1Turn = false;
      }
      /**if loop to check if the selected tile has not already been used and is a valid move in the game */
      if(b.getBackgroundColor() != Color.GHOSTWHITE && ((newRow1 == previousRow1+1 && newColumn1 == previousColumn1)|| (newRow1 == previousRow1-1 && newColumn1 == previousColumn1 ) || (newRow1 == previousRow1 && newColumn1 == previousColumn1 +1) ||  (newRow1 == previousRow1 && newColumn1 == previousColumn1 - 1)))
           {
      if(b.getBackgroundColor() != Color.GHOSTWHITE && isHighlighted1() == true)
      {
      /** for loop to remove the stone from the previously placed tile */
      for (int i =0; i<=7; i++)
      {
      butArray[previousRow1][previousColumn1].removeStone(i);
      }
      }
        /** for loop to check if any of the tiles are highlighted in player 1's hand */
        for(int i = 0; i< playerHand1.length; i++)
        {
          /** if loop to add the highlighted tile from player 1's hand to the correct position on the board */
          if(playerHand1[i].getBackgroundColor()== Color.YELLOW)
          {
             butArray[newRow1][newColumn1].setConnections(playerHand1[i].getConnections());
             previousRow1 = newRow1;
             previousColumn1 = newColumn1; 
             b.addStone(Color.BLUE, playerHand1[i].getConnections()[6]);
             b.setBackgroundColor(Color.GHOSTWHITE);
             playerHand1[i].setBackgroundColor(Color.WHITE);
             playerHand1[i].setConnections(playerHand1[i].makeRandomConnectionArray());
            
          }
          
        }
        
        player1Turn = false; 
      }
    }
     
     /** if statement to check if it's player 2's turn */
     if(player2Turn == true)
     {
      /** Nested For Loops to store the value of the row and column for the button pressed */
      for(int row = 0; row < butArray.length; row++) {
      for(int col = 0; col < butArray[row].length; col++) {
      if(butArray[row][col] == b)
      {
         newRow2 = row;
         newColumn2 = col; 
      } 
    }
 }
      /** if statement to check if it's player 2's first move */
      if(player2MoveCounter == 0)
      {
        /** for loop to check if any of the tiles are highlighted in player 2's hand */
        for(int i = 0; i< playerHand2.length; i++)
        {
          /** if loop to replace the selected board tile with the player's highlighted tile*/
          if(playerHand2[i].getBackgroundColor()== Color.YELLOW)
          {
             butArray[newRow2][newColumn2].setConnections(playerHand2[i].getConnections());
             previousRow2 = newRow2;
             previousColumn2 = newColumn2; 
             b.addStone(Color.GREEN, playerHand2[i].getConnections()[2]);
             b.setBackgroundColor(Color.GHOSTWHITE);
             playerHand2[i].setBackgroundColor(Color.WHITE);
             playerHand2[i].setConnections(playerHand2[i].makeRandomConnectionArray());           
          }
          
        }
        player2MoveCounter++; 
        player1Turn = true;
        player2Turn = false;
      }
      /**if loop to check if the selected tile has not already been used and is a valid move in the game */
      if(b.getBackgroundColor() != Color.GHOSTWHITE && ((newRow2 == previousRow2+1 && newColumn2 == previousColumn2)|| (newRow2 == previousRow2-1 && newColumn2 == previousColumn2 ) || (newRow2 == previousRow2 && newColumn2 == previousColumn2 +1) ||  (newRow2 == previousRow2 && newColumn2 == previousColumn2 - 1)))
           {
      if(b.getBackgroundColor() != Color.GHOSTWHITE && isHighlighted2() == true)
      {
      /** for loop to remove the stone from the previously placed tile */
      for (int i =0; i<=7; i++)
      {
      butArray[previousRow2][previousColumn2].removeStone(i);
      }
      }
    
        /** for loop to check if any of the tiles are highlighted in player 1's hand */
        for(int i = 0; i< playerHand2.length; i++)
        {
          /** if loop to add the highlighted tile from player 2's hand to the correct position on the board */
          if(playerHand2[i].getBackgroundColor()== Color.YELLOW)
          {
             butArray[newRow2][newColumn2].setConnections(playerHand2[i].getConnections());
             previousRow2 = newRow2;
             previousColumn2 = newColumn2; 
             b.addStone(Color.GREEN, playerHand2[i].getConnections()[2]);
             b.setBackgroundColor(Color.GHOSTWHITE);
             playerHand2[i].setBackgroundColor(Color.WHITE);
             playerHand2[i].setConnections(playerHand2[i].makeRandomConnectionArray());
          }
          
        }
        
        player1Turn = true; 
        player2Turn = false;
      }
    }
     
     /** if statement to check if it's player 1's turn and set player 2's turn accordingly */
     if(player1Turn == false)
     {
       player2Turn = true;
     }
    }
  }
   
 /**
   * Launch the GUI
   * @param args an array of strings that also takes in rowSize, columnSize, and handSize for the GUI
   */
  public static void main(String[] args) {
    /** if statement to check if 2 or more paramters are passed into args */
    if(args.length >=2)
    {
    rowSize = Integer.parseInt(args[0]);
    columnSize = Integer.parseInt(args[1]);
    }
    /** if statement to check if 3 paramters are passed into args */
    if(args.length ==3)
    {
      handSize = Integer.parseInt(args[2]);
    }
    Application.launch(args);
  }
  
  }

    
