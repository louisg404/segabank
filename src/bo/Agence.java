package bo;

import java.util.List;

public class Agence {

    private int id;
    private int code;
    private String adresse;
    private List<Compte> comptes;

    public Agence(int id, int code, String adresse, List<Compte> comptes) {
        this.id = id;
        this.code = code;
        this.adresse = adresse;
        this.comptes = comptes;
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
    public String toString() {
        return "agence{" +
                "id=" + id +
                ", code=" + code +
                ", adresse='" + adresse + '\'' +
                ", comptes=" + comptes +
                '}';
    }
}
