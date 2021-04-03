package cs146F20.dang.project3;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
* 1) Creates a perfect maze by randomly removing interior walls to connect adjacent rooms, 
*    so that every room (therefore also the finishing room) is reachable from the starting room.
* 2) Prints out a display of the maze with either hashtags showing the shortest solution path 
*    or the order * that the rooms were visited by the BFS and DFS algorithms, respectively.
*
*
* @author Chloe Dang, Luc Tang
* @version 1.0
* @since 2020-11-04 
**/
import java.util.*;

public class Maze {

    Node[][] maze;
    Stack<Node> cellStack;
    int totalCells;
    Node currentCell;
    int visitedCells;
    ArrayList<Node> shortestPath;
    Random rand;
    
    public Maze (int r)
    {
        maze = new Node[r][r];
        totalCells = r * r;
        rand = new Random(39);
        shortestPath = new ArrayList<>();
    }
    
    /**
    * initialize 2D array maze with nodes and add their respective neighbors to 
    * their list of neighbors
    **/
    public void createGrid(){
        // fill each cell with Node
        for (int i = 0; i < maze.length; i++) 
        {
            for (int j = 0; j < maze[i].length; j++)
            {
                maze[i][j] = new Node(i,j);
            }
        }
        
        // add neighbors to the cells
        for (int i = 0; i < maze.length; i++) 
        {
            for (int j = 0; j < maze[i].length; j++)
            {
                Node current = maze[i][j];
                //check east neighbor and add to current node's list of neighbors if it exists
                if (current.getJ() + 1 < maze.length) {
                    current.addNeighbor(maze[i][j+1]);
                }
                //check south neighbor and add to current node's list of neighbors if it exists
                if (current.getI() + 1 < maze.length) {
                    current.addNeighbor(maze[i+1][j]);
                }
                //check west neighbor and add to current node's list of neighbors if it exists
                if (current.getJ() - 1 >= 0) {
                    current.addNeighbor(maze[i][j-1]);
                }
                //check north neighbor and add to current node's list of neighbors if it exists
                if (current.getI() - 1 >= 0) {
                    current.addNeighbor(maze[i-1][j]);
                }
            }
        }
        
    }
    /**
    * returns a random number within a given range
    * @param int i - will this number to determine range
    **/
    public int generateRandom(int i)
    {
        //Random r = new Random();
        //r.setSeed(10);
        
        return rand.nextInt(i); //returns a number in the range [0, i)
    }
    
    /**
    * Creates a perfect maze by randomly removing interior walls to connect adjacent rooms, so that 
    * every room (therefore also the finishing room) is reachable from the starting room.
    **/
    public void createMaze()
    {
        cellStack = new Stack<Node>();
    
        currentCell = maze[0][0]; // instantiate currentCell at 0,0
        visitedCells = 1; // instantiate visitedCells = 1
     
        
        while (visitedCells < totalCells) 
        {
             //find all neighbors of currentCell with all walls intact
             ArrayList<Node> wallsInNeighbors = findNeighbors(currentCell);
                      
             if (wallsInNeighbors.size() >= 1)
             {
                 Node otherCell = wallsInNeighbors.get(generateRandom(wallsInNeighbors.size()));
                 // knock down the wall between it and CurrentCell
                 breakWall(currentCell, otherCell);
                 // push CurrentCell location on the CellStack
                 cellStack.push(currentCell);
                 // make the new cell CurrentCell
                 currentCell = otherCell;
                 // add 1 to VisitedCells
                 visitedCells++;
             } 
             else
             {
                 // pop the most recent cell entry off the CellStack and make it CurrentCell
                 currentCell = cellStack.pop();
             }
         }
        maze[0][0].breakNorthEdge();
        maze[maze.length-1][maze.length-1].breakSouthEdge();
    }
    
    /**
    * @param node whose neighbors want to be found
    * @return arraylist of neighbors with all walls intact
    **/
    public ArrayList<Node> findNeighbors(Node current)
    {
        ArrayList<Node> wallsInNeighbors  = new ArrayList<>();
        for (Node neighbor: current.getNeighbors())
        {
            if (neighbor.getNorthEdge() && neighbor.getEastEdge() && neighbor.getSouthEdge() && neighbor.getWestEdge())
            {
                wallsInNeighbors.add(neighbor);
            }
        } 
        return wallsInNeighbors; 
    }
    
    /**
    * locates which direction the neighbor is and breaks down the wall              
    * between the neighbor and the current node
    * @param current - current node
    * @param other - random neighbor node
    **/
    public void breakWall(Node current, Node other)
    {
        // if other is the west neighbor of current
        if (current.getJ() > other.getJ())
        {
            current.breakWestEdge();
            other.breakEastEdge();    
        }
        
        // if other is the east neighbor of current
        else if (current.getJ() < other.getJ())
        {
            current.breakEastEdge();
            other.breakWestEdge();    
        }
        
        // if other is the north neighbor of current
        else if (current.getI() > other.getI())
        {
            current.breakNorthEdge();
            other.breakSouthEdge();    
        }
        
        // if other is the south neighbor of current
        else
        {
            current.breakSouthEdge();
            other.breakNorthEdge();    
        }
    } 
    
