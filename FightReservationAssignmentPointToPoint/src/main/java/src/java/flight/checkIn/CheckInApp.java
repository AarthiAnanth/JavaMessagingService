package src.java.flight.checkIn;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import src.java.flight.model.Passenger;

public class CheckInApp {

	public static void main(String[] args) throws Exception {
		InitialContext initialContext=new InitialContext();
		Queue flightRequestQueue = (Queue) initialContext.lookup("queue/flightRequestQueue");
		
		Queue flightReplyQueue=(Queue) initialContext.lookup("queue/flightReplyQueue");
		
		try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
				JMSContext jmsContext=cf.createContext();){
			
			JMSProducer jmsRequestProducer = jmsContext.createProducer();
			ObjectMessage requestMessage=jmsContext.createObjectMessage();
			Passenger passenger1=new Passenger();
			passenger1.setId(101);
			passenger1.setFirstName("Aarthi");
			passenger1.setLastName("Ananth");
			passenger1.setEmail("rthi.shini@gmail.com");
			passenger1.setPhone(8197846298l);
			requestMessage.setObject(passenger1);
			jmsRequestProducer.send(flightRequestQueue, requestMessage);
			
			JMSConsumer jmsConsumer = jmsContext.createConsumer(flightReplyQueue);
			Message message = jmsConsumer.receive();
			System.out.println("REply message from reservation system"+message);
			
		}

	}

}
