curl -i -X POST -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8083/connectors -d @todos-service-debezium-connector.json
curl -i -X POST -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8083/connectors -d @tasks-service-debezium-connector.json
