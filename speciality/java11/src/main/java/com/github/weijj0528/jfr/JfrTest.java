package com.github.weijj0528.jfr;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 飞行记录器 文件分析
 *
 * @author William.Wei
 */
public class JfrTest {
    public static void main(String[] args) throws Exception {
        final Path path = Paths.get("recording.jfr");
        final List<RecordedEvent> recordedEvents = RecordingFile.readAllEvents(path);
        for (RecordedEvent event : recordedEvents) {
            System.out.println(event.getStartTime() + "," + event.getValue("message"));
        }
    }
}
