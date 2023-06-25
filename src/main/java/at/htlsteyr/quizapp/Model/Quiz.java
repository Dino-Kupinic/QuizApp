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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Quiz(name, questionArrayList, topPlayers);
    }
}
