package com.tnt.logging.server;

import org.apache.thrift.TException;

public class LoggingHandler implements LoggingService.Iface {

	public void log(int v, String time, String m) throws TException {
		System.out.println("log(" + v + "," + time + "," + m + ")");
	}
}