    /**
     * Resets the properties of each cell
     */
    public void reset()
    {
        this.shortestPath.clear();
        for (int i = 0; i < maze.length; i++) 
        {
            for (int j = 0; j < maze[i].length; j++)
            {
            	maze[i][j].setColor("white");
                maze[i][j].setParent(null);
                maze[i][j].setInPath(false);
                maze[i][j].setDiscovery(Integer.MAX_VALUE);
                maze[i][j].setDistance(0);
            }
        }
    }
    
    BufferedWriter bfw; //instantiate BufferedWriter object
    /**
     * initializes BufferedWriter object by wrapping it arounf a FileWriter object that takes in the output file as a parameter
     * @throws IOException
     */
    public void newFile() throws IOException 
    {
    	 bfw = new BufferedWriter(new FileWriter("Actual.txt", true)); //FileWriter appends to the output file instead of overriding it
    }
   
    /**
     * clears the output file to prevent adding onto the previous file generated from the JUnit test case run
     * @throws IOException
     */
    public void clearFile() throws IOException 
    {
    	 bfw = new BufferedWriter(new FileWriter("Actual.txt")); 
    }
    
    /**
     * Replace System.out.print statement with writing to a file
     * @param print
     * @throws IOException
     */
    public void filePrint(String print) throws IOException 
    {  
        bfw.write(print);
    }

    /**
     * Replace System.out.println statement with writing to a file and moving to the next line
     * @param println
     * @throws IOException
     */
    public void filePrintln(String println) throws IOException 
    {  
        bfw.write(println);
        bfw.newLine();;     
    }
    
    /**
    * prints the order that the rooms were visited by the algorithm
     * @throws IOException 
    *
    **/
    public void printMazeDiscovery() throws IOException
        {
            Node currentCell;
            
            for (int i = 0; i < maze.length; i++) // iterates through each row in the maze
            {
                for (int j = 0; j < maze.length; j++) // iterates through each column of each row in the maze to print "+-"
                {
                    currentCell = maze[i][j];
                    // has north edge
                    if (currentCell.getNorthEdge() == true)
                    {
                        if (j == maze.length -1)    //end of the row
                        	filePrintln("+-+"); 
                        else
                        	filePrint("+-");    //prints north edge
                    }
                    else // no north edge
                    {
                        if (j == maze.length -1)
                        	filePrintln("+ +"); // end of the row
                        else
                        	filePrint("+ "); // prints no north edge
                    }
                }
                
                for (int k = 0; k < maze.length; k++) // iterates through each column in the row to print "|"
                {
                    currentCell = maze[i][k];
                    // has west edge
                    if (currentCell.getWestEdge() == true)
                    {
                        if (k == maze.length -1)    //end of the row
                        {
                            if (currentCell.getDiscovery() == Integer.MAX_VALUE) // if node was not discovered during search
                            	filePrintln("| |"); // prints west edge and east edge without discovery time
                            else    
                            	filePrintln("|" + (currentCell.getDiscovery()%10) + "|"); // prints discovery time of current node with west and east edges
                        }
                        else    // not end of row
                        {
                            if (currentCell.getDiscovery() == Integer.MAX_VALUE) // if node was not discovered during search
                            	filePrint("| "); // prints west edge and no discovery time
                            else   
                            	filePrint("|" + (currentCell.getDiscovery()%10)); // prints discovery time of current node with west edge
                        }
                    }
                    else // no west edge
                    {
                        if (k == maze.length -1)    //end of the row
                        {
                            if (currentCell.getDiscovery() == Integer.MAX_VALUE) // if node was not discovered during search
                            	filePrintln("  |"); // prints east edge without discovery time
                            else    
                            	filePrintln(" " + (currentCell.getDiscovery()%10) + "|"); // print discovery time of current node and east edge
                        }
                        else    //not end of the row
                        {
                            if (currentCell.getDiscovery() == Integer.MAX_VALUE) // if node was not discovered during search
                            	filePrint("  "); // prints no west edge and no discovery time
                            else
                            	filePrint(" " + (currentCell.getDiscovery()%10));    // prints discovery time with no west edge
                        }
                    }
                }
                
            }
            // printing last row of the maze
            for (int i = 0; i < maze.length; i++)
            {
                if (maze[maze.length - 1][i].getSouthEdge() == false)
                	filePrintln("+ +");    //end of the row
                else
                	filePrint("+-");     //has a edge
               
            }
            System.out.println();
        }
        
