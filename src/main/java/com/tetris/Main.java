package com.tetris;

import com.tetris.presentation.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame("Simple Tetris ^_^");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Esto sirve para cuando se cierra la ventana, se para la ejecución
        window.setResizable(false);
        window.setVisible(true);


        // Add class GamePanel to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // The size of the GamePanel becomes the size of the window
        gamePanel.launchGame();

        // Center the window in the middle
        window.setLocationRelativeTo(null);


    }
}