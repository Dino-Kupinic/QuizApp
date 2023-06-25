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

import at.htlsteyr.quizapp.MainApplication;
import at.htlsteyr.quizapp.Model.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FXML
    private Label erorrLbl;
    //private ArrayList<Quiz> newQuizes = new ArrayList<>();
    private Quiz selectedQuiz;
    private Question selectedQuestion;
    private Answer selectAnwser;
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

    /**
     * Fills data of the right side with quiz data
     */
    @FXML
    private void onClickQuizList() {
        try {
                checkValidationOfAnswers();
                String quizname = quizList.getSelectionModel().getSelectedItem();
                selectedQuiz = jsonHandler.getQuizByName(quizname);
                quizNameTextField.setText(quizname);
                questionList.getItems().clear();
                questionList.getItems().addAll(selectedQuiz.getQuestionArrayList());
                erorrLbl.setText("");
                answerTable.getItems().clear();
        } catch (NullPointerException e) {
            if (PRINT_NUllPOINTEXCEP) e.printStackTrace();
            erorrLbl.setText("Please click on a valid quiz element!");
        } catch (IOException e){
            if (PRINT_IOEXCEPTION) e.printStackTrace();
        }
    }

    /**
     * Fills data into the UI when a question is clicked
     */
    @FXML
    private void onClickQuestionList(Event event) {
        try {
            if (event != null) checkValidationOfAnswers();
            selectedQuestion = questionList.getSelectionModel().getSelectedItem();
            questionTextArea.setText(selectedQuestion.getQuestion());

            int correct = 0;
            for (Answer a : selectedQuestion.getAnswerArrayList()) {
                if (a.getIsCorrect()) correct++;
            }

            multipleChoiceToggle.setSelected(correct > 1);

            answerTable.getItems().clear();

            answerTable.getItems().addAll(jsonHandler.getAllAnswerForTableView(selectedQuestion.getAnswerArrayList()));
            answerCol.setCellValueFactory(new PropertyValueFactory<>("answerText"));
            isCorrectCol.setCellValueFactory(new PropertyValueFactory<>("isCorrect"));

            erorrLbl.setText("");
        } catch (NullPointerException e) {
            if (PRINT_NUllPOINTEXCEP) e.printStackTrace();
            erorrLbl.setText("Please click on a valid question element!");
        } catch (IOException e){
            if (PRINT_IOEXCEPTION) e.printStackTrace();
        }
    }

    @FXML
    private void onClickAnswerList() {
        try {
            selectAnwser = answerTable.getSelectionModel().getSelectedItem();
            answerTextArea.setText(selectAnwser.getAnswerText());
            isCorrectToggle.setSelected(selectAnwser.getIsCorrect());
            erorrLbl.setText("");
        } catch (NullPointerException e) {
            if (PRINT_NUllPOINTEXCEP) e.printStackTrace();
            erorrLbl.setText("Please click on a valid element!");
        }
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
            initAnswers.add(new Answer("newAnwser2", false));

            ArrayList<Question> init = new ArrayList<>();
            init.add(new Question("newQuestion1", initAnswers));

            ArrayList<Player> topPlayers = new ArrayList<>();
            topPlayers.add(new Player(1, "samc", new Score(0.0), new Score(0.0)));

            tempQuiz = new Quiz("newQuiz" + (getNumber(quizList) + 1), init, topPlayers);
            quizList.getItems().add(tempQuiz.getName());
            jsonHandler.writeQuizToJson(tempQuiz);
        } else {
            String tempSelection = quizList.getSelectionModel().getSelectedItem();
            quizList.getItems().remove(tempSelection);
            jsonHandler.deleteQuizFromJson(tempSelection);
            onClickQuizList();
        }
    }


    @FXML
    private boolean onClickEditorApply() {
        try {
            checkValidationOfAnswers();
            Quiz oldQuiz = selectedQuiz;
            Quiz newQuiz = null;
            String newName = quizNameTextField.getText();
            int index = quizList.getSelectionModel().getSelectedIndex();

            try {
                newQuiz = (Quiz) selectedQuiz.clone();
                newQuiz.setName(newName);
                jsonHandler.replaceQuizInJson(newQuiz, oldQuiz);
                addQuizToList();
                quizList.getSelectionModel().select(index);
            } catch (CloneNotSupportedException | NullPointerException e) {
                if (PRINT_CLONENOTSUP || PRINT_NUllPOINTEXCEP) e.printStackTrace();
            }
        } catch (IOException e){
            if (PRINT_IOEXCEPTION) e.printStackTrace();
            return false;
        }

        return true;

    }

    @FXML
    private void onClickQuestionBtn(Event e) {
        Object node = e.getSource();
        Button eventBtn = (Button) node;
        String btnValue = eventBtn.getText();


        Quiz temp = null;
        int index = questionList.getSelectionModel().getSelectedIndex();
        try {
            temp = (Quiz) jsonHandler.getQuizByName(quizList.getSelectionModel().getSelectedItem()).clone();


            if (btnValue.equals("New")) {
                ArrayList<Answer> initAnswers = new ArrayList<>();
                initAnswers.add(new Answer("newAnwser1", true));
                initAnswers.add(new Answer("newAnwser2", false));
                temp.getQuestionArrayList().add(new Question("newQuestion" + (getNumber(questionList) + 1), initAnswers));
            } else if (btnValue.equals("Remove")) {
                temp.getQuestionArrayList().remove(questionList.getSelectionModel().getSelectedIndex());
                index -= 1;
            } else if (btnValue.equals("Apply")) {
                String newquestionName = questionTextArea.getText();
                ArrayList<Question> arrayList = temp.getQuestionArrayList();
                arrayList.get(arrayList.indexOf(questionList.getSelectionModel().getSelectedItem())).setQuestion(newquestionName);
            } else {
                questionTextArea.clear();
                return;
            }

        } catch (CloneNotSupportedException | IndexOutOfBoundsException ex) {
            if (PRINT_CLONENOTSUP || PRINT_INDEXOUTOFBOUNDSEXCEP) ex.printStackTrace();
        }


        jsonHandler.replaceQuizInJson(temp);
        onClickQuizList();
        questionList.getSelectionModel().select(index);
    }

    @FXML
    private void onClickAnswerBtn(Event e) {
        Object node = e.getSource();
        Button eventBtn = (Button) node;
        String btnValue = eventBtn.getText();

        Quiz temp = null;
        Question tempQuestion;
        int index = answerTable.getSelectionModel().getSelectedIndex();
        try {
            temp = (Quiz) selectedQuiz.clone();
            tempQuestion = temp.getQuestionArrayList().get(temp.getQuestionArrayList().indexOf(selectedQuestion));

            if (btnValue.equals("New")) {
                tempQuestion.getAnswerArrayList().add(new Answer("newAnswer" + (getNumber(answerTable) + 1), false));
            } else if (btnValue.equals("Remove")) {
                tempQuestion.getAnswerArrayList().remove(answerTable.getSelectionModel().getSelectedItem());
                index -= 1;
            } else if (btnValue.equals("Apply")) {
                String newquestionName = answerTextArea.getText();
                ArrayList<Answer> answers = tempQuestion.getAnswerArrayList();
                answers.get(answers.indexOf(answerTable.getSelectionModel().getSelectedItem())).setAnswerText(newquestionName);
                answers.get(answers.indexOf(answerTable.getSelectionModel().getSelectedItem())).setCorrect(isCorrectToggle.isSelected());
            } else {
                answerTextArea.clear();
                return;
            }

        } catch (CloneNotSupportedException | IndexOutOfBoundsException ex) {
            if (PRINT_CLONENOTSUP) ex.printStackTrace();
        }

        jsonHandler.replaceQuizInJson(temp);
        onClickQuestionList(null);
        answerTable.getSelectionModel().select(index);
    }

    @FXML
    private void onClickEditorOkay(){
        if (onClickEditorApply()){
            changeWindow();
        }

    }

    @FXML
    private void onClickEditorCancel(){
        jsonHandler.revertDataJson();
        changeWindow();
    }

    private void changeWindow(){
        try {
            MainApplication.mainWindow = new WindowManager(440, 600, "Quiz", "start-up-view.fxml");
            MainApplication.mainWindow.getGlobalStage().show();
            StartUpController.game.close();
        } catch (IOException e) {
            if (PRINT_IOEXCEPTION) e.printStackTrace();
        }
    }

    private void checkValidationOfAnswers() throws IOException {
            if (answerTable.getItems().size() == 2 || answerTable.getItems().size() == 4 || answerTable.getItems().size() == 0){
                erorrLbl.setText("");
            } else {
                erorrLbl.setText("Es müssen entweder 2 oder 4 Antwortmöglichkeiten sein");
                throw new IOException("Answertable invalid");
            }
    }

    /**
     * This function searches in a ListView for the highest number of an element
     *
     * @param view which ListView should be searched
     * @param <T>  could be String or Question
     * @return highes Number in ListView
     */
    private <T> int getNumber(ListView<T> view) {
        Pattern pattern = Pattern.compile("\\d+$");
        Matcher matcher;
        int highest = -1;
        try {
            if (view.getItems().get(0).getClass().equals(String.class)) {
                for (String i : quizList.getItems()) {
                    matcher = pattern.matcher(i);

                    if (i.matches("newQuiz\\d+$") && matcher.find()) {
                        int temp = Integer.parseInt(matcher.group());
                        if (highest == -1) {
                            highest = temp;
                        } else if (temp > highest) {
                            highest = temp;
                        }
                    }

                }
            } else if (view.getItems().get(0).getClass().equals(Question.class)) {
                for (Question q : questionList.getItems()) {
                    matcher = pattern.matcher(q.getQuestion());

                    if (q.getQuestion().matches("newQuestion\\d+$") && matcher.find()) {
                        int temp = Integer.parseInt(matcher.group());
                        if (highest == -1) {
                            highest = temp;
                        } else if (temp > highest) {
                            highest = temp;
                        }
                    }

                }
            }
        } catch (IndexOutOfBoundsException e) {
            if (PRINT_INDEXOUTOFBOUNDSEXCEP) e.printStackTrace();
        }

        return highest;
    }

    /**
     * This function searches in a TableView for the highest number of an element
     *
     * @param view which TabelView should be searched
     * @param <T>  could be Answer.java
     * @return highes Number in ListView
     */
    private <T> int getNumber(TableView<T> view) {
        Pattern pattern = Pattern.compile("\\d+$");
        Matcher matcher;
        int highest = -1;
        try {
            if (view.getItems().get(0).getClass().equals(Answer.class)) {
                for (Answer a : answerTable.getItems()) {
                    matcher = pattern.matcher(a.getAnswerText());

                    if (a.getAnswerText().matches("newAnswer\\d+$") && matcher.find()) {
                        int temp = Integer.parseInt(matcher.group());
                        if (highest == -1) {
                            highest = temp;
                        } else if (temp > highest) {
                            highest = temp;
                        }
                    }

                }
            }
        } catch (IndexOutOfBoundsException e) {
            if (PRINT_INDEXOUTOFBOUNDSEXCEP) e.printStackTrace();
        }

        return highest;
    }


}
