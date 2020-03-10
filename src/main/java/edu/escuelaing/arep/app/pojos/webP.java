package edu.escuelaing.arep.app.pojos;

import edu.escuelaing.arep.app.framework.annotatations.serverAnotac;
import edu.escuelaing.arep.app.framework.annotatations.webAnotac;

@serverAnotac(path = "/helloPojo")
public class webP {

    @webAnotac(path = "/hello")
    public static String Hello() {
        return "<h1>Hello Pojo!</h1>";
    }
}