package at.htlsteyr.quizapp.Controller;

import at.htlsteyr.quizapp.Model.Player;
import at.htlsteyr.quizapp.Model.Quiz;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private ListView<Quiz> quizesList;
    @FXML
    private Button playButton;
}
