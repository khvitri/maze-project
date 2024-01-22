import java.awt.Color;
import java.util.*;
import tester.Tester;
import javalib.worldimages.*;
import javalib.impworld.*;

class ExampleMaze {
  MazeWorld aMaze2x2;
  MazeWorld aMaze3x3;

  // aMaze2x2
  MazeCell cell1AM2;
  MazeCell cell2AM2;
  MazeCell cell3AM2;
  MazeCell cell4AM2;
  Edge edge12AM2;
  Edge edge13AM2;
  Edge edge24AM2;
  Edge edge34AM2;
  ArrayList<ArrayList<MazeCell>> boardAM2;
  HashMap<MazeCell, MazeCell> repAM2;
  ArrayList<Edge> edgesITAM2;
  ArrayList<Edge> workListAM2;

  // aMaze3x3
  MazeCell cell1AM3;
  MazeCell cell2AM3;
  MazeCell cell3AM3;
  MazeCell cell4AM3;
  MazeCell cell5AM3;
  MazeCell cell6AM3;
  MazeCell cell7AM3;
  MazeCell cell8AM3;
  MazeCell cell9AM3;
  Edge edge12AM3;
  Edge edge23AM3;
  Edge edge14AM3;
  Edge edge45AM3;
  Edge edge25AM3;
  Edge edge56AM3;
  Edge edge36AM3;
  Edge edge47AM3;
  Edge edge78AM3;
  Edge edge58AM3;
  Edge edge89AM3;
  Edge edge69AM3;
  ArrayList<ArrayList<MazeCell>> boardAM3;
  HashMap<MazeCell, MazeCell> repAM3;
  ArrayList<Edge> edgesITAM3;
  ArrayList<Edge> workListAM3;

  // Single maze cells and edges
  MazeCell cell1;
  MazeCell cell2;
  MazeCell cell3;
  MazeCell cell4;
  Edge edge12;

  // Image
  WorldScene bgCell1;
  WorldScene bgCell1AM2;
  WorldScene bgCell2AM2;
  WorldScene bgCell3AM2;
  WorldScene bgCell4AM2;
  WorldScene bgCell5AM3;

  WorldImage cell1Img;

  WorldImage greenCell2x2;
  WorldImage grayCell2x2;
  WorldImage redCell2x2;
  WorldImage hLine2x2;
  WorldImage vLine2x2;

  WorldImage greenCell3x3;
  WorldImage grayCell3x3;
  WorldImage redCell3x3;
  WorldImage hLine3x3;
  WorldImage vLine3x3;

