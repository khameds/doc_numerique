package parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Gestion du parser SAX
 *
 */
public class ParserSAX
{
    /**
     * Constructeur vide
     */
    public ParserSAX()
    {

    }

    /**
     * Cree le parser
     * @param handler DefaultHandler
     * @param xml String
     */
    public void monParsing(DefaultHandler handler, String xml)
    {
        SAXParserFactory factory= SAXParserFactory.newInstance();
        factory.setValidating(true);
        SAXParser parser;

        try
        {
            parser = factory.newSAXParser();
            InputStream xmlInput = new FileInputStream(xml);
            parser.parse(xmlInput, handler);
            xmlInput.close();
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
