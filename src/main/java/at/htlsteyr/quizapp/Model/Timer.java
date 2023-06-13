package at.htlsteyr.quizapp.Model;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;

import at.htlsteyr.quizapp.Controller.StartUpController;


/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic, Jannick Angerer
 * @date    : 22.4.2023
 *
 * @details
 * Class to handle the timer in timed-mode
 */


import java.util.Date;

public class Timer {
    private static AnimationTimer animationTimer;
    private static boolean activeTimer;
    private static float timePlayed = 15;
    private long lastFrame = -1;
    Score score = new Score(1500.0);

    public static boolean getActiveTimer() {
        return activeTimer;
    }

    public static void setActiveTimer(boolean bool) {
        activeTimer = bool;
    }

    public static void setTimePlayed(int value) {
        timePlayed = value;
    }

    /**
     * starts the timer and updates the timer label
     *
     * @param controller the MainController
     */
    public void startTimer(StartUpController controller) {
        activeTimer = true;
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (timePlayed <= 0) {
                    stopTimer();
                    return; // exit the method to prevent further updates
                }
                if (lastFrame != -1 ) {
                    timePlayed -= (new Date().getTime() - lastFrame) / 1000.0;
                }
                score.updateScore();
                System.out.printf("%.1f%n", score.getScore());
                lastFrame = new Date().getTime();
                Platform.runLater(() -> controller.setTimerLabel(String.format("%.1fs", timePlayed)));
            }
        };
        animationTimer.start();
    }

    /**
     * stops the animation timer
     */
    public void stopTimer() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
        timePlayed = 0;
        lastFrame = -1;
    }
}

