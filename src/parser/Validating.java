/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
 */
package parser;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author chris
 */
public class Validating
{
    public boolean validate(String xsdPath,String xmlPath)
    {
        File xsdFile = new File(xsdPath);
        Source xmlFile = new StreamSource(new File(xmlPath));
        
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        
        try
        {
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            
            System.out.println(xmlFile.getSystemId() + " validé");
            return true;
        }
        catch(SAXException e)
        {
            System.out.println(xmlFile.getSystemId() + " non validé:" + e);
            return false;
        }
        catch(IOException e)
        {}
        return false;
    }
    
}
