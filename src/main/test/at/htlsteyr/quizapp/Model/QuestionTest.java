/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 7.6.2023
 * @details Class to test the Question class
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

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    private Question question;

    @BeforeEach
    public void setup() {
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("2", true));
        answers.add(new Answer("21", false));
        answers.add(new Answer("11", false));
        answers.add(new Answer("1", false));
        question = new Question("What is 1+1?", answers);
    }

    @Test
    public void testGetQuestion() {
        assertEquals("What is 1+1?", question.getQuestion());
    }

    @Test
    public void testSetQuestion() {
        question.setQuestion("What is 1+2?");
        assertEquals("What is 1+2?", question.getQuestion());
    }

    @Test
    public void testGetAnswerArrayList() {
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("2", true));
        answers.add(new Answer("21", false));
        answers.add(new Answer("11", false));
        answers.add(new Answer("1", false));
        for (int i = 0; i < answers.size(); i++) {
            assertEquals(answers.get(i).getAnswerText(), question.getAnswerArrayList().get(i).getAnswerText());
        }
    }

    @Test
    public void testSetAnswerArrayList() {
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("4", true));
        answers.add(new Answer("21", false));
        answers.add(new Answer("11", false));
        answers.add(new Answer("1", false));
        question.setAnswerArrayList(answers);
        assertEquals(answers, question.getAnswerArrayList());
    }

    @Test
    public void testCheckValidCorrectAnswerTrue() {
        assertTrue(question.checkValidCorrectAnswer(new Answer("a", true)));
    }

    @Test
    public void testCheckValidCorrectAnswerFalse() {
        assertFalse(question.checkValidCorrectAnswer(new Answer("", true)));
    }

    @Test
    public void testChangeAnswer() {
        question.changeAnswer(1, "99");
        assertEquals("99", question.getAnswerArrayList().get(1).getAnswerText());
    }

    @Test
    public void removeAnswer() {
        question.removeAnswer(1);
        assertEquals("2", question.getAnswerArrayList().get(0).getAnswerText());
    }
}
