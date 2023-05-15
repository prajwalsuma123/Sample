package org.example;

import org.example.client.AudioFile;
import org.example.client.Microphone;

/**
 * Entry point on customer end
 * 1. for Microphone just comment Audio file code.
 * 2. for Audio file just comment Microphone code.
 */
public class Main {


    public static void main(String[] args) throws Exception {

        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("java.version"));

        Microphone microphone=new Microphone();
        microphone.startMicrophone();

//        AudioFile file=new AudioFile();
//        file.sendAudio();

    }
}
