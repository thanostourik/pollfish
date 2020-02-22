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
import java.util.Random;

public class LoggingThriftClient {

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
		Random random = new Random();
		Integer eventIndex = random.nextInt(LogEvent.values().length) + 1;

		Event event = new Event(
				1,
				ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_DATE_TIME),
				LogEvent.findByValue(eventIndex),
				"This is just a log comment."
		);

		System.out.println("LoggingClient: Sending event: " + event);
		client.log(event);
	}
}