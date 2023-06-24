/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 13.6.2023
 * @details Controller to handle all controls for the quiz selection view
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

package at.htlsteyr.quizapp.Controller;

import at.htlsteyr.quizapp.MainApplication;
import at.htlsteyr.quizapp.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class SelectionViewController {
    @FXML
    private TableView<Player> topPlayers;
    @FXML
    private TableColumn<Player, String> nameColBestPlayers;
    @FXML
    private TableColumn<Player, Score> scoreColBestPlayers;
    @FXML
    private TableView<Player> leaderboard;
    @FXML
    private TableColumn<Player, Score> scoreCol;
    @FXML
    private TableColumn<Player, String> nameCol;
    @FXML
    private ListView<String> quizesList;
    @FXML
    private Button playButton;
    @FXML
    private ListView listView;
    public static String selectedItem;

    public void initialize() {
        addQuizToList();
        displayLeaderboard();
    }

    /**
     * displays the top players of a selected quiz
     */
    public void displayTopPlayers() {
        selectedItem = quizesList.getSelectionModel().getSelectedItem();
        if (!Objects.equals(selectedItem, "")) {
            JsonHandler jsonHandler = new JsonHandler();
            ArrayList<Quiz> quizArrayList = jsonHandler.getAllQuizes();
            for (Quiz q : quizArrayList) {
                if (Objects.equals(q.getName(), selectedItem)) {
                    topPlayers.setItems(jsonHandler.getAllTopPlayersForTableView(q));
                    nameColBestPlayers.setCellValueFactory(new PropertyValueFactory<>("name"));
                    scoreColBestPlayers.setCellValueFactory(new PropertyValueFactory<>("currentScore"));
                    getPropertyOfScoreClass(scoreColBestPlayers);
                }
            }
        }
    }

    /**
     * gets the score property of the score class and updates the top player view
     */
    private void getPropertyOfScoreClass(TableColumn<Player, Score> scoreCol) {
        scoreCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Score score, boolean empty) {
                super.updateItem(score, empty);
                if (empty || score == null) {
                    setText("");
                } else {
                    setText(String.valueOf(score.getScore()));
                }
            }
        });
    }

    /**
     * fills the Leaderboard with all players
     */
    public void displayLeaderboard() {
        JsonHandler jsonHandler = new JsonHandler();
        ArrayList<Player> playerArrayList = null;
        if (jsonHandler.isPlayerJsonValid()) {
            playerArrayList = jsonHandler.readAllPlayers();
        }
        if (playerArrayList != null) {
            leaderboard.setItems(jsonHandler.getAllPlayersForTableView(playerArrayList));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            scoreCol.setCellValueFactory(new PropertyValueFactory<>("totalScore"));
            getPropertyOfScoreClass(scoreCol);
        }
    }

    /**
     * Adds all quiz to the list view
     */
    public void addQuizToList() {
        JsonHandler jsonHandler = new JsonHandler();
        ArrayList<Quiz> quizArrayList = null;
        if (jsonHandler.isDataJsonValid()) {
            quizArrayList = jsonHandler.getAllQuizes();
        }
        if (quizArrayList != null) {
            for (Quiz q : quizArrayList) {
                quizesList.getItems().add(q.getName());
            }
        }
    }


    @FXML
    public void qsPlayBtnClicked() throws IOException {
        StartUpController.game = new WindowManager("Game", "fourAnswer-view.fxml");
        StartUpController.game.getGlobalStage().show();
    }
}
