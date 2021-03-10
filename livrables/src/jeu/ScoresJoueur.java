package jeu;


import java.util.ArrayList;

public class ScoresJoueur {
    private ArrayList<Integer> pars;
    private ArrayList<Integer> scores;

    public ScoresJoueur(ArrayList<Integer> pars) {
        this.pars  = pars;
        this.scores = new ArrayList<Integer>();
        for (int i = 0; i < pars.size() ; i++) {
            this.scores.add(0);
        }
    }

    public void addCoup(int numTrou) {
        this.scores.set(numTrou-1, this.scores.get(numTrou-1) + 1);
    }

    public int getPar(int n) {
        return this.pars.get(n-1);
    }

    public void setPar(int n, int par) {
        this.pars.add(n-1,par);
    }

    public int getScore(int n) {
        return this.scores.get(n-1);
    }

    public void setScore(int n, int score) {
        this.scores.set(n-1,score);
    }

    public ArrayList<Integer> getScores() {
        return this.scores;
    }

    public int total() {
        int tot = 0;
        for (int i = 0; i < pars.size() ; i++) {
            tot += this.scores.get(i);
        }
        return tot;
    }
}
