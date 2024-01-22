import java.util.Comparator;

// Compares the weight of edges
class LeastWeight implements Comparator<Edge> {
  // Given two edge, return the difference of its weight as a result in this
  // compare class
  public int compare(Edge o1, Edge o2) {
    return o1.weight - o2.weight;
  }
}
