import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A class representing the testing for the triangle solitaire model class.
 */
public class TriangleSolitaireModelTest {
  TriangleSolitaireModel modelSize5;
  TriangleSolitaireModel modelSize6;
  TriangleSolitaireModel modelSize7;

  @Before
  public void init() {
    this.modelSize5 = new TriangleSolitaireModel();
    this.modelSize6 = new TriangleSolitaireModel(6);
    this.modelSize7 = new TriangleSolitaireModel(7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleConstructorInvalidThickness() {
    new TriangleSolitaireModel(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleConstructorNegThickness() {
    new TriangleSolitaireModel(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleConstructorInvalidMtSlot() {
    new TriangleSolitaireModel(3, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleConstructorNegRowSlot() {
    new TriangleSolitaireModel(3, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleConstructorNegColSlot() {
    new TriangleSolitaireModel(6, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleConstructorInvalidBoard() {
    new TriangleSolitaireModel(null);
  }

  @Test
  public void testTriangleConstructorErrorMessageInvalidThickness() {
    try {
      new TriangleSolitaireModel(2);
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness must be a positive odd integer.", e.getMessage());
    }
  }

  @Test
  public void testTriangleConstructorErrorMessageInvalidMT() {
    try {
      new TriangleSolitaireModel(3, -1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Empty spot (-1, 0) must be a valid slot.", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(5, 6, 7);
    } catch (IllegalArgumentException e) {
      assertEquals("Empty spot (6, 7) must be a valid slot.", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(4, 3, -3);
    } catch (IllegalArgumentException e) {
      assertEquals("Empty spot (3, -3) must be a valid slot.", e.getMessage());
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, this.modelSize5.getBoardSize());
    assertEquals(6, this.modelSize6.getBoardSize());
    assertEquals(7, this.modelSize7.getBoardSize());
  }

  @Test
  public void testCheckValidThickness() {
    assertFalse(this.modelSize5.checkValidThickness(-5));
    assertFalse(this.modelSize5.checkValidThickness(0));
    assertFalse(this.modelSize5.checkValidThickness(1));
    assertTrue(this.modelSize5.checkValidThickness(2));
    assertTrue(this.modelSize6.checkValidThickness(3));
    assertTrue(this.modelSize6.checkValidThickness(4));
    assertTrue(this.modelSize6.checkValidThickness(5));
    assertTrue(this.modelSize7.checkValidThickness(57));
    assertTrue(this.modelSize7.checkValidThickness(56));
  }

  // these tests all passed (no changes have been made...)
  //  @Test
  //  public void testCheckValidSlot() {
  //    assertTrue(this.modelSize5.checkValidSlot(3, 3));
  //    assertTrue(this.modelSize5.checkValidSlot(0, 2));
  //    assertTrue(this.modelSize5.checkValidSlot(6, 4));
  //    assertFalse(this.modelSize6.checkValidSlot(0, 0));
  //    assertFalse(this.modelSize6.checkValidSlot(0, 5));
  //    assertFalse(this.modelSize6.checkValidSlot(5, 1));
  //    assertFalse(this.modelSize7.checkValidSlot(5, 6));
  //    assertFalse(this.modelSize7.checkValidSlot(-3, 3));
  //  }

  //  @Test
  //  public void testGetCenterSlot() {
  //    assertEquals(this.modelSize5.getCenterSlot(), 3);
  //    assertEquals(this.modelSize6.getCenterSlot(), 6);
  //    assertEquals(this.modelSize7.getCenterSlot(), 9);
  //  }


  //  @Test
  //  public void testTotalCells() {
  //    assertEquals(37, this.modelSize5.totalCells());
  //    assertEquals(129, this.modelSize6.totalCells());
  //    assertEquals(277, this.modelSize7.totalCells());
  //  }
}
