package com.tnt.logging.server;

import com.tnt.logging.shared.Event;
import com.tnt.logging.shared.LoggingService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoggingThriftServer {

	public static LoggingThriftHandler handler;
	public static LoggingService.Processor processor;

	/**
	 * Constructor
	 *
	 * @param kafkaTemplate
	 */
	@Autowired
	public LoggingThriftServer(KafkaTemplate<String, Event> kafkaTemplate) {

		handler = new LoggingThriftHandler(kafkaTemplate);
		processor = new LoggingService.Processor(handler);

		Runnable simple = () -> simple(processor);
		new Thread(simple).start();
	}

	/**
	 *
	 * @param processor
	 */
	public static void simple(LoggingService.Processor processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

			System.out.println("LoggingThriftServer is listening...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}