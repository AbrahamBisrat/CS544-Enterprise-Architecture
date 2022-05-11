package edu.miu.cs.cs544.exercise15x.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExecutionTimeAspect {
    public static String dash = "  #############################################  ";

    @Around("@annotation(ExecutionTime)")
    public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch clock = new StopWatch("");
        clock.start(pjp.toShortString());

        Object retVal = pjp.proceed();

        clock.stop();

        System.out.println("\n\n" + dash + "Time taken to execute \t"
                + pjp.getSignature().getName() + "()"
                + " = " + clock.getTotalTimeMillis() + " ms \t"
                + dash + "\n\n");

        return retVal;
    }

}
