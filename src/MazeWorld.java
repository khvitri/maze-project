import java.awt.Color;
import java.util.*;
import javalib.impworld.*;
import javalib.worldimages.*;

// Represents a maze game
class MazeWorld extends World {
  ArrayList<ArrayList<MazeCell>> mazeBoard;

  int height;
  int width;
  int cellSize;

  Random random;

  HashMap<MazeCell, MazeCell> representatives;
  ArrayList<Edge> edgesInTree;
  ArrayList<Edge> workList;

  Stack<MazeCell> depthList;
  LinkedList<MazeCell> breadthList;
  ArrayDeque<MazeCell> solution;

  HashMap<MazeCell, MazeCell> bfsPaths;

  Utils utils;

  MazeCell start;
  MazeCell end;

  boolean constructMaze;

  // Constructor for testing
  MazeWorld(int width, int height, Random random) {
    this.utils = new Utils();
    this.height = height;
    this.width = width;
    // Resize cell so that it fits on the board
    if (750 / height < 1000 / width) {
      this.cellSize = 750 / height;
    }
    else {
      this.cellSize = 1000 / width;
    }
    // Ensures that cell size is always an even number to avoid truncation issues
    // when dividing by 2. This leads to cells/lines/edges to be drawn in the wrong
    // positions
    if (this.cellSize % 2 != 0) {
      this.cellSize = this.cellSize - 1;
    }
    this.random = random;
    this.mazeBoard = this.initMazeBoard();
    this.start = this.mazeBoard.get(0).get(0);
    this.end = this.mazeBoard.get(this.height - 1).get(this.width - 1);
    this.depthList = new Stack<MazeCell>();
    this.breadthList = new LinkedList<MazeCell>();
    this.solution = new ArrayDeque<MazeCell>();
    this.bfsPaths = new HashMap<MazeCell, MazeCell>();
    this.constructMaze = this.edgesInTree.size() < this.representatives.size() - 1;
  }

  // Constructor for the actual game
  MazeWorld(int width, int height) {
    this(width, height, new Random());
  }

  // Initializes the maze board
  // EFFECT: Updates the representatives and work list as the board is being
  // initialized. Once the board is initialized, sort the work list.
  ArrayList<ArrayList<MazeCell>> initMazeBoard() {
    HashMap<MazeCell, MazeCell> repTemp = new HashMap<MazeCell, MazeCell>();
    ArrayList<Edge> workListTemp = new ArrayList<Edge>();
    ArrayList<ArrayList<MazeCell>> boardTemp = new ArrayList<ArrayList<MazeCell>>(this.height);
    for (int j = 0; j < this.height; j = j + 1) {
      boardTemp.add(new ArrayList<MazeCell>(this.width));
      for (int i = 0; i < this.width; i = i + 1) {
        MazeCell newMazeCell = new MazeCell(Color.lightGray, i * this.cellSize, j * this.cellSize);
        if (j == this.height - 1 && i == this.width - 1) {
          newMazeCell = new MazeCell(Color.red, i * this.cellSize, j * this.cellSize);
        }
        if (i - 1 >= 0) {
          Edge newLeftEdge = new Edge(boardTemp.get(j).get(i - 1), newMazeCell,
              random.nextInt(1000));
          newMazeCell.updateLeftEdge(boardTemp.get(j).get(i - 1), newLeftEdge);
          workListTemp.add(newLeftEdge);
        }
        if (j - 1 >= 0) {
          Edge newTopEdge = new Edge(boardTemp.get(j - 1).get(i), newMazeCell,
              random.nextInt(1000));
          newMazeCell.updateTopEdge(boardTemp.get(j - 1).get(i), newTopEdge);
          workListTemp.add(newTopEdge);
        }
        else if (i == 0 && j == 0) {
          newMazeCell = new MazeCell(Color.green, i * this.cellSize, j * this.cellSize);
        }
        boardTemp.get(j).add(newMazeCell);
        repTemp.put(boardTemp.get(j).get(i), boardTemp.get(j).get(i));
      }
    }
    workListTemp.sort(new LeastWeight());
    this.representatives = repTemp;
    this.edgesInTree = new ArrayList<Edge>();
    this.workList = workListTemp;
    return boardTemp;
  }

