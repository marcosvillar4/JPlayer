package com.marcos.jplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.marcos.jplayer.modulos.miscFunc;

import java.io.File;


public class HelloController {
    @FXML
    private Label welcomeText;
    miscFunc func = new miscFunc();

    @FXML
    protected void onHelloButtonClick() {

        File audioFile = func.fileChooser(false, welcomeText.getScene().getWindow());
        //System.out.println(Arrays.toString(audioFile.list()));
        if (audioFile != null) {
            func.audioPlayer(audioFile);
            System.out.println("AAAAAAAAA");
        }
        welcomeText.setText("Welcome to JavaFX Application!");



    }

    public void audioPause() {
        func.playPause();
    }
}