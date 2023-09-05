package com.example.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label songNameLbl;
    @FXML
    private ComboBox<Number> comboBox;

    private Number[] speeds = {25,50,75,100,150,200};
    private File[] files;
    private File directory;
    private int songIndex;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File currentSongFile;
    List<File> songs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        songs = new ArrayList<>();
        songIndex = 0;
        directory = new File("music");
        files = directory.listFiles();
        if(files != null) {
            for(File file:files) {
                songs.add(file);
            }
        }
        currentSongFile = songs.get(songIndex);
        songNameLbl.setText(currentSongFile.getName());
        media = new Media(currentSongFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        comboBox.getItems().addAll(speeds);
    }

    public void play() {
        mediaPlayer.play();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void next() {
        mediaPlayer.stop();
        songIndex++;
        if(songIndex > songs.size() - 1) {
            songIndex = 0;
        }
        currentSongFile = songs.get(songIndex);
        songNameLbl.setText(currentSongFile.getName());
        media = new Media(currentSongFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void previous() {
        mediaPlayer.stop();
        songIndex--;
        if(songIndex < 0) {
            songIndex = songs.size() - 1;
        }
        currentSongFile = songs.get(songIndex);
        songNameLbl.setText(currentSongFile.getName());
        media = new Media(currentSongFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void reset() {
        mediaPlayer.seek(Duration.ZERO);
    }

    public void changeSpeed(ActionEvent event) {
        ComboBox<Number> source = (ComboBox<Number>) event.getSource();
        int selectedItem = (int)source.getSelectionModel().getSelectedItem();

    }
}