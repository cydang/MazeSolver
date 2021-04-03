package cs146F20.dang.project3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test cases for different sized mazes to assess equality 
 * of individual cell properties and the properties of the entire maze
 * @author Chloe Dang, Luc Tang
 * @version 1.0
 * @since 2020/11/12
 */
class MazeTest {

	SolveBFS bfs;
    SolveDFS dfs;
    String expectedLine;
 
    @BeforeEach
    public void setUp() throws Exception {
        bfs = new SolveBFS();
        dfs = new SolveDFS();
    }
    
    @Test
    void maze4() throws IOException {
        Maze maze4 = new Maze(4);
        maze4.clearFile();	// clears the output file to prevent adding onto the previous file generated from the JUnit test case run
        maze4.bfw.close();
        maze4.newFile();
        maze4.filePrintln("Maze Size: 4");
        long start = System.nanoTime();
        maze4.createGrid();
        maze4.createMaze();
        long finish = System.nanoTime();
        System.out.println("Time to create maze 4 in nanoseconds: " + (finish-start));
        maze4.filePrintln("Maze: ");
        maze4.printMazeDiscovery();
        maze4.filePrintln("\nBFS: ");
        start = System.nanoTime();
        bfs.solveBFS(maze4, maze4.maze[0][0]);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 4 using BFS in nanoseconds: " + (finish-start));
        maze4.printMazeDiscovery();
        maze4.printMazePath();
        assertEquals(maze4.getLengthOfPath(), 9);
        assertEquals(maze4.getVisitedCells(), 9);
        maze4.printPath();
        maze4.getLengthOfPath();
        maze4.getVisitedCells();
        
        maze4.reset();
        
        assertEquals(maze4.maze[0][0].getColor(), "white");
        assertEquals(maze4.maze[3][3].getColor(), "white");
        assertEquals(maze4.maze[0][0].getParent(), null);
        assertEquals(maze4.maze[3][3].getParent(), null);
        assertEquals(maze4.maze[0][0].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze4.maze[3][3].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze4.maze[0][0].getDistance(), 0);
        assertEquals(maze4.maze[3][3].getDistance(), 0);
        
        maze4.filePrintln("\nDFS: ");
        start = System.nanoTime();
        dfs.solveDFS(maze4);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 4 using DFS in nanoseconds: " + (finish-start));
        maze4.printMazeDiscovery();
        maze4.printMazePath();
        assertEquals(maze4.getLengthOfPath(), 9);
        assertEquals(maze4.getVisitedCells(), 9);
        maze4.printPath();
        maze4.getLengthOfPath();
        maze4.getVisitedCells();
        
        maze4.filePrintln("======================\n  Program Completed!\n======================");
        maze4.bfw.close();
    }
    
    @Test
    void maze5() throws IOException {
        Maze maze5 = new Maze(5);
        maze5.newFile();
        maze5.filePrintln("\nMaze Size: 5");
        long start = System.nanoTime();
        maze5.createGrid();
        maze5.createMaze();
        long finish = System.nanoTime();
        System.out.println("Time to create maze 5 in nanoseconds: " + (finish-start));
        maze5.filePrintln("Maze: ");
        maze5.printMazeDiscovery();
        maze5.filePrintln("\nBFS: ");
        start = System.nanoTime();
        bfs.solveBFS(maze5, maze5.maze[0][0]);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 5 using BFS in nanoseconds: " + (finish-start));
        maze5.printMazeDiscovery();
        maze5.printMazePath();
        assertEquals(maze5.getLengthOfPath(), 21);
        assertEquals(maze5.getVisitedCells(), 24);
        maze5.printPath();
        maze5.getLengthOfPath();
        maze5.getVisitedCells();
        
        maze5.reset();
        
        assertEquals(maze5.maze[0][0].getColor(), "white");
        assertEquals(maze5.maze[4][4].getColor(), "white");
        assertEquals(maze5.maze[0][0].getParent(), null);
        assertEquals(maze5.maze[4][4].getParent(), null);
        assertEquals(maze5.maze[0][0].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze5.maze[4][4].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze5.maze[0][0].getDistance(), 0);
        assertEquals(maze5.maze[4][4].getDistance(), 0);
        
        maze5.filePrintln("\nDFS: ");
        start = System.nanoTime();
        dfs.solveDFS(maze5);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 5 using DFS in nanoseconds: " + (finish-start));
        maze5.printMazeDiscovery();
        maze5.printMazePath();
        assertEquals(maze5.getLengthOfPath(), 21);
        assertEquals(maze5.getVisitedCells(), 22);
        maze5.printPath();
        maze5.getLengthOfPath();
        maze5.getVisitedCells();
        
        maze5.filePrintln("======================\n  Program Completed!\n======================");
        maze5.bfw.close();
    }

