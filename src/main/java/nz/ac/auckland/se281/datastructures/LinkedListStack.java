package nz.ac.auckland.se281.datastructures;

/**
 * The LinkedListStack class represents a stack data structure implemented using a linked list. It
 * implements the Stack interface and provides operations to interact with the Stack.
 *
 * @param <T> the type of elements stored in the stack
 */
public class LinkedListStack<T> implements Stack<T> {

  // instance variables
  protected LinkedList<T> stackData;

  // constructor
  public LinkedListStack() {
    this.stackData = new LinkedList<T>();
  }

  /**
   * This method determines if the stack is empty or not, returns true if so.
   *
   * @return a boolean
   */
  @Override
  public boolean isEmpty() {
    return stackData.size() == 0;
  }

  /**
   * This method adds the input element to the top of the stack.
   *
   * @param element : a T type value
   */
  @Override
  public void push(T element) {
    stackData.prepend(element);
  }

  /**
   * This method removes the element at the top of the stack and returns it.
   *
   * @return a T type value
   */
  @Override
  public T pop() {
    T element = stackData.fetchHead();
    stackData.removeHead();

    return element;
  }
}
