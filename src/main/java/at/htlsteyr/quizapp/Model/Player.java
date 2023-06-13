/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 12.6.2023
 * @details Class to store each Player
 */

package at.htlsteyr.quizapp.Model;

public class Player {
    private Integer id;
    private String name;
    private Score currentScore;
    private Score totalScore;

    public Player(Integer id, String name, Score currentScore, Score totalScore) {
        this.id = id;
        this.name = name;
        this.currentScore = currentScore;
        this.totalScore = totalScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Score getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Score currentScore) {
        this.currentScore = currentScore;
    }

    public Score getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Score totalScore) {
        this.totalScore = totalScore;
    }
}
