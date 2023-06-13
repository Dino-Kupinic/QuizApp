package at.htlsteyr.quizapp.Model;

import at.htlsteyr.quizapp.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager {
    private Stage globalStage;
    private Scene globalScene;
    private int height;
    private int width;
    private String title;
    private String fxmlFilePath;
    private FXMLLoader fxmlLoader;

    public WindowManager(int height, int width, String title, String fxmlFilePath) throws IOException {
        globalStage = new Stage();
        setHeight(height);
        setWidth(width);
        setTitle(title);
        setFxmlFilePath(fxmlFilePath);
        globalStage.setScene(globalScene);
    }

    public Stage getGlobalStage() {
        return globalStage;
    }

    public void setGlobalStage(Stage globalStage) {
        this.globalStage = globalStage;
    }

    public void updateGlobalStage(){
        globalStage.setScene(globalScene);
    }

    public Stage newStage(){
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

    public Scene getGlobalScene() {
        return globalScene;
    }

    public void setGlobalScene(Scene globalScene) {
        this.globalScene = globalScene;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        try {
            globalStage.setHeight(height);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        try {
            globalStage.setWidth(width);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        try {
            globalStage.setTitle(title);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public String getFxmlFilePath() {
        return fxmlFilePath;
    }

    public void setFxmlFilePath(String fxmlFilePath) throws IOException {
        this.fxmlFilePath = fxmlFilePath;
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFilePath));
        globalScene = new Scene(fxmlLoader.load(),width, height);
    }

    public Object getController(){
        return fxmlLoader.getController();
    }

}
