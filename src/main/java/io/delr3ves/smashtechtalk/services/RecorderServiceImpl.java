package io.delr3ves.smashtechtalk.services;

import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;

import javax.inject.Inject;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class RecorderServiceImpl implements RecorderService {

    private HttpClient client;

    private final static String START_PATH = "/rec/start";
    private final static String STOP_PATH = "/rec/save/{0}";
    private final static String REJECT_PATH = "/rec/stop";

    @Inject
    public RecorderServiceImpl(HttpClient client) {
        this.client = client;
    }

    @Override
    public void startRecording() {
        callToRecorderServer(START_PATH);
    }


    @Override
    public String stopRecording(String name) {
        String file = URLEncoder.encode(name);
        callToRecorderServer(MessageFormat.format(STOP_PATH, file));
        return name + ".mov";
    }

    @Override
    public void rejectRecording() {
        callToRecorderServer(REJECT_PATH);
    }

    private HttpResponse callToRecorderServer(String path) {
        try {
            HttpRequest startRecordingRequest = new HttpRequest(HttpMethod.GET, path);
            return client.execute(startRecordingRequest, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
