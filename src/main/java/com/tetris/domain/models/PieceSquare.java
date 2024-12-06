package com.tetris.domain.models;

import java.awt.*;

public class PieceSquare extends Piece {

    public PieceSquare() {
        create(Color.YELLOW);
    }

    public void setXY(int x, int y) {
        // Type of piece:
        //  0  0
        //  0  0
        //
        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x;
        block[1].y = block[0].y + Block.SIZE;
        block[2].x = block[0].x + Block.SIZE;
        block[2].y = block[0].y;
        block[3].x = block[0].x + Block.SIZE;
        block[3].y = block[0].y + Block.SIZE;
    }

    public void getDirection1() {

    }

    public void getDirection2() {

    }

    public void getDirection3() {


    }

    public void getDirection4() {

    }
}
