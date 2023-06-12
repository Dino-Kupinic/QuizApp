package at.htlsteyr.quizapp.Model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music {
    File musicFile = new File("src/main/resources/at/htlsteyr/quizapp/Tobu-Candyland.mp3");
    Media media = new Media(musicFile.toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    Boolean musicStatus = false;

    public void PlayMusic () {
        mediaPlayer.play();
        setMusicStatus(true);
    }

    public void StopMusic () {
        mediaPlayer.stop();
        setMusicStatus(false);
    }

    public Boolean getMusicStatus() {
        return musicStatus;
    }

    public void setMusicStatus(Boolean musicStatus) {
        this.musicStatus = musicStatus;
    }
}
