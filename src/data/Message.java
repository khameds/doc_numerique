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
    private Information information;
    private Autorisation autorisation;
    private Demande demande;
    private Reponse reponse;
    
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
        this.typeMessage = typeMessage;
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

    public Information getInformation()
    {
        return information;
    }

    public void setInformation(Information information)
    {
        this.information = information;
    }

    public Autorisation getAutorisation()
    {
        return autorisation;
    }

    public void setAutorisation(Autorisation autorisation)
    {
        this.autorisation = autorisation;
    }

    public Demande getDemande()
    {
        return demande;
    }

    public void setDemande(Demande demande)
    {
        this.demande = demande;
    }

    public Reponse getReponse()
    {
        return reponse;
    }

    public void setReponse(Reponse reponse)
    {
        this.reponse = reponse;
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
        String ajout = "";
        if(autorisation==null&&demande==null&&reponse==null&&information==null)
        {
            ajout = "(Impossible d'afficher le message)";
        }
        else
        {
            switch(typeMessage)
            {
                case AUTORISATION:
                    ajout = autorisation.toString();
                    break;
                case INFORMATION:
                    ajout = information.toString();
                    break;
                case REPONSE:
                    ajout = reponse.toString();
                    break;
                case DEMANDE:
                    ajout = demande.toString();
                    break;
                default:
                    ajout = "(Impossible d'afficher le message)";
                    break;
            }
        }
        
        return "Message{" + "id=" + id + ", typeMessage=" + typeMessage + ", mailExp=" + mailExp + ", mailDest=" + mailDest + ", " + ajout;       
    }
}
