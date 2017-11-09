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
		System.out.println("Fichier termine");
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
                        Message message = new Message();
                        doc.addMessage(message);
                                
			dernierARemplir = "message";
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
		
		if(dernierARemplir.compareTo("texte")==0)
		{
			/*dernierARemplir = "";
			System.out.println("Remplissage du texte: "+contenu);
			Message last = chat.getDernierMessage();
			last.setTexte(contenu);*/
		}
	}
}
