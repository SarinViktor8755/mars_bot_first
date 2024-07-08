package org.example;
import java.util.Date;


public class Event {
    String data_event;
    String description;
    String uti;


    public Event(String data_event, String description) {
        this.data_event = data_event;
        this.description = description;
    }

    public Event(String data_event, String description, String uti) {
        this.data_event = data_event;
        this.description = description;
        this.uti = uti;
    }

    public String getUti() {
        return uti;
    }
}
