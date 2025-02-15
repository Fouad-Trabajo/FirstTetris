package com.tetris.domain.models;

import java.awt.*;

public class PieceZ2 extends Piece {

    public PieceZ2() {
        create(Color.GREEN);
    }

    public void setXY(int x, int y) {
        // Type of piece:
        // 0
        // 0  0
        //    0
        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x;
        block[1].y = block[0].y - Block.SIZE;
        block[2].x = block[0].x + Block.SIZE;
        block[2].y = block[0].y;
        block[3].x = block[0].x + Block.SIZE;
        block[3].y = block[0].y + Block.SIZE;
    }

    public void getDirection1() {
        // 0
        // 0  0
        //    0
        blockTemporal[0].x = block[0].x;
        blockTemporal[0].y = block[0].y;
        blockTemporal[1].x = block[0].x;
        blockTemporal[1].y = block[0].y - Block.SIZE;
        blockTemporal[2].x = block[0].x + Block.SIZE;
        blockTemporal[2].y = block[0].y;
        blockTemporal[3].x = block[0].x + Block.SIZE;
        blockTemporal[3].y = block[0].y + Block.SIZE;

        updateXY(1);
    }

    public void getDirection2() {
        //    0  0
        // 0  0
        //

        blockTemporal[0].x = block[0].x;
        blockTemporal[0].y = block[0].y;
        blockTemporal[1].x = block[0].x - Block.SIZE;
        blockTemporal[1].y = block[0].y;
        blockTemporal[2].x = block[0].x;
        blockTemporal[2].y = block[0].y - Block.SIZE;
        blockTemporal[3].x = block[0].x + Block.SIZE;
        blockTemporal[3].y = block[0].y - Block.SIZE;

        updateXY(2);

    }

    public void getDirection3() {
        getDirection1();
    }

    public void getDirection4() {
        getDirection2();
    }
}
