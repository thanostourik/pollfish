package com.tnt.logging.kafka.model;

import com.tnt.logging.shared.LogEvent;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;

@Data
@Table("Event")
public class EventModel {

	@PrimaryKey
	private String id;

	public int v;
	public Timestamp time;
	public LogEvent m;
	public String comment;
}