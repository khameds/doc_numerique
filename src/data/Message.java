/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/
package data;

import java.util.ArrayList;

/**
 * 
 */
public class Message
{
    private String id;
    private TypeMessage typeMessage;
    private String mailExp;
    private ArrayList<String> mailDest;
    private Contenu contenu;
    
    public Message()
    {
        mailDest = new ArrayList<>();
    }
    
    public Message(TypeMessage type)
    {
        this();
        typeMessage = type;
    }

    public TypeMessage getTypeMessage()
    {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage)
    {
        if(contenu==null)
        {
            this.typeMessage = typeMessage;
            switch(typeMessage)
            {
                case AUTORISATION:
                    contenu = new Autorisation();
                    break;
                case DEMANDE:
                    contenu = new Demande();
                    break;
                case INFORMATION:
                    contenu = new Information();
                    break;
                case REPONSE:
                    contenu = new Reponse();
                    break;
            }
        }
        else
        {
            System.err.println("Erreur: Tentative de modifier le type d'un message déjà rempli");
        }
    }

    public String getMailExp()
    {
        return mailExp;
    }

    public void setMailExp(String mailExp)
    {
        this.mailExp = mailExp;
    }
    
    public void addMailDest(String mail)
    {
        mailDest.add(mail);
    }

    public ArrayList<String> getMailDest()
    {
        return mailDest;
    }

    public void setMailDest(ArrayList<String> mailDest)
    {
        this.mailDest = mailDest;
    }
    
    public Contenu getContenu()
    {
        return contenu;
    }

    public Information getInformation()
    {
        return (Information) contenu;
    }
    
    public Autorisation getAutorisation()
    {
        return (Autorisation) contenu;
    }
    
    public Demande getDemande()
    {
        return (Demande) contenu;
    }
    
    public Reponse getReponse()
    {
        return (Reponse) contenu;
    }

    public void setContenu(Contenu contenu)
    {
        this.contenu = contenu;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Message{" + "id=" + id + ", typeMessage=" + typeMessage + ", mailExp=" + mailExp + ", mailDest=" + mailDest + ", contenu=" + contenu.toString() + '}';
    }
}
