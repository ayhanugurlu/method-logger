package com.au.examples.interceptor;

import java.io.IOException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
	Object returnObject = null;
	System.out.println("--------------" + invocation.getMethod().getDeclaringClass().getCanonicalName() + "-----------------");
	System.out.println("method name:" + invocation.getMethod().getName());
	for (Object o : invocation.getArguments()) {
	    System.out.println(objectToJson(o));
	}
	returnObject = invocation.proceed();
	if (returnObject != null) {
	    System.out.println(objectToJson(returnObject));
	}

	return returnObject;

    }

    private String objectToJson(Object input) {

	ObjectMapper mapper = new ObjectMapper();
	// Object to JSON in String
	String jsonInString = null;
	try {
	    jsonInString = mapper.writeValueAsString(input);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return jsonInString;

    }
}
