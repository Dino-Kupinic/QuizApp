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

public class JsonHandler implements Debug{
    private final Path PATH_DATA_JSON;
    private final Path PATH_PLAYER_JSON;
    private final File questionJsonFile;
    private final File playerJsonFile;
    private final Gson gson;
    private String dataJsonBackUpString;

    public JsonHandler() {
        PATH_DATA_JSON = Paths.get("src/main/resources/at/htlsteyr/quizapp/json/data.json");
        PATH_PLAYER_JSON = Paths.get("src/main/resources/at/htlsteyr/quizapp/json/player.json");
        questionJsonFile = new File(PATH_DATA_JSON.toUri());
        playerJsonFile = new File(PATH_PLAYER_JSON.toUri());
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String getDataJsonBackUpString() {
        return dataJsonBackUpString;
    }

    public void setDataJsonBackUpString(String dataJsonBackUpString) {
        this.dataJsonBackUpString = dataJsonBackUpString;
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

            assembleQuizJsonObject(quiz, jsonArray);

            String json = gson.toJson(jsonArray);
            FileWriter fW = new FileWriter(questionJsonFile);
            fW.write(json);
            fW.close();

            // Log action
            System.out.println("Added " + quiz.getName() + " Quiz to data.json!");
        } catch (IOException e) {
            if(PRINT_IOEXCEPTION) e.printStackTrace();
        }
    }

