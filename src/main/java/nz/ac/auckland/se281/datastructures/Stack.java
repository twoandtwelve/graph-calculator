package nz.ac.auckland.se281.datastructures;

/**
 * The Stack interface represents a data structure that follows the LIFO (Last-In-First-Out)
 * principle. It defines the basic operations for interacting with a stack.
 *
 * @param <T> the type of elements stored in the stack
 */
public interface Stack<T> {

  /**
   * This method determines if the stack is empty or not, returns true if so.
   *
   * @return a boolean
   */
  public boolean isEmpty();

  /**
   * This method adds the input element to the top of the stack.
   *
   * @param element : a T type value
   */
  public void push(T element);

  /**
   * This method removes the element at the top of the stack and returns it.
   *
   * @return a T type value
   */
  public T pop();
}
