/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package data;

/**
 *
 * @author Chris
 */
public class Institution
{
    String nom;
    ListeAutorisations listeAutorisations;
    
    public Institution()
    {
        
    }
    
    public Institution(String nom)
    {
        this();
        this.nom = nom;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public ListeAutorisations getListeAutorisations()
    {
        return listeAutorisations;
    }

    public void setListeAutorisations(ListeAutorisations listeAutorisations)
    {
        this.listeAutorisations = listeAutorisations;
    }
    
    public void addAutorisation(Autorisation a)
    {
        listeAutorisations.add(a);
    }
}
