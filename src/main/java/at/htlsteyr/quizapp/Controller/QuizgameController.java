package at.htlsteyr.quizapp.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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

    public void setGameStart() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        bottomleftBtn.setEffect(shadow);
        topleftBtn.setEffect(shadow);
        bottomrightBtn.setEffect(shadow);
        toprightBtn.setEffect(shadow);
        questionLbl.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
        questionLbl.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Image background = new Image("H:\\Schule\\3_Klasse\\ITP2\\QuizApp\\src\\main\\resources\\img\\ClassroomBackground.png");
        fourAnswerAnchorPane.setBackground(new Background(new BackgroundImage(background, null,null,null,null)));
    }
}
