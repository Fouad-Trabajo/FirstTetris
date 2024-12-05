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
        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x - Block.SIZE;
        block[1].y = block[0].y;
        block[2].x = block[0].x + Block.SIZE;
        block[2].y = block[0].y;
        block[3].x = block[0].x + Block.SIZE*2;
        block[3].y = block[0].y;
    }

    public void getDirection1() {
        //
        // 0  0  0  0
        //
        blockTemporal[0].x = block[0].x;
        blockTemporal[0].y = block[0].y;
        blockTemporal[1].x = block[0].x  - Block.SIZE;
        blockTemporal[1].y = block[0].y;
        blockTemporal[2].x = block[0].x + Block.SIZE;
        blockTemporal[2].y = block[0].y;
        blockTemporal[3].x = block[0].x + Block.SIZE*2;
        blockTemporal[3].y = block[0].y;

        updateXY(1);
    }

    public void getDirection2() {
        //    0
        //    0
        //    0
        //    0

        blockTemporal[0].x = block[0].x;
        blockTemporal[0].y = block[0].y;
        blockTemporal[1].x = block[0].x;
        blockTemporal[1].y = block[0].y - Block.SIZE;
        blockTemporal[2].x = block[0].x;
        blockTemporal[2].y = block[0].y + Block.SIZE;
        blockTemporal[3].x = block[0].x;
        blockTemporal[3].y = block[0].y + Block.SIZE*2;

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
