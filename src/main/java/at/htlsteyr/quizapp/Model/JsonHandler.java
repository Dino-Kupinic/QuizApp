/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 6.6.2023
 * @details Class to easily handle JSON
 */

package at.htlsteyr.quizapp.Model;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonHandler {
    private final Path PATH_DATA_JSON;
    private final Path PATH_PLAYER_JSON;
    private final File questionJsonFile;
    private final File playerJsonFile;
    private final Gson gson;

    public JsonHandler() {
        PATH_DATA_JSON = Paths.get("src/main/resources/at/htlsteyr/quizapp/data.json");
        PATH_PLAYER_JSON = Paths.get("src/main/resources/at/htlsteyr/quizapp/player.json");
        questionJsonFile = new File(PATH_DATA_JSON.toUri());
        playerJsonFile = new File(PATH_PLAYER_JSON.toUri());
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * adds a quiz to data.json
     *
     * @param quiz quiz object containg name, questions and optionally its top players
     */
    public void writeQuizToJson(Quiz quiz) {
        try {
            StringBuilder sb = getStringBuilder(questionJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);

            // populate jsonObject with quiz data
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", quiz.getName());
            JsonArray questionArray = gson.toJsonTree(quiz.getQuestionArrayList()).getAsJsonArray();
            jsonObject.add("questions", questionArray);
            JsonArray topPlayerArray = gson.toJsonTree(quiz.getTopPlayers()).getAsJsonArray();
            jsonObject.add("topPlayers", topPlayerArray);
            jsonArray.add(jsonObject);

            String json = gson.toJson(jsonArray);
            FileWriter fW = new FileWriter(questionJsonFile);
            fW.write(json);
            fW.close();

            // Log action
            System.out.println("Added " + quiz.getName() + " Quiz to data.json!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * writes a player to player.json
     *
     * @param player player object containing id, name and scores
     */
    public void writePlayerToJson(Player player) {
        try {
            StringBuilder sb = getStringBuilder(playerJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", player.getId());
            jsonObject.addProperty("name", player.getName());
            jsonObject.addProperty("totalScore", player.getTotalScore().getScore());
            jsonArray.add(jsonObject);

            String json = gson.toJson(jsonArray);
            FileWriter fileWriter = new FileWriter(playerJsonFile);
            fileWriter.write(json);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads all questios from data.json
     *
     * @return arrayList of all quizes
     */
    public ArrayList<Quiz> getAllQuizes() {
        try {
            StringBuilder sb = getStringBuilder(questionJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);
            ArrayList<Quiz> quizArrayList = new ArrayList<>();

            for (JsonElement element : jsonArray) {
                JsonObject object = element.getAsJsonObject();
                String quizName = object.get("name").getAsString().replaceAll("\"", "");

                JsonArray questionArray = object.get("questions").getAsJsonArray();
                ArrayList<Question> questionArrayList = readQuestions(questionArray);

                ArrayList<Player> topPlayers = new ArrayList<>();
                if (!(object.get("topPlayers").getAsJsonArray() == null)) {
                    topPlayers = readTopPlayers(object);
                }
                if (topPlayers.size() != 0) {
                    quizArrayList.add(new Quiz(quizName, questionArrayList, topPlayers));
                } else {
                    quizArrayList.add(new Quiz(quizName, questionArrayList));
                }
            }
            // Log action
            System.out.println("Read all quizes from data.json!");

            return quizArrayList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * reads all questions from each quiz
     *
     * @param questionArray arrayList containing the questions from the quiz
     * @return arrayList of questions
     */
    private ArrayList<Question> readQuestions(JsonArray questionArray) {
        ArrayList<Question> tempQuestionList = new ArrayList<>();
        for (JsonElement questionElement : questionArray) {
            JsonObject questionObject = questionElement.getAsJsonObject();
            String question = questionObject.get("question").getAsString();

            ArrayList<Answer> answerArrayList = new ArrayList<>();
            JsonArray answerArray = questionObject.get("answerArrayList").getAsJsonArray();

            for (JsonElement answerElement : answerArray) {
                JsonObject answer = answerElement.getAsJsonObject();
                String answerText = answer.get("answerText").getAsString();
                Boolean isCorrect = answer.get("isCorrect").getAsBoolean();
                answerArrayList.add(new Answer(answerText, isCorrect));
            }
            tempQuestionList.add(new Question(question, answerArrayList));
        }
        return tempQuestionList;
    }

    /**
     * reads the top players from data json
     *
     * @param object jsonObject (quiz)
     * @return ArrayList of players
     */
    private ArrayList<Player> readTopPlayers(JsonObject object) {
        ArrayList<Player> tempPlayerArray = new ArrayList<>();
        JsonArray topPlayerArray = object.getAsJsonArray("topPlayers");
        for (JsonElement el : topPlayerArray) {
            JsonObject player = el.getAsJsonObject();
            Integer id = player.get("id").getAsInt();
            String name = player.get("name").getAsString();
            JsonObject currentScoreObject = player.get("currentScore").getAsJsonObject();
            JsonObject totalScoreObject = player.get("totalScore").getAsJsonObject();
            int currentScore = currentScoreObject.get("score").getAsInt();
            int totalScore = totalScoreObject.get("score").getAsInt();
            tempPlayerArray.add(new Player(id, name, new Score(currentScore), new Score(totalScore)));
        }
        return tempPlayerArray;
    }

    /**
     * reads all players from the quiz
     * @return observable list with all players
     */
    public ObservableList<Player> getAllPlayers(Quiz quiz) {
        ArrayList<Player> playerArrayList = quiz.getTopPlayers();
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.addAll(playerArrayList);
        return players;
    }

    /**
     * utility function read a whole file with a string builder
     *
     * @param file the file which is supposed to be read
     * @return StringBuilder
     * @throws FileNotFoundException Thrown when the target file can't be found
     */
    private StringBuilder getStringBuilder(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        return sb;
    }

    /**
     * Adds an array initializer when the JSON is empty
     *
     * @param file target file
     * @throws IOException Thrown when something goes wrong when trying to access the file
     */
    private void addJSONArray(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("[]");
        fileWriter.close();
        System.out.println("Opening Array not found, created []");
    }

    /**
     * Checks if the data JSON is valid
     *
     * @throws IOException Thrown when something goes wrong when trying to access the file
     */
    public void addJsonArrayIfJsonIsntValid() throws IOException {
        try {
            Scanner scanner = new Scanner(questionJsonFile);
            if (!scanner.hasNextLine()) {
                addJSONArray(questionJsonFile);
            } else {
                System.out.println("No problems found, [] exists in data.json");
            }
        } catch (FileNotFoundException e) {
            File f = new File(PATH_DATA_JSON.toUri());
            addJSONArray(f);
        }
    }

    /**
     * checks if data json is valid or not
     *
     * @return boolean
     */
    public boolean isDataJsonValid() {
        try {
            Scanner scanner = new Scanner(questionJsonFile);
            return scanner.hasNextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
