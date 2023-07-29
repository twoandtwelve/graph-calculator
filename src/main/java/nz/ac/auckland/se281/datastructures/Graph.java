package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {

  // instance variables
  private Set<T> verticies;
  private Set<Edge<T>> edges;

  // constructor
  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;
  }

  /**
   * This method determines the root verticies of the graph and returns it.
   *
   * @return the root verticies of the graph
   */
  public Set<T> getRoots() {

    // initialises a tree set with the getNumericalComparator to ensure any integers added
    // to the set are sorted in ascending numerical order
    Set<T> roots = new TreeSet<>(getNumericalComparator());

    // iterates through all verticies of the graph
    for (T currentVertex : this.verticies) {

      // checks if the vertex is a root by use of its degree property
      if (checkIfRootViaDegree(currentVertex)) {
        roots.add(currentVertex);

        // checks if the vertex is a root by use of its equivalence class property
      } else if (getEquivalenceClass(currentVertex).contains(currentVertex)
          && checkSmallestEquivalence(currentVertex)) {
        roots.add(currentVertex);
      }
    }

    return roots;
  }

  /**
   * This method determines whether the graph is reflexive, returns true if so.
   *
   * @return boolean
   */
  public boolean isReflexive() {

    return checkIfReflexive() ? true : false;
  }

  /**
   * This method determines whether the graph is symmetric, returns true if so.
   *
   * @return boolean
   */
  public boolean isSymmetric() {

    return checkIfSymmetric() ? true : false;
  }

  /**
   * This method determines whether the graph is transitive, returns true if so.
   *
   * @return boolean
   */
  public boolean isTransitive() {

    return checkIfTransitive() ? true : false;
  }

  /**
   * This method determines whether the graph is anti-symmetric, returns true if so.
   *
   * @return boolean
   */
  public boolean isAntiSymmetric() {

    return checkIfAntiSymmetric() ? true : false;
  }

  /**
   * This method determines whether the graph is an equivalence relation, returns true if so.
   *
   * @return boolean
   */
  public boolean isEquivalence() {

    return checkIfEquivalence() ? true : false;
  }

  /**
   * This method determines the equivalence class with the given vertex and returns it as a set.
   *
   * @param vertex : a vertex in the graph
   * @return a set of the verticies of the equivalence class
   */
  public Set<T> getEquivalenceClass(T vertex) {

    Set<T> equivalenceClass = new HashSet<T>();

    // ensures the graph is an equivalence relation
    if (!isEquivalence()) {
      return equivalenceClass;
    }

    // iterates through all edges of the graph
    for (Edge<T> currentEdge : this.edges) {

      // adds all equivalence class members
      if (currentEdge.getSource().equals(vertex)) {
        equivalenceClass.add(currentEdge.getDestination());
      } else if (currentEdge.getDestination().equals(vertex)) {
        equivalenceClass.add(currentEdge.getSource());
      }
    }

    return equivalenceClass;
  }

  /**
   * Performs an iterative breadth-first search traversal on the graph and returns a list of visited
   * vertices. A queue will be implemented to perform this search.
   *
   * @return a list of visited vertices in the order they were visited
   */
  public List<T> iterativeBreadthFirstSearch() {

    LinkedListQueue<T> queue = new LinkedListQueue<T>();
    List<T> visitedVerticies = new ArrayList<T>();

    // enqueues all roots of the graph to the queue
    for (T root : getRoots()) {
      queue.enqueue(root);
    }

    // loops while queue is not empty
    while (!queue.isEmpty()) {

      // dequeue the first vertex in queue
      T currentVertex = queue.dequeue();

      // adds the dequeued vertex to the visited verticies list if it has not already been added
      if (!visitedVerticies.contains(currentVertex)) {
        visitedVerticies.add(currentVertex);
      }

      // iterates through all verticies adjacent to the current vertex
      for (T vertex : getAdjacentVerticiesAscendingOrder(currentVertex)) {

        // enqueues the adjacent vertex if it has not already been visited to the queue
        if (!visitedVerticies.contains(vertex)) {
          queue.enqueue(vertex);
        }
      }
    }

    return visitedVerticies;
  }

  /**
   * Performs an iterative depth-first search traversal on the graph and returns a list of visited
   * vertices. A stack will be implemented to perform this search.
   *
   * @return a list of visited vertices in the order they were visited
   */
  public List<T> iterativeDepthFirstSearch() {

    LinkedListStack<T> stack = new LinkedListStack<>();
    List<T> visitedVerticies = new ArrayList<>();

    // pushes all roots of the graph to the stack
    int count = 0;
    for (T root : getRoots()) {
      stack.push(root);
      count++;
    }

    // pushes all the stack pops into a temporary stack to sort the stack in reverse order
    LinkedListStack<T> tempStack = new LinkedListStack<>();
    for (int i = 0; i < count; i++) {
      tempStack.push(stack.pop());
    }

    // assigns the original stack to the temporary stack
    stack = tempStack;

    // loops while stack is not empty
    while (!stack.isEmpty()) {

      // pop the top verex in stack
      T currentVertex = stack.pop();

      // adds the dequeued vertex to the visited verticies list if it has not already been added
      if (!visitedVerticies.contains(currentVertex)) {
        visitedVerticies.add(currentVertex);

        // iterates through all verticies adjacent to the current vertex
        for (T adjacentVertex : getAdjacentVerticiesDescendingOrder(currentVertex)) {

          // pushes the adjacent vertex if it has not already been visited to the stack
          stack.push(adjacentVertex);
        }
      }
    }

    return visitedVerticies;
  }

  /**
   * Performs a recursive breadth-first search traversal on the graph and returns a list of visited
   * vertices. A queue will be implemented to perform this search.
   *
   * @return a list of visited vertices in the order they were visited
   */
  public List<T> recursiveBreadthFirstSearch() {

    LinkedListQueue<T> queue = new LinkedListQueue<T>();
    List<T> visitedVerticies = new ArrayList<T>();

    // iterates through all the roots of the graph and enqueues them to the queue
    for (T root : getRoots()) {
      queue.enqueue(root);
    }

    // calls the recursive BFS function until it returns the visited verticies list
    return recursiveBreadthFunction(queue, visitedVerticies);
  }

  /**
   * Performs a recursive depth-first search traversal on the graph and returns a list of visited
   * vertices. A stack will be implemented to perform this search.
   *
   * @return a list of visited vertices in the order they were visited
   */
  public List<T> recursiveDepthFirstSearch() {

    LinkedListStack<T> stack = new LinkedListStack<T>();
    List<T> visitedVerticies = new ArrayList<T>();

    // iterates through all the roots of the graph and pushes them to the stack
    int count = 0;
    for (T root : getRoots()) {
      stack.push(root);
      count++;
    }

    // pushes all the stack pops into a temporary stack to sort the stack in reverse order
    LinkedListStack<T> tempStack = new LinkedListStack<>();
    for (int i = 0; i < count; i++) {
      tempStack.push(stack.pop());
    }

    // assigns the original stack to the temporary stack
    stack = tempStack;

    // calls the recursive DFS function until it returns the visited verticies list
    return recursiveDepthFunction(stack, visitedVerticies);
  }

  // private methods

  /**
   * This method determines whether the input vertex is a root in the graph, returns true if so.
   *
   * @param a vertex in the graph
   * @return a boolean
   */
  private boolean checkIfRootViaDegree(T vertex) {

    int inDegree = 0;
    int outDegree = 0;

    // iterates through all edges of the graph
    for (Edge<T> currentEdge : this.edges) {

      // counts the number of out degrees of this vertex
      if (currentEdge.getSource() == vertex) {
        outDegree++;
      }

      // counts the number of in degrees of this vertex
      if (currentEdge.getDestination() == vertex) {
        inDegree++;
      }
    }

    if (inDegree == 0 && outDegree > 0) {
      return true;
    }

    return false;
  }

  /**
   * This method determines whether the graph is reflexive, returns true if so.
   *
   * @return boolean
   */
  private boolean checkIfReflexive() {

    boolean reflexiveCase = false;

    // iterates through all verticies of the graph
    for (T currentVertex : this.verticies) {

      // iterates through all edges of the graph
      for (Edge<T> currentEdge : this.edges) {

        // checks if the current vertex has a destination to itself
        if ((currentEdge.getSource() == currentVertex)
            && (currentEdge.getSource() == currentEdge.getDestination())) {
          reflexiveCase = true;
        }
      }

      // returns false if any vertex is not reflexive
      if (!reflexiveCase) {
        return false;
      }
      reflexiveCase = false;
    }

    return true;
  }

  /**
   * This method determines whether the graph is symmetric, returns true if so.
   *
   * @return boolean
   */
  private boolean checkIfSymmetric() {

    boolean symmetricCase = false;

    // iterates through all edges of the graph using a nested loop
    for (Edge<T> currentEdge : this.edges) {
      for (Edge<T> nextEdge : this.edges) {

        // checks if the current edge has a symmetric edge in the graph
        if ((currentEdge.getDestination() == nextEdge.getSource())
            && (currentEdge.getSource() == nextEdge.getDestination())) {
          symmetricCase = true;
        }
      }

      // returns false if any edge does not have a symmetric edge counterpart
      if (!symmetricCase) {
        return false;
      }
      symmetricCase = false;
    }
    return true;
  }

  /**
   * This method determines whether the graph is transitive, returns true if so.
   *
   * @return boolean
   */
  private boolean checkIfTransitive() {

    boolean transitiveCase;

    // iterates through all edges of the graph using a nested loop
    for (Edge<T> firstEdge : this.edges) {
      for (Edge<T> secondEdge : this.edges) {

        // checks if the first edge ends where the second edge starts
        if (firstEdge.getDestination() == secondEdge.getSource()) {
          transitiveCase = false;

          // iterates through all edges of the graph
          for (Edge<T> thirdEdge : this.edges) {

            // checks if there is another edge that starts where the first edge starts and
            // ends where the second edge ends
            if ((firstEdge.getSource() == thirdEdge.getSource())
                && (secondEdge.getDestination() == thirdEdge.getDestination())) {
              transitiveCase = true;
              break;
            }
          }

          // returns false if there is no transitive edge
          if (!transitiveCase) {

            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * This method determines whether the graph is anti-symmetric, returns true if so.
   *
   * @return boolean
   */
  private boolean checkIfAntiSymmetric() {

    boolean antiSymmetricCase = true;

    // itereates through all edges of the graph using a nested loop
    for (Edge<T> currentEdge : this.edges) {
      for (Edge<T> nextEdge : this.edges) {

        // checks if there are two edges in the graph that are symmetric and not reflexive
        if ((currentEdge.getSource() == nextEdge.getDestination())
            && (currentEdge.getDestination() == nextEdge.getSource())
            && (currentEdge.getSource() != currentEdge.getDestination())) {
          antiSymmetricCase = false;
        }
      }

      // returns false if any edge does not have an anti-symmetric edge counterpart
      if (!antiSymmetricCase) {
        return false;
      }
      antiSymmetricCase = true;
    }

    return true;
  }

  /**
   * This method determines whether the graph is an equivalence relation, returns true if so.
   *
   * @return boolean
   */
  private boolean checkIfEquivalence() {

    // checks if the graph is equivalence by checking its reflexive, symmetric and transitive
    // properties
    return checkIfReflexive() && checkIfSymmetric() && checkIfTransitive() ? true : false;
  }

  /**
   * This method determines whether the input vertex is the smallest of it's equivalence class,
   * returns true if so.
   *
   * @param a vertex of the graph
   * @return a boolean
   */
  private boolean checkSmallestEquivalence(T vertex) {

    // checks if the input vertex is the smallest of it's equivalence class
    for (T equivalenceMember : getEquivalenceClass(vertex)) {
      if (vertex.compareTo(equivalenceMember) > 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * This method gets all the adjacent verticies to the input vertex and returns it as a list,
   * sorted in ascending numerical order.
   *
   * @param a vertex of the graph
   * @return list of adjacent verticies
   */
  private List<T> getAdjacentVerticiesAscendingOrder(T vertex) {

    List<T> adjacentVerticies = new ArrayList<>();

    // initialises a tree set with the getNumericalComparator to ensure any integers added
    // to the set are sorted in ascending numerical order
    TreeSet<T> sortedAdjacentVerticies = new TreeSet<>(getNumericalComparator());

    // iterates through all edges of the graph
    for (Edge<T> currentEdge : this.edges) {

      // adds all edges that start from this vertex to the tree set
      if (currentEdge.getSource().equals(vertex)) {
        sortedAdjacentVerticies.add(currentEdge.getDestination());
      }
    }

    // adds all the values from the tree set into the list
    adjacentVerticies.addAll(sortedAdjacentVerticies);
    return adjacentVerticies;
  }

  /**
   * This method gets all the adjacent verticies to the input vertex and returns it as a list,
   * sorted in descending numerical order.
   *
   * @param a vertex of the graph
   * @return list of adjacent verticies
   */
  private List<T> getAdjacentVerticiesDescendingOrder(T vertex) {
    List<T> adjacentVerticies = new ArrayList<>();

    // initialises a tree set with the getNumericalComparator to ensure any integers added
    // to the set are sorted in descending numerical order
    TreeSet<T> sortedAdjacentVerticies = new TreeSet<>(getReverseNumericalComparator());

    // iterates through all edges of the graph
    for (Edge<T> currentEdge : this.edges) {

      // adds all edges that start from this vertex to the tree set
      if (currentEdge.getSource().equals(vertex)) {
        sortedAdjacentVerticies.add(currentEdge.getDestination());
      }
    }

    // adds all the values from the tree set into the list
    adjacentVerticies.addAll(sortedAdjacentVerticies);
    return adjacentVerticies;
  }

  /**
   * Returns a numerical comparator for elements of type T. The comparator compares the elements in
   * ascending numerical order. If the elements are of type String, their integer values are
   * compared. Otherwise, the default string representation of the elements is used for comparison.
   *
   * @return a numerical comparator for elements of type T
   */
  private Comparator<T> getNumericalComparator() {
    return new Comparator<T>() {
      @Override
      public int compare(T o1, T o2) {

        // ensures both inputs are strings
        if (o1 instanceof String && o2 instanceof String) {

          // casting both objects to strings to compare their integer values and sort
          // them in ascending numerical order
          String s1 = (String) o1;
          String s2 = (String) o2;
          return Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2));
        }

        // compares the two inputs using default string representation
        return o1.toString().compareTo(o2.toString());
      }
    };
  }

  /**
   * Returns a reverse numerical comparator for elements of type T. The comparator compares the
   * elements in descending numerical order. If the elements are of type String, their integer
   * values are compared. Otherwise, the default string representation of the elements is used for
   * comparison.
   *
   * @return a reverse numerical comparator for elements of type T
   */
  private Comparator<T> getReverseNumericalComparator() {
    return new Comparator<T>() {
      @Override
      public int compare(T o1, T o2) {

        // ensures both inputs are strings
        if (o1 instanceof String && o2 instanceof String) {

          // casting both objects to strings to compare their integer values and sort
          // them in descending numerical order
          String s1 = (String) o1;
          String s2 = (String) o2;
          return Integer.compare(Integer.parseInt(s2), Integer.parseInt(s1));
        }

        // compares the two inputs using default string representation
        return o2.toString().compareTo(o1.toString());
      }
    };
  }

  /**
   * Performs a recursive breadth-first traversal on the graph using a queue and returns a list of
   * visited vertices.
   *
   * @param queue : the queue used for traversal
   * @param visitedVertices : the list of visited vertices
   * @return a list of visited vertices in the order they were visited
   */
  private List<T> recursiveBreadthFunction(LinkedListQueue<T> queue, List<T> visitedVerticies) {

    // checks if the queue is empty, if so, return the visited verticies list
    if (queue.isEmpty()) {
      return visitedVerticies;

      // if queue is not empty
    } else {

      // dequeue the first vertex in queue
      T currentVertex = queue.dequeue();

      // adds the dequeued vertex to the visited verticies list if it has not already been added
      if (!visitedVerticies.contains(currentVertex)) {
        visitedVerticies.add(currentVertex);
      }

      // iterates through all verticies adjacent to the current vertex
      for (T vertex : getAdjacentVerticiesAscendingOrder(currentVertex)) {

        // enqueues the adjacent vertex if it has not already been visited to the queue
        if (!visitedVerticies.contains(vertex)) {
          queue.enqueue(vertex);
        }
      }

      return recursiveBreadthFunction(queue, visitedVerticies);
    }
  }

  /**
   * Performs a recursive depth-first traversal on the graph using a stack and returns a list of
   * visited vertices.
   *
   * @param stack : the stack used for traversal
   * @param visitedVertices : the list of visited vertices
   * @return a list of visited vertices in the order they were visited
   */
  private List<T> recursiveDepthFunction(LinkedListStack<T> stack, List<T> visitedVerticies) {

    // checks if the stack is empty, if so. return the visited verticies list
    if (stack.isEmpty()) {
      return visitedVerticies;

      // if the stack is not empty
    } else {

      // pop the top verex in stack
      T currentVertex = stack.pop();

      // adds the dequeued vertex to the visited verticies list if it has not already been added
      if (!visitedVerticies.contains(currentVertex)) {
        visitedVerticies.add(currentVertex);

        // iterates through all verticies adjacent to the current vertex
        for (T adjacentVertex : getAdjacentVerticiesDescendingOrder(currentVertex)) {

          // pushes the adjacent vertex if it has not already been visited to the stack
          stack.push(adjacentVertex);
        }
      }

      return recursiveDepthFunction(stack, visitedVerticies);
    }
  }
}
