/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/
package parser;

import data.Document;

/**
 * Sert aux tests sur le XML et les parsers
 *
 */
public class TestParser
{

    public static void main(String[] args)
    {
        Document doc = new Document();
        
        ParserSAX p = new ParserSAX();
        HandlerSAX handlerFichier = new HandlerSAX(doc);
        p.monParsing(handlerFichier, "fichiers_test/valides/some_informations.xml");
        
        Validating.validate("fichiers_definitions/definition.xsd","fichiers_test/valides/some_informations.xml");
        
        System.out.println("---- Informations importés : ----\n");        
        System.out.println("File ID = "+doc.getId());   
        System.out.println("Nom de l'institution = "+doc.getInstitution().getNom());     
        System.out.println("Nombre de message annoncé = "+doc.getNombreMessageAnnonce());  
        System.out.println("Vrai nombre de message = "+doc.getNombreMessage()); 
        System.out.println("Premier message = "+doc.getMessage(0));    
        
        
        System.out.println("\n---- Fin fichier ----"); 
    }
}