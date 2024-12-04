package com.tetris.presentation;

import com.tetris.domain.MovePieceKeyboardUseCase;
import com.tetris.domain.models.Block;
import com.tetris.domain.models.Piece;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 650;
    public static final int FPS = 60;
    Thread gameThread; // Hilos...
    PlayArea playArea;

    // Constructor
    public GamePanel() {
        //Panel Settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        playArea = new PlayArea();
        this.setFocusable(true);
        // Implement KeyListener

        this.addKeyListener(new MovePieceKeyboardUseCase());


    }

    public void launchGame() {
        this.requestFocusInWindow();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Game loop
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        playArea.update();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        playArea.draw(g2);
    }
}
