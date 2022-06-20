package sbs.baka.cheetah.bluetooth;

import javax.microedition.io.*;

public class ProcessConnectionThread implements Runnable {

    private StreamConnection connection;

    public ProcessConnectionThread(StreamConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {

    }
}
