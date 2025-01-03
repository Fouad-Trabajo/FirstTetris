package com.tetris.domain;

import com.tetris.presentation.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovePieceKeyboardUseCase implements KeyListener {

    public static boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed; // Initialize with false

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        System.out.println("TECLA PRESIONADA: " + code);
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        } else if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        } else if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        } else if (code == KeyEvent.VK_SPACE) {
            pausePressed = !pausePressed;
            if (pausePressed) {
                GamePanel.music.stop();
            } else {
                GamePanel.music.loop();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
