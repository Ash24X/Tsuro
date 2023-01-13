/** A JUnit test case class.
  * Every method starting with the word "test" will be called when running
  * the test with JUnit.
  */
 import static org.junit.Assert.assertEquals;
 import org.junit.Test;
 import javafx.scene.paint.Color;

 public class TsuroTester {
  
  /** A test method.
    * (Replace "X" with a name describing the test.  You may write as
    * many "testSomething" methods in this class as you wish, and each
    * one will be called when running JUnit over this class.)
    */
   
   /**
   * Test the 2D butArray Creation
   */
  @Test
  public void testbutArray() 
  {
    Tsuro.setRowSize(6);
    Tsuro.setColumnSize(4);
    TsuroButton[][]butArray1 = new TsuroButton[Tsuro.getRowSize()][Tsuro.getColumnSize()];
    for(int row = 0; row < butArray1.length; row++) {
    for(int col = 0; col < butArray1[row].length; col++) {       
    }  
 }
    assertEquals(Tsuro.getRowSize(), butArray1.length);
  }
  
  /**
   * Test the 1D Array playerHand1 Creation
   */
  @Test
    public void testPlayerHand1Array() 
  {
    Tsuro.setHandSize(4);
    TsuroButton[]PlayerHand1 = new TsuroButton[Tsuro.getHandSize()];
    for(int i = 0; i < PlayerHand1.length; i++) {
 }
    assertEquals(Tsuro.getHandSize(), PlayerHand1.length);
  }
  
  /**
   * Test the 1D Array playerHand2 Creation
   */
  @Test
    public void testPlayerHand2Array() 
  {
    Tsuro.setHandSize(7);
    TsuroButton[]PlayerHand2 = new TsuroButton[Tsuro.getHandSize()];
    for(int i = 0; i < PlayerHand2.length; i++) {
 }
    assertEquals(Tsuro.getHandSize(), PlayerHand2.length);
  }
  
  /**
   * Test that clicked Button Highlights for player1 Hand
   */
    public void testClickedButtonHighlight() 
  {
    TsuroButton clickedButton = new TsuroButton(100,100);
    clickedButton.setBackgroundColor(Color.YELLOW);
    
    assertEquals(clickedButton.getBackgroundColor(), Color.YELLOW);
  }
    
  /**
   * Test that clicked Button Rotates for player1 Hand
   */  
    @Test
    public void testButtonRotation() 
  {
    int x = Tsuro.rotate(90);
    assertEquals(x,270);  
  }
  /**
   * Test loop with player1Turn Boolean when it's player 1's turn 
   */  
    @Test
    public void testPlayer1TurnBoolean() 
  {
    Tsuro.player1Turn = true;
    assertEquals(Tsuro.isPlayerOneTurn(),true);
     
  }
 /**
   * Test loop with player1Turn Boolean when it's Not player 1's turn 
   */  
    @Test
    public void testNotPlayer1TurnBoolean() 
  {
    Tsuro.player1Turn = false;
    assertEquals(Tsuro.isPlayerOneTurn(),false);    
  }
    
 }
