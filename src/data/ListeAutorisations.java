/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris
 */
class ListeAutorisations
{
    private ArrayList liste;
    
    public ListeAutorisations()
    {
        liste = new ArrayList<Autorisation>();
    }

    public ArrayList getListe()
    {
        return liste;
    }

    public void setListe(ArrayList liste)
    {
        this.liste = liste;
    }
    
    public void add(Autorisation a)
    {
        liste.add(a);
    }
}