  void initMazeExample() {
    this.aMaze2x2 = new MazeWorld(2, 2, new Random(2));
    this.aMaze3x3 = new MazeWorld(3, 3, new Random(2));

    this.cell1AM2 = new MazeCell(Color.green, 0, 0);
    this.cell2AM2 = new MazeCell(Color.lightGray, 374, 0);
    this.cell3AM2 = new MazeCell(Color.lightGray, 0, 374);
    this.cell4AM2 = new MazeCell(Color.red, 374, 374);
    this.edge12AM2 = new Edge(this.cell1AM2, this.cell2AM2, 108, true);
    this.edge13AM2 = new Edge(this.cell1AM2, this.cell3AM2, 372, false);
    this.edge24AM2 = new Edge(this.cell2AM2, this.cell4AM2, 67, true);
    this.edge34AM2 = new Edge(this.cell3AM2, this.cell4AM2, 40, true);
    this.cell2AM2.updateLeftEdge(this.cell1AM2, this.edge12AM2);
    this.cell3AM2.updateTopEdge(this.cell1AM2, this.edge13AM2);
    this.cell4AM2.updateLeftEdge(this.cell3AM2, this.edge34AM2);
    this.cell4AM2.updateTopEdge(this.cell2AM2, this.edge24AM2);
    this.boardAM2 = new ArrayList<ArrayList<MazeCell>>(
        Arrays.asList(new ArrayList<MazeCell>(Arrays.asList(this.cell1AM2, this.cell2AM2)),
            new ArrayList<MazeCell>(Arrays.asList(this.cell3AM2, this.cell4AM2))));
    this.repAM2 = new HashMap<MazeCell, MazeCell>();
    this.repAM2.put(this.cell1AM2, this.cell1AM2);
    this.repAM2.put(this.cell2AM2, this.cell1AM2);
    this.repAM2.put(this.cell3AM2, this.cell2AM2);
    this.repAM2.put(this.cell4AM2, this.cell3AM2);
    this.edgesITAM2 = new ArrayList<Edge>(
        Arrays.asList(this.edge34AM2, this.edge24AM2, this.edge12AM2));
    this.workListAM2 = new ArrayList<Edge>(
        Arrays.asList(this.edge34AM2, this.edge24AM2, this.edge12AM2, this.edge13AM2));

    this.cell1AM3 = new MazeCell(Color.green, 0, 0);
    this.cell2AM3 = new MazeCell(Color.lightGray, 250, 0);
    this.cell3AM3 = new MazeCell(Color.lightGray, 500, 0);
    this.cell4AM3 = new MazeCell(Color.lightGray, 0, 250);
    this.cell5AM3 = new MazeCell(Color.lightGray, 250, 250);
    this.cell6AM3 = new MazeCell(Color.lightGray, 500, 250);
    this.cell7AM3 = new MazeCell(Color.lightGray, 0, 500);
    this.cell8AM3 = new MazeCell(Color.lightGray, 250, 500);
    this.cell9AM3 = new MazeCell(Color.red, 500, 500);
    this.edge12AM3 = new Edge(this.cell1AM3, this.cell2AM3, 108, true);
    this.edge23AM3 = new Edge(this.cell2AM3, this.cell3AM3, 372, true);
    this.edge14AM3 = new Edge(this.cell1AM3, this.cell4AM3, 40, true);
    this.edge45AM3 = new Edge(this.cell4AM3, this.cell5AM3, 67, true);
    this.edge25AM3 = new Edge(this.cell2AM3, this.cell5AM3, 389, false);
    this.edge56AM3 = new Edge(this.cell5AM3, this.cell6AM3, 350, false);
    this.edge36AM3 = new Edge(this.cell3AM3, this.cell6AM3, 606, false);
    this.edge47AM3 = new Edge(this.cell4AM3, this.cell7AM3, 719, true);
    this.edge78AM3 = new Edge(this.cell7AM3, this.cell8AM3, 847, false);
    this.edge58AM3 = new Edge(this.cell5AM3, this.cell8AM3, 68, true);
    this.edge89AM3 = new Edge(this.cell8AM3, this.cell9AM3, 94, true);
    this.edge69AM3 = new Edge(this.cell6AM3, this.cell9AM3, 86, true);
    this.cell2AM3.updateLeftEdge(this.cell1AM3, this.edge12AM3);
    this.cell3AM3.updateLeftEdge(this.cell2AM3, this.edge23AM3);
    this.cell4AM3.updateTopEdge(this.cell1AM3, this.edge14AM3);
    this.cell5AM3.updateLeftEdge(this.cell4AM3, this.edge45AM3);
    this.cell5AM3.updateTopEdge(this.cell2AM3, this.edge25AM3);
    this.cell6AM3.updateLeftEdge(this.cell5AM3, this.edge56AM3);
    this.cell6AM3.updateTopEdge(this.cell3AM3, this.edge36AM3);
    this.cell7AM3.updateTopEdge(this.cell4AM3, this.edge47AM3);
    this.cell8AM3.updateLeftEdge(this.cell7AM3, this.edge78AM3);
    this.cell8AM3.updateTopEdge(this.cell5AM3, this.edge58AM3);
    this.cell9AM3.updateLeftEdge(this.cell8AM3, this.edge89AM3);
    this.cell9AM3.updateTopEdge(this.cell6AM3, this.edge69AM3);
    this.boardAM3 = new ArrayList<ArrayList<MazeCell>>(Arrays.asList(
        new ArrayList<MazeCell>(Arrays.asList(this.cell1AM3, this.cell2AM3, this.cell3AM3)),
        new ArrayList<MazeCell>(Arrays.asList(this.cell4AM3, this.cell5AM3, this.cell6AM3)),
        new ArrayList<MazeCell>(Arrays.asList(this.cell7AM3, this.cell8AM3, this.cell9AM3))));
    this.repAM3 = new HashMap<MazeCell, MazeCell>();
    this.repAM3.put(this.cell1AM3, this.cell1AM3);
    this.repAM3.put(this.cell2AM3, this.cell1AM3);
    this.repAM3.put(this.cell3AM3, this.cell1AM3);
    this.repAM3.put(this.cell4AM3, this.cell1AM3);
    this.repAM3.put(this.cell5AM3, this.cell1AM3);
    this.repAM3.put(this.cell6AM3, this.cell1AM3);
    this.repAM3.put(this.cell7AM3, this.cell1AM3);
    this.repAM3.put(this.cell8AM3, this.cell1AM3);
    this.repAM3.put(this.cell9AM3, this.cell6AM3);
    this.edgesITAM3 = new ArrayList<Edge>(
        Arrays.asList(this.edge14AM3, this.edge45AM3, this.edge58AM3, this.edge69AM3,
            this.edge89AM3, this.edge12AM3, this.edge23AM3, this.edge47AM3));
    this.workListAM3 = new ArrayList<Edge>(Arrays.asList(this.edge14AM3, this.edge45AM3,
        this.edge58AM3, this.edge69AM3, this.edge89AM3, this.edge12AM3, this.edge56AM3,
        this.edge23AM3, this.edge25AM3, this.edge36AM3, this.edge47AM3, this.edge78AM3));

    this.cell1 = new MazeCell(Color.red, 0, 0);
    this.cell2 = new MazeCell(Color.yellow, 0, 0);
    this.cell3 = new MazeCell(Color.green, 0, 0);
    this.cell4 = new MazeCell(Color.blue, 0, 0);
    this.edge12 = new Edge(this.cell1, this.cell2, 0);

    this.bgCell1 = new WorldScene(1500, 750);
    this.bgCell1AM2 = new WorldScene(1500, 750);
    this.bgCell2AM2 = new WorldScene(1500, 750);
    this.bgCell3AM2 = new WorldScene(1500, 750);
    this.bgCell4AM2 = new WorldScene(1500, 750);
    this.bgCell5AM3 = new WorldScene(1500, 750);

    this.cell1Img = new RectangleImage(750, 750, OutlineMode.SOLID, Color.red);

    this.greenCell2x2 = new RectangleImage(374, 374, OutlineMode.SOLID, Color.green);
    this.grayCell2x2 = new RectangleImage(374, 374, OutlineMode.SOLID, Color.lightGray);
    this.redCell2x2 = new RectangleImage(374, 374, OutlineMode.SOLID, Color.red);
    this.hLine2x2 = new LineImage(new Posn(374, 0), Color.black);
    this.vLine2x2 = new LineImage(new Posn(0, 374), Color.black);

    this.greenCell3x3 = new RectangleImage(250, 250, OutlineMode.SOLID, Color.green);
    this.grayCell3x3 = new RectangleImage(250, 250, OutlineMode.SOLID, Color.lightGray);
    this.redCell3x3 = new RectangleImage(250, 250, OutlineMode.SOLID, Color.red);
    this.hLine3x3 = new LineImage(new Posn(250, 0), Color.black);
    this.vLine3x3 = new LineImage(new Posn(0, 250), Color.black);
  }

