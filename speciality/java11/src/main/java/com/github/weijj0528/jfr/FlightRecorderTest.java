package com.github.weijj0528.jfr;

import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;

/**
 * 飞行记录器测试
 * -XX:StartFlightRecording=duration=1s, filename=recording.jfr
 * @author William.Wei
 */
public class FlightRecorderTest extends Event {
    @Label("Hello World")
    @Description("Helps the programmer getting started")
    static class HelloWorld extends Event {
        @Label("Message")
        String message;
    }

    public static void main(String[] args) {
        HelloWorld event = new HelloWorld();
        event.message = "hello, world!";
        event.commit();
    }
}