        /**
        * prints the shortest solution path, using hash ‘#’ character
        * for rooms and wall openings on the solution path.  
         * @throws IOException 
        **/
        public void printMazePath() throws IOException
        {
            Node current = maze[maze.length-1][maze.length-1]; // start at the finishing room
            current.setInPath(true);    // node in finishing room is part of the shortest path
            shortestPath.add(current); // add node in finishing room to list of shortestPath nodes
            
            // while the current node has a parent node
            while (current.getParent() != null)
            {
                current.getParent().setInPath(true); // parent node is part of shortest path
                shortestPath.add(current.getParent()); // add parent node to list of nodes that are part of shortest path
                current = current.getParent();   // set current node to its parent node
            }
            
            for (int i = 0; i < maze.length; i++) // iterates through each row in the maze
            {
                for (int j = 0; j < maze.length; j++) // iterates through each column of each row in the maze to print "+-"
                {
                    currentCell = maze[i][j];
                    
                    // has north edge
                    if (currentCell.getNorthEdge() == true)
                    {
                        if (j == maze.length -1)    //end of the row
                        	filePrintln("+-+"); 
                        else
                        	filePrint("+-");    //prints north edge
                    }
                    // no north edge
                    else 
                    {
                        if (i != 0)
                        {
                            // checks if current cell and neighbor are in the path
                            if (currentCell.getInPath() && maze[i-1][j].getInPath())
                            {
                                if (j == maze.length -1) 
                                	filePrintln("+#+");    // end of the row
                                else
                                	filePrint("+#");    // not end of row
                            }
                            // else, current cell and/or neighbor is not in path
                            else
                            {
                                if (j == maze.length -1)
                                	filePrintln("+ +"); // end of the row
                                else
                                	filePrint("+ "); // prints no north edge   
                            }
                         }
                         else // prints top border of maze
                         {
                             if (j == maze.length -1)
                            	 filePrintln("+ +"); // end of the row
                             else
                            	 filePrint("+ "); // prints no north edge
                         }
                    }
                }
                
                for (int k = 0; k < maze.length; k++) // iterates through each column in the row to print "|"
                {
                    currentCell = maze[i][k];
                    // has west edge
                    if (currentCell.getWestEdge() == true)
                    {
                        if (k == maze.length -1)    //end of the row
                        {
                            if (currentCell.getInPath()) // if node is part of the shortest path   
                            	filePrintln("|#|"); //prints '#' with west and east edges
                             else 
                                filePrintln("| |"); // prints west edge and east edge without '#'
                        }
                        else    // not end of row
                        {
                            if (currentCell.getInPath()) // if node is part of the shortest path
                            	filePrint("|#"); // prints '#' with west edge
                            else   
                            	filePrint("| "); // prints west edge and empty space
                        }
                    }

                    else // no west edge
                    {
                        
                        // checks if current cell and neighbor are in the path
                        if (currentCell.getInPath() && maze[i][k-1].getInPath())
                        {
                            if (k == maze.length -1)    //end of the row
                                filePrintln("##|"); // prints "##" for current node the path in between its neighbor in addition to east edge
                            else    
                            	filePrint("##"); // prints "##" for current node the path in between its neighbor
                            }    
                        else    // else, current cell and/or neighbor are not in the path
                        {       
                            if (currentCell.getInPath())    // if current is in path, but neighbor is not
                            {
                                if (k == maze.length -1)    // end of the row
                                	filePrintln(" #|"); // the way our maze is generated, this case never occurs
                                else
                                	filePrint(" #");    // no west edge and west neighbor is in the path
                            }
                            else    // current is also not in the path
                            {
                                if (k == maze.length -1)    // end of the row
                                	filePrintln("  |");    // print out east edge
                                else
                                	filePrint("  ");    // prints no edge and no path
                            }
                        }         
                    }
                 }
            }
                  
            // printing last row of the maze
            for (int i = 0; i < maze.length; i++)
            {
                if (maze[maze.length - 1][i].getSouthEdge() == false)
                	filePrintln("+ +");    //end of the row
                else
                	filePrint("+-");     //has a edge
               
            }
            //System.out.println();
            bfw.newLine();
            
        }
        
        /**
         * Returns the order of cells traversed in the shortest path
         * @return
         * @throws IOException
         */
        public String printPath() throws IOException
        {
            String path = "";
            for (int i = this.shortestPath.size() - 1; i >= 0; i--)
            {
                path += ( "(" + shortestPath.get(i).getI() + "," + shortestPath.get(i).getJ() + ")" );
            }
            filePrintln("Path: " + path);
            return path;
        }
        
        /**
         * Returns the length of the shortest path
         * @return
         * @throws IOException
         */
        public int getLengthOfPath() throws IOException
        {
        	int length = maze[maze.length - 1][maze.length - 1].getDistance();
        	filePrintln("Length of path: " + length);
            return length;
        }
        
        /**
         * Returns the number of visited cells
         * @return
         * @throws IOException
         */
        public int getVisitedCells() throws IOException
        {
        	int visitedCells = maze[maze.length - 1][maze.length - 1].getDiscovery() + 1;
        	filePrintln("Visited cells: " + visitedCells);
        	return visitedCells;
        }
}