  // test for the display of the game
  void testMaze(Tester t) {
    this.initMazeExample();
    MazeWorld starterWorld = new MazeWorld(80, 80);
    starterWorld.bigBang(1500, 750, 0.0001);
  }

  // test for updateLeftEdge method for a maze cell
  void testUpdateLeftEdge(Tester t) {
    this.initMazeExample();

    t.checkExpect(this.cell1, new MazeCell(Color.red, 0, 0));
    t.checkExpect(this.cell2, new MazeCell(Color.yellow, 0, 0));
    t.checkExpect(this.cell3, new MazeCell(Color.green, 0, 0));
    t.checkExpect(this.cell4, new MazeCell(Color.blue, 0, 0));

    this.cell1.updateLeftEdge(this.cell2, this.edge12);
    this.cell3.updateLeftEdge(this.cell4, this.edge12);

    t.checkExpect(this.cell1,
        new MazeCell(Color.red, 0, 0, new MtEdge(), new MtEdge(), new MtEdge(), this.edge12));
    t.checkExpect(this.cell2,
        new MazeCell(Color.yellow, 0, 0, new MtEdge(), new MtEdge(), this.edge12, new MtEdge()));
    t.checkExpect(this.cell3, new MazeCell(Color.green, 0, 0));
    t.checkExpect(this.cell4, new MazeCell(Color.blue, 0, 0));
  }

  // test for updateTopEdge method for a maze cell
  void testUpdateTopEdge(Tester t) {
    this.initMazeExample();

    t.checkExpect(this.cell1, new MazeCell(Color.red, 0, 0));
    t.checkExpect(this.cell2, new MazeCell(Color.yellow, 0, 0));
    t.checkExpect(this.cell3, new MazeCell(Color.green, 0, 0));
    t.checkExpect(this.cell4, new MazeCell(Color.blue, 0, 0));

    this.cell1.updateTopEdge(this.cell2, this.edge12);
    this.cell3.updateTopEdge(this.cell4, this.edge12);

    t.checkExpect(this.cell1,
        new MazeCell(Color.red, 0, 0, this.edge12, new MtEdge(), new MtEdge(), new MtEdge()));
    t.checkExpect(this.cell2,
        new MazeCell(Color.yellow, 0, 0, new MtEdge(), this.edge12, new MtEdge(), new MtEdge()));
    t.checkExpect(this.cell3, new MazeCell(Color.green, 0, 0));
    t.checkExpect(this.cell4, new MazeCell(Color.blue, 0, 0));
  }

  // test for drawCell method for a maze cell
  void testDrawCell(Tester t) {
    this.initMazeExample();

    t.checkExpect(this.bgCell1, new WorldScene(1500, 750));
    t.checkExpect(this.bgCell1AM2, new WorldScene(1500, 750));
    t.checkExpect(this.bgCell2AM2, new WorldScene(1500, 750));
    t.checkExpect(this.bgCell3AM2, new WorldScene(1500, 750));
    t.checkExpect(this.bgCell4AM2, new WorldScene(1500, 750));
    t.checkExpect(this.bgCell5AM3, new WorldScene(1500, 750));

    this.cell1.drawCell(this.bgCell1, 750);
    this.cell1AM2.drawCell(this.bgCell1AM2, 374);
    this.cell2AM2.drawCell(this.bgCell2AM2, 374);
    this.cell3AM2.drawCell(this.bgCell3AM2, 374);
    this.cell4AM2.drawCell(this.bgCell4AM2, 374);
    this.cell5AM3.drawCell(this.bgCell5AM3, 250);

    WorldScene newBgCell1 = new WorldScene(1500, 750);
    WorldScene newBgCell1AM2 = new WorldScene(1500, 750);
    WorldScene newBgCell2AM2 = new WorldScene(1500, 750);
    WorldScene newBgCell3AM2 = new WorldScene(1500, 750);
    WorldScene newBgCell4AM2 = new WorldScene(1500, 750);
    WorldScene newBgCell5AM3 = new WorldScene(1500, 750);
    newBgCell1.placeImageXY(this.cell1Img, 375, 375);
    newBgCell1AM2.placeImageXY(this.greenCell2x2, 187, 187);
    newBgCell2AM2.placeImageXY(this.grayCell2x2, 561, 187);
    newBgCell2AM2.placeImageXY(this.vLine2x2, 0, 187);
    newBgCell3AM2.placeImageXY(this.grayCell2x2, 187, 561);
    newBgCell3AM2.placeImageXY(this.hLine2x2, 187, 374);
    newBgCell4AM2.placeImageXY(this.redCell2x2, 561, 561);
    newBgCell5AM3.placeImageXY(this.grayCell3x3, 375, 375);
    newBgCell5AM3.placeImageXY(this.hLine3x3, 375, 250);

    t.checkExpect(this.bgCell1, newBgCell1);
    t.checkExpect(this.bgCell1AM2, newBgCell1AM2);
    t.checkExpect(this.bgCell2AM2, newBgCell2AM2);
    t.checkExpect(this.bgCell3AM2, newBgCell3AM2);
    t.checkExpect(this.bgCell4AM2, newBgCell4AM2);
    t.checkExpect(this.bgCell5AM3, newBgCell5AM3);
  }

