package com.tnt.logging.kafka.listener;

import com.datastax.driver.core.utils.UUIDs;
import com.tnt.logging.kafka.model.EventModel;
import com.tnt.logging.kafka.repository.EventRepository;
import com.tnt.logging.shared.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Service
public class LoggingListener {

	private EventRepository eventRepository;

	/**
	 * Constructor
	 *
	 * @param eventRepository
	 */
	@Autowired
	public LoggingListener(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@KafkaListener(topics = "logging", groupId = "logging")
	public void listen(ConsumerRecord<String, Event> cr) {

		ZonedDateTime dateTime = ZonedDateTime.parse(cr.value().getTime());
		Timestamp timestamp = Timestamp.from(dateTime.toInstant());

		EventModel event = new EventModel();
		event.setId(UUIDs.timeBased().toString());
		event.setV(cr.value().getV());
		event.setM(cr.value().getM());
		event.setTime(timestamp);
		event.setComment(cr.value().getComment());
		eventRepository.save(event);

		System.out.println("Received message -> time: " + cr.value().getTime() + ", event: " + cr.value().getM());
	}
}
