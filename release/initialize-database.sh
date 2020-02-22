CQLSH_USER="cassandra"
CQLSH_PASS="cassandra"
while getopts u:p: option
do
  case "${option}"
    in
      u) CQLSH_USER=${OPTARG};;
      p) CQLSH_PASS=${OPTARG};;
  esac
done

cqlsh -u $CQLSH_USER -p $CQLSH_PASS -e "DROP KEYSPACE IF EXISTS pollfish_logging;"
cqlsh -u $CQLSH_USER -p $CQLSH_PASS -e "CREATE KEYSPACE pollfish_logging WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 1 };"
cqlsh -u $CQLSH_USER -p $CQLSH_PASS -e "USE pollfish_logging;\
CREATE TABLE Event (\
  id text PRIMARY KEY,\
  v smallint,\
  time timestamp,\
  m text,\
  comment text\
);"