package edu.escuelaing.arep.app;

import java.io.IOException;
import edu.escuelaing.arep.app.framework.Servidor;

public class MainApp{

    public static void main(String[] args) throws IOException {
        
        Servidor server = new Servidor(Manager.getPathC());
        server.Start();
    }
}