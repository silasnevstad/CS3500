package listadt;

/**
 * This interface represents an immutable list object. A list which cannot be modified
 * once it's been created.
 * @param <T> object type.
 */
public interface ImmutableListADT<T> extends CommonListADT<T> {
  /**
   * Gets a counter mutable list object of this immutable list.
   * @return a MutableListADT object.
   */
  MutableListADT<T> getMutableList();
}