  // test for isTraversablePath for a maze cell
  void testIsTraversablepath(Tester t) {
    this.initMazeExample();

    t.checkExpect(this.cell1.isTraversablePath(true), true);
    t.checkExpect(this.cell1.isTraversablePath(false), false);

    this.cell2.visited = true;

    t.checkExpect(this.cell2.isTraversablePath(false), false);
    t.checkExpect(this.cell2.isTraversablePath(true), false);
  }

  // test for drawEdge method for an edge
  void testDrawEdge(Tester t) {
    this.initMazeExample();

    t.checkExpect(new MtEdge().drawEdge(374, false), new EmptyImage());
    t.checkExpect(new MtEdge().drawEdge(374, true), new EmptyImage());
    t.checkExpect(this.edge12AM2.drawEdge(374, false), new EmptyImage());
    t.checkExpect(this.edge13AM2.drawEdge(374, true), this.hLine2x2);
    t.checkExpect(this.edge12AM3.drawEdge(250, false), new EmptyImage());
    t.checkExpect(this.edge56AM3.drawEdge(250, false), this.vLine3x3);
    t.checkExpect(this.edge36AM3.drawEdge(250, true), this.hLine3x3);
    t.checkExpect(this.edge14AM3.drawEdge(250, true), new EmptyImage());
  }

  // test for addTo method for an edge
  void testAddTo(Tester t) {
    this.initMazeExample();

    this.edge12.minEdge = true;
    Stack<MazeCell> depthList = new Stack<MazeCell>();
    LinkedList<MazeCell> breadthList = new LinkedList<MazeCell>();

    this.edge12.addTo(depthList, this.cell1);
    this.edge12.addTo(depthList, this.cell2);
    this.edge12.addTo(depthList, this.cell3);
    this.edge12.addTo(breadthList, this.cell1);
    this.edge12.addTo(breadthList, this.cell2);
    this.edge12.addTo(breadthList, this.cell3);

    Stack<MazeCell> newDepthList = new Stack<MazeCell>();
    newDepthList.push(this.cell2);
    newDepthList.push(this.cell1);

    LinkedList<MazeCell> newBreadthList = new LinkedList<MazeCell>();
    newBreadthList.addLast(this.cell2);
    newBreadthList.addLast(this.cell1);

    t.checkExpect(depthList, newDepthList);
    t.checkExpect(breadthList, newBreadthList);
  }

  // test for isTraversablePath method for an edge
  void testIsTraversablePath(Tester t) {
    this.initMazeExample();

    t.checkExpect(this.edge12.isTraversablePath(this.cell1), false);
    t.checkExpect(this.edge12AM2.isTraversablePath(this.cell1AM2), true);
    t.checkException(new RuntimeException("No such cell found!"), this.edge12AM2,
        "isTraversablePath", this.cell1);
  }

  // test for updateBfsPath method for an edge
  void testUpdateBfsPath(Tester t) {
    this.initMazeExample();

    HashMap<MazeCell, MazeCell> bfsPath1 = new HashMap<MazeCell, MazeCell>();
    HashMap<MazeCell, MazeCell> bfsPath2 = new HashMap<MazeCell, MazeCell>();

    this.edge12.updateBfsPath(bfsPath1, this.cell1);
    this.edge12AM2.updateBfsPath(bfsPath2, this.cell1AM2);

    HashMap<MazeCell, MazeCell> newBfsPath2 = new HashMap<MazeCell, MazeCell>();
    newBfsPath2.put(this.cell2AM2, this.cell1AM2);

    t.checkExpect(bfsPath1, new HashMap<MazeCell, MazeCell>());
    t.checkExpect(bfsPath2, newBfsPath2);
  }

  // test for find method in Utils class
  void testFind(Tester t) {
    this.initMazeExample();

    HashMap<MazeCell, MazeCell> smallRep = new HashMap<MazeCell, MazeCell>();
    smallRep.put(this.cell1, this.cell1);
    smallRep.put(this.cell2, this.cell2);

    t.checkExpect(new Utils().find(this.repAM2, this.cell4AM2), this.cell1AM2);
    t.checkExpect(new Utils().find(this.repAM2, this.cell1AM2), this.cell1AM2);
    t.checkExpect(new Utils().find(this.repAM2, this.cell2AM2), this.cell1AM2);
    t.checkExpect(new Utils().find(this.repAM3, this.cell2AM3), this.cell1AM3);
    t.checkExpect(new Utils().find(smallRep, this.cell2), this.cell2);
  }

