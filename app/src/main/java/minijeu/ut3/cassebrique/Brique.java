package minijeu.ut3.cassebrique;

public class Brique {
    private int x;
    private int y;
    private int vie;
    public Brique (int x, int y){
        this.x = x;
        this.y = y;
        this.vie = 0;
    }

    public Brique (int x, int y, int vie){
        this.x = x;
        this.y = y;
        this.vie = vie;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVie() {
        return vie;
    }
    public void hit(){
        vie--;
    }
}
