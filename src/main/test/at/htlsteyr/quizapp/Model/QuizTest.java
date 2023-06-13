///*-----------------------------------------------------------------------------
// *              Hoehere Technische Bundeslehranstalt STEYR
// *----------------------------------------------------------------------------*/
///**
// * Kurzbeschreibung
// *
// * @author : Dino Kupinic
// * @date : 7.6.2023
// * @details Class to test the Quiz class
// */
//
//package at.htlsteyr.quizapp.Model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class QuizTest {
//    private Quiz<Integer> quiz;
//
//    @BeforeEach
//    public void setup() {
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("2");
//        answers.add("4");
//        answers.add("21");
//        answers.add("1");
//        Question<Integer> question = new Question<>("What is 1+1?", answers, 3);
//        ArrayList<Question<Integer>> questions = new ArrayList<>();
//        questions.add(question);
//        quiz = new Quiz<>("myQuiz", questions);
//    }
//
//    @Test
//    public void testGetName() {
//        assertEquals("myQuiz", quiz.getName());
//    }
//
//    @Test
//    public void testSetName() {
//        quiz.setName("notMyQuiz");
//        assertEquals("notMyQuiz", quiz.getName());
//    }
//
//    @Test
//    public void testGetQuestionArrayList() {
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("2");
//        answers.add("4");
//        answers.add("21");
//        answers.add("1");
//        Question<Integer> question = new Question<>("What is 1+1?", answers, 3);
//        ArrayList<Question<Integer>> questions = new ArrayList<>();
//        questions.add(question);
//        assertEquals(questions.size(), quiz.getQuestionArrayList().size());
//
//        for (int i = 0; i < questions.size(); i++) {
//            assertEquals(questions.get(i).toString(), quiz.getQuestionArrayList().get(i).toString());
//        }
//    }
//
//    @Test
//    public void testSetQuestionArrayList() {
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("1");
//        answers.add("5");
//        answers.add("22");
//        answers.add("2");
//        Question<Integer> question = new Question<>("What is 1+1?", answers, 3);
//        ArrayList<Question<Integer>> questions = new ArrayList<>();
//        questions.add(question);
//        quiz.setQuestionArrayList(questions);
//        assertEquals(questions, quiz.getQuestionArrayList());
//    }
//
//    @Test
//    public void testAddQuestion() {
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("2");
//        answers.add("4");
//        answers.add("21");
//        answers.add("1");
//        Question<Integer> question = new Question<>("What is 1+1?", answers, 3);
//        ArrayList<Question<Integer>> questions = new ArrayList<>();
//        questions.add(question);
//        questions.add(new Question<>("What is 1+2?", answers, 0));
//        quiz.addQuestion(new Question<>("What is 1+2?", answers, 0));
//        assertEquals(questions.toString(), quiz.getQuestionArrayList().toString());
//    }
//
//    @Test
//    public void testRemoveQuestion() {
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("2");
//        answers.add("4");
//        answers.add("21");
//        answers.add("1");
//        Question<Integer> question = new Question<>("What is 1+1?", answers, 3);
//        ArrayList<Question<Integer>> questions = new ArrayList<>();
//        questions.add(question);
//        quiz.addQuestion(new Question<>("What is 1+2?", answers, 0));
//        quiz.removeQuestion(1);
//        assertEquals(questions.toString(), quiz.getQuestionArrayList().toString());
//    }
//
//    @Test
//    public void testChangeQuestions() {
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("2");
//        answers.add("4");
//        answers.add("21");
//        answers.add("1");
//        Question<Integer> question = new Question<>("What is 1+1?", answers, 3);
//        ArrayList<Question<Integer>> questions = new ArrayList<>();
//        questions.add(question);
//        questions.set(0, new Question<>("hello?", answers, 0));
//        quiz.changeQuestion(0, new Question<>("hello?", answers, 0));
//        assertEquals(questions.toString(), quiz.getQuestionArrayList().toString());
//    }
//
//    @Test
//    public void testClearAllQuestions() {
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("2");
//        answers.add("4");
//        answers.add("21");
//        answers.add("1");
//        Question<Integer> question = new Question<>("What is 1+1?", answers, 3);
//        ArrayList<Question<Integer>> questions = new ArrayList<>();
//        questions.add(question);
//        questions.clear();
//        quiz.clearAllQuestions();
//        assertEquals(questions, quiz.getQuestionArrayList());
//    }
//}
