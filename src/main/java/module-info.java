module at.htlsteyr.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;

    opens at.htlsteyr.quizapp.Model to com.google.gson;
    opens at.htlsteyr.quizapp to javafx.fxml;
    exports at.htlsteyr.quizapp;
    exports at.htlsteyr.quizapp.Controller;
    exports at.htlsteyr.quizapp.Model;
    opens at.htlsteyr.quizapp.Controller to javafx.fxml;
}