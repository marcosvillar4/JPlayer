package com.marcos.jplayer;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.marcos.jplayer.modulos.miscFunc;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.File;



public class HelloController {


    public Label artist;
    public Label title;
    public Label album;
    public ListView<String> songList;
    @FXML
    private ImageView cover;
    miscFunc func = new miscFunc();




    public File[] songListFiles;

    @FXML
    protected void onHelloButtonClick() {
        title.setText("");

        genSongList();

    }

    public void audioPause() {
        func.playPause();
    }

    public void playSelectedSong(){
        if (!songList.getItems().isEmpty()){
            func.audioPlayer(songListFiles[songList.getSelectionModel().getSelectedIndex()]);
            func.displayMetadata(cover, title, artist, album);
        }
    }

    public void genSongList(){

        File songFolder = func.fileChooser(true, title.getScene().getWindow());
        songListFiles = func.getFolderSongs(songFolder);
        String[] songListNames = func.getFolderSongsName(songListFiles);
        ObservableList<String> names= FXCollections.observableArrayList(songListNames);
        songList.setItems(names);


    }
}