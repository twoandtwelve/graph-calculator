package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {

  // instance variables
  private T source;
  private T destination;

  // constructor
  public Edge(T source, T destination) {
    this.source = source;
    this.destination = destination;
  }

  // getter methods

  /**
   * This method gets the source value of the current edge.
   *
   * @return the source of this edge
   */
  public T getSource() {
    return source;
  }

  /**
   * This method gets the destination value of the current edge.
   *
   * @return the destination of this edge
   */
  public T getDestination() {
    return destination;
  }
}
