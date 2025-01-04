package com.tetris.domain.models;

import com.tetris.domain.MovePieceKeyboardUseCase;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.IOException;
import java.io.InputStream;

public class Sound {
    Player player;

    private void getResource(String path){
        try (InputStream is = getClass().getResourceAsStream(path)) {
            player = new Player(is);
            player.play();
        } catch (IOException e) {
            System.out.println("Error al acceder al sonido: " + e.getMessage());
        } catch (JavaLayerException e) {
            System.out.println("Error al reproducir sonido: " + e.getMessage());
        }
    }
    public void play(String path) {
         new Thread(()->{
            getResource(path);
        }).start();
    }

    public void loop() {
        new Thread(()->{
            while (!MovePieceKeyboardUseCase.pausePressed) {
                getResource("/raw/background_sound.mp3");
            }
        }).start();
    }

    public void stop() {
        if (player != null) {
            player.close();
        }
    }
}
