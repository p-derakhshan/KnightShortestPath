package com.der.main;

import com.der.graphics.Frame;
import static com.der.main.Start.pSize;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class KnightShortestPathMain {

    public static int src;
    public static int dest;
    public static int[][] FinalMatrix;
    public static int pSize;

    public static void main(String[] args) {
//        Start st = new Start();
//        st.setVisible(true);
        boolean srcCheck = false, destCheck = false;
        int src = 11, dest = 88;
        while (!srcCheck) {
            String source = JOptionPane.showInputDialog(null, "Starting Point XY : ", "Input", JOptionPane.QUESTION_MESSAGE);
            if (source.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        " Strting Point Is Requiered To Continue !", "No Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            src = Integer.parseInt(source);
            if (src < 11 || src > 88) {
                JOptionPane.showMessageDialog(null,
                        " XY Should Be Between 11 To 88", "Wrong Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            srcCheck = true;
        }
        while (!destCheck) {
            String destination = JOptionPane.showInputDialog(null, "Ending Point XY: ", "Input", JOptionPane.QUESTION_MESSAGE);
            if (destination.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        " Ending Point Is Requiered To Continue !", "No Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            dest = Integer.parseInt(destination);
            if (dest < 11 || dest > 88) {
                JOptionPane.showMessageDialog(null,
                        " XY Should Be Between 11 To 88", "Wrong Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            destCheck = true;
        }

        int size = 8;
        Graph g = new Graph(88);
        g.Make();
     //   g.BFS(src);
        ShortestPath sp = new ShortestPath(g.GetAdjMatrix());
        sp.dijkstra(src);
        FinalMatrix = sp.PrintPath(src, dest);
        pSize = FinalMatrix.length;
        JOptionPane.showMessageDialog(null,
                "Shortest path's lenght   =   " + (pSize - 1), "Result",
                JOptionPane.INFORMATION_MESSAGE);
        Frame frame = new Frame();
        frame = new Frame(FinalMatrix, pSize);
        frame.setVisible(true);
    }

    public static void DoMain() {

    }
}
