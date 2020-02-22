package com.tnt.logging.server;

import com.tnt.logging.shared.Event;
import com.tnt.logging.shared.LoggingService;
import org.apache.thrift.TException;
import org.springframework.kafka.core.KafkaTemplate;

public class LoggingHandler implements LoggingService.Iface {

	private KafkaTemplate<String, Event> kafkaTemplate;

	/**
	 *
	 * @param kafkaTemplate
	 */
	public LoggingHandler(KafkaTemplate<String, Event> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void log(Event event) throws TException {
		System.out.println("LoggingServer: Received event and pushing to kafka: " + event);

		kafkaTemplate.send("logging", event);
	}
}