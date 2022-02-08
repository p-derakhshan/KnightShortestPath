package com.der.main;

import java.util.LinkedList;
import java.util.Queue;

class Cell {

    int x, y; //cell's coordinant
    int distTo; //cell's ditance from source

    public Cell(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.distTo = dist;
    }
}

public class ChessBoard {

    private int Size;
    Graph graph;

    public void CreateBoard(int size) {
        this.Size = size;
        graph = new Graph(size);
    }

    private boolean IsInBound(int x, int y) { //checks if cell is inside the board
        if (x >= 1 && x <= Size && y >= 1 && y <= Size) {
            return true;
        }
        return false;
    }

    public void steps(int knightPos[]) {
        int knight_x = knightPos[0];
        int knight_y = knightPos[1];

        // x and y direction, where a knight can move  
        int move_x[] = {-2, -1, 1, 2, -2, -1, 1, 2};
        int move_y[] = {-1, -2, -2, -1, 1, 2, 2, 1};
        //(-2,-1) (-1,-2) (1,-2) (2,-1) (-2,1) (-1,2) (1,2) (2,1)   

        Cell t = new Cell(knight_x, knight_y, 0);
        int x, y;

        // tries all reachable cells 
        for (int i = 0; i < 8; i++) {
            x = t.x + move_x[i];
            y = t.y + move_y[i];

            //if reachable cell is possible add to graph
            if (IsInBound(x, y)) {
                int destNode = (x * 10) + y;
                int srcNode = (t.x * 10) + t.y;
                graph.addEdge(srcNode, destNode, 1);
                int[] yyyy = new int[2];
                yyyy[0] = x;
                yyyy[1] = y;
                steps(yyyy);
            }
        }

        graph.BFS((knight_x * 10) + knight_y);
        graph.PrintBFS();

    }
////////////////////////////////////////////////////////////////////////////////
    boolean[] visited = new boolean[89];

    public void findPossibleMoves(int k_x, int k_y) {
        // All possible moves of a knight 

        int X[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int Y[] = {1, 2, 2, 1, -1, -2, -2, -1};

        // Check if each possible move is valid or not 
        for (int i = 0; i < 8; i++) {

            // Position of knight after move 
            int d_x = k_x + X[i];
            int d_y = k_y + Y[i];

            int destNode = (d_x * 10) + d_y;
            int srcNode = (k_x * 10) + k_y;
            // count valid moves   
            if (IsInBound(d_x, d_y) && !visited[destNode] && !visited[65]) {
                graph.addEdge(srcNode, destNode, 1);
                visited[destNode] = true;
                findPossibleMoves(d_x, d_y);
            }
        }

    }
///////////////////////////////////////////////////////////////////////////////

    int minStepToReachTarget(int knightPos[], int targetPos[]) {

        int knight_x = knightPos[0];
        int knight_y = knightPos[1];
        int target_x = targetPos[0];
        int target_y = targetPos[1];

        // x and y direction, where a knight can move  
        int move_x[] = {-2, -1, 1, 2, -2, -1, 1, 2};
        int move_y[] = {-1, -2, -2, -1, 1, 2, 2, 1};
        //(-2,-1) (-1,-2) (1,-2) (2,-1) (-2,1) (-1,2) (1,2) (2,1)   

        // queue for storing states of knight in board 
        Queue<Cell> queue = new LinkedList<Cell>();

        // push starting position of knight 
        queue.add(new Cell(knight_x, knight_y, 0)); //distTo=0

        boolean[][] visited = new boolean[Size + 1][Size + 1];

        // make all cells unvisited  
        for (int i = 1; i <= Size; i++) {
            for (int j = 1; j <= Size; j++) {
                visited[i][j] = false;
            }
        }

        visited[knight_x][knight_y] = true; //starting cell

        Cell t;
        int x, y;

        // repeat untill we have one element is left in queue  
        while (!queue.isEmpty()) {

            t = queue.poll();
            //System.out.println(t.x+","+t.y);

            // if current cell is equal to target cell,return its distance  
            if (t.x == target_x && t.y == target_y) {
                return t.distTo;
            }

            // tries all reachable cells 
            for (int i = 0; i < 8; i++) {
                x = t.x + move_x[i];
                y = t.y + move_y[i];

                //if reachable cell is not visited ,push to queue
                if (IsInBound(x, y) && !visited[x][y]) {
                    visited[x][y] = true;
                    // dist = number of moves taken + new move

                    queue.add(new Cell(x, y, t.distTo + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
