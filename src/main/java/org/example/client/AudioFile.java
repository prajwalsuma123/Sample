package org.example.client;
import com.sumasoft.stt.audio.AcceptAudio;
import org.json.JSONObject;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Read the Audio file(.wav) and send to ASR(SDK)
 * Send streaming data 
 */
public class AudioFile {

    public String wavFilePath = "/home/prajwal.sonawane/Desktop/shivaji.wav";
    public AcceptAudio acceptAudio;
    int sampleRate;

    public AudioFile() throws Exception {
    }


    /**
     * send audiostreaming data to library
     */
    public void sendAudio(){

        try {
            // Load the .wav file
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(wavFilePath));

            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Get the audio format from the file
            AudioFormat audioFormat = audioInputStream.getFormat();
            sampleRate= (int) audioFormat.getSampleRate();

            // Open the audio line for playback
            SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);
            line.open(audioFormat);

            // Start playback
            line.start();

            System.out.println("client server connection established");

            this.acceptAudio=new AcceptAudio(sampleRate);

            // Read audio data from the file and play it back
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
                acceptAudio.send(buffer);
            }

            // Wait for playback to complete
            line.drain();

            // Close the audio line
            line.close();
            audioInputStream.close();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

