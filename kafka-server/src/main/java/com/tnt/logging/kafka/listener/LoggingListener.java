package com.tnt.logging.kafka.listener;

import com.tnt.logging.shared.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LoggingListener {

	@KafkaListener(topics = "logging", groupId = "logging")
	public void listen(ConsumerRecord<String, Event> cr) {
		System.out.println("Received message -> time: " + cr.value().getTime() + ", event: " + cr.value().getM());
	}
}
