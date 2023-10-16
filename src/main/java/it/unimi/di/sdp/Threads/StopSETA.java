package it.unimi.di.sdp.Threads;

import it.unimi.di.sdp.SETA;

import java.io.IOException;

public class StopSETA extends Thread{

    @Override
    public void run() {
        try {

            System.in.read();

            System.out.println("\n[SETA] Stopping SETA");

            SETA.stop = true;
            SETA.persistentQueues.stop();

        } catch (IOException e ){
            System.out.println(e.getCause()+" : "+e.getMessage());
        }

    }
}
