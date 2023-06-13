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
