# Getting Started

## Install Kafka locally
[Link](https://medium.com/@Ankitthakur/apache-kafka-installation-on-mac-using-homebrew-a367cdefd273)
## Commands I used
### Start zookeeper
```commandline
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
```
### Start Kafka Service
```commandline
kafka-server-start /usr/local/etc/kafka/server.properties
```

### Create Kafka topic: web-domains
```commandline
kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic web-domains
```
