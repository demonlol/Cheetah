package sbs.baka.cheetah.audio;

import javax.sound.sampled.*;
import java.io.*;

public class AudioFileProcessor {

    public static void main(String[] args) {
        copyAudio("/tmp/uke.wav", "/tmp/uke-shortened.wav", 2, 1);
    }

    public static void copyAudio(String sourceFileName, String destinationFileName, int startSecond, int secondsToCopy) {
        AudioInputStream inputStream = null, shortenedStream = null;

        try {
            File file = new File(sourceFileName);
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
            AudioFormat format = fileFormat.getFormat();
            inputStream = AudioSystem.getAudioInputStream(file);
            int bytesPerSecond = format.getFrameSize() * (int) format.getFrameRate();
            inputStream.skip((long) startSecond * bytesPerSecond);
            long framesOfAudioToCopy = (long) secondsToCopy * (int) format.getFrameRate();
            shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
            File destinationFile = new File(destinationFileName);
            AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
        } catch (Exception e) {
            println(e);
        } finally {
            if (inputStream != null) try {
                inputStream.close();
            } catch (Exception e) {
                println(e);
            }
            if (shortenedStream != null) try {
                shortenedStream.close();
            } catch (Exception e) {
                println(e);
            }
        }
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void print(Object o) {
        System.out.print(o);
    }
}