  // test for union method in Utils class
  void testUnion(Tester t) {
    this.initMazeExample();

    HashMap<MazeCell, MazeCell> initRepAM2 = new HashMap<MazeCell, MazeCell>();
    initRepAM2.put(this.cell1AM2, this.cell1AM2);
    initRepAM2.put(this.cell2AM2, this.cell2AM2);
    initRepAM2.put(this.cell3AM2, this.cell3AM2);
    initRepAM2.put(this.cell4AM2, this.cell4AM2);

    HashMap<MazeCell, MazeCell> smallRep = new HashMap<MazeCell, MazeCell>();
    smallRep.put(this.cell1, this.cell1);
    smallRep.put(this.cell2, this.cell2);

    new Utils().union(initRepAM2, this.cell1AM2, this.cell2AM2);
    new Utils().union(smallRep, this.cell2, this.cell1);

    HashMap<MazeCell, MazeCell> newRepAM2 = new HashMap<MazeCell, MazeCell>();
    newRepAM2.put(this.cell1AM2, this.cell1AM2);
    newRepAM2.put(this.cell2AM2, this.cell1AM2);
    newRepAM2.put(this.cell3AM2, this.cell3AM2);
    newRepAM2.put(this.cell4AM2, this.cell4AM2);

    HashMap<MazeCell, MazeCell> newSmallRep = new HashMap<MazeCell, MazeCell>();
    newSmallRep.put(this.cell1, this.cell2);
    newSmallRep.put(this.cell2, this.cell2);

    t.checkExpect(initRepAM2, newRepAM2);
    t.checkExpect(smallRep, newSmallRep);
  }

  // test for kruskal method in Utils class
  void testKruskal(Tester t) {
    this.initMazeExample();

    HashMap<MazeCell, MazeCell> initRepAM2 = new HashMap<MazeCell, MazeCell>();
    initRepAM2.put(this.cell1AM2, this.cell1AM2);
    initRepAM2.put(this.cell2AM2, this.cell2AM2);
    initRepAM2.put(this.cell3AM2, this.cell3AM2);
    initRepAM2.put(this.cell4AM2, this.cell4AM2);
    ArrayList<Edge> initEdgesITAM2 = new ArrayList<Edge>();

    HashMap<MazeCell, MazeCell> initRepAM3 = new HashMap<MazeCell, MazeCell>();
    initRepAM3.put(this.cell1AM3, this.cell1AM3);
    initRepAM3.put(this.cell2AM3, this.cell2AM3);
    initRepAM3.put(this.cell3AM3, this.cell3AM3);
    initRepAM3.put(this.cell4AM3, this.cell4AM3);
    initRepAM3.put(this.cell5AM3, this.cell5AM3);
    initRepAM3.put(this.cell6AM3, this.cell6AM3);
    initRepAM3.put(this.cell7AM3, this.cell7AM3);
    initRepAM3.put(this.cell8AM3, this.cell8AM3);
    initRepAM3.put(this.cell9AM3, this.cell9AM3);
    ArrayList<Edge> initEdgesITAM3 = new ArrayList<Edge>();

    HashMap<MazeCell, MazeCell> emptyRep = new HashMap<MazeCell, MazeCell>();
    ArrayList<Edge> emptyEdgesInTree = new ArrayList<Edge>();
    ArrayList<Edge> emptyWorkList = new ArrayList<Edge>();

    new Utils().kruskal(initRepAM2, initEdgesITAM2, this.workListAM2);
    new Utils().kruskal(initRepAM3, initEdgesITAM3, this.workListAM3);
    new Utils().kruskal(emptyRep, emptyEdgesInTree, emptyWorkList);

    HashMap<MazeCell, MazeCell> newRepAM2 = new HashMap<MazeCell, MazeCell>();
    newRepAM2.put(this.cell1AM2, this.cell1AM2);
    newRepAM2.put(this.cell2AM2, this.cell2AM2);
    newRepAM2.put(this.cell3AM2, this.cell3AM2);
    newRepAM2.put(this.cell4AM2, this.cell3AM2);

    HashMap<MazeCell, MazeCell> newRepAM3 = new HashMap<MazeCell, MazeCell>();
    newRepAM3.put(this.cell1AM3, this.cell1AM3);
    newRepAM3.put(this.cell2AM3, this.cell2AM3);
    newRepAM3.put(this.cell3AM3, this.cell3AM3);
    newRepAM3.put(this.cell4AM3, this.cell1AM3);
    newRepAM3.put(this.cell5AM3, this.cell5AM3);
    newRepAM3.put(this.cell6AM3, this.cell6AM3);
    newRepAM3.put(this.cell7AM3, this.cell7AM3);
    newRepAM3.put(this.cell8AM3, this.cell8AM3);
    newRepAM3.put(this.cell9AM3, this.cell9AM3);

    t.checkExpect(initRepAM2, newRepAM2);
    t.checkExpect(initEdgesITAM2, new ArrayList<Edge>(Arrays.asList(this.edge34AM2)));
    t.checkExpect(initRepAM3, newRepAM3);
    t.checkExpect(initEdgesITAM3, new ArrayList<Edge>(Arrays.asList(this.edge14AM3)));
    t.checkExpect(emptyRep, new HashMap<MazeCell, MazeCell>());
    t.checkExpect(emptyEdgesInTree, new ArrayList<Edge>());
  }

