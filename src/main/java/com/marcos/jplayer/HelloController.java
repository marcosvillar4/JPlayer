package com.marcos.jplayer;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.marcos.jplayer.modulos.miscFunc;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Arrays;


public class HelloController {


    public Label artist;
    public Label title;
    public Label album;
    public ListView<String> songList;
    @FXML
    private ImageView cover;
    miscFunc func = new miscFunc();

    @FXML
    protected void onHelloButtonClick() {
        title.setText("");

        genSongList();

    }
    public void audioPause() {
        func.playPause();
    }

    public void genSongList(){
        File songFolder = func.fileChooser(true, title.getScene().getWindow());
        String[] songListNames = func.getFolderSongs(songFolder);
        ObservableList<String> names= FXCollections.observableArrayList(songListNames);
        songList.setItems(names);
    }
}