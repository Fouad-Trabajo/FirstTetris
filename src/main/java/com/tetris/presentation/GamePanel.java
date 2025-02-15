package com.tetris.presentation;

import com.tetris.domain.MovePieceKeyboardUseCase;
import com.tetris.domain.models.Sound;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 650;
    public static final int FPS = 60;
    Thread gameThread; // Hilos...
    PlayArea playArea;
    public static Sound backgroundSound = new Sound();
    public static Sound soundEffect = new Sound();


    // Constructor
    public GamePanel() {
        //Panel Settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        playArea = new PlayArea();
        // Implement KeyListener

        this.addKeyListener(new MovePieceKeyboardUseCase());
        this.setFocusable(true);


    }

    public void launchGame() {
        this.requestFocusInWindow();
        gameThread = new Thread(this);
        gameThread.start();
        backgroundSound.loop();
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
        if(!MovePieceKeyboardUseCase.pausePressed) {
            playArea.update();
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        playArea.draw(g2);
    }
}
