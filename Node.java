package cs146F20.dang.project3;

import java.util.*;
/**
* The Node class holds variables that are essential for
* creating a graph that can utilize BFS and DFS. It also
* has simple getter and setter methods for its variables
*
* 
* @author Chloe Dang, Luc Tang
* @version 1.0
* @since 2020-11-04 
**/
public class Node {
    int i; // row index
    int j; // column index
    boolean northEdge; // indicate whether northern wall exists
    boolean southEdge; // indicate whether southern wall exists
    boolean westEdge; // indicate whether western wall exists
    boolean eastEdge; // indicate whether eastern wall exists
    
    Node parent; // node before
    ArrayList<Node> neighbors;
    
    int distance; // distance from source node
    int discovery; // when the node is first discovered
    
    boolean inPath; // true if node is part of the shortest path
    
    /**
    * white nodes are unexplored
    * grey nodes are not fully explored
    * black nodes are fully explored
    **/
    String color; // indicates status of discovery/exploration
    
    
    public Node(int i, int j)
    {
        this.i = i;
        this.j = j;
        color = "white";
        northEdge = true;
        southEdge = true;  
        westEdge = true;
        eastEdge = true;
        this.discovery = Integer.MAX_VALUE;
        this.parent = null;
        this.neighbors = new ArrayList<Node>();
        this.inPath = false;
    }
    
    /**
    * get row index of node
    * @return i
    **/
    public int getI()
    {
        return  this.i;
    }
    
    /**
    * get column index of node
    * @return j
    **/
    public int getJ()
    {
        return  this.j;
    }
    
    /**
    * get color of node
    * @return color
    **/
    public String getColor()
    {
        return this.color;
    }
    /**
    * get list of neighbors
    * @return neighbors
    **/
    public ArrayList<Node> getNeighbors()
    {
        return  this.neighbors;
    }
    
    /**
    * get distance from the source node
    * @return distance
    **/
    public int getDistance()
    {
        return this.distance;
    }
    /**
    *get time of discovery from start
    *@return discovery
    **/
    public int getDiscovery()
    {
        return this.discovery;
    }
    /**
    * indicates whether node is in shortest path
    * @return boolean - is in path or not
    **/
    public boolean getInPath()
    {
        return inPath;
    }
    /**
    * indicates whether node has wall in direction X
    * @return boolean - is wall in direction X broken
    **/
    public boolean getNorthEdge()
    {
        return northEdge;
    }
    
    public boolean getSouthEdge()
    {
        return southEdge;
    }
    
    public boolean getWestEdge()
    {
        return westEdge;
    }

    public boolean getEastEdge()
    {
        return eastEdge;
    }

    /**
    * points to a parent node in a graph
    * @returns Node parent - node before the current node in a path
    **/
    public Node getParent()
    {
        return parent;
    }

    /**
    * breakXEdge methods break down 
    * wall in direction X
    **/
    public void breakNorthEdge(){
        this.northEdge = false;
    }
    
    public void breakSouthEdge(){
        this.southEdge = false;
    }
    
    public void breakWestEdge(){
        this.westEdge = false;
    }
    
    public void breakEastEdge(){
        this.eastEdge = false;
    }
    
    /**
    * set parent node
    * @param parent node
    **/
    public void setParent(Node p)
    {
        this.parent = p;
    }
    
    /**
    * adds a node to the neighbor list
    * @param neighbor node
    **/ 
    public void addNeighbor(Node n)
    {
        this.neighbors.add(n);
    }
    
    /**
    * set color of node
    * @param new color of node
    **/
    public void setColor(String c)
    {
        this.color = c;
    }
    
    /**
    * set distance from the source node
    * @param distance from the source node
    **/
    public void setDistance(int i)
    {
        this.distance = i;
    }
    
    /**
    * set time of discovery from start
    * @param time of discovery
    **/
    public void setDiscovery(int i)
    {
        this.discovery = i;
    }
    
    /**
    * if node is part of the shortest path, set inPath to be true
    *
    **/
    public void setInPath(boolean i)
    {
        this.inPath = i;
    }
    
    /**
    * compares current done with a neighboring node and returns
    * what direction the neighbor is in
    * @param Node neighbor - neighbor node to be compared
    **/
    public Direction getDirection(Node neighbor)
    {
          // if other is the west neighbor of current
        if (this.getJ() > neighbor.getJ())
        {
            return Direction.WEST;  
        }
        
        // if other is the east neighbor of current
        else if (this.getJ() < neighbor.getJ())
        {
            return Direction.EAST;    
        }
        
        // if other is the north neighbor of current
        else if (this.getI() > neighbor.getI())
        {
            return Direction.NORTH;   
        }
        
        // if other is the south neighbor of current
        else
        {
            return Direction.SOUTH;   
        }
    }
}