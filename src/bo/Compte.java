package bo;

public class Compte {

    private int id;
    private double solde;
    private int agence;

    public Compte(double solde) {
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public int getAgence() {
        return agence;
    }

    public void setAgence(int agence) {
        this.agence = agence;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", solde=" + solde +
                ", agence=" + agence +
                '}';
    }

    public double retrait(double montant) {

        if(this.getSolde() - montant > 0){
            this.setSolde(this.solde - montant);
        }
        return this.getSolde();
    }

    public double versement(double montant){

        this.setSolde(this.solde + montant);
        return this.getSolde();
    }
}