  // Draws the maze game in this maze world state
  public WorldScene makeScene() {
    WorldScene background = new WorldScene(1500, 750);
    for (int j = 0; j < this.height; j = j + 1) {
      for (int i = 0; i < this.width; i = i + 1) {
        this.mazeBoard.get(j).get(i).drawCell(background, this.cellSize);
      }
    }
    background.placeImageXY(
        new RectangleImage(this.cellSize * this.width, this.cellSize * this.height,
            OutlineMode.OUTLINE, Color.black),
        (this.cellSize * this.width) / 2, (this.cellSize * this.height) / 2);
    return background;
  }

  // EFFECT: Handles each tick of the maze world
  public void onTick() {
    if (this.constructMaze && this.depthList.isEmpty() && this.breadthList.isEmpty()) {
      for (int i = 0; i < 20; i = i + 1) {
        this.utils.kruskal(this.representatives, this.edgesInTree, this.workList);
      }
      this.constructMaze = this.edgesInTree.size() < this.representatives.size() - 1;
    }
    else if (this.constructMaze && (!this.depthList.isEmpty() || !this.breadthList.isEmpty())) {
      while (this.constructMaze) {
        this.utils.kruskal(this.representatives, this.edgesInTree, this.workList);
        this.constructMaze = this.edgesInTree.size() < this.representatives.size() - 1;
      }
    }
    if (!this.depthList.isEmpty() && !this.constructMaze) {
      for (int i = 0; i < 10; i = i + 1) {
        if (this.utils.dfsFound(this.start, this.end, this.depthList, this.solution)) {
          this.depthList = new Stack<MazeCell>();
          break;
        }
      }
    }
    if (!this.breadthList.isEmpty() && !this.constructMaze) {
      if (this.utils.bfsFound(this.start, this.end, this.breadthList, this.bfsPaths)) {
        this.breadthList = new LinkedList<MazeCell>();
        this.solution = this.utils.bfsPathSolution(this.bfsPaths, this.start, this.end);
      }
    }
    if (!this.solution.isEmpty() && this.depthList.isEmpty() && this.breadthList.isEmpty()) {
      MazeCell currCell = this.solution.removeFirst();
      currCell.color = new Color(62, 118, 204);
    }
  }

  // Handles key pressed by the user
  public void onKeyEvent(String key) {
    if (key.equals("d")) {
      this.reset();
      this.depthList.add(this.start);
    }
    if (key.equals("b")) {
      this.reset();
      this.breadthList.add(this.start);
    }
    if (key.equals("r")) {
      this.reset();
    }
    if (key.equals("n")) {
      this.newWorld();
    }
  }

  // EFFECT: Resets this maze game
  void reset() {
    for (int j = 0; j < this.height; j = j + 1) {
      for (int i = 0; i < this.width; i = i + 1) {
        MazeCell cell = this.mazeBoard.get(j).get(i);
        if (i == 0 && j == 0) {
          cell.color = Color.green;
        }
        else if (i == this.width - 1 && j == this.height - 1) {
          cell.color = Color.red;
        }
        else {
          cell.color = Color.lightGray;
        }
        cell.visited = false;
      }
    }
    this.depthList = new Stack<MazeCell>();
    this.breadthList = new LinkedList<MazeCell>();
    this.solution = new ArrayDeque<MazeCell>();
    this.bfsPaths = new HashMap<MazeCell, MazeCell>();
  }

  // EFFECT: Creates a new maze
  void newWorld() {
    this.mazeBoard = this.initMazeBoard();
    this.start = this.mazeBoard.get(0).get(0);
    this.end = this.mazeBoard.get(this.height - 1).get(this.width - 1);
    this.constructMaze = this.edgesInTree.size() < this.representatives.size() - 1;
    this.depthList = new Stack<MazeCell>();
    this.breadthList = new LinkedList<MazeCell>();
    this.solution = new ArrayDeque<MazeCell>();
    this.bfsPaths = new HashMap<MazeCell, MazeCell>();
  }
}