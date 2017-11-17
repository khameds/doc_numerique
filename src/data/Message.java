/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/
package data;

/**
 * 
 * @author Chris
 */
public class Message
{
    private int id;
    private TypeMessage typeMessage;
    private String mailExp;
    private String mailDest;
    private Contenu contenu;
    
    public Message()
    {
        
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

    public String getMailDest()
    {
        return mailDest;
    }

    public void setMailDest(String mailDest)
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Message{" + "id=" + id + ", typeMessage=" + typeMessage + ", mailExp=" + mailExp + ", mailDest=" + mailDest + ", contenu=" + contenu.toString() + '}';
    }
}
