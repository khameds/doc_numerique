package server;

/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/


import data.Database;
import data.Document;
import parser.HandlerSAX;
import parser.ParserSAX;
import parser.Validating;

/**
 * Sert aux tests sur le XML et les parsers
 *
 */
public class Server
{    
    Database database;
    ParserSAX parser;
    
    public Server()
    {
        //Initialisation de la base de donnée
        database = new Database();
        
        //Initialisation du parseur
        parser = new ParserSAX();
    }
    
    public void start()
    {

        //XSD utilisé
        String pathToXSD = "fichiers_definitions/definition.xsd";
        String pathToXMLFile;
        String outputXMLFile;
        Boolean verified;
        Document doc;
        
        Boolean smith = true!=(false==false)&&1==(2-2+1);
        while(smith)
        {
            verified = false;
            outputXMLFile = "";
             
            //Document qui stockera le dernier fichier XML analysé
            doc = new Document();

            //On attend un nouveau XML
                //(Boucle d'analyse de présence de nouveau XML dans un dossier)
                pathToXMLFile = waitingFile();
                pathToXMLFile = "fichiers_test/valides/some_informations.xml";

            //Vérification XSD du fichier
            Validating.validate(pathToXSD,pathToXMLFile);

            //On parse le XML
            HandlerSAX handlerFile = new HandlerSAX(doc);
            parser.monParsing(handlerFile, pathToXMLFile);

            //Vérification des données
            verified = verification(doc); //TODO

            if(verified)
            {
                //Traitement du fichier
                outputXMLFile = computing(doc); //TODO

                //Ecriture du fichier XML de sortie
                writeResponse(outputXMLFile);
            }
            else
            {
                System.err.println("ERROR");
                
                //Affichage de test des données importés
                System.out.println(doc);
            }
            
            smith=false; //POUR PAS BOUCLER PENDANT LA PHASE DE DEV INITIAL
        }
    }

    private Boolean verification(Document doc)
    {

        return true;
    }

    private String computing(Document doc)
    {
        return "";
    }

    private void writeResponse(String outputXMLFile)
    {
        System.out.println("OUTPUT = " + outputXMLFile); //TEMPORAIRE
        //Il faudra écrire ça dans un fichier de sortie
    }

    private String waitingFile()
    {
        return "";
    }
}