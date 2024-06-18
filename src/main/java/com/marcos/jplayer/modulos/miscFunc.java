package com.marcos.jplayer.modulos;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class miscFunc {

    MediaPlayer mediaPlayer;
    boolean paused;
    boolean fileLoaded;

    public void audioPlayer(File audioFile){

        Media media = new Media(audioFile.toURI().toString());
        if (this.mediaPlayer != null){
            mediaPlayer.dispose();
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        paused = false;
        fileLoaded = true;
    }

    public File fileChooser(Boolean folder, Window parent) {
        File selectedFile;
        if (folder) {
            DirectoryChooser fc = new DirectoryChooser();
            fc.setTitle("Select Audio Directory");

            selectedFile = fc.showDialog(parent);
            if (selectedFile == null) {
                System.out.println("Directorio no valido");
            }
        }
        else {



            FileChooser fc = new FileChooser();
            fc.setTitle("Select Audio File");
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Music Files","*.mp3"));
            selectedFile = fc.showOpenDialog(parent);

            if (selectedFile == null) {
                System.out.println("Archivo audio no valido");
            }
        }
        return selectedFile;
    }

    public void playPause(){
        if (!paused){
            mediaPlayer.pause();
            paused = true;
        }
        else{
            mediaPlayer.play();
            paused = false;


        }
    }


}
