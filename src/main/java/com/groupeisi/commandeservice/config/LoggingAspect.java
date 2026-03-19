package com.groupeisi.commandeservice.config;
import org.aspectj.lang.JoinPoint; import org.aspectj.lang.annotation.*;
import org.slf4j.*; import org.springframework.stereotype.Component; import java.util.Arrays;
@Aspect @Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    @Pointcut("within(com.groupeisi.commandeservice.controller..*)") public void ctrl() {}
    @Before("ctrl()") public void before(JoinPoint jp) { log.info("→ {} args={}", jp.getSignature().toShortString(), Arrays.toString(jp.getArgs())); }
    @AfterReturning(pointcut="ctrl()", returning="r") public void after(JoinPoint jp, Object r) { log.info("← {} result={}", jp.getSignature().toShortString(), r); }
    @AfterThrowing(pointcut="ctrl()", throwing="ex") public void error(JoinPoint jp, Throwable ex) { log.error("✗ {} msg={}", jp.getSignature().toShortString(), ex.getMessage()); }
}
