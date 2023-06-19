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
