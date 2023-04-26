import org.junit.Test;

import listadt.GenericElementNode;
import listadt.GenericEmptyNode;
import listadt.ImmutableListADTImpl;
import listadt.MutableListADT;
import listadt.MutableListADTImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the testing for this project,
 * including mutable and immutable list objects and their operations.
 */
public class ImmutableListADTImplTest {
  @Test
  public void testBuilder() {
    ImmutableListADTImpl<String> immutableList = new ImmutableListADTImpl.ListBuilder()
            .addBack("Hello").addBack("world").addBack("!").build();
    assertEquals(3, immutableList.getSize());
  }

  @Test
  public void testGet() {
    ImmutableListADTImpl<Integer> immutableList = new ImmutableListADTImpl.ListBuilder()
            .addBack(1).addBack(2).addBack(3).build();
    assertEquals(new Integer(1), immutableList.get(0));
    assertEquals(new Integer(2), immutableList.get(1));
    assertEquals(new Integer(3), immutableList.get(2));
  }

  @Test
  public void testGetError() {
    ImmutableListADTImpl<Integer> immutableList = new ImmutableListADTImpl.ListBuilder()
            .addBack(1).addBack(2).addBack(3).build();
    try {
      immutableList.get(3);
    } catch (IllegalArgumentException e) {
      assertEquals("Wrong index", e.getMessage());
    }
  }

  @Test
  public void testMutableListADTToString() {
    GenericEmptyNode mt = new GenericEmptyNode();
    GenericElementNode node4 = new GenericElementNode<Integer>(4, mt);
    GenericElementNode node3 = new GenericElementNode<Integer>(3, node4);
    GenericElementNode node2 = new GenericElementNode<Integer>(2, node3);
    GenericElementNode node1 = new GenericElementNode<Integer>(1, node2);
    MutableListADTImpl<Integer> mutableList = new MutableListADTImpl(node1);
    assertEquals("(1 2 3 4)", mutableList.toString());
  }

  @Test
  public void testImmutableListADTToString() {
    ImmutableListADTImpl<Integer> immutableList = new ImmutableListADTImpl.ListBuilder()
            .addBack(1).addBack(2).addBack(3).addBack(4).build();
    assertEquals("(1 2 3 4)", immutableList.toString());
  }

  @Test
  public void testTogether() {
    GenericEmptyNode mt = new GenericEmptyNode();
    GenericElementNode node4 = new GenericElementNode<Integer>(4, mt);
    GenericElementNode node3 = new GenericElementNode<Integer>(3, node4);
    GenericElementNode node2 = new GenericElementNode<Integer>(2, node3);
    GenericElementNode node1 = new GenericElementNode<Integer>(1, node2);
    MutableListADTImpl<Integer> mutableList = new MutableListADTImpl(node1);
    ImmutableListADTImpl<Integer> immutableList = new ImmutableListADTImpl.ListBuilder()
            .addBack(1).addBack(2).addBack(3).addBack(4).build();
    assertEquals("(1 2 3 4)", immutableList.toString());
    MutableListADT<Integer> immutableMutableCounterPart = immutableList.getMutableList();
    immutableMutableCounterPart.addBack(5);
    immutableMutableCounterPart.addBack(6);
    assertEquals("(1 2 3 4 5 6)", immutableMutableCounterPart.toString());
    assertEquals("(1 2 3 4)", mutableList.toString());
    assertEquals("(1 2 3 4)", immutableList.toString());
  }
}
