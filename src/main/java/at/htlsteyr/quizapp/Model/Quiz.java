package at.htlsteyr.quizapp.Model;

import java.util.ArrayList;

public class Quiz {
    private String name;
    private ArrayList<Question> questionArrayList;

    public Quiz(String name, ArrayList<Question> questionArrayList, int correctAnswer) {
        this.name = name;
        this.questionArrayList = questionArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(ArrayList<Question> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }
}
