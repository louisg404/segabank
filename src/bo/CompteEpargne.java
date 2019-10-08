package bo;

public class CompteEpargne extends Compte {

    private double taux;

    public CompteEpargne(int id, double solde, Agence agence, double taux) {
        super(id, solde, agence);
        this.taux = taux;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