  // test for initMazeBoard method for the maze game
  void testInitMazeBoard(Tester t) {
    this.initMazeExample();

    this.edge12AM2.minEdge = false;
    this.edge13AM2.minEdge = false;
    this.edge24AM2.minEdge = false;
    this.edge34AM2.minEdge = false;

    // test for 2x2
    t.checkExpect(this.aMaze2x2.mazeBoard, this.boardAM2);
    // Checking for the values of the hash table
    for (int j = 0; j < this.aMaze2x2.mazeBoard.size(); j = j + 1) {
      for (int i = 0; i < this.aMaze2x2.mazeBoard.size(); i = i + 1) {
        t.checkExpect(this.aMaze2x2.representatives.get(this.aMaze2x2.mazeBoard.get(j).get(i)),
            this.aMaze2x2.mazeBoard.get(j).get(i));
      }
    }
    t.checkExpect(this.aMaze2x2.edgesInTree, new ArrayList<Edge>());
    t.checkExpect(this.aMaze2x2.workList, this.workListAM2);

    Edge newEdge12AM2 = new Edge(this.cell1AM2, this.cell2AM2, 389, false);
    Edge newEdge13AM2 = new Edge(this.cell1AM2, this.cell3AM2, 350, false);
    Edge newEdge24AM2 = new Edge(this.cell2AM2, this.cell4AM2, 719, false);
    Edge newEdge34AM2 = new Edge(this.cell3AM2, this.cell4AM2, 606, false);
    this.cell2AM2.updateLeftEdge(this.cell1AM2, newEdge12AM2);
    this.cell3AM2.updateTopEdge(this.cell1AM2, newEdge13AM2);
    this.cell4AM2.updateLeftEdge(this.cell3AM2, newEdge34AM2);
    this.cell4AM2.updateTopEdge(this.cell2AM2, newEdge24AM2);
    ArrayList<ArrayList<MazeCell>> newBoardAM2 = new ArrayList<ArrayList<MazeCell>>(
        Arrays.asList(new ArrayList<MazeCell>(Arrays.asList(this.cell1AM2, this.cell2AM2)),
            new ArrayList<MazeCell>(Arrays.asList(this.cell3AM2, this.cell4AM2))));
    HashMap<MazeCell, MazeCell> newRepAM2 = new HashMap<MazeCell, MazeCell>();
    newRepAM2.put(this.cell1AM2, this.cell1AM2);
    newRepAM2.put(this.cell2AM2, this.cell2AM2);
    newRepAM2.put(this.cell3AM2, this.cell3AM2);
    newRepAM2.put(this.cell4AM2, this.cell4AM2);
    ArrayList<Edge> newWorkListAM2 = new ArrayList<Edge>(
        Arrays.asList(newEdge13AM2, newEdge12AM2, newEdge34AM2, newEdge24AM2));

    ArrayList<ArrayList<MazeCell>> mutateBoard2x2 = this.aMaze2x2.initMazeBoard();
    t.checkExpect(mutateBoard2x2, newBoardAM2);
    // Checking for the values of the hash table
    for (int j = 0; j < this.aMaze2x2.mazeBoard.size(); j++) {
      for (int i = 0; i < this.aMaze2x2.mazeBoard.size(); i++) {
        t.checkExpect(this.aMaze2x2.representatives.get(mutateBoard2x2.get(j).get(i)),
            newRepAM2.get(newBoardAM2.get(j).get(i)));
      }
    }
    t.checkExpect(this.aMaze2x2.workList, newWorkListAM2);

    // test for 3x3
    this.edge12AM3.minEdge = false;
    this.edge23AM3.minEdge = false;
    this.edge14AM3.minEdge = false;
    this.edge45AM3.minEdge = false;
    this.edge25AM3.minEdge = false;
    this.edge56AM3.minEdge = false;
    this.edge36AM3.minEdge = false;
    this.edge47AM3.minEdge = false;
    this.edge78AM3.minEdge = false;
    this.edge58AM3.minEdge = false;
    this.edge89AM3.minEdge = false;
    this.edge69AM3.minEdge = false;

    t.checkExpect(this.aMaze3x3.mazeBoard, this.boardAM3);
    // Checking for the values of the hash table
    for (int j = 0; j < this.aMaze3x3.mazeBoard.size(); j = j + 1) {
      for (int i = 0; i < this.aMaze3x3.mazeBoard.size(); i = i + 1) {
        t.checkExpect(this.aMaze3x3.representatives.get(this.aMaze3x3.mazeBoard.get(j).get(i)),
            this.aMaze3x3.mazeBoard.get(j).get(i));
      }
    }
    t.checkExpect(this.aMaze3x3.edgesInTree, new ArrayList<Edge>());
    t.checkExpect(this.aMaze3x3.workList, this.workListAM3);

    Edge newEdge12AM3 = new Edge(this.cell1AM3, this.cell2AM3, 434, false);
    Edge newEdge23AM3 = new Edge(this.cell2AM3, this.cell3AM3, 614, false);
    Edge newEdge14AM3 = new Edge(this.cell1AM3, this.cell4AM3, 514, false);
    Edge newEdge45AM3 = new Edge(this.cell4AM3, this.cell5AM3, 416, false);
    Edge newEdge25AM3 = new Edge(this.cell2AM3, this.cell5AM3, 67, false);
    Edge newEdge56AM3 = new Edge(this.cell5AM3, this.cell6AM3, 399, false);
    Edge newEdge36AM3 = new Edge(this.cell3AM3, this.cell6AM3, 530, false);
    Edge newEdge47AM3 = new Edge(this.cell4AM3, this.cell7AM3, 231, false);
    Edge newEdge78AM3 = new Edge(this.cell7AM3, this.cell8AM3, 678, false);
    Edge newEdge58AM3 = new Edge(this.cell5AM3, this.cell8AM3, 647, false);
    Edge newEdge89AM3 = new Edge(this.cell8AM3, this.cell9AM3, 599, false);
    Edge newEdge69AM3 = new Edge(this.cell6AM3, this.cell9AM3, 642, false);
    this.cell2AM3.updateLeftEdge(this.cell1AM3, newEdge12AM3);
    this.cell3AM3.updateLeftEdge(this.cell2AM3, newEdge23AM3);
    this.cell4AM3.updateTopEdge(this.cell1AM3, newEdge14AM3);
    this.cell5AM3.updateLeftEdge(this.cell4AM3, newEdge45AM3);
    this.cell5AM3.updateTopEdge(this.cell2AM3, newEdge25AM3);
    this.cell6AM3.updateLeftEdge(this.cell5AM3, newEdge56AM3);
    this.cell6AM3.updateTopEdge(this.cell3AM3, newEdge36AM3);
    this.cell7AM3.updateTopEdge(this.cell4AM3, newEdge47AM3);
    this.cell8AM3.updateLeftEdge(this.cell7AM3, newEdge78AM3);
    this.cell8AM3.updateTopEdge(this.cell5AM3, newEdge58AM3);
    this.cell9AM3.updateLeftEdge(this.cell8AM3, newEdge89AM3);
    this.cell9AM3.updateTopEdge(this.cell6AM3, newEdge69AM3);
    ArrayList<ArrayList<MazeCell>> newBoardAM3 = new ArrayList<ArrayList<MazeCell>>(Arrays.asList(
        new ArrayList<MazeCell>(Arrays.asList(this.cell1AM3, this.cell2AM3, this.cell3AM3)),
        new ArrayList<MazeCell>(Arrays.asList(this.cell4AM3, this.cell5AM3, this.cell6AM3)),
        new ArrayList<MazeCell>(Arrays.asList(this.cell7AM3, this.cell8AM3, this.cell9AM3))));
    HashMap<MazeCell, MazeCell> newRepAM3 = new HashMap<MazeCell, MazeCell>();
    newRepAM3.put(this.cell1AM3, this.cell1AM3);
    newRepAM3.put(this.cell2AM3, this.cell2AM3);
    newRepAM3.put(this.cell3AM3, this.cell3AM3);
    newRepAM3.put(this.cell4AM3, this.cell4AM3);
    newRepAM3.put(this.cell5AM3, this.cell5AM3);
    newRepAM3.put(this.cell6AM3, this.cell6AM3);
    newRepAM3.put(this.cell7AM3, this.cell7AM3);
    newRepAM3.put(this.cell8AM3, this.cell8AM3);
    newRepAM3.put(this.cell9AM3, this.cell9AM3);
    ArrayList<Edge> newWorkListAM3 = new ArrayList<Edge>(Arrays.asList(newEdge25AM3, newEdge47AM3,
        newEdge56AM3, newEdge45AM3, newEdge12AM3, newEdge14AM3, newEdge36AM3, newEdge89AM3,
        newEdge23AM3, newEdge69AM3, newEdge58AM3, newEdge78AM3));

    ArrayList<ArrayList<MazeCell>> mutateBoard3x3 = this.aMaze3x3.initMazeBoard();
    t.checkExpect(mutateBoard3x3, newBoardAM3);
    for (int j = 0; j < this.aMaze3x3.mazeBoard.size(); j = j + 1) {
      for (int i = 0; i < this.aMaze3x3.mazeBoard.size(); i = i + 1) {
        t.checkExpect(this.aMaze3x3.representatives.get(mutateBoard3x3.get(j).get(i)),
            mutateBoard3x3.get(j).get(i));
      }
    }
    t.checkExpect(this.aMaze3x3.workList, newWorkListAM3);
  }

