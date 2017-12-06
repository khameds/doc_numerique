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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
	database.connect();
	database.dropTable();
	database.createTableMail();
	database.addData();
	database.close();
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
            System.err.println("ERREUR Fichier non validé car le nombre de message indiqué ne correspond pas.");

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
	String response = "";
        System.out.println(doc.toString());
        for (int i=0; i<doc.getNombreMessage(); i++)
        {
	    
            Message m = doc.getMessage(i);
	    response += "<message id='"+m.getId()+"'>\n";
            boolean reject = false;
            List<String> listDest = m.getMailDest();
            if (listDest != null)
            {
                for (int j=0; j<listDest.size(); j++)
                {
		    database.connect();
		    if(!database.mailExist(listDest.get(j)))
		    {
                        System.err.println("Le mail est inexistant");
			response += "\t<error>L'un des mails destinataires n'est pas bon</error>\n";
			reject = true;
		    }
//                    if (! existsInDB(listDest.get(i))) //Si l'email n'existe pas
//                        reject = true; //Il faudra rejeter le message
		    database.close();
                }
            }
            TypeMessage type = m.getTypeMessage();
            String id = m.getId();
            
            if (! m.toString().matches("\\A\\p{ASCII}*\\z")) //Faudra tester ça
            {
                System.err.println("Le message contient des caractères non ASCII");
		response += "\t<error>Le message contient des caractères non ASCII</error>\n";
                reject = true;
            }
            
            switch (type)
            {
                case AUTORISATION:
		    response += "\t<type>AUTORISATION</type>\n";
                    if (listDest.size()!=1 || doc.getListeMessage().get(i).getMailExp()==null)
                    {
                        System.err.println("L'autorisation "+id+" n'est pas valide. Il doit y avoir 1 mail expéditeur et 1 mail destinataire.");
                        response += "\t<error>L'autorisation n'est pas valide, il faut 1 mail expéditeur, 1 mail destinataire</error>\n";
			reject = true;
                    }
                    
                    if ( ! isValidDate(m.getAutorisation().getDateDebut(), m.getAutorisation().getDuree()))
                    {
                        System.err.println("La date du message "+id+" n'est pas valide");
			response += "\t<error>La date du message n'est pas valide</error>\n";
                        reject = true;
                    }
                    
                    break;
                
                case DEMANDE:
                    response += "\t<type>DEMANDE</type>\n";
                    if ( ! isValidDate(m.getDemande().getDateDebut(), m.getDemande().getDuree()))
                    {
                        System.err.println("La date du message "+id+" n'est pas valide");
			response += "\t<error>La date du message n'est pas valide</error>\n";
                        reject = true;
                    }
                    
		    if (listDest.size()<1 || doc.getListeMessage().get(i).getMailExp()==null)
		    {
			System.err.println("La demande "+id+" n'est pas valide. Il doit y avoir 1 mail expéditeur et au moins 1 mail destinataire.");
                        response += "\t<error>La demande n'est pas valide, il faut 1 mail expéditeur, 1 mail destinataire</error>\n";

			reject = true;
		    }
		    
                    String sujetDemande = m.getDemande().getSujet();

                    if (sujetDemande.length()>100 || sujetDemande.length()<2)
                    {
                        System.err.println("Le sujet du message "+id+" ne respecte pas le nombre de caratère.");
			response += "\t<error>Le sujet du message ne respecte pas le nombre de caratère</error>\n";
                        reject = true;
                    }
                    String contenuDemande = m.getDemande().getSujet();
                    if (contenuDemande.length()>1000 || contenuDemande.length()<2)
                    {
                        System.err.println("Le contenu du message "+id+" ne respecte pas le nombre de caratère.");
			response += "\t<error>Le contenu du message ne respecte pas le nombre de caratère</error>\n";
                        reject = true;
                    }
		    
		    database.connect();
		    int  idInstitution = database.GetInstitutionIDFromMail(doc.getListeMessage().get(i).getMailExp());
		    if(idInstitution == -1)
		    {
			System.err.println("L'institution exterieure de "+id+" est inconnue!");
			response += "\t<error>L'institution exterieure est inconnue!</error>\n";
                        reject = true;
		    }
		    String idAuth = m.getDemande().getAuthId();
		    for (int j=0; j<listDest.size(); j++)
		    {
			if(!database.checkAuthorization(idInstitution,idAuth,listDest.get(j)))
			{
			    System.err.println("L'autorisation de"+id+" n'est pas bonne");
			    response += "\t<error>L'institution exterieure n'a pas la bonne autorisation!</error>\n";
			    reject = true;
			}
		    }
		    
		    if(reject!=true)
		    {
			database.addMessageDemande(m.getDemande().getSujet(),m.getDemande().getDateDebut().toPattern(),listDest);
		    }
		    
		    
		    database.close();
		    
		    
		    //for each dest, check if authorization is good
                    
                    break;
                    
                case INFORMATION :
                    response += "\t<type>INFORMATION</type>\n";
                    if ( ! isValidDate(m.getInformation().getDateDebut(), m.getInformation().getDuree()))
                    {
                        System.err.println("La date du message "+id+" n'est pas valide");
			response += "\t<error>La date du message n'est pas valide</error>\n";
                        reject = true;
                    }
                    String sujetInfo = m.getInformation().getSujet();

                    if (sujetInfo.length()>100 || sujetInfo.length()<2)
                    {
                        System.err.println("Le sujet du message "+id+" ne respecte pas le nombre de caratère.");
			response += "\t<error>Le sujet n'a pas le bon nombre de caractère</error>\n";
                        reject = true;
                    }
                    String contenuInfo = m.getInformation().getContenuTexte();
                    if (contenuInfo.length()>1000 || contenuInfo.length()<2)
                    {
                        System.err.println("Le contenu du message "+id+" ne respecte pas le nombre de caratère.");
			response += "\t<error>Le contenu n'a pas le bon nombre de caractère</error>\n";
                        reject = true;
                    }
		    if(reject!=true)
		    {
			database.connect();
			database.addMessage(sujetInfo,contenuInfo,m.getInformation().getDateDebut().toPattern());
			database.close();
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
            if (!reject)
            {
                System.out.println("Il faut ajouter le message à la BDD");
		response += "\t<succes>Le message est bon!</succes>\n</message>\n";
            }
            else 
            {
                System.out.println("Le message "+ id + "va être supprimer.");
		response += "\t<Fail>Le message doit être réédité</Fail>\n</message>\n";
            }
        }
        
        // EXEMPLE DE LOG
        // Logger.getLogger(Server.class.getName()).log(Level.INFO,"SALUT");
        
        return response;
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
    
    private boolean isValidDate(SimpleDateFormat date, String duree)
    {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        
        Date d = new Date();
        try
        {
            if (date != null)
                d = simpleFormat.parse(date.toPattern());
            else
                return false;
        }
        catch (ParseException e)
        {
            return false;
        }
        c.setTime(d);
        
        Calendar dateDebut = (Calendar) c.clone();
        
        if (duree == null)
            return true;
        
        if (duree.contains("semaine"))
        {
            int nbSemaine = Integer.parseInt(duree.split(" ")[0]); //On récupère le nombre de semaines.
            c.add(Calendar.DATE, nbSemaine*7);
        }
        
        if (duree.contains("mois"))
        {
            int nbMois = Integer.parseInt(duree.split(" ")[0]); //On récupère le nombre de mois.
            c.add(Calendar.MONTH, nbMois);
        }
        
        Calendar dateFin = c;
        
        Calendar aujourdhui = Calendar.getInstance();
        
        if (dateFin.compareTo(aujourdhui) == -1) //Date de fin est passée
            return false;
        
        aujourdhui.add(Calendar.MONTH, 6);
        
        if (dateDebut.compareTo(aujourdhui) == 1) //Date de début est trop tard
            return false;
        

        return true;
    }
}
