CREATE KEYSPACE pollfish_logging WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 1 };
USER pollfish_logging;
CREATE TABLE Event (
  id text PRIMARY KEY,
  v smallint,
  time timestamp,
  m text,
  comment text
);