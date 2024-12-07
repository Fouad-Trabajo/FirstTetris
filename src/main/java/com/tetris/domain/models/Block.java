package com.tetris.domain.models;

import java.awt.*;

public class Block extends Rectangle {

    public int x, y;
    public static final int SIZE = 30;
    public Color c; // The class Color

    public Block(Color c){
        this.c = c;
    }

    public void draw(Graphics2D g2){
        g2.setColor(c);
        int margin = 2;
        g2.fillRect(x+margin,y+margin , SIZE-(margin*2), SIZE-(margin*2));
    }
}
