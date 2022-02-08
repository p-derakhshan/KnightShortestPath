package com.der.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private int adjMatrix[][];
    private static int Size;

    public Graph(int size) {
        this.Size = size + 1;
        adjMatrix = new int[Size][Size];
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }

    public void Make() {
        int move_x[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int move_y[] = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int i = 1; i <= 8; i += 1) {
            for (int j = 1; j <= 8; j++) {
                for (int k = 0; k < 8; k++) {
                    int newX = i + move_x[k];
                    int newY = j + move_y[k];
                    if (IsInBound(newX, newY)) {
                        int srcNode = (i * 10) + j;
                        int destNode = (newX * 10) + newY;
                        addEdge(srcNode, destNode, 1);
                    }
                }
            }
        }
    }

    private boolean IsInBound(int x, int y) {
        if (x >= 1 && x <= 8 && y >= 1 && y <= 8) {
            return true;
        }
        return false;
    }

    public void addEdge(int i, int j, int w) {
        adjMatrix[i][j] = w;
        adjMatrix[j][i] = w;

        // System.out.println(i + " -> " + j);
    }

    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = 0;
        adjMatrix[j][i] = 0;
    }

    public boolean isConnected(int i, int j) {
        return (adjMatrix[i][j] != 0);
    }

    //private LinkedList<Integer> BFS_vertices = new LinkedList<>();
    private ArrayList<Integer> BFS_vertices = new ArrayList<>();

    public void BFS(int vertex) {
        boolean visited[] = new boolean[Size];
        for (int i = 0; i < 64; i++) {
            visited[i] = false;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[vertex] = true;
        queue.add(vertex);
        while (queue.size() != 0) {
            vertex = queue.poll();
            BFS_vertices.add(vertex); //bfs output
            for (int i = 1; i < 64; i++) {
                if (isConnected(vertex, i) && (!visited[i])) {
                    visited[i] = true;
                    queue.add(i);

                }
            }
        }
        PrintBFS();
    }

    public void PrintBFS() {
        for (int i : BFS_vertices) {
            System.out.println(" - " + i + " - ");
        }
    }

    public ArrayList GetBFS() {
        return BFS_vertices;
    }

    public void IsConnected() {
        if (BFS_vertices.size() == Size) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public int[][] GetAdjMatrix() {
        return adjMatrix;
    }

}
