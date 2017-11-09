/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package projetdocnumerique;

/**
 *
 * @author Tony
 */
class Reponse
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
    
    
}
