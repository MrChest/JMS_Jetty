/**
 * Created by chestnov.v on 15.08.2017.
 */
public class GameUser {
    private String myName;
    private String enemyName;
    private int myScore = 0;
    private int enemyScore = 0;

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public int getMyScore() {
        return myScore;
    }

    public void setEnemyScore(int enemyScore) {
        this.enemyScore = enemyScore;
    }

    public void incrementMyScore(){
        myScore++;
    }

    public void incrementEnemyScore(){
        enemyScore++;
    }
}
