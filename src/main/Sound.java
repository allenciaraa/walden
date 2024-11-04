package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/ongoldenpie.wav");
        soundURL[1] = getClass().getResource("/sound/drunkwalk.wav");
        soundURL[2] = getClass().getResource("/sound/gofast.wav");
        soundURL[3] = getClass().getResource("/sound/fallingdown.wav");
        soundURL[4] = getClass().getResource("/sound/pickup.wav");
        soundURL[5] = getClass().getResource("/sound/goldenpiefast.wav");
        soundURL[6] = getClass().getResource("/sound/goldenpiefaster.wav");
        soundURL[7] = getClass().getResource("/sound/youwon.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }




}
