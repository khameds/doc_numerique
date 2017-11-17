/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class Document
{
    private int id;
    
    private int nombreMessageAnnonce;
    private Institution institutionOrigine;
    private ArrayList<Message> listeMessage;
    
    public Document()
    {
        listeMessage = new ArrayList<>();
    }
    
    public Document(int id)
    {
        this();
        this.id = id;
    }
    
    public void addMessage(Message m)
    {     
        listeMessage.add(m);
    }
    
    public Message getMessage(int i)
    {
        return listeMessage.get(i);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getNombreMessage()
    {
        return listeMessage.size();
    }
    
    public int getNombreMessageAnnonce()
    {
        return nombreMessageAnnonce;
    }

    public void setNombreMessageAnnonce(int nombreMessage)
    {
        this.nombreMessageAnnonce = nombreMessage;
    }

    public Institution getInstitution()
    {
        return institutionOrigine;
    }

    public void setInstitution(Institution institution)
    {
        this.institutionOrigine = institution;
    }
}
