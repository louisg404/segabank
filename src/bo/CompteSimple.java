package bo;

public class CompteSimple extends Compte {

    private double decouvert;

    public CompteSimple(int id, double solde, Agence agence, double decouvert) {
        super(id, solde, agence);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
