package org.auvua.logging;

import org.auvua.model.Model;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggingHandler extends Handler {

    public LoggingHandler() {
        super();
        Logger.getLogger("LUMBERJACK").addHandler(this);
    }

    @Override
    public void publish(LogRecord record) {
        Model.getInstance().addLogRecord(record);
        //TODO: something more permanent
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }

}
