/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/
package projetdocnumerique;

/**
 * 
 * @author Chris
 */
public class Message
{
    TypeMessage typeMessage;
    
    public Message()
    {
        
    }
    
    public Message(TypeMessage type)
    {
        this();
        typeMessage = type;
    }
}
