package cs146F20.dang.project3;

import java.util.LinkedList;
import java.util.Queue;

/**
*
* Solves the maze by finding the shortest path using BFS 
*
* @author Chloe Dang, Luc Tang
* @version 1.0
* @since 2020-11-04
**/
public class SolveBFS {
    
    int visitedCells = 0;
    Node currentCell;
    /**
    * Finds the shortest path through the maze using BFS 
    * @param Maze m - takes in a maze to be solved
    * @param Node r - the root node / starting position of the maze
    *
    **/
    public void solveBFS(Maze m, Node r)
    {
        Queue<Node> Q = new LinkedList<>(); // instantiates a queue
        Node finish = m.maze[m.maze.length-1][m.maze.length-1]; // initializes the finishing room
        // preparing the starting. room for exploration
        r.setColor("grey");
        r.setDistance(1);
        r.setDiscovery(visitedCells);
        Q.add(r); // add starting room to queue
        
        // while the finishing has not been reached and there are still cells to explore
        while (!Q.isEmpty())
        {
            currentCell = Q.poll(); // retrieves and removes the head of this queue
            
            // iterate through each neighbor of the current cell
            for (Node neighbor : currentCell.getNeighbors())
            {
               // if the neighbor has not been discovered and is not in the queue
               if (neighbor.getColor() == "white")
               {
                   /*check if the neighbor is on the east, south, west, or north side 
                   and if the wall between the neighbor and the current cell is false*/
                   if (currentCell.getEastEdge() == false && currentCell.getDirection(neighbor) == Direction.EAST)
                   {
                       neighbor.setColor("grey");    // indicate that the neighbor has been enqueued, and is not fully explored
                       neighbor.setDistance(currentCell.getDistance()+1);    // distance from root node based on parent node  
                       neighbor.setParent(currentCell);    // set neighbor's parent to the current cell
                       visitedCells++;    // increment amount of visited cells every time a neighbor is discovered
                       neighbor.setDiscovery(visitedCells); // assign order the neighbor was visited to discovery time
                       Q.add(neighbor);    // adds neighbor onto the queue for further exploration
                   }
                   else if (currentCell.getSouthEdge() == false && currentCell.getDirection(neighbor) == Direction.SOUTH)
                   {
                       neighbor.setColor("grey");
                       neighbor.setDistance(currentCell.getDistance() + 1); 
                       neighbor.setParent(currentCell);
                       visitedCells++;
                       neighbor.setDiscovery(visitedCells);
                       Q.add(neighbor);
                   }
                   else if (currentCell.getWestEdge() == false && currentCell.getDirection(neighbor) == Direction.WEST)
                   {
                       neighbor.setColor("grey");
                       neighbor.setDistance(currentCell.getDistance() + 1); 
                       neighbor.setParent(currentCell);
                       visitedCells++;
                       neighbor.setDiscovery(visitedCells);
                       Q.add(neighbor);
                   } 
                   else if (currentCell.getNorthEdge() == false && currentCell.getDirection(neighbor) == Direction.NORTH)
                   {
                       neighbor.setColor("grey");
                       neighbor.setDistance(currentCell.getDistance() + 1); 
                       neighbor.setParent(currentCell);
                       visitedCells++;
                       neighbor.setDiscovery(visitedCells);
                       Q.add(neighbor);  
                   }
                   
               }
               // neighbor of current cell matches the finishing room 
               if (neighbor.getColor() == "grey" && neighbor == finish)
                   return; // end method
            }
            currentCell.setColor("black");    // neighbor has been completely explored
        }
        
    }
    
}
