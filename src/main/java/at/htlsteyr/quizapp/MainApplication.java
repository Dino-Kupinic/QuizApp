package at.htlsteyr.quizapp;

import at.htlsteyr.quizapp.Controller.StartUpController;
import at.htlsteyr.quizapp.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApplication extends Application {
    public static int WIDTH = 1024;
    public static int HEIGHT = 768;
    public static WindowManager mainWindow;
    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = new WindowManager(HEIGHT, WIDTH,"Quiz","start-up-view.fxml");
        mainWindow.getGlobalStage().show();
        checkDataJson();

        StartUpController controller = (StartUpController) mainWindow.getController();
        controller.initController();

        // test
//        JsonHandler jsonHandler = new JsonHandler();
//        ArrayList<Answer> answers = new ArrayList<>();
//        answers.add(new Answer("a", false));
//        answers.add(new Answer("b", true));
//        Question question = new Question("samc", answers);
//        ArrayList<Question> questions = new ArrayList<>();
//        questions.add(question);
//        ArrayList<Player> topPlayers = new ArrayList<>();
//        topPlayers.add(new Player(1, "samc", new Score(0), new Score(0)));
//        Quiz quiz = new Quiz("samc2", questions, topPlayers);
//        jsonHandler.writeQuizToJson(quiz);
//
//        jsonHandler.writePlayerToJson(new Player(1, "dino", new Score(0), new Score(0)));
//        System.out.println(jsonHandler.getAllQuizes());
    }

    public static void main(String[] args) {
        launch();
    }

    public void checkDataJson() throws IOException {
        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.addJsonArrayIfJsonIsntValid();
    }
}