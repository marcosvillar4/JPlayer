package com.marcos.jplayer.modulos;

import javafx.collections.MapChangeListener;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileFilter;

public class miscFunc {

    MediaPlayer mediaPlayer;
    MediaView mediaView;
    Media media;
    boolean paused;
    boolean fileLoaded;

    public void audioPlayer(File audioFile){

        media = new Media(audioFile.toURI().toString());
        killAudio();
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        paused = false;
        fileLoaded = true;

    }

    public void killAudio(){
        if (this.mediaPlayer != null){
            mediaPlayer.dispose();
        }
    }

    public void displayMetadata(ImageView cover, Label t, Label a, Label album){
        if (media != null){

            media.getMetadata().addListener((MapChangeListener<String,Object>) change-> {


                if (change.getMap().get("title") != null) {
                    t.setText((String) change.getMap().get("title"));
                }
                if (change.getMap().get("artist") != null) {
                    a.setText((String) change.getMap().get("artist"));
                }

                if (change.getMap().get("image") != null) {
                    cover.setImage((Image) change.getMap().get("image"));
                    cover.setX(300);
                    cover.setY(300);
                }

                if (change.getMap().get("album") != null) {
                    album.setText((String) change.getMap().get("album"));
                }

                System.out.println(change);

            });
        }
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

    public File[] getFolderSongs(File folder){
        FileFilter Filefilter = new FileFilter()
        {
            public boolean accept(File file) {
                return file.getName().endsWith(".mp3");
            }
        };

        return folder.listFiles(Filefilter);
    }

    public String[] getFolderSongsName(File[] files){
        String[] names = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        return names;
    }
}
