package at.htlsteyr.quizapp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class QuizgameController {
    @FXML
    public AnchorPane anchorPane;
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
        //Image background = new Image(System.getProperty("user.dir")+ "src/main/resources/Download.jfif");
        //anchorPane.setBackground(new Background(new BackgroundImage(background, null,null,null,null)));
    }
}
