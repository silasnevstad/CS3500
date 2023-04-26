package listadt;

import java.util.function.Function;

/**
 * This class represents an implementation of an ImmutableList.
 * @param <T> the type of objects in the list.
 */
public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {
  private GenericListADTNode<T> head;

  /**
   * Creates an immutable list based of a given GenericListADTNode.
   * @param head A GenericListADTNode entailing the list.
   */
  private ImmutableListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * Creates an immutable list based of a given builder.
   * @param builder A builder class entailing the list.
   */
  protected ImmutableListADTImpl(ListBuilder builder) {
    this.head = builder.head;
  }

  @Override
  public MutableListADT<T> getMutableList() {
    GenericListADTNode<T> newHead = new GenericElementNode<>(this.head, new GenericEmptyNode());
    MutableListADTImpl<T> counterList = new MutableListADTImpl<T>(newHead);
    return counterList;
  }

  /**
   * A general purpose map higher order function on this list, that returns
   * the corresponding list of type R.
   *
   * @param converter the function that converts T into R
   * @param <R>       the type of data in the resulting list
   * @return the resulting list that is identical in structure to this list,
   *          but has data of type R
   */
  public <R> CommonListADT<T> map(Function<T, R> converter) {
    return new ImmutableListADTImpl(this.head.map(converter));
  }

  @Override
  public int getSize() {
    return this.head.count();
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    return this.head.get(index);
  }

  @Override
  public String toString() {
    return "(" + this.head.toString() + ")";
  }

  /**
   * This class represents a builder to append to the back of the list.
   */
  public static class ListBuilder<T> {
    private GenericListADTNode<T> head;

    /**
     * Constructs an empty list builder object.
     */
    public ListBuilder() {
      this.head = new GenericEmptyNode();
    }

    /**
     * Builds an immutable list object with this list.
     * @return an immutableListADTImpl object
     */
    public ImmutableListADTImpl<T> build() {
      ImmutableListADTImpl<T> immutableList = new ImmutableListADTImpl<T>(returnBuilder());
      return immutableList;
    }

    /**
     * Returns the current ListBuilder object.
     * @return a ListBuilder object
     */
    protected ListBuilder<T> returnBuilder() {
      return this;
    }

    /**
     * Adds to the back of the current list.
     * @param elem the element to append to the back of the list (of type T)
     * @return a ListBuilder object.
     */
    public ListBuilder<T> addBack(T elem) {
      this.head = this.head.addBack(elem);
      return returnBuilder();
    }
  }
}
