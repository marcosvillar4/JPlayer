package com.marcos.jplayer;


import com.marcos.jplayer.modulos.miscFunc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

import java.io.File;



public class MainController {


    public Label artist;
    public Label title;
    public Label album;
    public ListView<String> songList;
    @FXML
    private ImageView cover;
    public Slider audioVolume;
    public Button pause;

    miscFunc func = new miscFunc();


    public File[] songListFiles;

    @FXML
    protected void onLoadButtonClick() {
        genSongList();
        songList.setOnMouseClicked(mouseClickedEvent -> {
            if (mouseClickedEvent.getButton().equals(MouseButton.PRIMARY) && mouseClickedEvent.getClickCount() == 2) {
                playSelectedSong();
            }
        });
    }

    public void audioPause() {
        if (func.isAlive()) {
            if (func.isPaused()){
                pause.setText("||");
            } else {
                pause.setText("▶");
            }
            func.playPause();
        } else {
            playSelectedSong();
        }
    }

    void playSelectedSong(){
        if (!songList.getItems().isEmpty()){
            func.setMediaUI(cover, title, artist, album);
            func.GenerateQueue(songListFiles, songList.getSelectionModel().getSelectedIndex());
        }
    }

    void genSongList(){
        File songFolder = func.fileChooser(true, title.getScene().getWindow());
        songListFiles = func.getFolderSongs(songFolder);
        String[] songListNames = func.getFolderSongsName(songListFiles);
        ObservableList<String> names= FXCollections.observableArrayList(songListNames);
        songList.setItems(names);
    }

    public void setAudioVolume(){
        System.out.println(audioVolume.valueProperty().get() / 100);
        func.setVolume(audioVolume.valueProperty().get());
    }

    public void enableLoop(){
        func.enableLoop();
    }

    public void skipSong(){
        func.skipSong();
    }

    public void prevSong(){
        func.prevSong();
    }

}