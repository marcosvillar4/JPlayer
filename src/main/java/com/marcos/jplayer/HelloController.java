package com.marcos.jplayer;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.marcos.jplayer.modulos.miscFunc;
import javafx.scene.image.ImageView;

import java.io.File;


public class HelloController {


    public Label artist;
    public Label title;
    public Label album;
    @FXML
    private ImageView cover;
    miscFunc func = new miscFunc();

    @FXML
    protected void onHelloButtonClick() {
        title.setText("");
        File audioFile = func.fileChooser(false, title.getScene().getWindow());
        if (audioFile != null) {
            func.audioPlayer(audioFile);
            func.displayMetadata(cover, artist, title, album);
        }
    }
    public void audioPause() {
        func.playPause();
    }
}