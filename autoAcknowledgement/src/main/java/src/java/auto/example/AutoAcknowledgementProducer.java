package src.java.auto.example;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;



public class AutoAcknowledgementProducer {

	public static void main(String[] args) throws Exception {
		InitialContext initialContext=new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");
		try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
				JMSContext jmsContext=cf.createContext(JMSContext.AUTO_ACKNOWLEDGE);){
			
			JMSProducer producer = jmsContext.createProducer();
			producer.send(requestQueue, "Message =1");
			
		}

	}

}
