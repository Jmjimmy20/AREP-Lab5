package edu.escuelaing.arep.app;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import edu.escuelaing.arep.app.framework.annotatations.serverAnotac;
import edu.escuelaing.arep.app.framework.annotatations.webAnotac;

public class Manager{

    
    public static Map<String, Method> getPathC(){

        List<Class> clasesServidor = new ArrayList<>();

        VerifyClasses(clasesServidor);

        Map<String, Method> URLHandler = new HashMap<String, Method>();

        PathGenerator(clasesServidor, URLHandler);

        return URLHandler;

    }


    private static Collection FindClasses(File dir, String addr) throws ClassNotFoundException {
        List classesL = new ArrayList();
			if (!dir.exists()) return classesL;
			File[] FilesL = dir.listFiles();
			for (File file : FilesL) {
					if (file.isDirectory()) {
							assert !file.getName().contains(".");
							classesL.addAll(FindClasses(file, addr + "." + file.getName()));
					} else if (file.getName().endsWith(".class")) {
							classesL.add(Class.forName(addr + '.' + file.getName().substring(0, file.getName().length() - 6)));
					}
			}
			return classesL;
    }

    private static Class[] getClases(String addr) throws ClassNotFoundException, IOException{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = addr.replace('.', '/');
        Enumeration resour = classLoader.getResources(path);
        List<File> FilesL = new ArrayList<File>();
        while (resour.hasMoreElements()) {
            URL URLRes = (URL) resour.nextElement();
            FilesL.add(new File(URLRes.getFile()));
        }
        ArrayList clases = new ArrayList();
        for (File archivo : FilesL) {
            clases.addAll(FindClasses(archivo, addr));
        }
        return (Class[]) clases.toArray(new Class[clases.size()]);
    }

    public static void VerifyClasses(List<Class> classServer){
        Class[] ClassesList;
        try{
            ClassesList = getClases("edu.escuelaing.arep.app");
            for(Class clase : ClassesList){
                if(clase.isAnnotationPresent(serverAnotac.class)) classServer.add(clase);
            }

        }catch (Exception e){
            System.err.println(e);
        }

    }

    public static void PathGenerator(List<Class> clasesServidor, Map<String, Method> URLHandler) {

        serverAnotac anServer;
        webAnotac anWeb;

        for (Class clase : clasesServidor) {
            anServer = (serverAnotac) clase.getAnnotation(serverAnotac.class);
            for (Method metodo : clase.getMethods()) {
                if (metodo.isAnnotationPresent(webAnotac.class)) {
                    anWeb = (webAnotac) metodo.getAnnotation(webAnotac.class);
                    URLHandler.put(anServer.path() + anWeb.path(), metodo);
                }
            }
        }
    }

}