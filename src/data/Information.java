/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package data;

import java.util.Date;

/**
 *
 * @author Chris
 */
class Information
{
    private String duree;
    private Date dateDebut;
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

    public Date getDateDebut()
    {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut)
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
    
    
}