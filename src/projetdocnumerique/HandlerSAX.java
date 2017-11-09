/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/
package projetdocnumerique;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler SAX de l'objet Fichier
 *
 */
public class HandlerSAX extends DefaultHandler
{	
	Document doc;
	String dernierARemplir;
	
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
		System.out.println("Debut fichier:\n\n");
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
        @Override
	public void endDocument()
	{
		System.out.println("Fichier termin�");
		System.out.println();
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
        @Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
	{
		if(qName.compareTo("message")==0)
		{
			System.out.println("Nouveau message");
			chat.ajouterMessage(new Message());
			chat.getDernierMessage().setIdUtilisateur(attributes.getValue(0));
			chat.getDernierMessage().setIdEvenement(attributes.getValue(1));
			dernierARemplir = "message";
		}
		if(qName.compareTo("date")==0)
		{
			System.out.println("Date");
			dernierARemplir = "date";
		}
		if(qName.compareTo("auteur")==0)
		{
			System.out.println("Auteur");
			dernierARemplir = "auteur";
		}
		if(qName.compareTo("texte")==0)
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
		
		if(dernierARemplir.compareTo("date")==0)
		{
			dernierARemplir = "";
			System.out.println("Remplissage de date: "+contenu);
			Message last = chat.getDernierMessage();
			last.setDate(contenu);
		}
		if(dernierARemplir.compareTo("auteur")==0)
		{
			dernierARemplir = "";
			System.out.println("Remplissage de l'auteur: "+contenu);
			Message last = chat.getDernierMessage();
			last.setIdUtilisateur(contenu);
		}
		if(dernierARemplir.compareTo("texte")==0)
		{
			dernierARemplir = "";
			System.out.println("Remplissage du texte: "+contenu);
			Message last = chat.getDernierMessage();
			last.setTexte(contenu);
		}
	}
}
