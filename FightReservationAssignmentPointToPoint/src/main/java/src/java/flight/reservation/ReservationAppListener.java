package src.java.flight.reservation;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import src.java.flight.model.Passenger;

public class ReservationAppListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage=(ObjectMessage) message;
		
		try (ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
				JMSContext jmsContext=cf.createContext()){
			
			InitialContext initialContext=new InitialContext();
			Queue flightReplyQueue=(Queue) initialContext.lookup("queue/flightReplyQueue");
			
			Passenger passenger=(Passenger) objectMessage.getObject();
			String email = passenger.getEmail();
			
			TextMessage replyTextMessage = jmsContext.createTextMessage("passengerEmail "+ email);
			JMSProducer producer = jmsContext.createProducer();
			producer.send(flightReplyQueue, replyTextMessage);
			
			
		} catch (JMSException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
