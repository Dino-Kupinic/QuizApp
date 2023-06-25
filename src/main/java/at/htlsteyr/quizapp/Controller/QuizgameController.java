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
import at.htlsteyr.quizapp.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
    @FXML
    private Label timerLabel;
    @FXML
    private Label correctLabel;
    @FXML
    private Label podiumScoreLabel;
    @FXML
    private TextField yourNameField;
    @FXML
    private Button OkButton;

    private WindowManager question;
    private static int questionCount;
    private static int i = 0;
    private final Path imagePath;
    private ArrayList<Question> questions;
    private static Score currentScore = new Score(0.0);
    private Timer timer;

    public QuizgameController() {
        this.imagePath = Paths.get("src/main/resources/at/htlsteyr/quizapp/media/ClassroomBackground.png");
    }

    /**
     * sets up the current question with correct data etc.
     */
    public void initialize() {
        JsonHandler jsonHandler = new JsonHandler();
        ArrayList<Quiz> quizes = jsonHandler.getAllQuizes();
        String chosenQuiz = SelectionViewController.selectedItem;
        questions = new ArrayList<>();

        timer = new Timer();
        timer.startTimer(this);

        if (pointsLabel != null) {
            pointsLabel.setText(String.valueOf(currentScore.getScore()));
        }

        if (ctnueBtn != null) {
            ctnueBtn.setDisable(true);
        }

        if (OkButton != null) {
            OkButton.setDisable(true);
        }

        if (podiumScoreLabel != null) {
            currentScore.setScore(Math.round(currentScore.getScore()));
            podiumScoreLabel.setText(String.valueOf(currentScore.getScore()));
        }

        if (quizes.get(i).getName().equals(chosenQuiz)) {
            questions = quizes.get(i).getQuestionArrayList();
        } else {
            i++;
        }

        if (questionCount != questions.size()) {
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
    }

    /**
     * updates timer label
     *
     * @param text new time
     */
    public void setTimerLabel(String text) {
        timerLabel.setText(text);
    }

    /**
     * Writes the score and player to json and closes the window, updates leaderboard
     */
    public void onOkButtonClicked() {
        if (!Objects.equals(yourNameField.getText(), "")) {
            JsonHandler j = new JsonHandler();
            j.writePlayerToJson(new Player(1, yourNameField.getText(), new Score(currentScore.getScore()), new Score(currentScore.getScore())));
            StartUpController.game.close();
            questionCount = 0;
            currentScore.setScore(0.0);
            SelectionViewController.getInstance().displayTopPlayers();
            SelectionViewController.getInstance().displayLeaderboard();
        }
    }

    /**
     * Disables/enables the ok button depending if there is something in the text field
     */
    public void onYourNameInput() {
        OkButton.setDisable(yourNameField.getText().equals(""));
    }

    /**
     * checks if the answer is correct or not
     *
     * @param text answer text of the clicked button
     * @return true or false
     */
    public boolean checkCorrect(String text) {
        Question q = questions.get(questionCount);
        for (Answer a : q.getAnswerArrayList()) {
            if (a.getAnswerText().equals(text) && a.getIsCorrect()) {
                return true;
            }
        }
        return false;
    }

    /**
     * stops the timer, sets the score and disables/enables the buttons on click
     *
     * @param event which button was clicked
     */
    public void onAnswerButtonClicked(ActionEvent event) {
        timer.stopTimer();

        Button clickedButton = (Button) event.getSource();

        if (checkCorrect(clickedButton.getText())) {
            correctLabel.setText("Correct");
            currentScore.setScore(currentScore.getScore() + timer.getScore());
        } else {
            correctLabel.setText("Incorrect");
        }
        pointsLabel.setText(Double.toString(currentScore.getScore()));

        Question q = questions.get(questionCount);
        if (topleftBtn != null) {
            topleftBtn.setDisable(true);
            toprightBtn.setDisable(true);
            bottomleftBtn.setDisable(true);
            bottomrightBtn.setDisable(true);
            displayCorrect4Answers(q);
        } else {
            trueBtn.setDisable(true);
            falseBtn.setDisable(true);
            displayCorrect2Answers(q);
        }
        ctnueBtn.setDisable(false);
    }

    /**
     * either makes the answer button red or green depending on if it is correct or not
     *
     * @param q question object where the answer is
     */
    private void displayCorrect4Answers(Question q) {
        if (q.getAnswerArrayList().get(0).getIsCorrect()) {
            topleftBtn.setStyle("-fx-background-color: green");
        } else {
            topleftBtn.setStyle("-fx-background-color: red");
        }
        if (q.getAnswerArrayList().get(1).getIsCorrect()) {
            toprightBtn.setStyle("-fx-background-color: green");
        } else {
            toprightBtn.setStyle("-fx-background-color: red");
        }
        if (q.getAnswerArrayList().get(2).getIsCorrect()) {
            bottomleftBtn.setStyle("-fx-background-color: green");
        } else {
            bottomleftBtn.setStyle("-fx-background-color: red");
        }
        if (q.getAnswerArrayList().get(3).getIsCorrect()) {
            bottomrightBtn.setStyle("-fx-background-color: green");
        } else {
            bottomrightBtn.setStyle("-fx-background-color: red");
        }
    }

    /**
     * either makes the answer button red or green depending on if it is correct or not
     *
     * @param q question object where the answer is
     */
    private void displayCorrect2Answers(Question q) {
        if (q.getAnswerArrayList().get(0).getIsCorrect()) {
            trueBtn.setStyle("-fx-background-color: green");
        } else {
            trueBtn.setStyle("-fx-background-color: red");
        }
        if (q.getAnswerArrayList().get(1).getIsCorrect()) {
            falseBtn.setStyle("-fx-background-color: green");
        } else {
            falseBtn.setStyle("-fx-background-color: red");
        }
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

    /**
     * continues to the next question or to the podium
     */
    public void ctnueBtnClicked() {
        try {
            StartUpController.game.close();

            if (questionCount < questions.size()) {
                questionCount++;
                StartUpController.game.close();
                if (questionCount == questions.size()) {
                    StartUpController.game.close();
                    StartUpController.game = new WindowManager(MainApplication.HEIGHT, MainApplication.WIDTH, "Game ending", "podium-view.fxml");
                    StartUpController.game.getGlobalStage().show();
                    return;
                }
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
