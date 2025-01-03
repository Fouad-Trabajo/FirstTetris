package com.tetris.domain.models;

import com.tetris.domain.MovePieceKeyboardUseCase;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.IOException;
import java.io.InputStream;

import static com.tetris.domain.MovePieceKeyboardUseCase.pausePressed;

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
    //We need use thread because, the sound interfer in the game play
    public void play(String path) {
        // Play the sound effect one time
         new Thread(()->{
            getResource(path);
        }).start();
    }

    // repeat the background sound
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
