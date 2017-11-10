package com.au.example.interceptor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

public class LogExtension implements Extension {

    List<AnnotatedType<?>> annotatedTypes = new ArrayList<>();
    
    public <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> processAnnotatedType) {

	AnnotatedType<T> annotatedType = processAnnotatedType.getAnnotatedType();
	
	Class<?>[] interfaces = annotatedType.getJavaClass().getInterfaces();
	
	for (Class<?> clazz : interfaces) {
	    if (clazz.isAnnotationPresent(Log.class)) {
		if (!annotatedTypes.contains(annotatedType)) {
		    
		    annotatedTypes.add(annotatedType);
		    
		    Annotation auditAnnotation = new Annotation() {

			@Override
			public Class<? extends Annotation> annotationType() {

			    return LogInternalAnnotation.class;
			}
		    };

		    AnnotatedTypeWrapper<T> wrapper = new AnnotatedTypeWrapper<T>(annotatedType, annotatedType.getAnnotations());
		    wrapper.addAnnotation(auditAnnotation);

		    processAnnotatedType.setAnnotatedType(wrapper);
		    
		    break;
		}
	    }
	}
	

//	for (AnnotatedType<?> type : annotatedTypes) {
//	    System.out.println("========================= : " + type.getJavaClass());
//	}
	
/*
	if (annotatedType.getJavaClass().equals(TestBean.class)) {

	    Annotation auditAnnotation = new Annotation() {

		@Override
		public Class<? extends Annotation> annotationType() {

		    return Audit.class;
		}
	    };

	    AnnotatedTypeWrapper<T> wrapper = new AnnotatedTypeWrapper<T>(annotatedType, annotatedType.getAnnotations());
	    wrapper.addAnnotation(auditAnnotation);

	    processAnnotatedType.setAnnotatedType(wrapper);
	}
*/
    }
}
