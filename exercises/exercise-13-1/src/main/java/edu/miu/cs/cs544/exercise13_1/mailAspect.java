package edu.miu.cs.cs544.exercise13_1;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Aspect
@Component
public class mailAspect {

    @After("execution(* edu.miu.cs.cs544.exercise13_1.EmailSender.sendEmail(..))")
    public void sendMailNotifier(JoinPoint joinPoint) {
        System.out.println(new Date() + "\tmethod : " + joinPoint.getSignature().getName());
        System.out.println("Address: \t" + joinPoint.getArgs()[0]);
        System.out.println("Message: \t" + joinPoint.getArgs()[1]);
    }

}
