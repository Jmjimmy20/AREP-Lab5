package edu.escuelaing.arep.app.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.arep.app.Manager;
import edu.escuelaing.arep.app.framework.Servidor;

public class MainThreadServer{

    ServerSocket socket;
    private Map<String, Method> mapeoURL = new HashMap<String, Method>();
    private ExecutorService executorService;
    private boolean bandera;
    
    public MainThreadServer(int Nthread) throws IOException{
        socket = new ServerSocket(getPuerto());
        mapeoURL = Manager.getPathC();
        executorService = Executors.newFixedThreadPool(Nthread);
        bandera = true;
    }
    
    
    public void Start(){
        while (bandera) {
            try {
                executorService.execute(new Servidor(mapeoURL, socket));
            } catch (IOException e) {
                executorService.shutdown();
            }
        }
    }
    
    
    /**
     * Metodo encargado de encontrar el puerto por el que se ara la conexion
     * @return int que es el puerto para realizar la conexion
     */
    public static int getPuerto(){
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
         }
        return 4567;
    }

}