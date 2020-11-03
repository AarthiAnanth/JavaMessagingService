package src.java.flight.reservation;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ReservationApp {

	public static void main(String[] args) throws Exception {
		InitialContext initialContext=new InitialContext();
		Queue flightRequestQueue = (Queue) initialContext.lookup("queue/flightRequestQueue");
		try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
				JMSContext jmsContext=cf.createContext();){
			JMSConsumer jmsConsumer = jmsContext.createConsumer(flightRequestQueue);
			jmsConsumer.setMessageListener(new ReservationAppListener());
		}
	}

}
