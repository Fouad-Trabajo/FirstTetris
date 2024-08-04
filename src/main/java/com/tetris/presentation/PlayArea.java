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

        // Draw Play Area Frame
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left-4,top-4, WIDTH+8, HEIGHT);

        // Draw Next Mino Frame
        int x = right + 80;
        int y = top + 225;
        g2.drawRect(x,y, 150,150);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Next piece", x, y-20);
    }
}
