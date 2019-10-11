package bo;

public class CompteEpargne extends Compte {

    private double tauxInteret;
    private int compteId;

    public CompteEpargne(double solde, float tauxInteret, int compteId) {

        super(solde);
        this.compteId = compteId;
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public int getCompteId() {
        return compteId;
    }

    public void setCompteId(int compteId) {
        this.compteId = compteId;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
