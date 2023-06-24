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

import at.htlsteyr.quizapp.Model.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class EditorController implements Debug {
    @FXML
    private TableView<Answer> answerTable;
    @FXML
    private TableColumn<Answer, Boolean> isCorrectCol;
    @FXML
    private TableColumn<Answer, String> answerCol;
    @FXML
    private ListView<Question> questionList;
    @FXML
    private ListView<String> quizList;
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

    //private ArrayList<Quiz> newQuizes = new ArrayList<>();
    private Quiz selectedQuiz;
    private Question selectedQuestion;
    private Answer selectAnwser;
    private static int count = 0;
    private JsonHandler jsonHandler = new JsonHandler();

    public void initFXML() {
        jsonHandler.backupDataJson();
        addQuizToList();
    }


    /**
     * Adds all quiz to the list view
     */
    public void addQuizToList() {
        quizList.getItems().clear();
        ArrayList<Quiz> quizArrayList = null;
        if (jsonHandler.isDataJsonValid()) {
            quizArrayList = jsonHandler.getAllQuizes();
        }
        if (quizArrayList != null) {
            for (Quiz q : quizArrayList) {
                quizList.getItems().add(q.getName());
            }
        }
    }

    //------------------OnClickEvents------------------\\

    //------------------Select from data.json------------------\\
    @FXML
    private void onClickQuizList() {
        String quizname = quizList.getSelectionModel().getSelectedItem();
        selectedQuiz = jsonHandler.getQuizByName(quizname);
        quizNameTextField.setText(quizname);
        questionList.getItems().clear();
        questionList.getItems().addAll(selectedQuiz.getQuestionArrayList());
    }

    @FXML
    private void onClickQuestionList() {
        selectedQuestion = questionList.getSelectionModel().getSelectedItem();
        questionTextArea.setText(selectedQuestion.getQuestion());

        multipleChoiceToggle.setSelected(selectedQuestion.getAnswerArrayList().size() > 1);

        answerTable.getItems().clear();
        answerTable.getItems().addAll(jsonHandler.getAllAnswerForTableView(selectedQuestion.getAnswerArrayList()));
        answerCol.setCellValueFactory(new PropertyValueFactory<>("answerText"));
        isCorrectCol.setCellValueFactory(new PropertyValueFactory<>("isCorrect"));
    }

    @FXML
    private void onClickAnswerList() {
        selectAnwser = answerTable.getSelectionModel().getSelectedItem();
        answerTextArea.setText(selectAnwser.getAnswerText());
        isCorrectToggle.setSelected(selectAnwser.getIsCorrect());
    }

    //------------------Manipulate------------------\\
    @FXML
    private void onClickQuizButton(Event e) {
        Object node = e.getSource();
        Button eventBtn = (Button) node;
        String btnValue = eventBtn.getText();

        if (btnValue.equals("New")) {
            Quiz tempQuiz;

            ArrayList<Answer> initAnswers = new ArrayList<>();
            initAnswers.add(new Answer("newAnwser1", true));

            ArrayList<Question> init = new ArrayList<>();
            init.add(new Question("newQuestion1", initAnswers));

            ArrayList<Player> topPlayers = new ArrayList<>();
            topPlayers.add(new Player(1, "samc", new Score(0.0), new Score(0.0)));

            tempQuiz = new Quiz("Quiz" + count, init, topPlayers);
            count++;
            quizList.getItems().add(tempQuiz.getName());
            jsonHandler.writeQuizToJson(tempQuiz);
        } else {
            String tempSelection = quizList.getSelectionModel().getSelectedItem();
            quizList.getItems().remove(tempSelection);
            jsonHandler.deleteQuizFromJson(tempSelection);
        }
    }


    @FXML
    private void onClickEditorApply(){
        Quiz oldQuiz = selectedQuiz;
        Quiz newQuiz = null;
        String newName = quizNameTextField.getText();
        int index = quizList.getSelectionModel().getSelectedIndex();

        try {
            newQuiz = (Quiz) selectedQuiz.clone();
            newQuiz.setName(newName);
        } catch (CloneNotSupportedException e) {
            if (PRINT_CLONENOTSUP) e.printStackTrace();
        }

        jsonHandler.replaceQuizInJson(newQuiz,oldQuiz);
        addQuizToList();
        quizList.getSelectionModel().select(index);

    }


}
