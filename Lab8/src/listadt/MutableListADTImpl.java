package listadt;

import java.util.function.Function;

/**
 * This class represents a mutable list, that can be modified and changed.
 * It extends the listadt.ListADTImpl class and implements the listadt.MutableListADT interface.
 * @param <T> the type of elements in the list.
 */
public class MutableListADTImpl<T> extends ListADTImpl<T> implements MutableListADT<T> {
  /**
   * Creates an immutable list based of a given GenericListADTNode.
   * @param head A GenericListADTNode entailing the list.
   */
  public MutableListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  @Override
  public <R> CommonListADT<T> map(Function<T, R> converter) {
    return new MutableListADTImpl(head.map(converter));
  }

  @Override
  public ImmutableListADT<T> getImmutableList() {
    return new ImmutableListADTImpl.ListBuilder().addBack(this.head).build();
  }
}
