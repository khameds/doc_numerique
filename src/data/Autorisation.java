/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package data;

import java.text.SimpleDateFormat;

/**
 *
 * @author Chris
 */
public class Autorisation implements Contenu
{
    private int id;
    private SimpleDateFormat dateDebut;
    private SimpleDateFormat dateFin;
    
    public Autorisation()
    {
        
    }
    
    public Autorisation(int id)
    {
        this();
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public SimpleDateFormat getDateDebut()
    {
        return dateDebut;
    }

    public void setDateDebut(SimpleDateFormat dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    public SimpleDateFormat getDateFin()
    {
        return dateFin;
    }

    public void setDateFin(SimpleDateFormat dateFin)
    {
        this.dateFin = dateFin;
    }

    @Override
    public String toString()
    {
        return "Autorisation{" + "id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
}
