package listadt;

import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements
 * the listadt.ListADT interface
 */
public class ListADTImpl<T> implements ListADT<T> {
  GenericListADTNode<T> head;

  /**
   * Constructs a new generic empty list.
   */
  public ListADTImpl() {
    head = new GenericEmptyNode();
  }

  //a private constructor that is used internally (see map)
  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  @Override
  public void addFront(T b) {
    this.head = this.head.addFront(b);
  }

  @Override
  public void addBack(T b) {
    this.head = this.head.addBack(b);
  }

  @Override
  public void add(int index, T b) {
    this.head = this.head.add(index, b);
  }

  @Override
  public int getSize() {
    return this.head.count();
  }

  @Override
  public void remove(T b) {
    this.head = this.head.remove(b);
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return this.head.get(index);
    }
    throw new IllegalArgumentException("Invalid index");
  }

  @Override
  public <R> CommonListADT<T>  map(Function<T, R> converter) {
    return new ListADTImpl(this.head.map(converter));
  }

  @Override
  public String toString() {
    return "(" + this.head.toString() + ")";
  }
}
