package com.au.example.interceptor;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.fasterxml.jackson.databind.ObjectMapper;



@Priority(Interceptor.Priority.APPLICATION + 1)
@LogInternalAnnotation
@Interceptor
public class LogInterceptor implements Serializable  {

    /**
     * 
     */
    private static final long serialVersionUID = 3545141423664045538L;

    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
	Object returnObject = null;

	    System.out.println("---------" + ctx.getMethod().getDeclaringClass().getName() + "---------");
	    Object[] objects = ctx.getParameters();
	    for (Object o : objects) {
		System.out.println(objectToJson(o));
	    }

	returnObject = ctx.proceed();

	if (returnObject != null) {
	    System.out.println(objectToJson(returnObject));
	}

	return returnObject;


    }

    /**
     * input olarak verilen objeti json stringine çevirir.
     * 
     * @param input 
     * @return
     */
    private String objectToJson(Object input) {

	ObjectMapper mapper = new ObjectMapper();
	 //Object to JSON in String
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
