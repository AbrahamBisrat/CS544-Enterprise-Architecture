package edu.miu.cs.cs544.exercise15x.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class ExecutionTimeAspect {

    @Around("annotations(ExecutionTime)")
    public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch clock = new StopWatch("");
        clock.start(pjp.toShortString());

        Object retVal = pjp.proceed();

        clock.stop();

        System.out.println("\n\nTime take to execute "
                + pjp.getSignature().getName()
                + " = " + clock.prettyPrint());

        return retVal;
    }

}
