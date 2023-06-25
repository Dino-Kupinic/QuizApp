/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic, Daniel Samhaber, Michael Ploier, Jannick Angerer
 * @date : 5.6.2023
 * @details Main class to start the program
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

package at.htlsteyr.quizapp;

import at.htlsteyr.quizapp.Model.JsonHandler;
import at.htlsteyr.quizapp.Model.WindowManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static int WIDTH = 1024;
    public static int HEIGHT = 768;
    public static WindowManager mainWindow;

    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = new WindowManager(440, 600, "Quiz", "start-up-view.fxml");
        mainWindow.getGlobalStage().show();
        checkDataJson();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Checks if Data json is valid and writes an example quiz into it if it is empty
     *
     * @throws IOException file error
     */
    public void checkDataJson() throws IOException {
        JsonHandler jsonHandler = new JsonHandler();
        if (!jsonHandler.isDataJsonValid()) {
            jsonHandler.addJsonArrayIfJsonIsntValid();
            jsonHandler.createExampleQuiz();
        }
    }
}