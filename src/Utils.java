import java.awt.Color;
import java.util.*;

class Utils {
  // Finds the minimum spanning tree that the given cell belongs to given the
  // representative
  MazeCell find(HashMap<MazeCell, MazeCell> representatives, MazeCell cell) {
    if (representatives.get(cell).equals(cell)) {
      return cell;
    }
    return this.find(representatives, representatives.get(cell));
  }

  // EFFECT: Combines two minimum spanning tree given the representative and two
  // cells that represents two minimum spanning tree
  void union(HashMap<MazeCell, MazeCell> representatives, MazeCell rep, MazeCell changeRep) {
    representatives.replace(changeRep, rep);
  }

  // EFFECT: Updates the given minimum spanning tree and representatives by
  // running the Kruskal's algorithm
  void kruskal(HashMap<MazeCell, MazeCell> representatives, ArrayList<Edge> edgesInTree,
      ArrayList<Edge> workList) {
    if (workList.size() > 0 && edgesInTree.size() < representatives.size() - 1) {
      Edge e = workList.remove(0);
      MazeCell rep1 = this.find(representatives, e.mazeCell1);
      MazeCell rep2 = this.find(representatives, e.mazeCell2);
      if (!(rep1.equals(rep2))) {
        e.minEdge = true;
        edgesInTree.add(e);
        this.union(representatives, rep1, rep2);
      }
    }
  }

  // EFFECT: Removes maze cells in the given list of solution that isn't a part of
  // the solution
  void backTrack(ArrayDeque<MazeCell> solution) {
    if (solution.size() > 0) {
      MazeCell endItem = solution.getLast();
      if (!(endItem.topEdge.isTraversablePath(endItem)
          || endItem.rightEdge.isTraversablePath(endItem)
          || endItem.bottomEdge.isTraversablePath(endItem)
          || endItem.leftEdge.isTraversablePath(endItem))) {
        solution.removeLast();
        this.backTrack(solution);
      }
    }
  }

  // Has breadth first search found the correct path
  // EFFECT: Records the paths visited so far in the given hashmap and updates the
  // given breadthList and the color of the current cell being searched/checked
  boolean bfsFound(MazeCell from, MazeCell to, LinkedList<MazeCell> breadthList,
      HashMap<MazeCell, MazeCell> bfsPaths) {
    for (int i = 0; i < breadthList.size(); i = i + 1) {
      MazeCell currCell = breadthList.remove();
      if (!currCell.visited) {
        currCell.topEdge.updateBfsPath(bfsPaths, currCell);
        currCell.rightEdge.updateBfsPath(bfsPaths, currCell);
        currCell.bottomEdge.updateBfsPath(bfsPaths, currCell);
        currCell.leftEdge.updateBfsPath(bfsPaths, currCell);
      }
      if (this.searchHelp(from, to, breadthList, currCell)) {
        return true;
      }
    }
    return false;
  }

  // Has depth first search found the correct path
  // EFFECT: Updates the given solution, depthList, and the color of the current
  // cell being searched/checked
  boolean dfsFound(MazeCell from, MazeCell to, Stack<MazeCell> depthList,
      ArrayDeque<MazeCell> solution) {
    MazeCell currCell = depthList.pop();
    if (!currCell.visited) {
      solution.add(currCell);
    }
    boolean found = this.searchHelp(from, to, depthList, currCell);
    if (!currCell.equals(to)) {
      this.backTrack(solution);
    }
    return found;
  }

  // Checks if the given desired cell is found for dfs/bfs
  boolean searchHelp(MazeCell from, MazeCell to, Collection<MazeCell> workList, MazeCell currCell) {
    currCell.color = new Color(144, 184, 242);
    if (currCell.equals(to)) {
      return true;
    }
    else if (!currCell.visited) {
      currCell.visited = true;
      currCell.topEdge.addTo(workList, currCell);
      currCell.leftEdge.addTo(workList, currCell);
      currCell.bottomEdge.addTo(workList, currCell);
      currCell.rightEdge.addTo(workList, currCell);
    }
    return false;
  }

  // Search for the solution given the recorded paths taken for a bfs algorithm
  ArrayDeque<MazeCell> bfsPathSolution(HashMap<MazeCell, MazeCell> bfsPaths, MazeCell from,
      MazeCell to) {
    ArrayDeque<MazeCell> solution = new ArrayDeque<MazeCell>();
    MazeCell currCell = to;
    while (!bfsPaths.get(currCell).equals(from)) {
      solution.addFirst(currCell);
      currCell = bfsPaths.get(currCell);
    }
    solution.addFirst(currCell);
    solution.addFirst(from);
    return solution;
  }
}