package SenderJMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SenderJMS {
    // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    // Name of the queue we will be sending messages to
    private static String subject = "CSS";

    public  void SendQue(String FormattedMsg) throws JMSException {
        // Getting JMS connection from the server and starting it
        
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.73.38.97:61616");//ActiveMQ server IP
        
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // JMS messages are sent and received using a Session. We will
        // create here a non-transactional session object. If you want
        // to use transactions you should set the first parameter to 'true'
        Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

        // Destination represents here our queue 'TESTQUEUE' on the
        // JMS server. You don't have to do anything special on the
        // server to create it, it will be created automatically.
        Destination destination = session.createQueue(subject);

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);

        // We will send a small text message saying 'Hello' in Japanese
        TextMessage message = session.createTextMessage(FormattedMsg);

        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
    }
    
    
    List PMSList=null;
    public List getPMIOnline(String IcNumber) throws JMSException{
       // Getting JMS connection from the server and starting it
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://10.73.38.97:61616?jms.watchTopicAdvisories=false"); //failover://tcp://localhost:61616

        Connection connection = connectionFactory.createConnection();
        connection.start();

        // JMS messages are sent and received using a Session. We will
        // create here a non-transactional session object. If you want
        // to use transactions you should set the first parameter to 'true'
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Destination represents here our queue 'TESTQUEUE' on the
        // JMS server. You don't have to do anything special on the
        // server to create it, it will be created automatically.
        Destination destination = session.createQueue(subject);

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);

        // create a queue sender
        //QueueRequestor requestor = new QueueRequestor(session, queue);

        //Create temp queue for response
        Queue responseQueue = session.createTemporaryQueue();
        MessageConsumer consumer = session.createConsumer(responseQueue);

        // We will send a small text message saying 'Hello' in Japanese
        // create a simple message
        //MapMessage message = session.createMapMessage();

        // send the messages
        TextMessage message = session.createTextMessage(IcNumber); //ic
        // TextMessage result = (TextMessage) requestor.request(message);
        //message.clearBody();

        //System.out.println(message.getText());
        message.setJMSReplyTo(responseQueue);
        System.out.println("...Sending Message...");

        // Here we are sending the message!
        producer.send(message);
        //System.out.println("Sent message '" + message.getText() + "'");

        String replyString = null;


        // See if there is a response
        Message replyMessage = consumer.receive(1000);

        System.out.println("....replyMessage :" + replyMessage);

        if (replyMessage instanceof TextMessage) {
            
            replyString = ((TextMessage) replyMessage).getText();
            
        } else if (replyMessage instanceof MapMessage) {
            
            Map map = (Map) replyMessage.getObjectProperty("profile");
            
        } else if (replyMessage instanceof ObjectMessage) {
            
            System.out.println("...in ObjectMessage....");
            PMSList = (ArrayList) replyMessage.getObjectProperty("PMS");
            
//            List<String> pdi = (ArrayList<String>)PMSList.get(0);
//            String[] PDI = pdi.toArray(new String[pdi.size()]);
//            
//            List<String> emp = (ArrayList<String>)PMSList.get(1);
//            String[] EMP = emp.toArray(new String[emp.size()]);
//            
//            List<String> nok = (ArrayList<String>)PMSList.get(2);
//            String[] NOK = nok.toArray(new String[nok.size()]);
//            
//            List<String> fmi = (ArrayList<String>)PMSList.get(3);
//            String[] FMI = fmi.toArray(new String[fmi.size()]);
//            
//            List<String> ins = (ArrayList<String>)PMSList.get(3);
//            String[] INS = ins.toArray(new String[ins.size()]);
            
                    System.out.println("...PDIList : "+ PMSList.get(0));
           
                }
                connection.close();
                return PMSList;
   
    
    
    
   
    
    
    
    
    
    
    
    }
    
}