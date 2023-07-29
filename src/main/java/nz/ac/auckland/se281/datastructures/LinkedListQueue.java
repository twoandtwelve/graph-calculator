package nz.ac.auckland.se281.datastructures;

/**
 * The LinkedListQueue class represents a queue data structure implemented using a linked list. It
 * implements the Queue interface and provides operations to interact with the queue.
 *
 * @param <T> the type of elements stored in the queue
 */
public class LinkedListQueue<T> implements Queue<T> {

  // instance variables
  protected LinkedList<T> queueData;

  // constructor
  public LinkedListQueue() {
    this.queueData = new LinkedList<T>();
  }

  /**
   * This method determines if the queue is empty or not, returns true if so.
   *
   * @return a boolean
   */
  @Override
  public boolean isEmpty() {
    return queueData.size() == 0;
  }

  /**
   * This method adds the input element to the end of the queue.
   *
   * @param element : a T type data value
   */
  @Override
  public void enqueue(T element) {
    queueData.append(element);
  }

  /**
   * This method removes the first element at the front of the queue and then returns it.
   *
   * @return element: a T type data value
   */
  @Override
  public T dequeue() {

    T element = queueData.fetchHead();
    queueData.removeHead();

    return element;
  }
}
