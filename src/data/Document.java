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
    
    private int nombreMessage;
    private Institution institution;
    private ArrayList<Message> listeMessage;
    
    public Document()
    {
        
    }
    
    public Document(int id)
    {
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
        return nombreMessage;
    }

    public void setNombreMessage(int nombreMessage)
    {
        this.nombreMessage = nombreMessage;
    }

    public Institution getInstitution()
    {
        return institution;
    }

    public void setInstitution(Institution institution)
    {
        this.institution = institution;
    }
}
