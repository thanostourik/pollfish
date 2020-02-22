../mvnw -f ../logging-shared/pom.xml install
../mvnw -f ../logging-client/pom.xml package
../mvnw -f ../logging-server/pom.xml package
../mvnw -f ../kafka-server/pom.xml package