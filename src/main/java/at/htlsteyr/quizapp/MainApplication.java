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
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Quiz App");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        checkDataJson();

        StartUpController controller = fxmlLoader.getController();
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