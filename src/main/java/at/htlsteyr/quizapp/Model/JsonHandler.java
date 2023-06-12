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

    public void writePlayerToJson(Player player) {
        try {
            StringBuilder sb = getStringBuilder(playerJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", player.getId());
            jsonObject.addProperty("name", player.getName());
            jsonObject.addProperty("totalScore", player.getTotalScore().getScore());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Quiz> getAllQuizes() {
        try {
            StringBuilder sb = getStringBuilder(questionJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);

            ArrayList<Quiz> quizArrayList = new ArrayList<>();

            for (JsonElement element : jsonArray) {
                JsonObject object = element.getAsJsonObject();

                String quizName = object.get("name").getAsString().replaceAll("\"", "");

                ArrayList<Question> questionArrayList = new ArrayList<>();
                JsonArray questionArray = object.get("questions").getAsJsonArray();

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
                    questionArrayList.add(new Question(question, answerArrayList));
                }

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
     * reads the top players from data json
     * @param object jsonObject (quiz)
     * @return ArrayList of players
     */
    public ArrayList<Player> readTopPlayers(JsonObject object) {
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
    public void checkIfDataJsonIsValid() throws IOException {
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

}
