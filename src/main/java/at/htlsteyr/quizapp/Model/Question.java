/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 6.6.2023
 *
 * @details
 * Class to store each Question and its answers
 */

package at.htlsteyr.quizapp.Model;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answerArrayList;
    private int correctAnswer;

    public Question(String question, ArrayList<String> answerArrayList, int correctAnswer) {
        this.question = question;
        this.answerArrayList = answerArrayList;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getAnswerArrayList() {
        return answerArrayList;
    }

    public void setAnswerArrayList(ArrayList<String> answerArrayList) {
        this.answerArrayList = answerArrayList;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * changes a specified answer from the list
     * @param index index of the answer that will be replaced
     * @param newAnswer text of the new answer
     */
    public void changeAnswer(int index, String newAnswer) {
        this.answerArrayList.set(index, newAnswer);
    }

    /**
     * Removes the specified answer by replacing it with an empty string
     * @param index index of what answer should be removed / cleared
     */
    public void removeAnswer(int index) {
        this.answerArrayList.set(index, "");
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answerArrayList=" + answerArrayList +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
