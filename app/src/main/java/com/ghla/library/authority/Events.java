package com.ghla.library.authority;

import org.greenrobot.eventbus.EventBus;

public class Events {
    public Report report;
    // Message is to identify the type of the event
    public String message;

    Events(Report report, String message){
        this.report = report;
        this.message = message;
    }

}
