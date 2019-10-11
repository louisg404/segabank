package bo;

public class ComptePayant extends Compte {

    private static double tauxOperation = 5;
    private int compteId;

    public ComptePayant(double solde, int compteId) {

        super(solde);
        this.compteId = compteId;
    }

    public static double getTauxOperation() {
        return tauxOperation;
    }

    public int getCompteId() {
        return compteId;
    }

    public void setCompteId(int compteId) {
        this.compteId = compteId;
    }

    public void setTauxOperation(double tauxOperation) {
        this.tauxOperation = tauxOperation;
    }
}
