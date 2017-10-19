package projetdocnumerique;

/**
 * Sert aux tests sur le XML et les parsers
 *
 */
public class TestParser
{

    public static void main(String[] args)
    {
        Message message = new Message();

        ParserSAX p3 = new ParserSAX();
        MySAXHandlerChat handlerChat = new MySAXHandlerChat(chat);

        p3.monParsing(handlerChat, "xml/chat.xml");
    }
}