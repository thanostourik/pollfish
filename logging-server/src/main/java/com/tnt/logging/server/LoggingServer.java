package com.tnt.logging.server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class LoggingServer {

	public static LoggingHandler handler;
	public static LoggingService.Processor processor;

	/**
	 *
	 * @param args
	 */
	public static void main(String [] args) {
		try {
			handler = new LoggingHandler();
			processor = new LoggingService.Processor(handler);

			Runnable simple = () -> simple(processor);

			new Thread(simple).start();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	/**
	 *
	 * @param processor
	 */
	public static void simple(LoggingService.Processor processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

			System.out.println("Logging server is listening...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}