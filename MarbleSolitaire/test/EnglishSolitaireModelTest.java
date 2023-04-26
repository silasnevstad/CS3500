import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This is a class representing the tests for the EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest {
  private EnglishSolitaireModel temp;
  private EnglishSolitaireModel temp2;
  private EnglishSolitaireModel temp3;
  private EnglishSolitaireModel tempOver;

  @Before
  public void init() {
    temp = new EnglishSolitaireModel();
    temp2 = new EnglishSolitaireModel(5);
    temp3 = new EnglishSolitaireModel(7);
    MarbleSolitaireModelState.SlotState[][] testBoard =
            new MarbleSolitaireModelState.SlotState[7][7];
    MarbleSolitaireModelState.SlotState[][] testBoardOver =
            new MarbleSolitaireModelState.SlotState[7][7];

    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if (i == 3 && j == 3) {
          testBoard[i][j] = MarbleSolitaireModelState.SlotState.Empty;
          testBoardOver[i][j] = MarbleSolitaireModelState.SlotState.Marble;
        } else if (j > 1 && j < 5) {
          testBoard[i][j] = MarbleSolitaireModelState.SlotState.Marble;
          testBoardOver[i][j] = MarbleSolitaireModelState.SlotState.Empty;
        } else if (i > 1 && i < 5) {
          testBoard[i][j] = MarbleSolitaireModelState.SlotState.Marble;
          testBoardOver[i][j] = MarbleSolitaireModelState.SlotState.Empty;
        } else {
          testBoard[i][j] = MarbleSolitaireModelState.SlotState.Invalid;
          testBoardOver[i][j] = MarbleSolitaireModelState.SlotState.Invalid;
        }
      }
    }
    tempOver = new EnglishSolitaireModel(testBoardOver);
  }

  @Test
  public void testCheckValidThickness() {
    assertFalse(temp.checkValidThickness(-5));
    assertFalse(temp.checkValidThickness(1));
    assertFalse(temp.checkValidThickness(2));
    assertTrue(temp.checkValidThickness(3));
    assertFalse(temp.checkValidThickness(4));
    assertTrue(temp.checkValidThickness(5));
    assertTrue(temp.checkValidThickness(57));
    assertFalse(temp.checkValidThickness(56));
  }

  //  @Test
  //  public void testCheckValidSlot() {
  //    assertTrue(temp.checkValidSlot(3, 3));
  //    assertTrue(temp.checkValidSlot(0, 2));
  //    assertTrue(temp.checkValidSlot(6, 4));
  //    assertFalse(temp.checkValidSlot(0, 0));
  //    assertFalse(temp.checkValidSlot(0, 5));
  //    assertFalse(temp.checkValidSlot(5, 1));
  //    assertFalse(temp.checkValidSlot(5, 6));
  //    assertFalse(temp.checkValidSlot(-3, 3));
  //  }

  @Test
  public void testGetCenterSlot() {
    assertEquals(temp.getCenterSlot(), 3);
    assertEquals(temp2.getCenterSlot(), 6);
    assertEquals(temp3.getCenterSlot(), 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEnglishConstructorInvalidThickness() {
    new EnglishSolitaireModel(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEnglishConstructorNegThickness() {
    new EnglishSolitaireModel(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEnglishConstructorInvalidMtSlot() {
    new EnglishSolitaireModel(3, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEnglishConstructorNegRowSlot() {
    new EnglishSolitaireModel(3, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEnglishConstructorNegColSlot() {
    new EnglishSolitaireModel(3, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEnglishConstructorInvalidBoard() {
    new EnglishSolitaireModel(null);
  }

  //  @Test
  //  public void testTotalCells() {
  //    assertEquals(temp.totalCells(), 33);
  //    assertEquals(temp2.totalCells(), 105);
  //    assertEquals(temp3.totalCells(), 217);
  //  }

  @Test
  public void testGetBoardSize() {
    assertEquals(temp.getBoardSize(), 7);
    assertEquals(temp2.getBoardSize(), 13);
    assertEquals(temp3.getBoardSize(), 19);
  }

  //  @Test
  //  public void testInitBoard() {
  //    MarbleSolitaireModelState.SlotState[][] testBoard2 =
  //            new MarbleSolitaireModelState.SlotState[13][13];
  //    for (int i = 0; i < 13; i++) {
  //      for (int j = 0; j < 13; j++) {
  //        if (i == 6 && j == 6) {
  //          testBoard2[i][j] = MarbleSolitaireModelState.SlotState.Empty;
  //        } else if (j > 3 && j < 9) {
  //          testBoard2[i][j] = MarbleSolitaireModelState.SlotState.Marble;
  //        } else if (i > 3 && i < 9) {
  //          testBoard2[i][j] = MarbleSolitaireModelState.SlotState.Marble;
  //        } else {
  //          testBoard2[i][j] = MarbleSolitaireModelState.SlotState.Invalid;
  //        }
  //        //System.out.println("x: " + i + " y: " + j + "   " + testBoard2[i][j]);
  //      }
  //    }
  //    assertEquals(temp.initBoard(), testBoard);
  //    assertEquals(temp2.initBoard(), testBoard2);
  //  }

  @Test
  public void testGetSlotAt() {
    assertEquals(temp.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(temp.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(temp.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(temp.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(temp2.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(temp2.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(temp2.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(temp2.getSlotAt(10, 9), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(temp2.getSlotAt(5, 8), MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(temp3.getSlotAt(9, 9), MarbleSolitaireModelState.SlotState.Empty);
  }

  //  public void testGetScore() {
  //    assertEquals(32, temp.getScore());
  //    assertEquals(104, temp2.getScore());
  //    assertEquals(216, temp3.getScore());
  //  }

  //  @Test
  //  public void testAnyPossibleMoves() {
  //    assertTrue(temp.anyPossibleMoves(5, 3));
  //    assertTrue(temp.anyPossibleMoves(1, 3));
  //    assertTrue(temp.anyPossibleMoves(3, 1));
  //    assertTrue(temp.anyPossibleMoves(3, 5));
  //    assertFalse(temp.anyPossibleMoves(4, 6));
  //    assertFalse(temp.anyPossibleMoves(0, 2));
  //    assertFalse(temp.anyPossibleMoves(3, 0));
  //    assertFalse(temp.anyPossibleMoves(6, 3));
  //  }

  @Test
  public void testIsGameOver() {
    assertFalse(temp.isGameOver());
    assertTrue(tempOver.isGameOver());
  }

  @Test
  public void testMoveExceptions() {
    try {
      temp.move(5, 3, 4, 3);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Move is too short.");
    }
    try {
      temp.move(6, 3, 4, 3);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "To spot has to be empty.");
    }
    try {
      temp.move(3, 3, 5, 3);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "No marble at from spot.");
    }
    try {
      temp.move(0, 0, 0, 2);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "From spot is invalid.");
    }
    try {
      temp.move(6, 4, 6, 6);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "To spot is invalid.");
    }
    try {
      temp.move(6, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Move is too far.");
    }
    try {
      temp.move(2, 2, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot make diagonal moves.");
    }
    try {
      temp.move(3, 3, 5, 3);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "No marble at from spot.");
    }
  }
}
