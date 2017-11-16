/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/
package parser;

import data.Document;

/**
 * Sert aux tests sur le XML et les parsers
 *
 */
public class TestParser
{
    public static void main(String[] args)
    {
        Document doc = new Document();

        ParserSAX p = new ParserSAX();
        HandlerSAX handlerFichier = new HandlerSAX(doc);

        p.monParsing(handlerFichier, "test.xml");
    }
}