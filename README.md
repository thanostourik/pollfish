# Overview
This is an aggregation/ingestion system for loggin events. A thrift client sends randomly generated logging events to a thrift server. The thrift server pushes them on to Kafta. Finally, the kafka server writes them to a Cassandra database.

## Prerequisites
The following are considered installed on the system that is going to build and run the application.
- JDK 1.8
- Kafka 2.4.0
- Cassandra 3.11.6
#### Start kafka
Zookeeper Server and Kafka Server should be running before continuing.
Considering you have downloaded and extracted kafka you can run the following to start them.
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```
#### Generate thrift code
Thrift has already been run and the code has been generated in logging-shared.
If you want to manually generate thrift client and server again you can run the following.
```bash
thrift -gen java -out logging-thrift-shared/src/main/java logging.thrift
```

## First steps / Preparations
#### LoggingServer config
If you don't use default settings setup kafka host and port in file: `logging-thrift-server/src/main/resources/application.properties`
```properties
kafka.bootstrapAddress=localhost:9092
```
#### KafkaConsumer config
If you don't use default settings setup kafka host and port in file: `logging-thrift-server/src/main/resources/application.properties`
```properties
kafka.bootstrapAddress=localhost:9092
```
Setup cassandra settings in file: `logging-thrift-server/src/main/resources/application.properties`
```properties
spring.data.cassandra.contact-points=127.0.0.1
spring.data.cassandra.port=9042
spring.data.cassandra.username=cassandra
spring.data.cassandra.password=cassandra
```
#### Create the kafka topic
Zookeeper and Kafka server should be running
```bash
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic logging
```
#### Run database initialization script
```bash
chmod +x release/initialize-database.sh
release/initialize-database.sh -u <user> -p <password>
```

## Build & Run
Every time you run the client it will send a randomly generated LogEvent
```bash
chmod +x release/*.sh
release/build.sh
release/run-servers.sh
release/run-client.sh
```

## Build & Run manually
#### Install thrift shared lib in local maven repo
```bash
./mvnw -f logging-shared/pom.xml install
```
#### Package applications into jars
```bash
./mvnw -f logging-thrift-client/pom.xml package
./mvnw -f logging-thrift-server/pom.xml package
./mvnw -f logging-kafka-consumer/pom.xml package
```
#### Run servers
```bash
java -jar logging-thrift-server/target/logging-thrift-server-1.0.jar
java -jar logging-kafka-consumer/target/logging-kafka-consumer-1.0.jar
java -jar logging-thrift-client/target/logging-thrift-client-1.0-jar-with-dependencies.jar
```