package server;


/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/

import data.Database;
import data.Document;
import java.io.IOException;

import data.Global;
import data.Message;
import data.TypeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    startComputing(Global.FILE_RECEIVING_FOLDER + "/" + newFilePath);
                }
                @Override
                public void onFileModify(String newFilePath)
                {
                    System.out.println("modification of " + newFilePath);
                    startComputing(Global.FILE_RECEIVING_FOLDER + "/" + newFilePath);
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
	    
	    database.insertIntoMail("test","test","test",1);
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
     *  Vérifications bloquante pour la suite de la lecture (ne concerne pas les erreurs de messages qui engendre seulement le passage au message suivant)
     * @param doc Document XML reçu
     * @return Vrai si le document est ok, Faux sinon
     */
    private Boolean verification(Document doc)
    {
        return doc.getNombreMessage() == doc.getNombreMessageAnnonce();
    }

    /**
     *  Prise en compte du XML reçu
     * @param doc Document XML reçu
     * @return XML de sortie en String
     */
    private String consideration(Document doc)
    {
        for (int i=0; i<doc.getNombreMessage(); i++)
        {
            Message m = doc.getMessage(i);
            boolean reject = false;
            List<String> listDest = m.getMailDest();
            if (listDest != null)
            {
                for (int j=0; j<listDest.size(); j++)
                {
//                    if (! existsInDB(listDest.get(i))) //Si l'email n'existe pas
//                        reject = true; //Il faudra rejeter le message
                }
            }
            TypeMessage type = m.getTypeMessage();
            String id = m.getId();
            
            if (! m.toString().matches("\\A\\p{ASCII}*\\z")) //Faudra tester ça
            {
                System.err.println("Le message contient des caractères non ASCII");
                reject = true;
            }
            
            switch (type)
            {
                case AUTORISATION:
                
                    break;
                
                case DEMANDE:
                    String sujetDemande = m.getDemande().getSujet();
                    if (sujetDemande.length()>100 || sujetDemande.length()<2)
                    {
                        System.err.println("Le sujet du message "+id+" ne respecte pas le nombre de caratère.");
                        reject = true;
                    }
                    String contenuDemande = m.getDemande().getSujet();
                    if (contenuDemande.length()>1000 || contenuDemande.length()<2)
                    {
                        System.err.println("Le contenu du message "+id+" ne respecte pas le nombre de caratère.");
                        reject = true;
                    }
                    
                    break;
                    
                case INFORMATION :
                    String sujetInfo = m.getInformation().getSujet();
                    if (sujetInfo.length()>100 || sujetInfo.length()<2)
                    {
                        System.err.println("Le sujet du message "+id+" ne respecte pas le nombre de caratère.");
                        reject = true;
                    }
                    String contenuInfo = m.getInformation().getContenuTexte();
                    if (contenuInfo.length()>1000 || contenuInfo.length()<2)
                    {
                        System.err.println("Le contenu du message "+id+" ne respecte pas le nombre de caratère.");
                        reject = true;
                    }
                    break;
                    
                case REPONSE :
                    String sujetReponse = m.getReponse().getSujet();
                    if (sujetReponse.length()>100 || sujetReponse.length()<2)
                    {
                        System.err.println("Le sujet du message "+id+" ne respecte pas le nombre de caratère.");
                        reject = true;
                    }
                    String contenuReponse = m.getReponse().getContenuTexte();
                    if (contenuReponse.length()>100 || contenuReponse.length()<2)
                    {
                        System.err.println("Le contenu du message "+id+" ne respecte pas le nombre de caratère.");
                        reject = true;
                    }
                    break;
            }
        }
        
        // EXEMPLE DE LOG
        // Logger.getLogger(Server.class.getName()).log(Level.INFO,"SALUT");
        
        return "(XML DE SORTIE)";
    }

    /**
     *  Ecriture du fichier XML de sortie
     * @param outputXMLFile Le XML en String
     */
    private void writeResponse(String outputXMLFile)
    {
        System.out.println("OUTPUT XML = " + outputXMLFile); //TEMPORAIRE
        //Il faudra écrire ça dans un fichier de sortie
        
        try
        {
            FileOutputStream fos = new FileOutputStream(new File("OutputFolder/"+new Date().toString().replace(":", "-")+".xml")); //Le nom du fichier de sortie est la date
            fos.write(outputXMLFile.getBytes());
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
