package com.tetris.domain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovePieceKeyboardUseCase implements KeyListener {

    public static boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed; // Initialize with true

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        System.out.println("TECLA PRESIONADA: " + code);
        if (code == KeyEvent.VK_UP){
            upPressed = true;
        } else if (code == KeyEvent.VK_DOWN){
            downPressed = true;
        } else if (code == KeyEvent.VK_LEFT){
            leftPressed = true;
        } else if (code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        } else if(code == KeyEvent.VK_SPACE){
            if (pausePressed){
                pausePressed = false;
            }else{
                pausePressed = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
