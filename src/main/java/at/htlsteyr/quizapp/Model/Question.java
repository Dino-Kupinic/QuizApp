/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 6.6.2023
 * @details Class to store each Question and its answers
 */

package at.htlsteyr.quizapp.Model;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<Answer> answerArrayList;

    public Question(String question, ArrayList<Answer> answerArrayList) {
        this.question = question;
        this.answerArrayList = answerArrayList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswerArrayList() {
        return answerArrayList;
    }

    public void setAnswerArrayList(ArrayList<Answer> answerArrayList) {
        this.answerArrayList = answerArrayList;
    }

    /**
     * changes a specified answer from the list
     *
     * @param index     index of the answer that will be replaced
     * @param newAnswer text of the new answer
     */
    public void changeAnswer(int index, String newAnswer) {
        this.answerArrayList.get(index).setAnswerText(newAnswer);
    }

    /**
     * Removes the specified answer by replacing it with an empty string
     *
     * @param index index of what answer should be removed / cleared
     */
    public void removeAnswer(int index) {
        this.answerArrayList.remove(index);
    }

    /**
     * Checks if correctAnswer is an Integer or an ArrayList<Integer>
     *
     * @throws IllegalArgumentException Thrown when the conditions are not met
     */
    public boolean checkValidCorrectAnswer(Answer answer) {
        return !answer.getAnswerText().equals("");
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answerArrayList=" + answerArrayList +
                '}';
    }
}
