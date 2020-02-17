# pollfish

### Start kafka
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```
#### In the first run create topic
```bash
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic logging
```

### Generate thrift code
```bash
thrift -gen java -out logging-shared/src/main/java logging.thrift
```
### Install thrift shared lib in local maven repo
```bash
cd logging-shared && mvn install && cd ..
```

### Package applications into jars
```bash
./mvnw -f logging-client/pom.xml package
./mvnw -f logging-server/pom.xml package
./mvnw -f kafka-server/pom.xml package
```

### Run servers
```bash
java -jar logging-server/target/logging-server-1.0.jar &
java -jar logging-server/target/kafka-server-1.0.jar &
java -jar logging-server/target/logging-client-1.0.jar
```