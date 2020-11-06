package com.jms.hopitalmanagement.filter;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.jms.hospitalmanagement.model.Claim;

public class ClaimManagement {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		InitialContext initialContext=new InitialContext();
		Queue filterQueue=(Queue) initialContext.lookup("queue/claimQueue");
		try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
				JMSContext jmsContext=cf.createContext()){
			JMSProducer producer = jmsContext.createProducer();
			//JMSConsumer consumer = jmsContext.createConsumer(filterQueue,"doctorName LIKE 'A%'");
			//JMSConsumer consumer = jmsContext.createConsumer(filterQueue,"InsuranceProvider IN ('blue cross','american')");
			JMSConsumer consumer = jmsContext.createConsumer(filterQueue,"ClaimAmount= '1000'");
			
			
			ObjectMessage message=jmsContext.createObjectMessage();
			//message.setStringProperty("doctorName", "Aarthi");
			//message.setStringProperty("InsuranceProvider", "blue cross");
			message.setStringProperty("ClaimAmount", "1000");
			Claim claim=new Claim();
			claim.setClaimAmount("1000");
			claim.setDoctorName("Aarthi");
			claim.setDoctorType("Gyn");
			claim.setHospitlId(1001);
			claim.setInsuranceProvider("blue cross");
			message.setObject(claim);
			
			producer.send(filterQueue, message);
			Claim receiveBody = consumer.receiveBody(Claim.class);
			System.out.println(receiveBody.getClaimAmount());
		}

	}

}
