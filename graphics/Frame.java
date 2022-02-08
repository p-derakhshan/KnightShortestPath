/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.der.graphics;

import com.der.main.KnightShortestPathMain;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JFrame;

public class Frame extends JFrame {

    public static boolean bool;

    public Frame() {

    }

    public Frame(int[][] a, int level) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.LIGHT_GRAY);
        setTitle("Knight's Shortest Path");
        setResizable(false);
        int count = 0;
        while (count <= level) {
            ChessBoard cb = new ChessBoard();
            cb.setSize(WIDTH, HEIGHT);
            cb.FinalMatrix = a;
            cb.level = count;
            cb.total=level;
            add(cb);
            init();
            try {
                Thread.sleep(900);
            } catch (Exception ex) {
            }
            remove(cb);
            count++;
        }
    }

    public void init() {
        // setLayout(new GridLayout(1, 1, 0, 0));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
