package io.delr3ves.smashtechtalk.services;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public interface RecorderService {

    void startRecording();

    String stopRecording(String name);

    void rejectRecording();
}
