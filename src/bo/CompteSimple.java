package bo;

public class CompteSimple extends Compte {

    private double decouvert;
    private int compteId;

    public CompteSimple(double solde, double decouvert, int compteId) {

        super(solde);
        this.decouvert = decouvert;
        this.compteId = compteId;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public int getCompteId() {
        return compteId;
    }

    public void setCompteId(int compteId) {
        this.compteId = compteId;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
