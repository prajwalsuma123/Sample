package org.example.client;

import com.sumasoft.stt.client.AudioVoskClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class Client extends AudioVoskClient {

    public Client(URI serverUri) {
        super(serverUri);
    }
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("On Open Server Event");
        
    }

    @Override
    public void onMessage(String s) {
        System.out.println("Message from Server :"+s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }

    
}
