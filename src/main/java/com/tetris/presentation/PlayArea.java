package com.tetris.presentation;

import com.tetris.domain.MovePieceKeyboardUseCase;
import com.tetris.domain.models.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class PlayArea {

    // Attributes
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left, right, top, bottom;

    // Pieces
    Piece currentPiece, nextPiece;
    final int PIECE_START_X, PIECE_START_Y, NEXT_PIECE_X, NEXT_PIECE_Y;
    public static ArrayList<Block> blocks = new ArrayList<>();
    public static int dropInterval = 60; // piece drops in every frames


    public PlayArea() {
        // Main Play Area Frame
        left = (GamePanel.WIDTH / 2) - (WIDTH / 2); // 1280/2 - 360/2 = 460
        right = left + WIDTH;
        top = 50;
        bottom = top + HEIGHT;
        PIECE_START_X = left + (WIDTH / 2) - Block.SIZE;
        PIECE_START_Y = top + Block.SIZE;

        NEXT_PIECE_X = right + 140;
        NEXT_PIECE_Y = top + 280;

        // Set the starting Piece
        //currentPiece = new PieceZ1();
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

            currentPiece.deactivating = false;

            currentPiece = nextPiece;
            currentPiece.setXY(PIECE_START_X, PIECE_START_Y);
            currentPiece.active = true; //now the newtPiece is active
            nextPiece = selectRandomPiece();
            nextPiece.setXY(NEXT_PIECE_X, NEXT_PIECE_Y);
        }else {
            currentPiece.update();
        }
    }

    public void draw(Graphics2D g2) {

        // Draw Play Area Frame
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left - 4, top - 4, WIDTH + 8, HEIGHT);

        // Draw Next Piece Frame
        int x = right + 80;
        int y = top + 225;
        g2.drawRect(x, y, 160, 180);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Next piece", x, y - 20);

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


        //Draw pause
        g2.setColor(Color.MAGENTA);
        g2.setFont(g2.getFont().deriveFont(50f));
        if (MovePieceKeyboardUseCase.pausePressed) {
            x = left + 15;
            y = top + 300;
            g2.drawString("GAME PAUSE", x, y);
        }
    }
}
