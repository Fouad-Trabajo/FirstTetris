package com.tetris.presentation;

import com.tetris.domain.models.*;

import java.awt.*;
import java.util.Random;

public class PlayArea {

    // Attributes
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left, right, top, bottom;

    // Pieces
    Piece currentPiece;
    final int PIECE_START_X;
    final int PIECE_START_Y;
    public static int dropInterval = 60; // piece drops in every frames


    public PlayArea() {
        // Main Play Area Frame
        left = (GamePanel.WIDTH / 2) - (WIDTH / 2); // 1280/2 - 360/2 = 460
        right = left + WIDTH;
        top = 50;
        bottom = top + HEIGHT;
        PIECE_START_X = left + (WIDTH / 2) - Block.SIZE;
        PIECE_START_Y = top + Block.SIZE;

        // Set the starting Piece
        currentPiece = new PieceL1();
        currentPiece.setXY(PIECE_START_X, PIECE_START_Y);
    }

    private Piece selectRandomPiece() {

        // Pick a random piece at the first
        Piece piece = null;
        int i = new Random().nextInt(7);

        switch (i) {
            case 0:
                piece = new PieceL1();
                break;
            case 1:
                piece = new PieceL2();
                break;
            case 2:
                piece = new PieceSquare();
                break;
            case 3:
                piece = new PieceLine();
                break;
            case 4:
                piece = new PieceT();
                break;
            case 5:
                piece = new PieceZ1();
                break;
            case 6:
                piece = new PieceZ2();
                break;
        }
        return piece;
    }

    public void update() {
        currentPiece.update();
    }

    public void draw(Graphics2D g2) {

        // Draw Play Area Frame
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left - 4, top - 4, WIDTH + 8, HEIGHT);

        // Draw Next Piece Frame
        int x = right + 80;
        int y = top + 225;
        g2.drawRect(x, y, 150, 150);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Next piece", x, y - 20);

        // Draw the currentPiece
        if (currentPiece != null) {
            currentPiece.draw(g2);
        }
    }
}
