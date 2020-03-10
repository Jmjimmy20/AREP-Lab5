package edu.escuelaing.arep.app.cliente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadInit{

    private ExecutorService executorService;
    public int tamanioPool;

    /**
     * Metodo encargado de inicializar la pool de los threads del cliente
     * @param tamanioPool 
     */
    public ThreadInit(int tamanioPool) {
        executorService = Executors.newFixedThreadPool(tamanioPool);
        this.tamanioPool = tamanioPool;
      }
    
    
    /**
     * metodo encargado de iniciar todos los threads solicitados
     * @param args 
     */
      public void Start(String args) {
          long tiempoDuracion = System.nanoTime();
          for (int i = 0; i < tamanioPool; i++) {
            executorService.execute(new Cliente(args, tiempoDuracion));
          }
          executorService.shutdown();
      }

}