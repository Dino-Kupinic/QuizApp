/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Michael Ploier
 * @date : 12.6.2023
 * @details Class to manage the windows
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

import at.htlsteyr.quizapp.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager implements Debug {
    private Stage globalStage;
    private Scene globalScene;
    private double height;
    private double width;
    private String title;
    private String fxmlFileName;
    private FXMLLoader fxmlLoader;

    /**
     * Main purpose is to easily change between
     * stages or scenes
     *
     * @param height       Height of the stage
     * @param width        Width of the stage
     * @param title        Titel of the stage
     * @param fxmlFileName Path to the fxml-file
     * @throws IOException If fxml-file can not been found
     */
    public WindowManager(double height, double width, String title, String fxmlFileName) throws IOException {
        globalStage = new Stage();
        setHeight(height);
        setWidth(width);
        setTitle(title);
        setFxmlFileName(fxmlFileName);
        globalStage.setScene(globalScene);
    }

    /**
     * Main purpose is to easily change between
     * stages or scenes.This constructor can be
     * used if a Scene already exists.
     *
     * @param height Height of the stage
     * @param width  Width of the stage
     * @param title  Titel of the stage
     * @param scene  Scene for the stage
     */
    public WindowManager(double height, double width, String title, Scene scene) {
        globalStage = new Stage();
        setHeight(height);
        setWidth(width);
        setTitle(title);
        globalStage.setScene(scene);
    }

    /**
     * Main purpose is to easily change between
     * stages or scenes. This constructor can be
     * used if a stage already exists. You have to set
     * the FXML-Loader via Scene.setUserData(FXML.Loader)
     *
     * @param stage global Stage
     */
    public WindowManager(Stage stage) {
        this.globalStage = stage;
        setTitle(stage.getTitle());
        setWidth(stage.getWidth());
        setHeight(stage.getHeight());
        setGlobalScene(stage.getScene());
        fxmlLoader = (FXMLLoader) getGlobalScene().getUserData();
    }

    //------------------------ Getter ------------------------

    public Stage getGlobalStage() {
        return globalStage;
    }

    public Scene getGlobalScene() {
        return globalScene;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public String getTitle() {
        return title;
    }

    public String getFxmlFileName() {
        return fxmlFileName;
    }

    public Object getController() {
        return fxmlLoader.getController();
    }

    //------------------------ Setter ------------------------
    public void setGlobalStage(Stage globalStage) {
        this.globalStage = globalStage;
    }

    public void updateGlobalStage() {
        globalStage.setScene(globalScene);
    }

    public Stage newStage() {
        Stage temp = new Stage();
        temp.setScene(globalScene);
        temp.show();
        return temp;
    }

    public Stage newStage(int height, int width, String Titel, String fxmlFilePath) throws IOException {
        FXMLLoader tmpLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFilePath));
        Stage temp = new Stage();
        temp.setScene(new Scene(tmpLoader.load()));
        temp.setHeight(height);
        temp.setWidth(width);
        temp.setTitle(Titel);
        temp.show();
        return temp;
    }

    public void setGlobalScene(Scene globalScene) {
        this.globalScene = globalScene;
    }

    public void setHeight(double height) {
        this.height = height;
        try {
            globalStage.setHeight(height);
        } catch (NullPointerException e) {
            if (PRINT_NUllPOINTEXEP) e.printStackTrace();
        }
    }

    public void setWidth(double width) {
        this.width = width;
        try {
            globalStage.setWidth(width);
        } catch (NullPointerException e) {
            if (PRINT_NUllPOINTEXEP) e.printStackTrace();
        }
    }

    public void setTitle(String title) {
        this.title = title;
        try {
            globalStage.setTitle(title);
        } catch (NullPointerException e) {
            if (PRINT_NUllPOINTEXEP) e.printStackTrace();
        }
    }

    public void setFxmlFileName(String fxmlFileName) throws IOException {
        this.fxmlFileName = fxmlFileName;
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFileName));
        globalScene = new Scene(fxmlLoader.load(), width, height);
    }

    public void close() {
        globalStage.close();
    }

    public void close(Stage stage) {
        stage.close();
    }

}
