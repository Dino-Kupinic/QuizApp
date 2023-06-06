package at.htlsteyr.quizapp;

import at.htlsteyr.quizapp.Model.JsonHandler;
import at.htlsteyr.quizapp.Model.Question;
import at.htlsteyr.quizapp.Model.Quiz;
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
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Quiz App");
        stage.setScene(scene);
        stage.show();
        checkDataJson();

        // test
        JsonHandler jsonHandler = new JsonHandler();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("samc");
        Question question = new Question("samc", arrayList, 0);
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question);
        Quiz quiz = new Quiz("samc", questions);
        jsonHandler.writeQuizToJson(quiz);

        System.out.println(jsonHandler.getAllQuizes());
    }

    public static void main(String[] args) {
        launch();
    }

    public void checkDataJson() throws IOException {
        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.checkIfDataJsonIsValid();
    }
}