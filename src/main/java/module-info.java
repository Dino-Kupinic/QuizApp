module at.htlsteyr.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens at.htlsteyr.quizapp.Model to com.google.gson;
    opens at.htlsteyr.quizapp to javafx.fxml;
    exports at.htlsteyr.quizapp;
    exports at.htlsteyr.quizapp.Controller;
    opens at.htlsteyr.quizapp.Controller to javafx.fxml;
}