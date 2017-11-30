/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package data;

/**
 *
 * @author Tony
 */
public class Reponse implements Contenu
{
    private String messageId;
    private String reponseId;
    private String sujet;
    private String contenuTexte;
    
    public Reponse()
    {
        
    }

    public String getMessageId()
    {
        return messageId;
    }

    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public String getReponseId()
    {
        return reponseId;
    }

    public void setReponseId(String reponseId)
    {
        this.reponseId = reponseId;
    }
    
    

    public String getSujet()
    {
        return sujet;
    }

    public void setSujet(String sujet)
    {
        this.sujet = sujet;
    }

    public String getContenuTexte()
    {
        return contenuTexte;
    }

    public void setContenuTexte(String contenuTexte)
    {
        this.contenuTexte = contenuTexte;
    }

    @Override
    public String toString()
    {
        return "Reponse{" + "messageId=" + messageId + ", reponseId=" + reponseId + ", sujet=" + sujet + ", contenuTexte=" + contenuTexte + '}';
    }
    
    
}
