/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic, Michael Ploier
 * @date : 19.6.2023
 * @details Class to handle Quiz Editor inputs
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

import at.htlsteyr.quizapp.Model.Answer;
import at.htlsteyr.quizapp.Model.Question;
import at.htlsteyr.quizapp.Model.Quiz;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditorController {
    @FXML
    private TableView<Answer> answerTable;
    @FXML
    private TableColumn<Answer, Boolean> isCorrectCol;
    @FXML
    private TableColumn<Answer, String> answerCol;
    @FXML
    private ListView<Question> questionList;
    @FXML
    private ListView<Quiz> quizList;
    @FXML
    private TextField quizNameTextField;
    @FXML
    private CheckBox multipleChoiceToggle;
    @FXML
    private CheckBox isCorrectToggle;
    @FXML
    private TextArea answerTextArea;
    @FXML
    private TextArea questionTextArea;
    @FXML
    private Button editorOkButton;
    @FXML
    private Button editorCancelButton;
    @FXML
    private Button editorApplyButton;
    @FXML
    private Button removeQuizButton;
    @FXML
    private Button newQuizButton;
    @FXML
    private Button removeQuestionButton;
    @FXML
    private Button newQuestionButton;
    @FXML
    private Button removeAnswerButton;
    @FXML
    private Button newAnswerButton;
    @FXML
    private Button questionResetButton;
    @FXML
    private Button questiomApplyButton;
    @FXML
    private Button answerResetButton;
    @FXML
    private Button answerApplyButton;

}
