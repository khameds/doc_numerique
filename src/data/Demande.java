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
class Demande
{
    private String sujet;
    private Date dateDebut;
    private String duree;
    private int authId;
    private Date dateSign;
            
    public Demande()
    {
        
    }

    public String getSujet()
    {
        return sujet;
    }

    public void setSujet(String sujet)
    {
        this.sujet = sujet;
    }

    public Date getDateDebut()
    {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    public String getDuree()
    {
        return duree;
    }

    public void setDuree(String duree)
    {
        this.duree = duree;
    }

    public int getAuthId()
    {
        return authId;
    }

    public void setAuthId(int authId)
    {
        this.authId = authId;
    }

    public Date getDateSign()
    {
        return dateSign;
    }

    public void setDateSign(Date dateSign)
    {
        this.dateSign = dateSign;
    }
    
    
}
