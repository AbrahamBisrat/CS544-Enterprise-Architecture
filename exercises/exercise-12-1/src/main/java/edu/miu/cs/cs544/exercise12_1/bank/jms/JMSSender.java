package edu.miu.cs.cs544.exercise12_1.bank.jms;


import org.springframework.stereotype.Component;

@Component
public class JMSSender implements IJMSSender{
	
	public void sendJMSMessage (String text){
		System.out.println("JMSSender: sending JMS message =" + text);
	}

}
