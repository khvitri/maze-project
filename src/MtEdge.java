import java.util.Collection;
import java.util.HashMap;

import javalib.worldimages.*;

// Represents an empty edge
class MtEdge implements IEdge {

  // Draws this empty edge given the maze cell size and whether to draw the edge
  // horizontally or not
  public WorldImage drawEdge(int cellSize, boolean drawHorizontal) {
    return new EmptyImage();
  }

  // Adds nothing
  public void addTo(Collection<MazeCell> worklist, MazeCell currCell) {
    return;
  }

  // return false because there is no where to go in this empty edge
  public boolean isTraversablePath(MazeCell currCell) {
    return false;
  }

  // Updates nothing
  public void updateBfsPath(HashMap<MazeCell, MazeCell> bfsPath, MazeCell currCell) {
    return;
  }
}