    /**
     * populates jsonObject with quiz data
     *
     * @param quiz      quiz object with data
     * @param jsonArray array to which the quiz will be added to
     */
    private void assembleQuizJsonObject(Quiz quiz, JsonArray jsonArray) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", quiz.getName());
        if (quiz.getQuestionArrayList() != null) {
            JsonArray questionArray = gson.toJsonTree(quiz.getQuestionArrayList()).getAsJsonArray();
            jsonObject.add("questions", questionArray);
        }
        if (quiz.getTopPlayers() != null) {
            JsonArray topPlayerArray = gson.toJsonTree(quiz.getTopPlayers()).getAsJsonArray();
            jsonObject.add("topPlayers", topPlayerArray);
        }
        jsonArray.add(jsonObject);
    }

    /**
     * replaces quiz in data.json
     *
     * @param quiz quiz which will be replace the old one
     */
    public void replaceQuizInJson(Quiz quiz) {
        if (!checkIfQuizAlreadyExists(quiz)) {
            throw new NullPointerException("Can't find quiz \"" + quiz.getName() + "\" in data.json!");
        }
        try {
            StringBuilder sb = getStringBuilder(questionJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                if (obj.get("name").getAsString().equals(quiz.getName())) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("name", quiz.getName());
                    if (quiz.getQuestionArrayList() != null) {
                        JsonArray questionArray = gson.toJsonTree(quiz.getQuestionArrayList()).getAsJsonArray();
                        jsonObject.add("questions", questionArray);
                    }
                    if (quiz.getTopPlayers() != null) {
                        JsonArray topPlayerArray = gson.toJsonTree(quiz.getTopPlayers()).getAsJsonArray();
                        jsonObject.add("topPlayers", topPlayerArray);
                    }
                    jsonArray.set(i, jsonObject);

                    String json = gson.toJson(jsonArray);
                    FileWriter fW = new FileWriter(questionJsonFile);
                    fW.write(json);
                    fW.close();

                    // Log action
                    System.out.println("Replaced \"" + quiz.getName() + "\" Quiz in data.json!");
                    return;
                }
            }
        } catch (IOException e) {
            if(PRINT_IOEXCEPTION) e.printStackTrace();
        }
    }

    /**
     * replaces quiz in data.json
     *
     * @param quiz    quiz which will be replace the old one
     * @param oldQuiz quiz with the old name
     */
    public void replaceQuizInJson(Quiz quiz, Quiz oldQuiz) {
        if (!checkIfQuizAlreadyExists(oldQuiz)) {
            throw new NullPointerException("Can't find quiz \"" + oldQuiz.getName() + "\" in data.json!");
        }
        try {
            StringBuilder sb = getStringBuilder(questionJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                if (obj.get("name").getAsString().equals(oldQuiz.getName())) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("name", quiz.getName());
                    if (quiz.getQuestionArrayList() != null) {
                        JsonArray questionArray = gson.toJsonTree(quiz.getQuestionArrayList()).getAsJsonArray();
                        jsonObject.add("questions", questionArray);
                    }
                    if (quiz.getTopPlayers() != null) {
                        JsonArray topPlayerArray = gson.toJsonTree(quiz.getTopPlayers()).getAsJsonArray();
                        jsonObject.add("topPlayers", topPlayerArray);
                    }
                    jsonArray.set(i, jsonObject);

                    String json = gson.toJson(jsonArray);
                    FileWriter fW = new FileWriter(questionJsonFile);
                    fW.write(json);
                    fW.close();

                    // Log action
                    System.out.println("Replaced \"" + quiz.getName() + "\" Quiz in data.json!");
                    return;
                }
            }
        } catch (IOException e) {
            if(PRINT_IOEXCEPTION) e.printStackTrace();
        }
    }

    /**
     * removes a quiz by checking for it's index
     *
     * @param quizOrName quiz which will be deleted, input either by object or name
     */
    public <T> void deleteQuizFromJson(T quizOrName) {
        try {
            StringBuilder sb = getStringBuilder(questionJsonFile);

            int index = -1;
            JsonArray array = gson.fromJson(sb.toString(), JsonArray.class);
            for (int i = 0; i < array.size(); i++) {
                JsonObject object = array.get(i).getAsJsonObject();
                if (isMatchingQuiz(object, quizOrName)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                array.remove(index);
            }

            String json = gson.toJson(array);
            FileWriter fW = new FileWriter(questionJsonFile);
            fW.write(json);
            fW.close();

            // Log action
            if (quizOrName instanceof String) {
                String name = (String) quizOrName;
                System.out.println("Removed \"" + name + "\" Quiz in data.json!");
            } else if (quizOrName instanceof Quiz) {
                Quiz quiz = (Quiz) quizOrName;
                System.out.println("Removed \"" + quiz.getName() + "\" Quiz in data.json!");
            }
        } catch (IOException e) {
            if(PRINT_IOEXCEPTION) e.printStackTrace();
        }

    }

    /**
     * checks if jsonObject matches quiz object or name
     *
     * @param object     jsonObject to check
     * @param quizOrName quiz object or name of quiz
     * @return true if object matches quiz object or name, false otherwise
     */
    private <T> boolean isMatchingQuiz(JsonObject object, T quizOrName) {
        if (quizOrName instanceof Quiz) {
            Quiz quiz = (Quiz) quizOrName;
            return object.get("name").getAsString().equals(quiz.getName());
        } else if (quizOrName instanceof String) {
            String name = (String) quizOrName;
            return object.get("name").getAsString().equals(name);
        }
        return false;
    }

    /**
     * checks answer already exits in a quiz
     *
     * @param quiz     quiz object
     * @param question question object
     * @param answer   answer object which is checked if it already exists
     * @return true or false
     */
    public boolean checkIfAnswerAlreadyExists(Quiz quiz, Question question, Answer answer) {
        ArrayList<Question> questionArrayList = quiz.getQuestionArrayList();
        for (Question q : questionArrayList) {
            if (q.getQuestion().equals(question.getQuestion())) {
                ArrayList<Answer> answerArrayList = q.getAnswerArrayList();
                for (Answer a : answerArrayList) {
                    if (a.getAnswerText().equals(answer.getAnswerText())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * checks if question is already in a quiz
     *
     * @param quiz     quiz object that contains the question
     * @param question question object to be checked if it is inside the quiz
     * @return true or false
     */
    public boolean checkIfQuestionAlreadyExists(Quiz quiz, Question question) {
        ArrayList<Question> questionArrayList = quiz.getQuestionArrayList();
        for (Question q : questionArrayList) {
            if (q.getQuestion().equals(question.getQuestion())) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if a quiz already exists
     *
     * @param quiz quiz object
     * @return true or false
     */
    public boolean checkIfQuizAlreadyExists(Quiz quiz) {
        ArrayList<Quiz> quizArrayList = getAllQuizes();
        for (Quiz q : quizArrayList) {
            if (q.getName().equals(quiz.getName())) {
                return true;
            }
        }
        return false;
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
            if(PRINT_IOEXCEPTION) e.printStackTrace();
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
            if(PRINT_IOEXCEPTION) e.printStackTrace();
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
     * Gets a quiz by its name
     *
     * @param text quiz name
     * @return quiz object
     */
    public Quiz getQuizByName(String text) {
        ArrayList<Quiz> quizArrayList = getAllQuizes();
        for (Quiz q : quizArrayList) {
            if (q.getName().equals(text)) {
                return q;
            }
        }
        System.out.println("Read quiz \"" + text + "\" from data.json");
        return null;
    }

    /**
     * Gets a question by its name in a quiz
     *
     * @param quiz quiz in which the question is found
     * @param text question text
     * @return question object
     */
    public Question getQuestionByText(Quiz quiz, String text) {
        ArrayList<Question> questionArrayList = quiz.getQuestionArrayList();
        for (Question question : questionArrayList) {
            if (question.getQuestion().equals(text)) {
                return question;
            }
        }
        // Log action
        System.out.println("Read question \"" + text + "\" from quiz \"" + quiz.getName() + "\"");
        return null;
    }

    /**
     * gets an answer from a question
     *
     * @param question question object that contains the answer
     * @param text     answer text
     * @return answer object
     */
    public Answer getAnswerByText(Question question, String text) {
        ArrayList<Answer> answerArrayList = question.getAnswerArrayList();
        for (Answer answer : answerArrayList) {
            if (answer.getAnswerText().equals(text)) {
                return answer;
            }
        }
        // Log action
        System.out.println("Read answer \"" + text + "\" from question \"" + question.getQuestion() + "\"");
        return null;
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
            Double currentScore = currentScoreObject.get("score").getAsDouble();
            Double totalScore = totalScoreObject.get("score").getAsDouble();
            tempPlayerArray.add(new Player(id, name, new Score(currentScore), new Score(totalScore)));
        }
        return tempPlayerArray;
    }

    /**
     * reads all players from the quiz
     *
     * @return observable list with all players
     */
    public ObservableList<Player> getAllTopPlayersForTableView(Quiz quiz) {
        ArrayList<Player> playerArrayList = quiz.getTopPlayers();
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.addAll(playerArrayList);
        return players;
    }

    /**
     * returns ObservableList for Leaderboard
     *
     * @param arrayList Player ArrayList
     * @return ObservableList with all Players
     */
    public ObservableList<Player> getAllPlayersForTableView(ArrayList<Player> arrayList) {
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.addAll(arrayList);
        return players;
    }

    /**
     * returns ObservableList for Answer Table for editing
     *
     * @param answers arraylist of Answers
     * @return ObseravbleList with all Answers
     */
    public ObservableList<Answer> getAllAnswerForTableView(ArrayList<Answer> answers) {
        ObservableList<Answer> answerObservableList = FXCollections.observableArrayList();
        answerObservableList.addAll(answers);
        return answerObservableList;
    }

    /**
     * reads all players from player.json
     *
     * @return arrayList of players
     */
    public ArrayList<Player> readAllPlayers() {
        try {
            StringBuilder sb = getStringBuilder(playerJsonFile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);
            ArrayList<Player> playerArrayList = new ArrayList<>();

            for (JsonElement e : jsonArray) {
                JsonObject jsonObject = e.getAsJsonObject();
                Integer id = jsonObject.get("id").getAsInt();
                String name = jsonObject.get("name").getAsString();
                Double score = jsonObject.get("totalScore").getAsDouble();

                playerArrayList.add(new Player(id, name, new Score(0.0), new Score(score)));
            }
            return playerArrayList;
        } catch (FileNotFoundException e) {
            if (PRINT_FILENOTFOUNDEXCEP) e.printStackTrace();
        }
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
            if (PRINT_FILENOTFOUNDEXCEP) e.printStackTrace();
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
            if (PRINT_FILENOTFOUNDEXCEP) e.printStackTrace();
        }
        return false;
    }

    /**
     * checks if player json is valid or not
     *
     * @return boolean
     */
    public boolean isPlayerJsonValid() {
        try {
            Scanner scanner = new Scanner(playerJsonFile);
            return scanner.hasNextLine();
        } catch (FileNotFoundException e) {
            if (PRINT_FILENOTFOUNDEXCEP) e.printStackTrace();
        }
        return false;
    }

    /**
     * creates an example quiz (used when no user created quiz are found)
     */
    public void createExampleQuiz() {
        // Question 1
        ArrayList<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("Example Answer 1", false));
        answers1.add(new Answer("Example Answer 2", true));
        answers1.add(new Answer("Example Answer 3", true));
        answers1.add(new Answer("Example Answer 4", true));
        Question question1 = new Question("Example Question 1", answers1);

        // Question 2
        ArrayList<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("Example Answer 1", false));
        answers2.add(new Answer("Example Answer 2", true));
        answers2.add(new Answer("Example Answer 3", false));
        answers2.add(new Answer("Example Answer 4", false));
        Question question2 = new Question("Example Question 2", answers2);

        // Question 3
        ArrayList<Answer> answers3 = new ArrayList<>();
        answers2.add(new Answer("Example Answer 1", false));
        answers2.add(new Answer("Example Answer 2", true));
        Question question3 = new Question("Example Question 3", answers3);

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        // Add fictional top players
        ArrayList<Player> topPlayers = new ArrayList<>();
        topPlayers.add(new Player(1, "Heinz Schweiger", new Score(9999.0), new Score(99999.0)));
        topPlayers.add(new Player(2, "Christian Samegmüller", new Score(1.0), new Score(1337.0)));
        topPlayers.add(new Player(3, "Wolfgang Reisinger", new Score(0.0), new Score(0.0)));

        Quiz quiz = new Quiz("Example Quiz", questions, topPlayers);
        writeQuizToJson(quiz);
    }

    /**
     * Makes a backup of data json
     */
    public void backupDataJson() {
        ArrayList<Quiz> quizArrayList = getAllQuizes();
        dataJsonBackUpString = gson.toJson(quizArrayList);

        // Log Action
        System.out.println("Created backup of data.json! \nValue of dataJsonBackUpString:\n" + dataJsonBackUpString);
    }
}
