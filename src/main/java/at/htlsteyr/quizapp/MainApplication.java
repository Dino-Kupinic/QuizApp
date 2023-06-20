/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic, Daniel Samhaber, Michael Ploier, Jannick Angerer
 * @date : 5.6.2023
 * @details Main class to start the program
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

package at.htlsteyr.quizapp;

import at.htlsteyr.quizapp.Controller.StartUpController;
import at.htlsteyr.quizapp.Model.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApplication extends Application {
    public static int WIDTH = 1024;
    public static int HEIGHT = 768;
    public static WindowManager mainWindow;

    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = new WindowManager(HEIGHT, WIDTH, "Quiz", "start-up-view.fxml");
        mainWindow.getGlobalStage().show();
        checkDataJson();

        StartUpController controller = (StartUpController) mainWindow.getController();
        controller.initController();

        JsonHandler jsonHandler = new JsonHandler();
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("a", false));
        answers.add(new Answer("b", true));
        Question question = new Question("samc", answers);
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question);
        ArrayList<Player> topPlayers = new ArrayList<>();
        topPlayers.add(new Player(1, "samc", new Score(0.0), new Score(0.0)));
        Quiz quiz = new Quiz("samc2", questions, topPlayers);
        jsonHandler.writeQuizToJson(quiz);

        jsonHandler.writePlayerToJson(new Player(1, "dino", new Score(0.0), new Score(0.0)));
        System.out.println(jsonHandler.getAllQuizes());

    }

    public static void main(String[] args) {
        launch();
    }

    public void checkDataJson() throws IOException {
        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.addJsonArrayIfJsonIsntValid();
    }
}