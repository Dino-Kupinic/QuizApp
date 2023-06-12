package at.htlsteyr.quizapp.Controller;

import at.htlsteyr.quizapp.Model.Music;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    private Music music = new Music();

    @FXML
    Button musicButton;

    @FXML
    public void onMusicButtonPressed() {
        if (music.getMusicStatus()) {
            musicButton.setText("Play Music");
            music.StopMusic();
        } else {
            musicButton.setText("Stop Music");
            music.PlayMusic();
        }

    }
}