docker-compose down
docker-compose up -d
docker exec -it learnflink_kafka_1 kafka-topics --create --topic new_topic --bootstrap-server localhost:9092