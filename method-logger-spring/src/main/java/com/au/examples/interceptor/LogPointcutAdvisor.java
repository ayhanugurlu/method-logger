package com.au.examples.interceptor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class LogPointcutAdvisor extends AbstractPointcutAdvisor {

    private static final long serialVersionUID = 1L;

    private final AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(Log.class, true);

    private LogInterceptor interceptor;

    public LogPointcutAdvisor(LogInterceptor interceptor) {

	super();
	this.interceptor = interceptor;
    }

    @Override
    public Pointcut getPointcut() {

	return this.pointcut;
    }

    @Override
    public Advice getAdvice() {

	return this.interceptor;
    }
}
