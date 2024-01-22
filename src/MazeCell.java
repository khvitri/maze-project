import java.awt.Color;

import javalib.impworld.WorldScene;
import javalib.worldimages.*;

// Represents a maze cell
class MazeCell {
  int x;
  int y;
  Color color;
  IEdge topEdge;
  IEdge bottomEdge;
  IEdge rightEdge;
  IEdge leftEdge;
  boolean visited;

  // Constructor for testing
  MazeCell(Color color, int x, int y, IEdge topEdge, IEdge bottomEdge, IEdge rightEdge,
      IEdge leftEdge) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.topEdge = topEdge;
    this.bottomEdge = bottomEdge;
    this.rightEdge = rightEdge;
    this.leftEdge = leftEdge;
    this.visited = false;
  }

  // Main constructor
  MazeCell(Color color, int x, int y) {
    this(color, x, y, new MtEdge(), new MtEdge(), new MtEdge(), new MtEdge());
  }

  // EFFECT: Updates the left edge of this maze cell and the right edge of the
  // given maze cell to the given left edge
  void updateLeftEdge(MazeCell otherCell, Edge leftEdge) {
    if ((!this.leftEdge.equals(leftEdge) || !otherCell.rightEdge.equals(leftEdge))
        && (leftEdge.mazeCell1.equals(this) || leftEdge.mazeCell2.equals(this))
        && (leftEdge.mazeCell1.equals(otherCell) || leftEdge.mazeCell2.equals(otherCell))) {
      this.leftEdge = leftEdge;
      otherCell.rightEdge = leftEdge;
    }
  }

  // EFFECT: Updates the top edge of this maze cell and the bottom edge of the
  // given maze cell to the given top edge
  void updateTopEdge(MazeCell otherCell, Edge topEdge) {
    if ((!this.topEdge.equals(topEdge) || !otherCell.bottomEdge.equals(topEdge))
        && (topEdge.mazeCell1.equals(this) || topEdge.mazeCell2.equals(this))
        && (topEdge.mazeCell1.equals(otherCell) || topEdge.mazeCell2.equals(otherCell))) {
      this.topEdge = topEdge;
      otherCell.bottomEdge = topEdge;
    }
  }

  // EFFECT: Draws this maze cell and places it on the given background
  void drawCell(WorldScene background, int cellSize) {
    background.placeImageXY(new RectangleImage(cellSize, cellSize, OutlineMode.SOLID, this.color),
        this.x + (cellSize / 2), this.y + (cellSize / 2));
    background.placeImageXY(this.topEdge.drawEdge(cellSize, true), this.x + (cellSize / 2), this.y);
    background.placeImageXY(this.leftEdge.drawEdge(cellSize, false), this.x,
        this.y + (cellSize / 2));
  }
  
  // Checks if this cell is traversable or not
  boolean isTraversablePath(boolean minEdge) {
    return !this.visited && minEdge;
  }
}