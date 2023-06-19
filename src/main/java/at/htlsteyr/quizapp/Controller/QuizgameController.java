/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Daniel Samhaber
 * @date : 12.6.2023
 * @details Class to handle action in the game
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
        fourAnswerAnchorPane.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
    }

    public void setTrueFalseGame() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        trueBtn.setEffect(shadow);
        falseBtn.setEffect(shadow);
        questionLblBackground.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
        Image background = new Image("H:\\Schule\\3_Klasse\\ITP2\\QuizApp\\src\\main\\resources\\img\\ClassroomBackground.png");
        trueFalseAnchorPane.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
    }
}
