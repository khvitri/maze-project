import javalib.worldimages.WorldImage;
import java.util.*;

// Represents an edge
interface IEdge {

  // Draws this edge
  WorldImage drawEdge(int cellSize, boolean drawHorizontal);

  // EFFECT: Adds the neighboring cell of the given cell in this edge to the given
  // collection only if it is a minimum edge
  void addTo(Collection<MazeCell> worklist, MazeCell currCell);

  // Checks if this edge is traversable or not
  boolean isTraversablePath(MazeCell currCell);
  
  // EFFECT: Updates the given hashmap which tracks the paths visited by bfs
  void updateBfsPath(HashMap<MazeCell, MazeCell> bfsPath, MazeCell currCell);
}