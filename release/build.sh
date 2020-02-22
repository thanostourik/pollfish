../mvnw -f ../logging-thrift-shared/pom.xml install
../mvnw -f ../logging-thrift-client/pom.xml package
../mvnw -f ../logging-thrift-server/pom.xml package
../mvnw -f ../logging-kafka-consumer/pom.xml package