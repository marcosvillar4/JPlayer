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
import javafx.util.Duration;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class miscFunc {

    MediaPlayer mediaPlayer;
    MediaView mediaView;
    Media media;
    boolean paused;
    double MediaVolume = 50;
    boolean loop = true;
    File[] Songs;
    int SongIndex;
    ImageView cover;
    Label t;
    Label a;
    Label album;

    public void audioPlayer(File audioFile){

        media = new Media(audioFile.toURI().toString());
        killAudio();
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        paused = false;
    }

    void killAudio(){
        if (this.mediaPlayer != null){
            mediaPlayer.dispose();
        }
    }

    void displayMetadata(){
        if (media != null){
            media.getMetadata().addListener((MapChangeListener<String,Object>) change-> {
                if (change.getMap().get("title") != null) {
                    t.setText((String) change.getMap().get("title"));
                } else {
                    t.setText("");
                }
                if (change.getMap().get("artist") != null) {
                    a.setText((String) change.getMap().get("artist"));
                } else {
                    a.setText("");
                }
                if (change.getMap().get("image") != null) {
                    cover.setImage((Image) change.getMap().get("image"));
                    cover.setX(300);
                    cover.setY(300);
                }
                if (change.getMap().get("album") != null) {
                    album.setText(change.getMap().get("album") + " • ");
                } else {
                    album.setText("");
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
        if (mediaPlayer != null) {
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

    public File[] getFolderSongs(File folder){
        FileFilter Filefilter = file -> file.getName().endsWith(".mp3");
        return folder.listFiles(Filefilter);
    }

    public String[] getFolderSongsName(File[] files){
        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        return names;
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            MediaVolume = volume;
            mediaPlayer.setVolume(volume / 100);
        }
    }


    public void GenerateQueue(File[] SongList, int index){
        Songs = SongList.clone();
        SongIndex = index;
        System.out.println(Arrays.toString(Songs));
        playQueue();
    }

    void playQueue(){
        System.out.println(Songs[SongIndex]);
        System.out.println(MediaVolume);
        audioPlayer(Songs[SongIndex]);
        setVolume(MediaVolume);
        displayMetadata();
        mediaPlayer.setOnEndOfMedia(this::playNext);
    }

    void playNext(){
        mediaPlayer.seek(Duration.ONE);
        if (SongIndex < Songs.length - 1){
            System.out.println("1");
            SongIndex++;
            playQueue();
        } else if (SongIndex == Songs.length - 1 && loop) {
            System.out.println("2");
            SongIndex = 0;
            playQueue();
        }
        else {
            System.out.println("3");
            killAudio();
        }


    }



    public void enableLoop(){
        loop = !loop;
        System.out.println("loop: " + loop);
    }

    public void skipSong(){
        if (mediaPlayer != null){
            mediaPlayer.seek(Duration.INDEFINITE);
        }
    }

    public void prevSong(){
        if (mediaPlayer != null){
            if (mediaPlayer.getCurrentTime().toSeconds() < Duration.seconds(5).toSeconds()){
                SongIndex--;
                playQueue();
            }
            else {
                mediaPlayer.seek(Duration.ONE);
            }
        }
    }

    public void setMediaUI(ImageView cover, Label t, Label a, Label album){
        this.cover = cover;
        this.t = t;
        this.a = a;
        this.album = album;
    }

    public boolean isAlive(){
        return (mediaPlayer!=null);
    }

}
