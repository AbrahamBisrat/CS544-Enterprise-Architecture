package edu.miu.cs.cs544.exercise13_1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.Date;

@Aspect
public class mailAspect {

    @Pointcut("execution(* edu.miu.cs.cs544.exercise13_1.EmailSender.sendEmail(..))")
    public void sendEmailCut() {}

    @Pointcut("execution(* edu.miu.cs.cs544.exercise13_1.EmailSender.getOutgoingMailServer(..))")
    public void outgoingEmailCut() {}

    @Pointcut("execution(* edu.miu.cs.cs544.exercise13_1.CustomerDAO.*(..))")
    public void customerCut() {}

    @After("sendEmailCut()")
    public void sendMailNotifier(JoinPoint jp) {
        String method = jp.getSignature().getName();
        String subject = jp.getArgs()[0].toString();
        String message = jp.getArgs()[1].toString();

        System.out.println(new Date()
                + "\tmethod : " + method
                + "\nAddress: " + "\t" + subject
                + "\nMessage: " + "\t" + message);
    }

    @AfterReturning(pointcut = "outgoingEmailCut()", returning="retVal")
    public void outgoingNotifier(String retVal) {
        System.out.println("Outgoing mail server = " + retVal);
    }

    @Around("customerCut()")
    public Object customerNotifier(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(pjp.getSignature().getName());
        Object retVal = pjp.proceed();
        sw.stop();
        System.out.println("Time to execute " + pjp.getSignature().getName()
                + "  =  " + sw.getLastTaskTimeMillis());

        return retVal;
    }

}
