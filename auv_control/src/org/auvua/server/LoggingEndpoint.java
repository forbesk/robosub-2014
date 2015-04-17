package org.auvua.server;

import org.auvua.model.Model;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.simple.JSONValue;
import sun.util.logging.PlatformLogger;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggingEndpoint extends WebSocketServer  {

    private HashSet<WebSocket> connections;

    public LoggingEndpoint(InetSocketAddress address) {
        super(address);
        connections = new HashSet<WebSocket>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(connections.size() > 0) {
                        for (LogRecord r : Model.getInstance().getLogQueue()) {
                            String msg = JSONValue.toJSONString(formatMessage(r));
                            for (WebSocket w : connections) {
                                w.send(msg);
                            }
                            Model.getInstance().getLogQueue().remove(r);
                        }
                    }
                }
            }
        }).start();
    }

    @Override
    public void onOpen(WebSocket ws, ClientHandshake ch) {
        connections.add(ws);
    }

    @Override
    public void onMessage(WebSocket ws, String msg) {

    }

    @Override
    public void onError(WebSocket ws, Exception e) {

    }

    @Override
    public void onClose(WebSocket ws, int arg1, String arg2, boolean arg3) {
        connections.remove(ws);
    }

    public Map<String, Object> formatMessage(LogRecord record) {
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("timestamp", record.getMillis());
        msg.put("source", record.getSourceClassName() + ":" + record.getSourceMethodName());
        msg.put("level", record.getLevel());
        msg.put("message", record.getMessage());

        return msg;
    }

}
