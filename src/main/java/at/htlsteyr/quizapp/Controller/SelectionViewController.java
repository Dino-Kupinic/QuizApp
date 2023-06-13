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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class SelectionViewController {
    @FXML
    private TableView<Player> topPlayers;
    @FXML
    private TableColumn<Player, String> nameColBestPlayers;
    @FXML
    private TableColumn<Player, Integer> scoreColBestPlayers;
    @FXML
    private TableView<Player> leaderboard;
    @FXML
    private TableColumn<Player, Integer> scoreCol;
    @FXML
    private TableColumn<Player, String> nameCol;
    @FXML
    private ListView<String> quizesList;
    @FXML
    private Button playButton;

    public void initialize() {
        addQuizToList();
    }

    public void displayTopPlayers() {

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
