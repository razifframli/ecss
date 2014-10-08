/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
/**
 *
 * @author BHEBEG
 */
public class sentQueue {
    
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "CSS";
       public void sentQueue(String msg) throws JMSException{
           
           // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
           
   // private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // Name of the queue we will be sending messages to
    

           // Getting JMS connection from the server and starting it
        
         //failover://tcp://localhost:61616 192.168.1.3 10.73.30.245
         //wifi : failover://tcp://192.168.1.3:61616 : 192.168.1.38
        ConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory("failover://tcp://localhost:61616");
        javax.jms.Connection connection = connectionFactory.createConnection();//  .createConnection();
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
        TextMessage message = session.createTextMessage(msg);
 //System.out.println("こんにちは - From dhana");
        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
       }
    
}
