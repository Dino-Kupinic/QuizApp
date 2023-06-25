/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 7.6.2023
 * @details Class to test the Quiz class
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizTest {
    private Quiz quiz;

    @BeforeEach
    public void setup() {
        ArrayList<Question> questions = new ArrayList<>();

        ArrayList<Answer> answerArrayList = new ArrayList<>();
        answerArrayList.add(new Answer("a", false));
        answerArrayList.add(new Answer("2", true));
        questions.add(new Question("1+1?", answerArrayList));

        quiz = new Quiz("myQuiz", questions);
    }

    @Test
    public void testGetName() {
        assertEquals("myQuiz", quiz.getName());
    }

    @Test
    public void testSetName() {
        quiz.setName("notMyQuiz");
        assertEquals("notMyQuiz", quiz.getName());
    }

    @Test
    public void testGetQuestionArrayList() {
        ArrayList<Question> questions = new ArrayList<>();

        ArrayList<Answer> answerArrayList = new ArrayList<>();
        answerArrayList.add(new Answer("a", false));
        answerArrayList.add(new Answer("2", true));
        questions.add(new Question("1+1?", answerArrayList));
        assertEquals(questions.size(), quiz.getQuestionArrayList().size());

        for (int i = 0; i < questions.size(); i++) {
            assertEquals(questions.get(i).getQuestion(), quiz.getQuestionArrayList().get(i).getQuestion());
        }
    }

    @Test
    public void testSetQuestionArrayList() {
        ArrayList<Question> questions = new ArrayList<>();

        ArrayList<Answer> answerArrayList = new ArrayList<>();
        answerArrayList.add(new Answer("a", false));
        answerArrayList.add(new Answer("2", true));
        questions.add(new Question("1+1?", answerArrayList));
        quiz.setQuestionArrayList(questions);
        assertEquals(questions, quiz.getQuestionArrayList());
    }


}
