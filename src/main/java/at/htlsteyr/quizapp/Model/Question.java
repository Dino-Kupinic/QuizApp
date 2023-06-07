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

public class Question<T> {
    private String question;
    private ArrayList<String> answerArrayList;
    private T correctAnswer;

    public Question(String question, ArrayList<String> answerArrayList, T correctAnswer) {
        this.question = question;
        this.answerArrayList = answerArrayList;
        checkValidCorrectAnswer(correctAnswer);
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

    public T getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(T correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * changes a specified answer from the list
     *
     * @param index     index of the answer that will be replaced
     * @param newAnswer text of the new answer
     */
    public void changeAnswer(int index, String newAnswer) {
        this.answerArrayList.set(index, newAnswer);
    }

    /**
     * Removes the specified answer by replacing it with an empty string
     *
     * @param index index of what answer should be removed / cleared
     */
    public void removeAnswer(int index) {
        this.answerArrayList.set(index, "");
    }

    /**
     * Checks if correctAnswer is an Integer or an ArrayList<Integer>
     *
     * @throws IllegalArgumentException Thrown when the conditions are not met
     */
    public void checkValidCorrectAnswer(T correctAnswer) {
        boolean instanceOfInteger = correctAnswer instanceof Integer;
        boolean instanceOfIntegerArrayList = (correctAnswer instanceof ArrayList && checkArrayListIsInteger((ArrayList<?>) correctAnswer));

        if (!(instanceOfInteger || instanceOfIntegerArrayList)) {
            throw new IllegalArgumentException("Invalid answer type");
        }
    }

    /**
     * Iterates through generic ArrayList and checks if each element is an instance of Number
     *
     * @param arrayList generic ArrayList
     * @return true if all elements are numbers
     */
    private boolean checkArrayListIsInteger(ArrayList<?> arrayList) {
        for (Object element : arrayList) {
            if (!(element instanceof Integer)) {
                return false;
            }
        }
        return true;
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
