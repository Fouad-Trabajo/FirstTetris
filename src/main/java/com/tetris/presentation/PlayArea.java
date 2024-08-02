package com.tetris.presentation;

import java.awt.*;

public class PlayArea {

    //Attributes
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left, right, top, bottom;

    public PlayArea(){
// Main Play Area Framo
        left = (GamePanel.WIDTH/2) - (WIDTH/2); // 1280/2 - 360/2 = 460
        right = left + WIDTH;
        top = 50;
        bottom = top + HEIGHT;
    }

    public void update(){

    }

    public void draw(Graphics2D g2){

        // Draw Play Area Fram
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left-4,top-4, WIDTH+8, HEIGHT);
    }
}
