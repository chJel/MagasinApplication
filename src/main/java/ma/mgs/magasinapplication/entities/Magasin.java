package ma.mgs.magasinapplication.entities;

import java.util.Objects;

public class Magasin {
    private int id;
    private String nom;
    private String adresse;
    private String ville;
    private String  Pays;
    private String numTel;
    private boolean estOuvert;


    public Magasin() {
    }

    public Magasin(String nom, String adresse, String ville, String pays, String numTel, boolean estOuvert) {
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        Pays = pays;
        this.numTel = numTel;
        this.estOuvert = estOuvert;

    }

    public Magasin(int id , String nom, String adresse, String ville, String pays, String numTel, boolean estOuvert) {
        this.id=id;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        Pays = pays;
        this.numTel = numTel;
        this.estOuvert = estOuvert;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String pays) {
        Pays = pays;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public boolean EstOuvert() {
        return estOuvert;
    }

    public void setEstOuvert(boolean estOuvert) {
        this.estOuvert = estOuvert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magasin)) return false;
        Magasin magazin = (Magasin) o;
        return EstOuvert() == magazin.EstOuvert() && Objects.equals(getNom(), magazin.getNom()) && Objects.equals(getAdresse(), magazin.getAdresse()) && Objects.equals(getVille(), magazin.getVille()) && Objects.equals(getPays(), magazin.getPays()) && Objects.equals(getNumTel(), magazin.getNumTel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getAdresse(), getVille(), getPays(), getNumTel(), EstOuvert());
    }

    @Override
    public String toString() {
        return "Magazin{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", Pays='" + Pays + '\'' +
                ", numTel='" + numTel + '\'' +
                ", estOuvert=" + estOuvert +
                '}';
    }
}
