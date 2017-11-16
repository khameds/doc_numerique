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
    TypeMessage typeMessage;
    String mailExp;
    String mailDest;
    Information information;
    Autorisation autorisation;
    Demande demande;
    Reponse reponse;
    
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
}