  // test for makeScene method for a maze game
  void testMakeScene(Tester t) {
    this.initMazeExample();

    this.edge12AM2.minEdge = false;
    this.edge13AM2.minEdge = false;
    this.edge24AM2.minEdge = false;
    this.edge34AM2.minEdge = false;

    WorldScene bgAM2 = new WorldScene(1500, 750);
    this.cell1AM2.drawCell(bgAM2, 374);
    this.cell2AM2.drawCell(bgAM2, 374);
    this.cell3AM2.drawCell(bgAM2, 374);
    this.cell4AM2.drawCell(bgAM2, 374);
    bgAM2.placeImageXY(new RectangleImage(748, 748, OutlineMode.OUTLINE, Color.black), 374, 374);

    this.edge12AM3.minEdge = false;
    this.edge23AM3.minEdge = false;
    this.edge14AM3.minEdge = false;
    this.edge45AM3.minEdge = false;
    this.edge25AM3.minEdge = false;
    this.edge56AM3.minEdge = false;
    this.edge36AM3.minEdge = false;
    this.edge47AM3.minEdge = false;
    this.edge78AM3.minEdge = false;
    this.edge58AM3.minEdge = false;
    this.edge89AM3.minEdge = false;
    this.edge69AM3.minEdge = false;

    WorldScene bgAM3 = new WorldScene(1500, 750);
    this.cell1AM3.drawCell(bgAM3, 250);
    this.cell2AM3.drawCell(bgAM3, 250);
    this.cell3AM3.drawCell(bgAM3, 250);
    this.cell4AM3.drawCell(bgAM3, 250);
    this.cell5AM3.drawCell(bgAM3, 250);
    this.cell6AM3.drawCell(bgAM3, 250);
    this.cell7AM3.drawCell(bgAM3, 250);
    this.cell8AM3.drawCell(bgAM3, 250);
    this.cell9AM3.drawCell(bgAM3, 250);
    bgAM3.placeImageXY(new RectangleImage(750, 750, OutlineMode.OUTLINE, Color.black), 375, 375);

    t.checkExpect(this.aMaze2x2.makeScene(), bgAM2);
    t.checkExpect(this.aMaze3x3.makeScene(), bgAM3);
  }

