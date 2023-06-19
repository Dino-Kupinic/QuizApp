package at.htlsteyr.quizapp.Model;

import at.htlsteyr.quizapp.Controller.StartUpController;
import at.htlsteyr.quizapp.MainApplication;
import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {

    @Test
    public void rightScoreAtTheBeginning (){
        Score score = new Score(1500.00);
        assertEquals(1500.00, score.getScore());
    }


    @Test
    public void TestSetScore () {
        Score score = new Score(1500.00);
        assertEquals(1500.00, score.getScore());
    }

    @Test
    public void TestUpdateScore () {
        Score score = new Score(1500.00);
        score.updateScore();
        assertEquals(1499.25, score.getScore());
    }


}