package edu.escuelaing.arep.app.cliente;

public class ClienteMain{
    /**
     * Genera el proceso de cliente por medio de un el pool de los threads
     * @param args 
     */
    public static void main(String[] args) {
        int numeroThreads = Integer.parseInt(args[0]);
        ThreadInit iniciadorThreadsCliente = new ThreadInit(numeroThreads);
        iniciadorThreadsCliente.Start("https://frozen-caverns-49125.herokuapp.com");
        
    }
}