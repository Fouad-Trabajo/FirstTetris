package com.tetris.domain.models;

import com.tetris.domain.MovePieceKeyboardUseCase;
import com.tetris.presentation.GamePanel;
import com.tetris.presentation.PlayArea;

import java.awt.*;

import static com.tetris.presentation.PlayArea.blocks;

public abstract class Piece {

    public Block[] block = new Block[4];
    public Block[] blockTemporal = new Block[4];
    int autoDropCounter = 0;
    public int direction = 1; // There are 4 directions
    public boolean active,deactivating, leftCollision, rightCollision, bottomCollision; //initialize with true
    int deactivatingCounter = 0;


    public void create(Color c) {
        block[0] = new Block(c);
        block[1] = new Block(c);
        block[2] = new Block(c);
        block[3] = new Block(c);
        blockTemporal[0] = new Block(c);
        blockTemporal[1] = new Block(c);
        blockTemporal[2] = new Block(c);
        blockTemporal[3] = new Block(c);
    }


    public void setXY(int x, int y) {

    }


    public void updateXY(int direction) {

        rotationCollision();

        if (!leftCollision && !rightCollision && !bottomCollision) {
            this.direction = direction;
            block[0].x = blockTemporal[0].x;
            block[0].y = blockTemporal[0].y;
            block[1].x = blockTemporal[1].x;
            block[1].y = blockTemporal[1].y;
            block[2].x = blockTemporal[2].x;
            block[2].y = blockTemporal[2].y;
            block[3].x = blockTemporal[3].x;
            block[3].y = blockTemporal[3].y;
        }

    }

    // Métodos abstractos que implementarán las subclases
    public abstract void getDirection1();


    public abstract void getDirection2();


    public abstract void getDirection3();


    public abstract void getDirection4();

    public void movementCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        staticBlocksCollision();
        for (Block b : block) {
            //right collision
            if (b.x == PlayArea.left) {
                leftCollision = true;
            }
            //left collision
            if (b.x + Block.SIZE == PlayArea.right) {
                rightCollision = true;
            }
            //bottom collision
            if (b.y + Block.SIZE == PlayArea.bottom) {
                bottomCollision = true;
            }
        }
    }

    public void rotationCollision() {
        staticBlocksCollision();
        for (Block b : blockTemporal) {
            //right collision
            if (b.x < PlayArea.left) {
                leftCollision = true;
            }
            //left collision
            if (b.x + Block.SIZE > PlayArea.right) {
                rightCollision = true;
            }
            //bottom collision
            if (b.y + Block.SIZE > PlayArea.bottom) {
                bottomCollision = true;
            }
        }
    }

    private void staticBlocksCollision() {
        for (Block block1 : blocks) {
            int targetX = block1.x;
            int targetY = block1.y;

            //down collision
            for (Block block2 : block) {
                if (block2.y + Block.SIZE == targetY && block2.x == targetX) {
                    bottomCollision = true;
                }
            }

            //left collision
            for (Block block3 : block) {
                if (block3.x - Block.SIZE == targetX && block3.y == targetY) {
                    leftCollision = true;
                }
            }

            //right collision
            for (Block block3 : block) {
                if (block3.x + Block.SIZE == targetX && block3.y == targetY) {
                    rightCollision = true;
                }
            }
        }
    }

    public void update() {

        if (deactivating){
            deactivating();
    }
        // Move the piece with keyboard
        if (MovePieceKeyboardUseCase.upPressed) { // Rotation
            switch (direction) {
                case 1:
                    getDirection2();
                    break;
                case 2:
                    getDirection3();
                    break;
                case 3:
                    getDirection4();
                    break;
                case 4:
                    getDirection1();
                    break;
            }
            MovePieceKeyboardUseCase.upPressed = false;
            GamePanel.se.play("/raw/rotation.mp3");
        }
        movementCollision();
        if (MovePieceKeyboardUseCase.downPressed) {
            if (!bottomCollision) {
                block[0].y += Block.SIZE;
                block[1].y += Block.SIZE;
                block[2].y += Block.SIZE;
                block[3].y += Block.SIZE;
            }

            // When the piece move down, reset the autoDropCounter
            autoDropCounter = 0;
            MovePieceKeyboardUseCase.downPressed = false;


        }
        if (MovePieceKeyboardUseCase.leftPressed) {
            if (!leftCollision) {
                block[0].x -= Block.SIZE;
                block[1].x -= Block.SIZE;
                block[2].x -= Block.SIZE;
                block[3].x -= Block.SIZE;
            }

            MovePieceKeyboardUseCase.leftPressed = false;

        }
        if (MovePieceKeyboardUseCase.rightPressed) {
            if (!rightCollision) {
                block[0].x += Block.SIZE;
                block[1].x += Block.SIZE;
                block[2].x += Block.SIZE;
                block[3].x += Block.SIZE;
            }


            MovePieceKeyboardUseCase.rightPressed = false;

        }

        if (bottomCollision) {
            if(!deactivating){
               GamePanel.se.play("/raw/touch_bottom.mp3");
            }
            deactivating = true;
        } else {
            autoDropCounter++; // The counter increases in every frame

            if (autoDropCounter == PlayArea.dropInterval) {
                // The piece goes down
                block[0].y += Block.SIZE;
                block[1].y += Block.SIZE;
                block[2].y += Block.SIZE;
                block[3].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }
    }

    private void deactivating(){
        deactivatingCounter++;
        //wait 60 frames until deactivate
        if (deactivatingCounter == 30){

            deactivatingCounter = 0;
            staticBlocksCollision(); //check if the bottom is still hitting

            //if hitting, deactivate the piece
            if(bottomCollision){
                active = false;
            }
        }
    }


    public void draw(Graphics2D g2) {

        int margin = 2;
        g2.setColor(block[0].c);
        g2.fillRect(block[0].x, block[0].y, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(block[1].x, block[1].y, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(block[2].x, block[2].y, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(block[3].x, block[3].y, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
    }
}
