package edu.miu.cs.cs544;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  @JmsListener(destination = "mailbox", containerFactory = "myFactory")
  public void receiveMessage(Email email) throws InterruptedException {
	Thread.sleep(10000);
    System.out.println("Finished sending the email: <" + email + ">");
  }

}
