/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.der.main;

import java.util.Stack;

public class ShortestPath {

    int[][] graph;
    int[] dist;
    int[] edgeTo;

    public ShortestPath(int graph[][]) {
        this.graph = graph;
        dist = new int[nodeSize];
        edgeTo = new int[nodeSize];
    }

    static final int nodeSize = 89;

    int minDistance(Boolean visited[]) {
        // Initialize min value 
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < nodeSize; v++) {
            if (visited[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }


    public int[][] PrintPath(int src, int dest) {
        int N = dist[dest]+1;
        int[][] result = new int[N][N];
        System.out.println("Array's lenght = " + N + "\n");
        int current = 00;
        Stack<Integer> s = new Stack<>();
        s.push(dest);
        while (current != src) {
            current = edgeTo[dest];
            s.push(current);
            dest = current;
        }
        int i = 0;
        while (!s.isEmpty()) {
            int t = s.pop();
            int y = t % 10;
            int x = (t / 10) % 10;
            result[i][0] = x;
            result[i][1] = y;
            System.out.println("(" + x + " , " + y + ")");
            i++;
        }
        return result;

    }

    void dijkstra(int src) {

        // the shortest distance from src to i 
        //s[i] : f vertex i is in shortest path --> true
        Boolean inPath[] = new Boolean[nodeSize];

        // Initialize all distances as Infinite and s[] as false 
        for (int i = 0; i < nodeSize; i++) {
            dist[i] = Integer.MAX_VALUE;
            inPath[i] = false;
        }
        for (int i = 0; i < nodeSize; i++) {
            if (graph[src][i] == 1) {
                dist[i] = 1;
                edgeTo[i] = src;
            }
        }
        // Distance of source vertex from itself is always 0 
        dist[src] = 0;

        // Find shortest path for all vertices 
        for (int count = 0; count < nodeSize - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices 
            int u = minDistance(inPath);

            inPath[u] = true;

            // Update dist value of the adjacent vertices of the picked vertex. 
            for (int v = 0; v < nodeSize; v++) {
                if (!inPath[v]
                        && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    edgeTo[v] = u;
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // print the constructed distance array 
        // printSolution(nodeSize);
    }
}
