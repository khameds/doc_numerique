package projetdocnumerique;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import donnees.Chat;
import donnees.Message;

/**
 * Handler SAX de l'objet Chat
 *
 */
public class MySAXHandlerChat extends DefaultHandler
{	
	Chat chat;
	String dernierARemplir;
	
	/**
	 * Constructeur et initialise le chat
	 * @param chat Chat
	 */
	public MySAXHandlerChat(Chat chat)
	{
		this.chat = chat;
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	public void startDocument()
	{
		System.out.println("D�but fichier:\n\n");
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	public void endDocument()
	{
		System.out.println("Fichier termin�");
		System.out.println();
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes)
	{
		if(qName=="message")
		{
			System.out.println("Nouveau message");
			chat.ajouterMessage(new Message());
			chat.getDernierMessage().setIdUtilisateur(attributes.getValue(0));
			chat.getDernierMessage().setIdEvenement(attributes.getValue(1));
			dernierARemplir = "message";
		}
		if(qName=="date")
		{
			System.out.println("Date");
			dernierARemplir = "date";
		}
		if(qName=="auteur")
		{
			System.out.println("Auteur");
			dernierARemplir = "auteur";
		}
		if(qName=="texte")
		{
			System.out.println("Texte");
			dernierARemplir = "texte";
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName)
	{
		
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length)
	{
		String contenu = "";
		for(int i=start; i<start+length; i++)
		{
			contenu = contenu.concat(ch[i]+"");
		}
		
		if(dernierARemplir == "date")
		{
			dernierARemplir = "";
			System.out.println("Remplissage de date: "+contenu);
			Message last = chat.getDernierMessage();
			last.setDate(contenu);
		}
		if(dernierARemplir == "auteur")
		{
			dernierARemplir = "";
			System.out.println("Remplissage de l'auteur: "+contenu);
			Message last = chat.getDernierMessage();
			last.setIdUtilisateur(contenu);
		}
		if(dernierARemplir == "texte")
		{
			dernierARemplir = "";
			System.out.println("Remplissage du texte: "+contenu);
			Message last = chat.getDernierMessage();
			last.setTexte(contenu);
		}
	}
}
