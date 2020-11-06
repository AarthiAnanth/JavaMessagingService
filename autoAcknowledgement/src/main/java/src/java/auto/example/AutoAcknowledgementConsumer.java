package src.java.auto.example;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;



public class AutoAcknowledgementConsumer {

	public static void main(String[] args) throws Exception {
		InitialContext initialContext=new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");
		try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
				JMSContext jmsContext=cf.createContext();){
			
			JMSConsumer consumer = jmsContext.createConsumer(requestQueue);
			TextMessage message = (TextMessage) consumer.receive();
			System.out.println(message.getText());
		}

	}

}
