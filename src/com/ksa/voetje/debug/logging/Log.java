package com.ksa.voetje.debug.logging;

import java.util.Date;

public class Log {
    private String log;

    public Log() {
        Date date = new Date();
        log = "Log " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    }

    public void updateLog(String add) {
        log += "\n" + add;
        System.out.println(add);
    }

    public String getLog() {
        return log;
    }
}

