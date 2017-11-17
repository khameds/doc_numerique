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
    private int messageId;
    private int reponseId;
    private String sujet;
    private String contenuTexte;
    
    public Reponse()
    {
        
    }

    public int getMessageId()
    {
        return messageId;
    }

    public void setMessageId(int messageId)
    {
        this.messageId = messageId;
    }

    public int getReponseId()
    {
        return reponseId;
    }

    public void setReponseId(int reponseId)
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
