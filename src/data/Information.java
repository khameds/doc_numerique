/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Chris
 */
public class Information implements Contenu
{
    private String duree;
    private SimpleDateFormat dateDebut;
    private String sujet;
    private String contenuTexte;

    public String getDuree()
    {
        return duree;
    }

    public void setDuree(String duree)
    {
        this.duree = duree;
    }

    public SimpleDateFormat getDateDebut()
    {
        return dateDebut;
    }

    public void setDateDebut(SimpleDateFormat dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    public String getSujet()
    {
        return sujet;
    }

    public void setSujet(String sujet)
    {
        this.sujet = sujet;
    }

    public String getContenuTexte()
    {
        return contenuTexte;
    }

    public void setContenuTexte(String contenuTexte)
    {
        this.contenuTexte = contenuTexte;
    }

    @Override
    public String toString()
    {
        return "Information{" + "duree=" + duree + ", dateDebut=" + dateDebut + ", sujet=" + sujet + ", contenuTexte=" + contenuTexte + '}';
    }
}