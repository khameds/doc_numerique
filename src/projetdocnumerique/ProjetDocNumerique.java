/*
* Projet de Documents Numérique
* Smail KHAMED, Clément COLIN, Dimitri BRUYERE, Christopher JEAMME
*/
package projetdocnumerique;

import server.Server;

/**
 *
 * @author Chris
 */
public class ProjetDocNumerique
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }
    
}
