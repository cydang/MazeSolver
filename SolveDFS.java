package cs146F20.dang.project3;

import java.util.*;

/**
*
* Solves a maze by finding the shortest path using a DFS traversal
*
* @author Chloe Dang, Luc Tang
* @version 1.0
* @since 2020/11/06
**/
public class SolveDFS {
    
    Node currentCell;
    int visitedCells = 0;
    
    /**
    * solves the maze using a stack implementation of DFS traversal
    * @param maze m - takes in a maze to solve
    *
    **/
    public void solveDFS(Maze m)
    {
        Stack<Node> cellStack = new Stack<>();
        // preparing starting room of maze for solving
        m.maze[0][0].setColor("grey");
        m.maze[0][0].setDiscovery(visitedCells);
        m.maze[0][0].setDistance(1);
        
        
        currentCell = m.maze[0][0]; // instantiate currentCell at 0,0
        cellStack.push(currentCell);
        
        Node finish = m.maze[m.maze.length-1][m.maze.length-1]; // initializes the finishing room
        // while currentCell isn't the finishing room
        while (currentCell != finish) 
        {
            // retrieve neighboring list of currentCell
            //for (Node neighbor : currentCell.getNeighbors())
        	for (int i = 0; i < currentCell.getNeighbors().size(); i++)
            {
                // if the neighbor has not been discovered
                if (currentCell.getNeighbors().get(i).getColor() == "white")
                {
                    /*check if the neighbor is on the east, south, west, or north side 
                    and if the wall between the neighbor and the current cell is false*/
                    if (currentCell.getEastEdge() == false && currentCell.getDirection(currentCell.getNeighbors().get(i)) == Direction.EAST)
                    { 
                        currentCell.getNeighbors().get(i).setColor("grey"); // indicate that the neighbor has been enqueued, and is not fully explored 
                        currentCell.getNeighbors().get(i).setDistance(currentCell.getDistance() + 1); // distance from root node based on parent node 
                        currentCell.getNeighbors().get(i).setParent(currentCell); // set neighbor's parent to the current cell
                        visitedCells++; // increment amount of visited cells every time a neighbor is discovered
                        currentCell.getNeighbors().get(i).setDiscovery(visitedCells); // assign order the neighbor was visited to discovery time
                        currentCell = currentCell.getNeighbors().get(i); // make the new cell CurrentCell
                        cellStack.push(currentCell); // push CurrentCell location on the CellStack
                        break;
                    }
                    
                    else if (currentCell.getSouthEdge() == false && currentCell.getDirection(currentCell.getNeighbors().get(i)) == Direction.SOUTH)
                    { 
                        currentCell.getNeighbors().get(i).setColor("grey");
                        currentCell.getNeighbors().get(i).setDistance(currentCell.getDistance() + 1); 
                        currentCell.getNeighbors().get(i).setParent(currentCell);
                        visitedCells++;
                        currentCell.getNeighbors().get(i).setDiscovery(visitedCells);
                        currentCell = currentCell.getNeighbors().get(i); 
                        cellStack.push(currentCell);
                        break;
                    }
                    
                    else if (currentCell.getWestEdge() == false && currentCell.getDirection(currentCell.getNeighbors().get(i)) == Direction.WEST)
                    { 
                        currentCell.getNeighbors().get(i).setColor("grey");
                        currentCell.getNeighbors().get(i).setDistance(currentCell.getDistance() + 1); 
                        currentCell.getNeighbors().get(i).setParent(currentCell);
                        visitedCells++;
                        currentCell.getNeighbors().get(i).setDiscovery(visitedCells);
                        currentCell = currentCell.getNeighbors().get(i); 
                        cellStack.push(currentCell);
                        break;
                    }
                    
                    else if (currentCell.getNorthEdge() == false && currentCell.getDirection(currentCell.getNeighbors().get(i)) == Direction.NORTH)
                    { 
                        currentCell.getNeighbors().get(i).setColor("grey");
                        currentCell.getNeighbors().get(i).setDistance(currentCell.getDistance() + 1); 
                        currentCell.getNeighbors().get(i).setParent(currentCell);
                        visitedCells++;
                        currentCell.getNeighbors().get(i).setDiscovery(visitedCells);
                        currentCell = currentCell.getNeighbors().get(i); 
                        cellStack.push(currentCell);
                        break;
                    }
                }
            
                if (i == currentCell.getNeighbors().size()-1) 
                {
                	currentCell.setColor("black");
                	// pop the most recent cell entry off the CellStack and make it CurrentCell
                	currentCell = cellStack.pop();   
                }
            }
         }
    }
}