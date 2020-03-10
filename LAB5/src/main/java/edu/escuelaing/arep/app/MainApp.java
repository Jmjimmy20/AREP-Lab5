package edu.escuelaing.arep.app;

import java.io.IOException;

import edu.escuelaing.arep.app.framework.MainThreadServer;
import edu.escuelaing.arep.app.framework.Servidor;

public class MainApp{

    public static void main(String[] args) throws IOException {
        
        MainThreadServer mainThreadServer = new MainThreadServer(30);
        mainThreadServer.Start();
    }
}