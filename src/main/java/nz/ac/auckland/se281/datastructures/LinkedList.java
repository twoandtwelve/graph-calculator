package nz.ac.auckland.se281.datastructures;

/**
 * The LinkedList class represents a linked list data structure. It implements the List interface
 * and provides operations to interact with the list.
 *
 * @param <T> the type of elements stored in the list
 */
public class LinkedList<T> implements List<T> {

  // instance variables
  private Node<T> head;
  private Node<T> tail;

  // constructor
  public LinkedList() {
    head = null;
    tail = null;
  }

  /**
   * This method adds a node with specified data as the start of the list.
   *
   * @param data : an integer, which is the value of the Node
   */
  public void prepend(T data) {
    // Note -- works even if list is empty

    Node<T> node = new Node<T>(data);

    // makes the new node reference the current head node
    node.setNext(this.head);

    // makes head reference the new node
    this.head = node;

    // if tail does not reference a node, make it reference the same node as head
    if (this.tail == null) {
      this.tail = this.head;
    }
  }

  /**
   * This method adds a node with specified data at the end of the list.
   *
   * @param data : an integer, which is the value of the Node
   */
  public void append(T data) {

    Node<T> node = new Node<T>(data, null);

    // if head does not reference a node, make it reference the same node as tail
    if (this.head == null) {
      this.head = node;

      // references the current tail node to the new node
    } else {
      this.tail.setNext(node);
    }

    // makes tail reference the new node
    this.tail = node;
  }

  /**
   * This method fetches the data value of the head node.
   *
   * @return the data value at of the head node
   */
  public T fetchHead() {

    return this.head.getValue();
  }

  /** This method removes the head node. */
  public void removeHead() {

    this.head = this.head.getNext();
  }

  /**
   * This method determines the size of a list and returns it.
   *
   * @return the size of the list
   */
  public int size() {

    // if there is no node created yet return size of 0
    if (this.head == null) {
      return 0;
    }

    // node references the head node
    Node<T> node = this.head;

    // default size to 1
    int size = 1;

    // if node does not reference to a null object, increment size and set node to the node it
    // currently references
    while (node.getNext() != null) {
      node = node.getNext();
      size++;
    }

    return size;
  }
}
