/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Daniel Samhaber, Dino Kupinic, Jannick Angerer
 * @date : 12.6.2023
 * @details Class to handle action in the game
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
import at.htlsteyr.quizapp.Model.JsonHandler;
import at.htlsteyr.quizapp.Model.Question;
import at.htlsteyr.quizapp.Model.Quiz;
import at.htlsteyr.quizapp.Model.WindowManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.util.ArrayList;

public class QuizgameController {
    @FXML
    private AnchorPane fourAnswerAnchorPane;
    @FXML
    private AnchorPane trueFalseAnchorPane;
    @FXML
    private Button falseBtn;
    @FXML
    private Button trueBtn;
    @FXML
    private ColumnConstraints trueFalsePane;
    @FXML
    private Button topleftBtn;
    @FXML
    private Button toprightBtn;
    @FXML
    private Button bottomleftBtn;
    @FXML
    private Button bottomrightBtn;
    @FXML
    private ColumnConstraints buttonPane;
    @FXML
    private Label questionLbl;
    @FXML
    private Label questionLblBackground;
    @FXML
    private Button ctnueBtn;
    @FXML
    private Label pointsLabel;

    private WindowManager question;
    private static int questionCount;
    private static int i = 0;
    private final Path imagePath;

    JsonHandler jsonHandler;
    ArrayList<Quiz> quizes;
    ArrayList<Question> questions;
    String chosenQuiz;

    public QuizgameController() {
        this.imagePath = Paths.get("src/main/resources/at/htlsteyr/quizapp/media/ClassroomBackground.png");
    }

    public void initialize() {
        jsonHandler = new JsonHandler();
        quizes = jsonHandler.getAllQuizes();
        questions = new ArrayList<>();
        chosenQuiz = SelectionViewController.selectedItem;

        if (quizes.get(i).getName().equals(chosenQuiz)) {
            questions = quizes.get(i).getQuestionArrayList();
        } else {
            i++;
        }

        Question q = questions.get(questionCount);
        questionLbl.setText(q.getQuestion());
        if (q.getAnswerArrayList().size() > 2) {
            setFourAnswerGame();
            topleftBtn.setText(q.getAnswerArrayList().get(0).getAnswerText());
            toprightBtn.setText(q.getAnswerArrayList().get(1).getAnswerText());
            bottomleftBtn.setText(q.getAnswerArrayList().get(2).getAnswerText());
            bottomrightBtn.setText(q.getAnswerArrayList().get(3).getAnswerText());
        } else {
            setTrueFalseGame();
            trueBtn.setText(q.getAnswerArrayList().get(0).getAnswerText());
            falseBtn.setText(q.getAnswerArrayList().get(1).getAnswerText());
        }
    }

    public void onAnswerButtonClicked() {
        System.out.println("Answer Click");
    }

    /**
     * Applies styles when the current question has 4 answers
     */
    public void setFourAnswerGame() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        bottomleftBtn.setEffect(shadow);
        topleftBtn.setEffect(shadow);
        setButtonsAndBackground(shadow, bottomrightBtn, toprightBtn, fourAnswerAnchorPane);
    }

    /**
     * Applies styles when the current question has 2 answers
     */
    public void setTrueFalseGame() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        setButtonsAndBackground(shadow, trueBtn, falseBtn, trueFalseAnchorPane);
    }

    /**
     * Sets the buttons and background correctly
     *
     * @param shadow               shadow object
     * @param bottomrightBtn       btn in the bottom right
     * @param toprightBtn          btn in the top right
     * @param fourAnswerAnchorPane pane containing 4 answer buttons
     */
    private void setButtonsAndBackground(DropShadow shadow, Button bottomrightBtn, Button toprightBtn, AnchorPane fourAnswerAnchorPane) {
        bottomrightBtn.setEffect(shadow);
        toprightBtn.setEffect(shadow);
        questionLblBackground.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
        Image background = new Image(imagePath.toUri().toString());
        fourAnswerAnchorPane.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
    }


    public void ctnueBtnClicked() {
        try {
            StartUpController.game.close();

            if (questionCount < questions.size()) {
                questionCount++;
                StartUpController.game.close();
                if (questions.get(questionCount).getAnswerArrayList().size() > 2) {
                    StartUpController.game = new WindowManager(MainApplication.HEIGHT, MainApplication.WIDTH, "Questions", "fourAnswer-view.fxml");
                } else {
                    StartUpController.game = new WindowManager(MainApplication.HEIGHT, MainApplication.WIDTH, "Questions", "trueFalse-view.fxml");
                }
                StartUpController.game.getGlobalStage().show();
            } else {
                StartUpController.game.close();
                StartUpController.game = new WindowManager(MainApplication.HEIGHT, MainApplication.WIDTH, "Game ending", "podium-view.fxml");
                StartUpController.game.getGlobalStage().show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
