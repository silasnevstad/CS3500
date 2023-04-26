package listadt;

/**
 * This interface represents a mutable list.
 * @param <T> the type of elements in the list.
 */
public interface MutableListADT<T> extends ListADT<T> {
  ImmutableListADT<T> getImmutableList();
}
