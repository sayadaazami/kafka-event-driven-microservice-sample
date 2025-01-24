# ▶️ `Install`

## 🙌 Manual

> visit https://kafka.apache.org/downloads

### Single server

```bash
# config the cluster
./bin/kafka-storage.sh random-uuid
./bin/kafka-storage.sh format -t [UUID] -c config/kraft/server.properties


# start the server
./bin/kafka-server-start.sh config/kraft/server.properties
```

### Multiple server

```bash
cp ./config/kraft/server.properties  ./config/kraft/server_1.properties
cp ./config/kraft/server.properties  ./config/kraft/server_2.properties
cp ./config/kraft/server.properties  ./config/kraft/server_3.properties

# open them and change
#     node.id
#       1 => node.id=1
#       2 => node.id=2
#       3 => node.id=3
#     listeners
#       1 => listeners=PLAINTEXT://:9092,CONTROLLER://:9093
#       2 => listeners=PLAINTEXT://:9094,CONTROLLER://:9095
#       3 => listeners=PLAINTEXT://:9096,CONTROLLER://:9097
#     controller.quorum.voters
#       1 => controller.quorum.voters=1@localhost:9093,2@localhost:9095,3@localhost:9097
#       2 => controller.quorum.voters=1@localhost:9093,2@localhost:9095,3@localhost:9097
#       3 => controller.quorum.voters=1@localhost:9093,2@localhost:9095,3@localhost:9097
#     advertised.listeners=PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093
#       1 => advertised.listeners=PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093
#       2 => advertised.listeners=PLAINTEXT://localhost:9094,CONTROLLER://localhost:9095
#       3 => advertised.listeners=PLAINTEXT://localhost:9096,CONTROLLER://localhost:9097
#     log.dirs
#       1 => log.dirs=/tmp/server_1/kraft-combined-logs
#       2 => log.dirs=/tmp/server_2/kraft-combined-logs
#       3 => log.dirs=/tmp/server_3/kraft-combined-logs

./bin/kafka-storage.sh random-uuid
./bin/kafka-storage.sh format -t [UUID] -c config/kraft/server_1.properties
./bin/kafka-storage.sh format -t [UUID] -c config/kraft/server_2.properties
./bin/kafka-storage.sh format -t [UUID] -c config/kraft/server_3.properties

# start the server
./bin/kafka-server-start.sh config/kraft/server_1.properties
./bin/kafka-server-start.sh config/kraft/server_2.properties
./bin/kafka-server-start.sh config/kraft/server_3.properties
```

### Stop server(s)

```bash
# to avoid losing data before shutdown do not use ctrl+C instead use
./bin/kafka-server-stop.sh
```

## 🐳 Docker

### Install

```bash
docker pull apache/kafka:3.9.0
docker run -p 9092:9092 --network kafka --name kafka_server apache/kafka:3.9.0

docker exec -it kafka_server sh
cd /opt/kafka/bin
```


## 📈 Admin panel
> visit https://www.kadeck.com/get-kadeck


<!--












 -->
<hr class="page-break"/>

# 🔳 `CLI Commands`

> ⚠️ to avoid losing data before shutdown do not use `ctrl+C` instead use  
> `./bin/kafka-server-stop.sh`

```bash
# Get Cluster Id
./kafka-cluster.sh 
    cluster-id
    --bootstrap-server :9092


# Create a topic
./kafka-topics.sh
    --create
    --topic test-topic
    --partitions 3
    --replication-factor 3 # should be <= brokers
    --config "min.insync.replicas=3" # how many acks is required
    --bootstrap-server :9092,:9094,:9096

# Create a topic
./kafka-topics.sh -list --bootstrap-server :9092


# Produce a message
./kafka-console-producer.sh 
    --topic test-topic
    --bootstrap-server :9092


# Consume messages
./kafka-console-consumer.sh
    --group group1
    --from-beginning
    --topic test-topic
    --bootstrap-server :9092 
```
