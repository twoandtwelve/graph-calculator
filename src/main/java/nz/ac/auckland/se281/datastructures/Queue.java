package nz.ac.auckland.se281.datastructures;

/**
 * The Queue interface represents a data structure that follows the FIFO (First-In-First-Out)
 * principle. It defines the basic operations for interacting with a queue.
 *
 * @param <T> the type of elements stored in the queue
 */
public interface Queue<T> {

  /**
   * This method determines if the queue is empty or not, returns true if so.
   *
   * @return a boolean
   */
  public boolean isEmpty();

  /**
   * This method adds the input element to the end of the queue.
   *
   * @param element : a T type data value
   */
  public void enqueue(T element);

  /**
   * This method removes the first element at the front of the queue and then returns it.
   *
   * @return element: a T type data value
   */
  public T dequeue();
}
