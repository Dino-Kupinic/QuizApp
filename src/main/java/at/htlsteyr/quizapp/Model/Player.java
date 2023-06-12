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
    private Score globalScore;

    public Player(Integer id, String name, Score score) {
        this.id = id;
        this.name = name;
        this.globalScore = score;
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

    public Score getGlobalScore() {
        return globalScore;
    }

    public void setGlobalScore(Score globalScore) {
        this.globalScore = globalScore;
    }
}
