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
import at.htlsteyr.quizapp.Model.Debug;
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

public class StartUpController implements Debug {
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
            if (PRINT_NUllPOINTEXCEP) e.printStackTrace();
        }
    }

    @FXML
    public void onPlayButtonClicked() {
        try {
            game = new WindowManager("Selection", "selection-view.fxml");
            game.getGlobalStage().show();
            MainApplication.mainWindow.close();
        } catch (IOException | NullPointerException e) {
            if (PRINT_IOEXCEPTION || PRINT_NUllPOINTEXCEP) e.printStackTrace();
        }
    }

    @FXML
    private void onCreateButtonClicked(){
        try {
            game = new WindowManager("Editor", "editor-view.fxml");
            game.getGlobalStage().show();
            MainApplication.mainWindow.close();
            EditorController ec = (EditorController) game.getController();
            ec.initFXML();
        } catch (IOException e) {
            if (PRINT_IOEXCEPTION) e.printStackTrace();
        }
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