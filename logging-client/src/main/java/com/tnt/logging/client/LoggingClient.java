package com.tnt.logging.client;

import com.tnt.logging.shared.Event;
import com.tnt.logging.shared.LogEvent;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import com.tnt.logging.shared.LoggingService;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

		Event event = new Event(
				1,
				ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_DATE_TIME),
				LogEvent.LOG_EVENT_2,
				"This is just a log comment."
		);

		client.log(event);
	}
}