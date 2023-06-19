package at.htlsteyr.quizapp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

    public void setFourAnswerGame() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        bottomleftBtn.setEffect(shadow);
        topleftBtn.setEffect(shadow);
        bottomrightBtn.setEffect(shadow);
        toprightBtn.setEffect(shadow);
        questionLblBackground.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
        Image background = new Image("H:\\Schule\\3_Klasse\\ITP2\\QuizApp\\src\\main\\resources\\img\\ClassroomBackground.png");
        fourAnswerAnchorPane.setBackground(new Background(new BackgroundImage(background, null,null,null,null)));
    }
    public void setTrueFalseGame() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        trueBtn.setEffect(shadow);
        falseBtn.setEffect(shadow);
        questionLblBackground.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
        Image background = new Image("H:\\Schule\\3_Klasse\\ITP2\\QuizApp\\src\\main\\resources\\img\\ClassroomBackground.png");
        trueFalseAnchorPane.setBackground(new Background(new BackgroundImage(background, null,null,null,null)));
    }
}