  // test for onKeyEvent method for a maze game. This also test reset and newWorld
  // method.
  void testOnKeyEvent(Tester t) {
    this.initMazeExample();

    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.depthList, new Stack<MazeCell>());
    this.aMaze2x2.onKeyEvent("d");
    Stack<MazeCell> depthList = new Stack<MazeCell>();
    depthList.push(this.cell1AM2);
    t.checkExpect(this.aMaze2x2.depthList, depthList);
    
    t.checkExpect(this.aMaze2x2.breadthList, new LinkedList<MazeCell>());
    this.aMaze2x2.onKeyEvent("b");
    LinkedList<MazeCell> breadthList = new LinkedList<MazeCell>();
    breadthList.add(this.cell1AM2);
    t.checkExpect(this.aMaze2x2.breadthList, breadthList);
    
    this.aMaze2x2.onKeyEvent("r");
    t.checkExpect(this.aMaze2x2.breadthList, new LinkedList<MazeCell>());
    
    t.checkExpect(this.aMaze2x2.mazeBoard, this.boardAM2);
    this.aMaze2x2.onKeyEvent("n");
    Edge newEdge12AM2 = new Edge(this.cell1AM2, this.cell2AM2, 389, false);
    Edge newEdge13AM2 = new Edge(this.cell1AM2, this.cell3AM2, 350, false);
    Edge newEdge24AM2 = new Edge(this.cell2AM2, this.cell4AM2, 719, false);
    Edge newEdge34AM2 = new Edge(this.cell3AM2, this.cell4AM2, 606, false);
    this.cell2AM2.updateLeftEdge(this.cell1AM2, newEdge12AM2);
    this.cell3AM2.updateTopEdge(this.cell1AM2, newEdge13AM2);
    this.cell4AM2.updateLeftEdge(this.cell3AM2, newEdge34AM2);
    this.cell4AM2.updateTopEdge(this.cell2AM2, newEdge24AM2);
    ArrayList<ArrayList<MazeCell>> newBoardAM2 = new ArrayList<ArrayList<MazeCell>>(
        Arrays.asList(new ArrayList<MazeCell>(Arrays.asList(this.cell1AM2, this.cell2AM2)),
            new ArrayList<MazeCell>(Arrays.asList(this.cell3AM2, this.cell4AM2))));
    t.checkExpect(this.aMaze2x2.mazeBoard, newBoardAM2);
  }

  // test for onTick method for a maze game. This also test dfsFound, bfsFound,
  // backTracking, and searchHelp in utils.
  void testOnTick(Tester t) {
    this.initMazeExample();

    Color lightBlue = new Color(144, 184, 242);
    Color darkBlue = new Color(62, 118, 204);

    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(0).color, Color.green);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(1).color, Color.lightGray);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(0).color, Color.lightGray);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(1).color, Color.red);
    this.aMaze2x2.onKeyEvent("d");
    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(0).color, darkBlue);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(1).color, lightBlue);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(0).color, Color.lightGray);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(1).color, lightBlue);
    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(1).color, darkBlue);
    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(1).color, darkBlue);

    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(0).color, Color.green);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(1).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(2).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(0).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(1).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(2).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(0).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(1).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(2).color, Color.red);
    this.aMaze3x3.onKeyEvent("d");
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(0).color, darkBlue);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(1).color, lightBlue);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(2).color, lightBlue);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(0).color, lightBlue);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(1).color, lightBlue);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(2).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(0).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(1).color, lightBlue);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(2).color, lightBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(0).color, darkBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(1).color, darkBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(1).color, darkBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(2).color, darkBlue);

    this.initMazeExample();

    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(0).color, Color.green);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(1).color, Color.lightGray);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(0).color, Color.lightGray);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(1).color, Color.red);
    this.aMaze2x2.onKeyEvent("b");
    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(0).color, lightBlue);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(1).color, Color.lightGray);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(0).color, Color.lightGray);
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(1).color, Color.red);
    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(1).color, lightBlue);
    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(1).color, lightBlue);
    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(0).color, darkBlue);
    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(0).get(1).color, darkBlue);
    this.aMaze2x2.onTick();
    t.checkExpect(this.aMaze2x2.mazeBoard.get(1).get(1).color, darkBlue);

    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(0).color, Color.green);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(1).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(2).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(0).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(1).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(2).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(0).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(1).color, Color.lightGray);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(2).color, Color.red);
    this.aMaze3x3.onKeyEvent("b");
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(0).color, lightBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(1).color, lightBlue);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(0).color, lightBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(2).color, lightBlue);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(1).color, lightBlue);
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(0).color, lightBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(1).color, lightBlue);
    this.aMaze3x3.onTick();
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(2).color, lightBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(0).get(0).color, darkBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(0).color, darkBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(1).get(1).color, darkBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(1).color, darkBlue);
    this.aMaze3x3.onTick();
    t.checkExpect(this.aMaze3x3.mazeBoard.get(2).get(2).color, darkBlue);
  }
}