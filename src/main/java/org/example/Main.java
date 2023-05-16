package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.AudioFile;
import org.example.client.Microphone;

import java.io.File;
import java.net.URI;

/**
 * Entry point on customer end
 * 1. for Microphone just comment Audio file code.
 * 2. for Audio file just comment Microphone code.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        String serveruri=null;
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("java.version"));

        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile=new File("/home/prajwal.sonawane/Desktop/Sample/config.json");
        JsonNode rootNode = objectMapper.readTree(jsonFile);

        JsonNode arrayNode = rootNode.get("Server");
        for (JsonNode objNode : arrayNode) {
            serveruri= String.valueOf(objNode.get("URI"));
        }
        serveruri=serveruri.replace("\"", "");

        URI uri=(new URI(serveruri));

        Microphone microphone=new Microphone(uri);
        microphone.startMicrophone();

//        AudioFile file=new AudioFile(uri);
//        file.sendAudio();

    }
}
