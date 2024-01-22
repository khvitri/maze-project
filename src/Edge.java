import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

import javalib.worldimages.*;

// Represents a weighted edge
class Edge implements IEdge {
  MazeCell mazeCell1;
  MazeCell mazeCell2;
  int weight;
  boolean minEdge;

  // Constructor for test
  Edge(MazeCell mazeCell1, MazeCell mazeCell2, int weight, boolean minEdge) {
    this.mazeCell1 = mazeCell1;
    this.mazeCell2 = mazeCell2;
    this.weight = weight;
    this.minEdge = minEdge;
  }

  // Main constructor
  Edge(MazeCell mazeCell1, MazeCell mazeCell2, int weight) {
    this(mazeCell1, mazeCell2, weight, false);
  }

  // Draws this weighted edge given the maze cell size and whether to draw the
  // edge horizontally or not
  public WorldImage drawEdge(int cellSize, boolean drawHorizontal) {
    if (!this.minEdge) {
      if (drawHorizontal) {
        return new LineImage(new Posn(cellSize, 0), Color.black);
      }
      return new LineImage(new Posn(0, cellSize), Color.black);
    }
    return new EmptyImage();
  }

  // EFFECT: Add the second cell of this edge to the given collection only if it
  // is a minimum edge
  public void addTo(Collection<MazeCell> worklist, MazeCell currCell) {
    if (this.minEdge && (this.mazeCell1.equals(currCell) || this.mazeCell2.equals(currCell))) {
      if (this.mazeCell1.equals(currCell)) {
        worklist.add(this.mazeCell2);
      }
      else {
        worklist.add(this.mazeCell1);
      }
    }
  }

  // Checks if this edge is traversable or not
  public boolean isTraversablePath(MazeCell currCell) {
    if (this.mazeCell1.equals(currCell)) {
      return this.mazeCell2.isTraversablePath(this.minEdge);
    }
    else if (this.mazeCell2.equals(currCell)) {
      return this.mazeCell1.isTraversablePath(this.minEdge);
    }
    throw new RuntimeException("No such cell found!");
  }

  // EFFECT: Records the neighboring cell of the given cell in the given hash map
  public void updateBfsPath(HashMap<MazeCell, MazeCell> bfsPath, MazeCell currCell) {
    if (this.isTraversablePath(currCell)) {
      if (this.mazeCell1.equals(currCell)) {
        bfsPath.put(this.mazeCell2, currCell);
      }
      else {
        bfsPath.put(this.mazeCell1, currCell);
      }
    }
  }

}