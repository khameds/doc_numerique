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
 * Serveur pour la reception, l'analyse et le traitement des fichiers XML
 */
public class Server
{    
    Database database;
    ParserSAX parser;
    
    /**
     * Initialisation du serveur
     */
    public Server()
    {
        //Initialisation de la base de donnée
        database = new Database();
        
        //Initialisation du parseur
        parser = new ParserSAX();
    }
    
    /**
     *  Lancement du serveur
     */
    public void startServer()
    {
        //XSD utilisé
        String pathToXSD = "fichiers_definitions/definition.xsd";
        String pathToXMLFile;
        String outputXMLFile;
        Boolean verified;
        Document doc;
        
        Boolean smith = true==(false==false)&&1==(2-2+1);     
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
                System.out.println("Nouveau fichier trouvé : " + pathToXMLFile);

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
                
                System.out.println("Fichier traité");
            }
            else
            {
                System.err.println("ERREUR Fichier non validé");
                
                //Affichage de test des données importés
                System.out.println(doc);
            }
            
            smith=false; //POUR PAS BOUCLER PENDANT LA PHASE DE DEV
        }
    }

    /**
     *  Vérifications selon le type de la requete des dates, droits, ..
     * @param doc Document XML reçu
     * @return Vrai si le document est ok, Faux sinon
     */
    private Boolean verification(Document doc)
    {
        //TODO 
        return true;
    }

    /**
     *  Traitement du XML reçu
     * @param doc Document XML reçu
     * @return XML de sortie en String
     */
    private String computing(Document doc)
    {
        //TODO
        return "";
    }

    /**
     *  Ecriture du fichier XML de sortie
     * @param outputXMLFile Le XML en String
     */
    private void writeResponse(String outputXMLFile)
    {
        System.out.println("OUTPUT = " + outputXMLFile); //TEMPORAIRE
        //Il faudra écrire ça dans un fichier de sortie
    }

    /**
     * Boucle sur l'analyse d'un répertoire jusqu'à ce qu'il trouve un fichier XML
     * @return Le document XML trouvé en String
     */
    private String waitingFile()
    {
        return "";
    }
}