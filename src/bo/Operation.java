package bo;

public class Operation {

    public Operation(double montant, String transaction, int compteId, int agenceId) {

        this.montant = montant;
        this.transaction = transaction;
        this.compteId = compteId;
        this.agenceId = agenceId;
    }

    private int id;
    private double montant;
    private String transaction;
    private int compteId;
    private int agenceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getCompteId() {
        return compteId;
    }

    public void setCompteId(int compteId) {
        this.compteId = compteId;
    }

    public int getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(int agenceId) {
        this.agenceId = agenceId;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}