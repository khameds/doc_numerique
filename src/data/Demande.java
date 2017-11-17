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
public class Demande implements Contenu
{
    private String sujet;
    private SimpleDateFormat dateDebut;
    private String duree;
    private int authId;
    private SimpleDateFormat dateSign;
            
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

    public SimpleDateFormat getDateDebut()
    {
        return dateDebut;
    }

    public void setDateDebut(SimpleDateFormat dateDebut)
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

    public SimpleDateFormat getDateSign()
    {
        return dateSign;
    }

    public void setDateSign(SimpleDateFormat dateSign)
    {
        this.dateSign = dateSign;
    }

    @Override
    public String toString()
    {
        return "Demande{" + "sujet=" + sujet + ", dateDebut=" + dateDebut + ", duree=" + duree + ", authId=" + authId + ", dateSign=" + dateSign + '}';
    }
}
