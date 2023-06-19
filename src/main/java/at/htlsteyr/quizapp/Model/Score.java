package at.htlsteyr.quizapp.Model;

public class Score {
    private Double score;

    public Score(Double score) {
        this.score = score;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void updateScore () {
        score -= 0.75;
    }
}
