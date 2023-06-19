package at.htlsteyr.quizapp.Controller;

import at.htlsteyr.quizapp.MainApplication;
import at.htlsteyr.quizapp.Model.Music;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.ArrayList;

public class StartUpController {
    private static final String DEFAULT = "DEFAULT";
    private static final String DARK = "DARK";

    @FXML
    private AnchorPane anchorRootPane;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnSetting;
    @FXML
    private Button btnSound;
    @FXML
    private Button btnColor;
    @FXML
    private Label lblTitel;
    @FXML
    private Label lblCredits;
    private static ArrayList<Region> controllNodes;
    private boolean change = false;
    private Music music = new Music();
    private static StartUpController instance;

    @FXML
    public void onMusicButtonPressed() {
        if (music.getMusicStatus()) {
            music.StopMusic();
        } else {
            music.PlayMusic();
        }
    }

    @FXML
    public void onPlayButtonClicked(){
        try {
            MainApplication.mainWindow.newStage(425,609,"Quiz","quizgame-view.fxml");
        } catch (IOException e){

        }
    }


    @FXML
    private void onSettingClick() {
        if (!change) {
            changeColor(DARK);
            change = true;
        } else {
            changeColor(DEFAULT);
            change = false;
        }
    }

    private void changeColor(String style) {
        Background tempBtnBgd = null;
        Background tempAnchorPaneBgd = null;
        Paint tempLblStyle = null;
        if (style.equals(DARK)) {
            tempBtnBgd = new Background(new BackgroundFill(Color.LIGHTGRAY.darker(), new CornerRadii(3.0), new Insets(-0.2, -0.2, -0.2, -0.2)));
            tempLblStyle = Color.WHITE;
            tempAnchorPaneBgd = new Background(new BackgroundFill(Color.DARKGRAY.darker().darker(), CornerRadii.EMPTY, Insets.EMPTY));
        } else {
            tempBtnBgd = new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(3.0), new Insets(-0.2, -0.2, -0.2, -0.2)));
            tempLblStyle = Color.BLACK;
            tempAnchorPaneBgd = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
        }

        for (Region r : controllNodes) {
            if (r.getClass().equals(Button.class)) {
                r.setBackground(tempBtnBgd);
                ((Button) r).setTextFill(tempLblStyle);
            } else if (r.getClass().equals(Label.class)) {
                ((Label) r).setTextFill(tempLblStyle);
            } else {
                r.setBackground(tempAnchorPaneBgd);
            }
        }
    }


    public void initController() {
        controllNodes = new ArrayList<>() {{
            add(anchorRootPane);
            add(btnPlay);
            add(btnCreate);
            add(btnSetting);
            add(btnSound);
            add(btnColor);
            add(lblTitel);
            add(lblCredits);
        }};
        changeColor(DEFAULT);
    }

    public static StartUpController getInstance() {
        if (instance == null) {
            instance = new StartUpController();
        }
        return instance;
    }
}