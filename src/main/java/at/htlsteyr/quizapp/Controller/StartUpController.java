/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Michael Ploier
 * @date : 12.6.2023
 * @details Class to handle the main menu inputs
 */
/*
 * MIT License
 *
 * Copyright (c) 2023 Dino Kupinic, Michael Ploier, Daniel Samhaber, Jannick Angerer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package at.htlsteyr.quizapp.Controller;

import at.htlsteyr.quizapp.MainApplication;
import at.htlsteyr.quizapp.Model.Music;
import at.htlsteyr.quizapp.Model.Timer;
import at.htlsteyr.quizapp.Model.WindowManager;
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
    public Label timerLabel;
    @FXML
    private Label timerHeader;
    private static ArrayList<Region> controllNodes;
    private boolean change = false;
    private Music music = new Music();
    private static StartUpController instance;
    public static WindowManager game;

    @FXML
    public void onMusicButtonPressed() {
        if (music.getMusicStatus()) {
            music.StopMusic();
        } else {
            music.PlayMusic();
        }
    }

    public void setTimerLabel(String time) {
        try {
            timerLabel.setText(time);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onPlayButtonClicked() {
        try {
            game = new WindowManager("Selection", "selection-view.fxml");
            game.getGlobalStage().show();
            MainApplication.mainWindow.close();


        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
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

    public StartUpController() {
        instance = this;
    }

    public static StartUpController getInstance() {
        if (instance == null) {
            instance = new StartUpController();
        }
        return instance;
    }
}