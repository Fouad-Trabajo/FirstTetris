package com.tetris.presentation;

import com.tetris.domain.MovePieceKeyboardUseCase;
import com.tetris.domain.models.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class PlayArea {

    // Attributes
    final int WIDTH = 360, HEIGHT = 600;
    public static int left, right, top, bottom;

    // Pieces
    Piece currentPiece, nextPiece;
    final int PIECE_START_X, PIECE_START_Y, NEXT_PIECE_X, NEXT_PIECE_Y;
    public static ArrayList<Block> blocks = new ArrayList<>();
    public static int dropInterval = 60; // piece drops in every frames

    boolean effectCounterOn, gameOver;
    int effectCounter;
    ArrayList<Integer> effects = new ArrayList<>();

    int level = 1, lines, score;


    public PlayArea() {
        // Main Play Area Frame
        left = (GamePanel.WIDTH / 2) - (WIDTH / 2); // 1280/2 - 360/2 = 460
        right = left + WIDTH;
        top = 50;
        bottom = top + HEIGHT;
        PIECE_START_X = left + (WIDTH / 2) - Block.SIZE;
        PIECE_START_Y = top + Block.SIZE;

        NEXT_PIECE_X = right + 160;
        NEXT_PIECE_Y = top + 460;

        // Set the starting Piece
        currentPiece = selectRandomPiece();
        currentPiece.setXY(-100, -100); //para que no aparezca en el medio
        nextPiece = selectRandomPiece();
        nextPiece.setXY(NEXT_PIECE_X, NEXT_PIECE_Y);

    }

    private Piece selectRandomPiece() {

        // Pick a random piece at the first
        Piece piece = null;
        int i = new Random().nextInt(7);

        piece = switch (i) {
            case 0 -> new PieceL1(); //Compatibilidad de tipos:
            case 1 -> new PieceL2();
            case 2 -> new PieceSquare();
            case 3 -> new PieceLine();
            case 4 -> new PieceT();
            case 5 -> new PieceZ1();
            case 6 -> new PieceZ2();
            default -> piece;
        };
        return piece;
    }

    public void update() {
        if (!currentPiece.active) {
            for (Block currentPiece : currentPiece.block) {
                blocks.add(currentPiece);
            }

            if (currentPiece.block[0].x == PIECE_START_X && currentPiece.block[0].y == PIECE_START_Y) {
                gameOver = true;
                MovePieceKeyboardUseCase.pausePressed = true;

            }

            currentPiece.deactivating = false;

            currentPiece = nextPiece;
            currentPiece.setXY(PIECE_START_X, PIECE_START_Y);
            currentPiece.active = true; //now the newtPiece is active
            nextPiece = selectRandomPiece();
            nextPiece.setXY(NEXT_PIECE_X, NEXT_PIECE_Y);

            deleteLine(); //when piece is deactived, check if line can delete
        } else {
            currentPiece.update();
        }
    }

    public void deleteLine() {
        int y = top, blockCount, lineCount = 0;
        while (y < bottom) {
            blockCount = 0;
            for (Block block : blocks) {
                if (block.y == y) {
                    blockCount++;
                }
            }

            if (blockCount == 12) {
                effectCounterOn = true;
                effects.add(y);

                //delete line
                int finalY = y;
                blocks.removeIf(block -> block.y == finalY);
                //score
                lineCount++;
                lines++;
                //drop speed
                if (lines % 10 == 0 && dropInterval > 1) {
                    level++;
                    if (dropInterval > 2) {
                        dropInterval -= 10;
                    } else {
                        dropInterval -= 1;
                    }
                }
                //move down one line
                for (Block block : blocks) {
                    if (block.y < y) {
                        block.y += Block.SIZE;
                    }
                }
            }
            y += Block.SIZE;
        }

        if (lineCount > 0) {
            int singleLineScore = 10 * level;
            score += singleLineScore * lineCount;
        }
    }

    public void draw(Graphics2D g2) {

        // Draw Play Area Frame
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left - 4, top - 4, WIDTH + 8, HEIGHT);

        // Draw Next Piece Frame
        int x = right + 100;
        int y = top + 200;
        g2.drawRect(x - 20, y + 200, 190, 180);

        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Next piece", x, y + 180);
        g2.drawString("Score", x, y - 180);
        g2.drawString("Space (Pause)", x - 750, y - 150);

        //draw the score
        g2.drawRect(x - 20, y - 170, 190, 180);
        g2.drawString("LEVEL: " + level, x, y - 130);
        y += 50;
        g2.drawString("LINES: " + lines, x, y - 125);
        y += 50;
        g2.drawString("SCORE: " + score, x, y - 120);


        // Draw the currentPiece
        if (currentPiece != null) {
            currentPiece.draw(g2);
        }

        //draw the next piece
        nextPiece.draw(g2);

        //draw the block in the end
        for (Block piece : blocks) {
            piece.draw(g2);
        }

        //draw delete effect
        if (effectCounterOn) {
            effectCounter++;

            g2.setColor(Color.RED);
            for (Integer effect : effects) {
                g2.fillRect(left, effect, WIDTH, Block.SIZE);
            }

            if (effectCounter == 10) {
                effectCounterOn = false;
                effectCounter = 0;
                effects.clear();
            }
        }


        //Draw pause, game over
        g2.setColor(Color.pink);
        g2.setFont(g2.getFont().deriveFont(50f));
        x = left + 15;
        y = top + 300;
        if (gameOver) {
            g2.drawString("GAME OVER", x, y);
        } else if (MovePieceKeyboardUseCase.pausePressed) {
            g2.drawString("GAME PAUSE", x, y);
        }
    }
}
