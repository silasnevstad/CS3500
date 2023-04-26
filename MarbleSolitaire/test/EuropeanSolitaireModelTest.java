import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class represents the testing for the european solitaire model class.
 */
public class EuropeanSolitaireModelTest {
  EuropeanSolitaireModel modelSize3;
  EuropeanSolitaireModel modelSize5;
  EuropeanSolitaireModel modelSize7;

  @Before
  public void init() {
    this.modelSize3 = new EuropeanSolitaireModel();
    this.modelSize5 = new EuropeanSolitaireModel(5);
    this.modelSize7 = new EuropeanSolitaireModel(7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuropeanConstructorInvalidThickness() {
    new EuropeanSolitaireModel(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuropeanConstructorNegThickness() {
    new EuropeanSolitaireModel(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuropeanConstructorInvalidMtSlot() {
    new EuropeanSolitaireModel(3, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuropeanConstructorNegRowSlot() {
    new EuropeanSolitaireModel(3, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuropeanConstructorNegColSlot() {
    new EuropeanSolitaireModel(3, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuropeanConstructorInvalidBoard() {
    new EuropeanSolitaireModel(null);
  }

  @Test
  public void testEuropeanConstructorErrorMessageInvalidThickness() {
    try {
      new EuropeanSolitaireModel(2);
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness must be valid.", e.getMessage());
    }
  }

  @Test
  public void testEuropeanConstructorErrorMessageInvalidMT() {
    try {
      new EuropeanSolitaireModel(3, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0, 0).", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(3, 6, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6, 6).", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(3, 3, -3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (3, -3).", e.getMessage());
    }
  }

  //  @Test
  //  public void testTotalCells() {
  //    assertEquals(37, this.modelSize3.totalCells());
  //    assertEquals(129, this.modelSize5.totalCells());
  //    assertEquals(277, this.modelSize7.totalCells());
  //  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, this.modelSize3.getBoardSize());
    assertEquals(13, this.modelSize5.getBoardSize());
    assertEquals(19, this.modelSize7.getBoardSize());
  }

  @Test
  public void testCheckValidThickness() {
    assertFalse(this.modelSize3.checkValidThickness(-5));
    assertFalse(this.modelSize3.checkValidThickness(1));
    assertFalse(this.modelSize3.checkValidThickness(2));
    assertTrue(this.modelSize5.checkValidThickness(3));
    assertFalse(this.modelSize5.checkValidThickness(4));
    assertTrue(this.modelSize5.checkValidThickness(5));
    assertTrue(this.modelSize7.checkValidThickness(57));
    assertFalse(this.modelSize7.checkValidThickness(56));
  }

  //  @Test
  //  public void testCheckValidSlot() {
  //    assertTrue(this.modelSize3.checkValidSlot(3, 3));
  //    assertTrue(this.modelSize3.checkValidSlot(0, 2));
  //    assertTrue(this.modelSize3.checkValidSlot(6, 4));
  //    assertFalse(this.modelSize5.checkValidSlot(0, 0));
  //    assertFalse(this.modelSize5.checkValidSlot(0, 5));
  //    assertFalse(this.modelSize5.checkValidSlot(5, 1));
  //    assertFalse(this.modelSize7.checkValidSlot(5, 6));
  //    assertFalse(this.modelSize7.checkValidSlot(-3, 3));
  //  }

  //  @Test
  //  public void testGetCenterSlot() {
  //    assertEquals(this.modelSize3.getCenterSlot(), 3);
  //    assertEquals(this.modelSize5.getCenterSlot(), 6);
  //    assertEquals(this.modelSize7.getCenterSlot(), 9);
  //  }
}
