package org.example.client;

import com.sumasoft.stt.audio.AcceptStream;
import org.json.JSONObject;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Read Microphone streaming data
 */

public class Microphone {
    
    public Client client;
    
    public float sampleRate=16000;
    AudioFormat format = new AudioFormat(sampleRate, 16, 1,  true, false);
    public DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

    public TargetDataLine microphone;


    public Microphone(URI uri) throws Exception {
        this.client=new Client(uri);
    }

    /**
     * send Microphone streaming  data to library
     * @throws InterruptedException
     * @throws URISyntaxException
     * @throws LineUnavailableException
     */
    public void startMicrophone() throws InterruptedException, URISyntaxException, LineUnavailableException {
        microphone = (TargetDataLine) AudioSystem.getLine(info);
        microphone.open(format);
        microphone.start();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int numBytesRead;
        int CHUNK_SIZE =1024;
        int bytesRead = 0;

        byte[] b = new byte[1024];
        
        this.client.connectBlocking();
        System.out.println("client server connection established");
        
        JSONObject outer=new JSONObject();
        JSONObject conf=new JSONObject();
        outer.put("config",conf.put("sample_rate",sampleRate));
        //  outer.put("config",conf.put("num_channels", 1));
        client.send(outer.toString());

        while (bytesRead <= 100000000) {
            numBytesRead = microphone.read(b, 0, CHUNK_SIZE);
            bytesRead += numBytesRead;

            /**
             * Send byte aaray
             */
            client.send(b);

        }

    }
}
