/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Jannick Angerer
 * @date : 13.6.2023
 * @details Class to handle the timer in timed-mode
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

import at.htlsteyr.quizapp.Controller.QuizgameController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

import at.htlsteyr.quizapp.Controller.StartUpController;


import java.util.Date;

public class Timer {
    private static AnimationTimer animationTimer;
    private static boolean activeTimer;
    private static float timePlayed = 15;
    private long lastFrame = -1;
    private Score score = new Score(1500.0);

    public static boolean getActiveTimer() {
        return activeTimer;
    }

    public static void setActiveTimer(boolean bool) {
        activeTimer = bool;
    }

    public static void setTimePlayed(int value) {
        timePlayed = value;
    }

    public double getScore() {
        return score.getScore();
    }

    public void setScore(Score score) {
        this.score = score;
    }

    /**
     * starts the timer and updates the timer label
     *
     * @param controller the MainController
     */
    public void startTimer(QuizgameController controller) {
        activeTimer = true;
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (timePlayed <= 0) {
                    stopTimer();
                    return; // exit the method to prevent further updates
                }
                if (lastFrame != -1) {
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

