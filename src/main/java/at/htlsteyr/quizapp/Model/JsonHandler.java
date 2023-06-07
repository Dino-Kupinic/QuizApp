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

public class JsonHandler<T> {
    private final Path PATH_DATA_JSON;
    private final File questionJsonFile;
    private final Gson gson;

    public JsonHandler() {
        PATH_DATA_JSON = Paths.get("src/main/resources/at/htlsteyr/quizapp/data.json");
        questionJsonFile = new File(PATH_DATA_JSON.toUri());
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void writeQuizToJson(Quiz<T> quiz) {
        try {
            StringBuilder sb = getStringBuilder(questionJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);

            // populate jsonObject with quiz data
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", quiz.getName());
            JsonArray questionArray = gson.toJsonTree(quiz.getQuestionArrayList()).getAsJsonArray();
            jsonObject.add("questions", questionArray);
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

    public ArrayList<Quiz<T>> getAllQuizes() {
        try {
            StringBuilder sb = getStringBuilder(questionJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);

            ArrayList<Quiz<T>> quizArrayList = new ArrayList<>();

            for (JsonElement element : jsonArray) {
                JsonObject object = element.getAsJsonObject();

                String quizName = object.get("name").getAsString().replaceAll("\"", "");

                ArrayList<Question<T>> questionArrayList = new ArrayList<>();
                JsonArray questionArray = object.get("questions").getAsJsonArray();

                for (JsonElement questionElement : questionArray) {
                    JsonObject questionObject = questionElement.getAsJsonObject();
                    String question = questionObject.get("question").getAsString();

                    JsonElement correctAnswerElement = questionObject.get("correctAnswer");

                    T correctAnswer;

                    if (correctAnswerElement.isJsonArray()) {
                        JsonArray correctAnswerArray = correctAnswerElement.getAsJsonArray();
                        ArrayList<Integer> correctAnswerList = new ArrayList<>();
                        for (JsonElement answerElement : correctAnswerArray) {
                            int answer = answerElement.getAsInt();
                            correctAnswerList.add(answer);
                        }
                        correctAnswer = (T) correctAnswerList;
                    } else {
                        correctAnswer = (T) Integer.valueOf(correctAnswerElement.getAsInt());
                    }

                    ArrayList<String> answerArrayList = new ArrayList<>();
                    JsonArray answerArray = questionObject.get("answerArrayList").getAsJsonArray();
                    for (JsonElement answerElement : answerArray) {
                        String answer = answerElement.getAsString();
                        answerArrayList.add(answer);
                    }
                    questionArrayList.add(new Question<>(question, answerArrayList, correctAnswer));
                }
                quizArrayList.add(new Quiz<>(quizName, questionArrayList));
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
     * utility function read a whole file with a string builder
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
     * Checks if the controls JSON is valid
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
