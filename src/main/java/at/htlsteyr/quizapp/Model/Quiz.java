/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 6.6.2023
 * @details Class to store each Quiz
 */

package at.htlsteyr.quizapp.Model;

import java.util.ArrayList;

public class Quiz {
    private String name;
    private ArrayList<Question> questionArrayList;
    private ArrayList<Player> topPlayers;

    public Quiz(String name, ArrayList<Question> questionArrayList, ArrayList<Player> topPlayers) {
        this.name = name;
        this.questionArrayList = questionArrayList;
        this.topPlayers = topPlayers;
    }

    public Quiz(String name, ArrayList<Question> questionArrayList) {
        this.name = name;
        this.questionArrayList = questionArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(ArrayList<Question> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }

    public ArrayList<Player> getTopPlayers() {
        return topPlayers;
    }

    public void setTopPlayers(ArrayList<Player> topPlayers) {
        this.topPlayers = topPlayers;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "name='" + name + '\'' +
                ", questionArrayList=" + questionArrayList +
                ", topPlayers=" + topPlayers +
                '}';
    }
}
