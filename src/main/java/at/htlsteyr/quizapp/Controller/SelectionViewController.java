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

package at.htlsteyr.quizapp.Controller;

import at.htlsteyr.quizapp.Model.JsonHandler;
import at.htlsteyr.quizapp.Model.Player;
import at.htlsteyr.quizapp.Model.Quiz;
import at.htlsteyr.quizapp.Model.Score;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    public void initialize() {
        addQuizToList();
    }

    /**
     * displays the top players of a selected quiz
     */
    public void displayTopPlayers() {
        String selectedItem = quizesList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            JsonHandler jsonHandler = new JsonHandler();
            ArrayList<Quiz> quizArrayList = jsonHandler.getAllQuizes();
            for (Quiz q : quizArrayList) {
                if (Objects.equals(q.getName(), selectedItem)) {
                    topPlayers.setItems(jsonHandler.getAllPlayers(q));
                    nameColBestPlayers.setCellValueFactory(new PropertyValueFactory<>("name"));
                    scoreColBestPlayers.setCellValueFactory(new PropertyValueFactory<>("currentScore"));
                    getPropertyOfScoreClass();
                }
            }
        }
    }

    /**
     * gets the score property of the score class and updates the top player view
     */
    private void getPropertyOfScoreClass() {
        scoreColBestPlayers.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Score score, boolean empty) {
                super.updateItem(score, empty);
                if (empty || score == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(score.getScore()));
                }
            }
        });
    }

    public void displayLeaderboard() {

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
}
