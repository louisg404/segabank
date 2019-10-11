package bo;

import java.util.List;

public class Agence {

    private int id;
    private int code;
    private String adresse;
    private List<Compte> comptes;

    public Agence(int code, String adresse) {

        this.code = code;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    @Override
    public String toString(){
        StringBuilder bd = new StringBuilder();
        bd.append(this.id).append(" ").append(this.code).append(" ").append(this.adresse);
        return bd.toString();
    }
}
