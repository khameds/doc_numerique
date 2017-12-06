/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package data;

import java.util.ArrayList;

/**
 *
 */
public class Document
{
    private String id;
    
    private int nombreMessageAnnonce;
    private Institution institutionOrigine;
    private ArrayList<Message> listeMessage;
    
    public Document()
    {
        listeMessage = new ArrayList<>();
    }
    
    public Document(String id)
    {
        this();
        this.id = id;
    }

    public ArrayList<Message> getListeMessage()
    {
        return listeMessage;
    }
    
    public void setListeMessage(ArrayList<Message> listeMessage)
    {
        this.listeMessage = listeMessage;
    }
    
    public void addMessage(Message m)
    {     
        listeMessage.add(m);
    }
    
    public Message getMessage(int i)
    {
        return listeMessage.get(i);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    @Override
    public String toString()
    {
        return "Document{" + "id=" + id + ", nombreMessageAnnonce=" + nombreMessageAnnonce + ", institutionOrigine=" + institutionOrigine + ", listeMessage=" + listeMessage + '}';
    }
}
