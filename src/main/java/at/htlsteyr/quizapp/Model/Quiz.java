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
 * Class to store each Quiz
 */

package at.htlsteyr.quizapp.Model;

import java.util.ArrayList;

public class Quiz<T> {
    private String name;
    private ArrayList<Question<T>> questionArrayList;

    public Quiz(String name, ArrayList<Question<T>> questionArrayList) {
        this.name = name;
        this.questionArrayList = questionArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question<T>> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(ArrayList<Question<T>> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }

    public void addQuestion(Question<T> question) {
        this.questionArrayList.add(question);
    }

    public void removeQuestion(int index) {
        this.questionArrayList.remove(index);
    }

    public void changeQuestion(int index, Question<T> newQuestion) {
        this.questionArrayList.set(index, newQuestion);
    }

    public void clearAllQuestions() {
        this.questionArrayList.clear();
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "name='" + name + '\'' +
                ", questionArrayList=" + questionArrayList +
                '}';
    }
}
