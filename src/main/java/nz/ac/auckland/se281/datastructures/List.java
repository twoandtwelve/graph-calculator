package nz.ac.auckland.se281.datastructures;

/**
 * The List interface represents a collection of elements. It defines the basic operations for
 * interacting with a list.
 *
 * @param <T> the type of elements stored in the list
 */
public interface List<T> {

  /**
   * This method adds a node with specified data as the start of the list.
   *
   * @param data : an integer, which is the value of the Node
   */
  public void prepend(T data);

  /**
   * This method adds a node with specified data at the end of the list.
   *
   * @param data : an integer, which is the value of the Node
   */
  public void append(T data);

  /**
   * This method fetches the data value of the head node.
   *
   * @return the data value at of the head node
   */
  public T fetchHead();

  /** This method removes the head node. */
  public void removeHead();

  /**
   * This method determines the size of a list and returns it.
   *
   * @return the size of the list
   */
  public int size();
}
