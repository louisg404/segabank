package bo;

public class ComptePayant extends Compte {

    private double interet;

    public ComptePayant(int id, double solde, Agence agence, double interet) {
        super(id, solde, agence);
        this.interet = interet;
    }

    public double getInteret() {
        return interet;
    }

    public void setInteret(double interet) {
        this.interet = interet;
    }
}
