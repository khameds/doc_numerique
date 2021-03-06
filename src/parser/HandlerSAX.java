/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/
package parser;

import data.Autorisation;
import data.Demande;
import data.Message;
import data.Document;
import data.Institution;
import data.TypeMessage;
import data.Information;
import data.Reponse;
import java.text.SimpleDateFormat;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler SAX de l'objet Fichier
 *
 */
public class HandlerSAX extends DefaultHandler
{	
    /**
     * Document que l'on va remplir
     */
    Document doc;
    /**
     * Objets qui vaont temporairement stocker des infos avant leur ajout au doc
     */
    Message message;
    Information information;
    Reponse reponse;
    Demande demande;
    Autorisation autorisation;
    /**
     * Dernière balise ouverte
     */
    String dernierARemplir = "";
    /**
     * Type du dernier message
     */
    TypeMessage dernierTypeMessage;
    

    /**
     * Constructeur et initialise le fichier
     * @param doc
     */
    public HandlerSAX(Document doc)
    {
        this.doc = doc;
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startDocument()
     */
    @Override
    public void startDocument()
    {
        //System.out.println("-- Debut fichier --\n\n");
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endDocument()
     */
    @Override
    public void endDocument()
    {
        //System.out.println("-- Fichier termine --");
        //System.out.println();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
    {
        //System.out.println("Debut de la balise "+qName);
        
        if(qName.compareTo("header")==0)
        {
            //On fixe fileID
            doc.setId(attributes.getValue("fileId"));        
            dernierARemplir = "header";
        }
        
        if(qName.compareTo("nbMessages")==0)
        {      
            dernierARemplir = "nbMessages";
        }

        if(qName.compareTo("institution")==0)
        {      
            dernierARemplir = "institution";
        }
        
        if(qName.compareTo("message")==0)
        {
            message = new Message();
            //On fixe l'id du message
            message.setId(attributes.getValue("messageId"));
            dernierARemplir = "message";
        }
        
        if(qName.compareTo("emailDest")==0)
        {
            dernierARemplir = "emailDest";
        }
        
        if(qName.compareTo("emailExp")==0)
        {
            dernierARemplir = "emailExp";
        }
        
        //Pour le type information
                
        if(qName.compareTo("information")==0)
        {
            dernierTypeMessage = TypeMessage.INFORMATION;
            //Ceci va également créer l'objet à l'intérieur de message
            message.setTypeMessage(dernierTypeMessage);
            information = new Information();
        }

        if(qName.compareTo("sujet")==0)
        {
            dernierARemplir = "sujet";
        }
        
        if(qName.compareTo("contenuTexte")==0)
        {
            dernierARemplir = "contenuTexte";
        }
        
        if(qName.compareTo("dateDebut")==0)
        {
            dernierARemplir = "dateDebut";
        }
        
        if(qName.compareTo("dateFin")==0)
        {
            dernierARemplir = "dateFin";
        }
        
        //Pour le type autorisation
        
        if(qName.compareTo("autorisation")==0)
        {
            dernierTypeMessage = TypeMessage.AUTORISATION;
            //Ceci va également créer l'objet à l'intérieur de message
            message.setTypeMessage(dernierTypeMessage);
            dernierARemplir = "autorisation";
            autorisation = new Autorisation();
        }
        
        if(qName.compareTo("autorisationID")==0)
        {
            dernierARemplir = "autorisationID";
        }
        
        //Pour le type demande
        
        if(qName.compareTo("demande")==0)
        {
            dernierTypeMessage = TypeMessage.DEMANDE;
            //Ceci va également créer l'objet à l'intérieur de message
            message.setTypeMessage(dernierTypeMessage);
            demande = new Demande();
            
            //2 attributs
            demande.setAuthId(attributes.getValue("authId"));
            
            dernierARemplir = "demande";
        }
        
        //Pour le type reponse
        
        if(qName.compareTo("reponse")==0)
        {
            dernierTypeMessage = TypeMessage.REPONSE;
            //Ceci va également créer l'objet à l'intérieur de message
            message.setTypeMessage(dernierTypeMessage);
            reponse = new Reponse();
            
            //2 attributs
            reponse.setMessageId(attributes.getValue("messageId"));
            reponse.setReponseId(attributes.getValue("reponseId"));
            
            dernierARemplir = "reponse";
        }
        
        if(qName.compareTo("semaines")==0)
        {
            dernierARemplir = "semaines";
        }
        
        if(qName.compareTo("mois")==0)
        {
            dernierARemplir = "mois";
        }
        
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName)
    {
        //System.out.println("Fin de "+qName);
        if(qName.compareTo("message")==0)
        {
            //On ajoute le message au doc maintenant qu'il est rempli
            doc.addMessage(message);
            dernierARemplir = "";
        }
        
        if(qName.compareTo("information")==0)
        {
            message.setContenu(information);
        }
        
        if(qName.compareTo("reponse")==0)
        {
            message.setContenu(reponse);
        }
        
        if(qName.compareTo("autorisation")==0)
        {
            message.setContenu(autorisation);
        }
        
        if(qName.compareTo("demande")==0)
        {
            message.setContenu(demande);
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    public void characters(char[] ch, int start, int length)
    {
        //Récupération du contenu de la balise
        String contenu = "";
        for(int i=start; i<start+length; i++)
        {
            contenu = contenu.concat(ch[i]+"");
        }
        
        //Traitement
       
        if(dernierARemplir.compareTo("nbMessages")==0)
        {
            dernierARemplir = "";
            //On fixe le nombre de message annoncé
            doc.setNombreMessageAnnonce(Integer.valueOf(contenu));
        }
        
        if(dernierARemplir.compareTo("institution")==0)
        {
            dernierARemplir = "";
            //On fixe l'institution qui émet ce fichier
            doc.setInstitution(new Institution(contenu));
        }
            
        if(dernierARemplir.compareTo("emailDest")==0)
        {
            dernierARemplir = "";
            //On ajoute le mail du destinataire
            message.addMailDest(contenu);
        }
        
        if(dernierARemplir.compareTo("emailExp")==0)
        {
            dernierARemplir = "";
            
            //On fixe le mail de l'expéditeur
            message.setMailExp(contenu);
        }
        
        
        //Pour le type INFORMATION
        
        if(dernierARemplir.compareTo("sujet")==0 && dernierTypeMessage==TypeMessage.INFORMATION)
        {
            dernierARemplir = "";
            information.setSujet(contenu);
        }
        
        if(dernierARemplir.compareTo("contenuTexte")==0 && dernierTypeMessage==TypeMessage.INFORMATION)
        {
            dernierARemplir = "";
            information.setContenuTexte(contenu);
        }
        
        if(dernierARemplir.compareTo("dateDebut")==0 && dernierTypeMessage==TypeMessage.INFORMATION)
        {
            dernierARemplir = "";
            information.setDateDebut(new SimpleDateFormat(contenu));
        }
        
        if(dernierARemplir.compareTo("dateFin")==0 && dernierTypeMessage==TypeMessage.INFORMATION)
        {
            dernierARemplir = "";
            information.setDateDebut(new SimpleDateFormat(contenu));
        }
                
        if(dernierARemplir.compareTo("semaines")==0 && dernierTypeMessage==TypeMessage.INFORMATION)
        {
            dernierARemplir = "";
            information.setDuree(contenu+" semaines");
        }
                
        if(dernierARemplir.compareTo("mois")==0 && dernierTypeMessage==TypeMessage.INFORMATION)
        {
            dernierARemplir = "";
            information.setDuree(contenu+" mois");
        }
        
        //Pour le type DEMANDE
        
        if(dernierARemplir.compareTo("sujet")==0 && dernierTypeMessage==TypeMessage.DEMANDE)
        {
            dernierARemplir = "";
            demande.setSujet(contenu);
        }
        
        if(dernierARemplir.compareTo("dateDebut")==0 && dernierTypeMessage==TypeMessage.DEMANDE)
        {
            dernierARemplir = "";
            demande.setDateDebut(new SimpleDateFormat(contenu));
        }
        
        if(dernierARemplir.compareTo("dateFin")==0 && dernierTypeMessage==TypeMessage.DEMANDE)
        {
            dernierARemplir = "";
            demande.setDateDebut(new SimpleDateFormat(contenu));
        }
            
        if(dernierARemplir.compareTo("semaines")==0 && dernierTypeMessage==TypeMessage.DEMANDE)
        {
            dernierARemplir = "";
            demande.setDuree(contenu+" semaines");
        }  
            
        if(dernierARemplir.compareTo("mois")==0 && dernierTypeMessage==TypeMessage.DEMANDE)
        {
            dernierARemplir = "";
            demande.setDuree(contenu+" mois");
        }
            
        //Pour le type AUTORISATION
        
        if(dernierARemplir.compareTo("autorisationID")==0 && dernierTypeMessage==TypeMessage.AUTORISATION)
        {
            dernierARemplir = "";
            //On fixe l'id de l'autorisation
            autorisation.setId(contenu);
        }
    
        if(dernierARemplir.compareTo("semaines")==0 && dernierTypeMessage==TypeMessage.AUTORISATION)
        {
            dernierARemplir = "";
            autorisation.setDuree(contenu+" semaines");
        }  
            
        if(dernierARemplir.compareTo("mois")==0 && dernierTypeMessage==TypeMessage.AUTORISATION)
        {
            dernierARemplir = "";
            autorisation.setDuree(contenu+" mois");
        }
        
        if(dernierARemplir.compareTo("dateDebut")==0 && dernierTypeMessage==TypeMessage.AUTORISATION)
        {
            dernierARemplir = "";
            autorisation.setDateDebut(new SimpleDateFormat(contenu));
        }
        
        if(dernierARemplir.compareTo("dateFin")==0 && dernierTypeMessage==TypeMessage.AUTORISATION)
        {
            dernierARemplir = "";
            autorisation.setDateDebut(new SimpleDateFormat(contenu));
        }
        
        //Pour le type REPONSE
        
        if(dernierARemplir.compareTo("sujet")==0 && dernierTypeMessage==TypeMessage.REPONSE)
        {
            dernierARemplir = "";
            reponse.setSujet(contenu);
        }
        
        if(dernierARemplir.compareTo("contenuTexte")==0 && dernierTypeMessage==TypeMessage.REPONSE)
        {
            dernierARemplir = "";
            reponse.setContenuTexte(contenu);
        }
    }
}
