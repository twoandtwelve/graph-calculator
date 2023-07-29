package nz.ac.auckland.se281.datastructures;

/**
 * The Node class represents a node in a linked list. It contains a data value and a reference to
 * the next node in the list.
 *
 * @param <T> the type of the node's value
 */
public class Node<T> {

  // instance variables
  private T val;
  private Node<T> next;

  // zero parameter constructor
  public Node() {}

  // one parameter constructor
  public Node(T val) {
    this.val = val;
    this.next = null;
  }

  // two parameter constructor
  public Node(T val, Node<T> next) {
    this.val = val;
    this.next = next;
  }

  // getter and setter methods

  /**
   * This method sets the node that this current node references.
   *
   * @param next : the referenced node
   */
  public void setNext(Node<T> next) {
    this.next = next;
  }

  /**
   * This method gets the node that this current node references.
   *
   * @return the referenced node
   */
  public Node<T> getNext() {
    return next;
  }

  /**
   * This method gets the data value of the current node.
   *
   * @return the data value of the node
   */
  public T getValue() {
    return val;
  }
}
