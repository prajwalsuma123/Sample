package org.example.client;

import com.sumasoft.stt.audio.AcceptStream;
import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;

/**
 * Read Microphone streaming data
 */

public class Microphone {
    public float sampleRate=16000;
    AudioFormat format = new AudioFormat(sampleRate, 16, 1,  true, false);
    public DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

    public TargetDataLine microphone;

    /**
     * AcceptStream is class which contains acceptStream() method
     * which will accept streaming audio data and send to vosk server 
     */
    AcceptStream acceptStream=new AcceptStream((int)sampleRate);

    public Microphone() throws Exception {
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

        while (bytesRead <= 100000000) {
            numBytesRead = microphone.read(b, 0, CHUNK_SIZE);
            bytesRead += numBytesRead;

            /**
             * Send byte aaray
             */
            acceptStream.acceptStream(b);

        }

    }
}
