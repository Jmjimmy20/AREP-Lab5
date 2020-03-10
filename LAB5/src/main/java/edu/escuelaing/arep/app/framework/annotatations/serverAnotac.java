package edu.escuelaing.arep.app.framework.annotatations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface serverAnotac{
    String path() default "/";
}