package minijeu.ut3.cassebrique;

import java.util.ArrayList;
import java.util.List;

public class Scores {

    class Score{
        private String name;
        private String points;

        public Score(String username, String points){
            this.name = username;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }

    private List<Score> scoreList = new ArrayList<>();

    public Scores() {

    }

    public void add(String username, String score){
        this.scoreList.add(new Score(username,score));
    }

    public int nbScores(){
        return this.scoreList.size();
    }

    public List<Score> getScores(){
        return this.scoreList;
    }
}