    @Test
    void maze6() throws IOException {
        Maze maze6 = new Maze(6);
        maze6.newFile();
        maze6.filePrintln("\nMaze Size: 6");
        long start = System.nanoTime();
        maze6.createGrid();
        maze6.createMaze();
        long finish = System.nanoTime();
        System.out.println("Time to create maze 6 in nanoseconds: " + (finish-start));
        maze6.filePrintln("Maze: ");
        maze6.printMazeDiscovery();
        maze6.filePrintln("\nBFS: ");
        start = System.nanoTime();
        bfs.solveBFS(maze6, maze6.maze[0][0]);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 6 using BFS in nanoseconds: " + (finish-start));
        maze6.printMazeDiscovery();
        maze6.printMazePath();
        assertEquals(maze6.getLengthOfPath(), 19);
        assertEquals(maze6.getVisitedCells(), 20);
        maze6.printPath();
        maze6.getLengthOfPath();
        maze6.getVisitedCells();
        
        maze6.reset();
        
        assertEquals(maze6.maze[0][0].getColor(), "white");
        assertEquals(maze6.maze[5][5].getColor(), "white");
        assertEquals(maze6.maze[0][0].getParent(), null);
        assertEquals(maze6.maze[5][5].getParent(), null);
        assertEquals(maze6.maze[0][0].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze6.maze[5][5].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze6.maze[0][0].getDistance(), 0);
        assertEquals(maze6.maze[5][5].getDistance(), 0);
        
        maze6.filePrintln("\nDFS: ");
        start = System.nanoTime();
        dfs.solveDFS(maze6);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 6 using DFS in nanoseconds: " + (finish-start));
        maze6.printMazeDiscovery();
        maze6.printMazePath();
        assertEquals(maze6.getLengthOfPath(), 19);
        assertEquals(maze6.getVisitedCells(), 19);
        maze6.printPath();
        maze6.getLengthOfPath();
        maze6.getVisitedCells();
        
        maze6.filePrintln("======================\n  Program Completed!\n======================");
        maze6.bfw.close();
    }
    
    @Test
    void maze7() throws IOException {
        Maze maze7 = new Maze(7);
        maze7.newFile();
        maze7.filePrintln("\nMaze Size: 7");
        long start = System.nanoTime();
        maze7.createGrid();
        maze7.createMaze();
        long finish = System.nanoTime();
        System.out.println("Time to create maze 7 in nanoseconds: " + (finish-start));
        maze7.filePrintln("Maze: ");
        maze7.printMazeDiscovery();
        maze7.filePrintln("\nBFS: ");
        start = System.nanoTime();
        bfs.solveBFS(maze7, maze7.maze[0][0]);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 7 using BFS in nanoseconds: " + (finish-start));
        maze7.printMazeDiscovery();
        maze7.printMazePath();
        assertEquals(maze7.getLengthOfPath(), 25);
        assertEquals(maze7.getVisitedCells(), 34);
        maze7.printPath();
        maze7.getLengthOfPath();
        maze7.getVisitedCells();
        
        maze7.reset();
        
        assertEquals(maze7.maze[0][0].getColor(), "white");
        assertEquals(maze7.maze[6][6].getColor(), "white");
        assertEquals(maze7.maze[0][0].getParent(), null);
        assertEquals(maze7.maze[6][6].getParent(), null);
        assertEquals(maze7.maze[0][0].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze7.maze[6][6].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze7.maze[0][0].getDistance(), 0);
        assertEquals(maze7.maze[6][6].getDistance(), 0);
        
        maze7.filePrintln("\nDFS: ");
        start = System.nanoTime();
        dfs.solveDFS(maze7);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 7 using DFS in nanoseconds: " + (finish-start));
        maze7.printMazeDiscovery();
        maze7.printMazePath();
        assertEquals(maze7.getLengthOfPath(), 25);
        assertEquals(maze7.getVisitedCells(), 25);
        maze7.printPath();
        maze7.getLengthOfPath();
        maze7.getVisitedCells();
        
        maze7.filePrintln("======================\n  Program Completed!\n======================");
        maze7.bfw.close();
    }
    
    @Test
    void maze8() throws IOException{
        Maze maze8 = new Maze(8);
        maze8.newFile();
        maze8.filePrintln("\nMaze Size: 8");
        long start = System.nanoTime();
        maze8.createGrid();
        maze8.createMaze();
        long finish = System.nanoTime();
        System.out.println("Time to create maze 8 in nanoseconds: " + (finish-start));
        maze8.filePrintln("Maze: ");
        maze8.printMazeDiscovery();
        maze8.filePrintln("\nBFS: ");
        start = System.nanoTime();
        bfs.solveBFS(maze8, maze8.maze[0][0]);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 8 using BFS in nanoseconds: " + (finish-start));
        maze8.printMazeDiscovery();
        maze8.printMazePath();
        assertEquals(maze8.getLengthOfPath(), 25);
        assertEquals(maze8.getVisitedCells(), 33);
        maze8.printPath();
        maze8.getLengthOfPath();
        maze8.getVisitedCells();
        
        maze8.reset();
        
        assertEquals(maze8.maze[0][0].getColor(), "white");
        assertEquals(maze8.maze[7][7].getColor(), "white");
        assertEquals(maze8.maze[0][0].getParent(), null);
        assertEquals(maze8.maze[7][7].getParent(), null);
        assertEquals(maze8.maze[0][0].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze8.maze[7][7].getDiscovery(), Integer.MAX_VALUE);
        assertEquals(maze8.maze[0][0].getDistance(), 0);
        assertEquals(maze8.maze[7][7].getDistance(), 0);
        
        maze8.filePrintln("\nDFS: ");
        start = System.nanoTime();
        dfs.solveDFS(maze8);
        finish = System.nanoTime();
        System.out.println("Time to solve maze 8 using DFS in nanoseconds: " + (finish-start));
        maze8.printMazeDiscovery();
        maze8.printMazePath();
        assertEquals(maze8.getLengthOfPath(), 25);
        assertEquals(maze8.getVisitedCells(), 25);
        maze8.printPath();
        maze8.getLengthOfPath();
        maze8.getVisitedCells();
        
        maze8.filePrintln("======================\n  Program Completed!\n======================");
        maze8.bfw.close();
    }
    
    @Test
    void fileEquality() throws IOException 
    {
        BufferedReader out = new BufferedReader (new FileReader ("Actual.txt")); // reads output file
        BufferedReader in = new BufferedReader (new FileReader ("Expected.txt"));    // reads expected file
        
		while ((expectedLine = in.readLine ()) != null) 
        {
            String actualLine = out.readLine (); // line read from output file
            assertEquals (expectedLine, actualLine);      
        }
        out.close();
        in.close();
    }
    
}
