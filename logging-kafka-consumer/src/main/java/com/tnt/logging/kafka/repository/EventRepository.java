package com.tnt.logging.kafka.repository;

import com.tnt.logging.kafka.model.EventModel;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CassandraRepository<EventModel, String> {
}
