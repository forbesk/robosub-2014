package org.auvua.model;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class HardwareConsumer {
	
	public static void main( String[] args ) {
		
		Context ctx = ZMQ.context(1);
		Context ctx2 = ZMQ.context(1);
		
		Socket sub = ctx.socket(ZMQ.PAIR);
		sub.bind("tcp://127.0.0.1:5559");
		
		Socket pub = ctx2.socket(ZMQ.PAIR);
		pub.connect("tcp://127.0.0.1:5559");

		pub.send("Hello");
		pub.send("Dude");
		pub.send("Text");
		while(true) {
			Object obj = sub.recvStr(0);
			System.out.println(obj);
		}
	}
	
}
