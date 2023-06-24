/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Daniel Samhaber
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
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private static int questionCount;
    private WindowManager question;
    private static int i=0;

    public void setFourAnswerGame() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        bottomleftBtn.setEffect(shadow);
        topleftBtn.setEffect(shadow);
        bottomrightBtn.setEffect(shadow);
        toprightBtn.setEffect(shadow);
        questionLblBackground.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
        //Image background = new Image("H:\\Schule\\3_Klasse\\ITP2\\QuizApp\\src\\main\\resources\\img\\ClassroomBackground.png");
        //fourAnswerAnchorPane.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
    }

    public void setTrueFalseGame() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        trueBtn.setEffect(shadow);
        falseBtn.setEffect(shadow);
        questionLblBackground.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
        Image background = new Image("H:\\Schule\\3_Klasse\\ITP2\\QuizApp\\src\\main\\resources\\img\\ClassroomBackground.png");
        trueFalseAnchorPane.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
    }

    public void ctnueBtnClicked () {
        JsonHandler jsonHandler = new JsonHandler();
        ArrayList<Quiz> quizes = jsonHandler.getAllQuizes();
        String chosenQuiz = SelectionViewController.selectedItem;
        ArrayList<Question> questions = new ArrayList<>();

        System.out.println(chosenQuiz);
        if (quizes.get(i).getName().equals(chosenQuiz)) {
            questions = quizes.get(i).getQuestionArrayList();
        } else {
            i++;
        }

        StartUpController.game.close();


        try {
            if (questionCount < questions.size()) {
                questionCount++;
                StartUpController.game.close();
                StartUpController.game = new WindowManager(MainApplication.HEIGHT, MainApplication.WIDTH, "Questions", "fourAnswer-view.fxml");
                StartUpController.game.getGlobalStage().show();
                System.out.println(questionCount);
            } else {
                StartUpController.game.close();
                StartUpController.game = new WindowManager(MainApplication.HEIGHT, MainApplication.WIDTH, "Game ending", "End.fxml");
                StartUpController.game.getGlobalStage().show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
