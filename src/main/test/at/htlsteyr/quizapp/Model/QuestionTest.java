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

package at.htlsteyr.quizapp.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    private Question question;

    @BeforeEach
    public void setup() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("2");
        answers.add("4");
        answers.add("21");
        answers.add("1");
        question = new Question("What is 1+1?", answers, 3);
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
        ArrayList<String> answers = new ArrayList<>();
        answers.add("2");
        answers.add("4");
        answers.add("21");
        answers.add("1");
        assertEquals(answers, question.getAnswerArrayList());
    }

    @Test
    public void testSetAnswerArrayList() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("2");
        answers.add("5");
        answers.add("22");
        answers.add("1");
        question.setAnswerArrayList(answers);
        assertEquals(answers, question.getAnswerArrayList());
    }

    @Test
    public void testGetCorrectAnswer() {
        assertEquals(3, question.getCorrectAnswer());
    }

    @Test
    public void testSetCorrectAnswer() {
        question.setCorrectAnswer(2);
        assertEquals(2, question.getCorrectAnswer());
    }

    @Test
    public void testChangeAnswer() {
        question.changeAnswer(1, "99");
        assertEquals("99", question.getAnswerArrayList().get(1));
    }

    @Test
    public void removeAnswer() {
        question.removeAnswer(1);
        assertEquals("2", question.getAnswerArrayList().get(0));
    }
}
