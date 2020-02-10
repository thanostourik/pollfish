package com.tnt.logging.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class LoggingClient {

	public static void main(String [] args) {
		try {
			TTransport transport;

			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new  TBinaryProtocol(transport);
			LoggingService.Client client = new LoggingService.Client(protocol);

			perform(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	private static void perform(LoggingService.Client client) throws TException {
		System.out.println("Call log method");
		client.log(1,"2020-02-11", "This is a log event");
	}
}