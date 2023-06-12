package at.htlsteyr.quizapp.Controller;

import at.htlsteyr.quizapp.Model.Music;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    Music music = new Music();

    @FXML
    Button musicButton;

    @FXML
    public void onMusicButtonPressed () {
        if (music.getMusicStatus()) {
            music.StopMusic();
        } else {
            music.PlayMusic();
        }

    }
}