package at.htlsteyr.quizapp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    private Image background;
    {
        try {
            background = new Image(new FileInputStream("src/main/resources/img/ClassroomBackground.png"));
        } catch (FileNotFoundException e) {
            System.out.println("IntelliJ: hawara keine Ahnung wo des büd is oida!");
        }
    }

    private final DropShadow buttonShadow = new DropShadow();
    public void setFourAnswerGame() {
        startGame();

        bottomleftBtn.setEffect(buttonShadow);
        topleftBtn.setEffect(buttonShadow);
        bottomrightBtn.setEffect(buttonShadow);
        toprightBtn.setEffect(buttonShadow);
        questionLbl.setText("Welche Farbe hat das Fach MATHEMATIK");

    }
    public void setTrueFalseGame() {
        startGame();

        trueBtn.setEffect(buttonShadow);
        falseBtn.setEffect(buttonShadow);
    }
    public void startGame() {
        buttonShadow.setRadius(5.0);
        questionLblBackground.setBackground(new Background(new BackgroundFill(Color.DARKGRAY.darker(), null, null)));
        fourAnswerAnchorPane.setBackground(new Background(new BackgroundImage(background, null,null,null,null)));
    }
}
