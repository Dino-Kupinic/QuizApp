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
