/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/
package parser;

import data.Autorisation;
import data.Message;
import data.Document;
import data.Institution;
import data.TypeMessage;
import data.Information;
import java.sql.Date;
import java.text.DateFormat;
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
     * Objet qui va temporairement stocker un message avant son ajout au doc
     */
    Message message;
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
        System.out.println("-- Debut fichier --\n\n");
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endDocument()
     */
    @Override
    public void endDocument()
    {
        System.out.println("-- Fichier termine --");
        System.out.println();
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
            doc.setId(Integer.valueOf(attributes.getValue("fileID")));        
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
            message.setId(Integer.valueOf(attributes.getValue("messageId")));
            dernierARemplir = "message";
        }
        
        if(qName.compareTo("mailDest")==0)
        {
            dernierARemplir = "mailDest";
        }
        
        if(qName.compareTo("mailExp")==0)
        {
            dernierARemplir = "mailExp";
        }
        
        //Pour le type information
                
        if(qName.compareTo("information")==0)
        {
            dernierTypeMessage = TypeMessage.INFORMATION;
            //Ceci va également créer l'objet à l'intérieur de message
            message.setTypeMessage(dernierTypeMessage);
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
        
        //Pour le type autorisation
        
        if(qName.compareTo("autorisation")==0)
        {
            dernierTypeMessage = TypeMessage.AUTORISATION;
            //Ceci va également créer l'objet à l'intérieur de message
            message.setTypeMessage(dernierTypeMessage);
        }
        
        //TODO
        
        //Pour le type demande
        
        if(qName.compareTo("demande")==0)
        {
            dernierTypeMessage = TypeMessage.DEMANDE;
            //Ceci va également créer l'objet à l'intérieur de message
            message.setTypeMessage(dernierTypeMessage);
        }        
        
        //TODO
        
        //Pour le type reponse
        
        if(qName.compareTo("reponse")==0)
        {
            dernierTypeMessage = TypeMessage.REPONSE;
            //Ceci va également créer l'objet à l'intérieur de message
            message.setTypeMessage(dernierTypeMessage);
        }
        
        //TODO
        
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName)
    {
        //System.out.println("Fin de "+qName);
        if(qName.compareTo("message")==0)
        {
            //On ajoute le message au doc maintenant qu'il est remplit
            doc.addMessage(message);
            dernierARemplir = "";
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
            
        if(dernierARemplir.compareTo("mailDest")==0)
        {
            dernierARemplir = "";
            //On fixe le mail du destinataire
            message.setMailDest(contenu);
        }
        
        if(dernierARemplir.compareTo("mailExp")==0)
        {
            dernierARemplir = "";
            //On fixe le mail de l'expéditeur
            message.setMailExp(contenu);
        }
        
        //Pour le type INFORMATION
        
        if(dernierARemplir.compareTo("sujet")==0 && dernierTypeMessage==TypeMessage.INFORMATION)
        {
            message.getInformation().setSujet(contenu);
        }
        
        if(dernierARemplir.compareTo("contenuTexte")==0 && dernierTypeMessage==TypeMessage.INFORMATION)
        {
            message.getInformation().setContenuTexte(contenu);
        }
        
        if(dernierARemplir.compareTo("dateDebut")==0 && dernierTypeMessage==TypeMessage.INFORMATION)
        {
            message.getInformation().setDateDebut(new SimpleDateFormat(contenu));
        }
        
        //Pour le type DEMANDE
        
        if(dernierARemplir.compareTo("sujet")==0 && dernierTypeMessage==TypeMessage.DEMANDE)
        {
            message.getDemande().setSujet(contenu);
        }
        
            //SUITE DEMANDE ICI
        
        //Pour le type REPONSE
        
        if(dernierARemplir.compareTo("sujet")==0 && dernierTypeMessage==TypeMessage.REPONSE)
        {
            message.getReponse().setSujet(contenu);
        }
        
            //SUITE REPONSE ICI
        
    }
}
