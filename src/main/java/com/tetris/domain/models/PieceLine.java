package com.tetris.domain.models;

import java.awt.*;

public class PieceLine extends Piece{


    public PieceLine(){
        create(Color.CYAN);
    }

    public void setXY(int x, int y){
        //
        // 0  0  0  0
        //
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x - Block.SIZE;
        b[1].y = b[0].y;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y;
    }

    public void getDirection1() {
        //
        // 0  0  0  0
        //
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x  - Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x + Block.SIZE*2;
        tempB[3].y = b[0].y;

        updateXY(1);
    }

    public void getDirection2() {
        //    0
        //    0
        //    0
        //    0

        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - Block.SIZE;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y + Block.SIZE;
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y + Block.SIZE*2;

        updateXY(2);

    }

    // The is only 2 directions
    public void getDirection3() {
        getDirection1();
    }

    public void getDirection4() {
        getDirection2();
    }
}