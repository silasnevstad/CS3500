package listadt;

/**
 * This inferace represents a common list with all the non-modifiable operations.
 * @param <T> the type of elements in the list.
 */
public interface CommonListADT<T> {
  /**
   * Return the number of objects currently in this list.
   *
   * @return the size of the list
   */
  int getSize();

  /**
   * Get the (index)th object in this list.
   *
   * @param index the index of the object to be returned
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  T get(int index) throws IllegalArgumentException;
}
