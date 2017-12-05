package server;


/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/

import data.Database;
import data.Document;
import java.io.IOException;

import data.Global;
import parser.HandlerSAX;
import parser.ParserSAX;
import parser.Validating;
import watchDir.DirectoryWatchService;
import watchDir.SimpleDirectoryWatchService;

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
        database = new Database(Global.DB_PATH); //TODO
        
        //Initialisation du parseur
        parser = new ParserSAX();
    }
    
    
    /**
     * Base du programme, déclenche le traitement lorsqu'un nouveau fichier XML est placé dans le répertoire FileReceivingFolder
     */
    public void start()
    {
        DirectoryWatchService watchService = null;

        try
        {
            watchService = new SimpleDirectoryWatchService();
            watchService.register(new DirectoryWatchService.OnFileChangeListener()
            {
                @Override
                public void onFileCreate(String newFilePath)
                {
                    System.out.println("creation of " + newFilePath);
                    startComputing(Global.FILE_RECEIVING_FOLDER + newFilePath);
                }
                @Override
                public void onFileModify(String newFilePath)
                {
                    System.out.println("modification of " + newFilePath);
                    startComputing(Global.FILE_RECEIVING_FOLDER + newFilePath);
                }
                @Override
                public void onFileDelete(String newFilePath)
                {
                    System.out.println("delete of "+newFilePath);
                }
            },
                Global.FILE_RECEIVING_FOLDER, // Directory to watch
                "*.xml"
            );

            watchService.start();
            database.connect();
	    database.dropTable();
	    database.createTableMail();
	    
	    //database.insertIntoMail("test","test","test");
	    }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     *  Lancement du traitement
     * @param pathToXMLFile Path to the XML file
     */
    public void startComputing(String pathToXMLFile)
    {
        //XSD utilisé
        String pathToXSD = Global.XSD_FILE_PATH;
        String outputXMLFile = Global.OUTPUT_TRACE_FILE_PATH;
        Boolean verified;
        Document doc;
        
        verified = false;

        //Document qui stockera le dernier fichier XML analysé
        doc = new Document();

        //Vérification XSD du fichier
        Validating.validate(pathToXSD, pathToXMLFile);

        //On parse le XML
        HandlerSAX handlerFile = new HandlerSAX(doc);
        parser.monParsing(handlerFile, pathToXMLFile);

        //Vérification des données
        verified = verification(doc); //TODO

        if(verified)
        {
            //Considération du fichier
            outputXMLFile = consideration(doc); //TODO

            //Ecriture du fichier XML de sortie
            writeResponse(outputXMLFile); //TODO

            System.out.println("Fichier traité");
        }
        else
        {
            System.err.println("ERREUR Fichier non validé");

            //Affichage de test des données importés
            System.out.println(doc);
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
     *  Prise en compte du XML reçu
     * @param doc Document XML reçu
     * @return XML de sortie en String
     */
    private String consideration(Document doc)
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
}
