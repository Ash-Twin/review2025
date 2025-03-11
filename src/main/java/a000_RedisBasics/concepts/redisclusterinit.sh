# replace one of your redis node container id
docker exec -it e9fea93b710b8faa1d4693a076fb08822c0aadef5efebb8e840641f22311ea3c redis-cli --cluster create 172.38.0.11:6379 172.38.0.12:6379 172.38.0.13:6379 172.38.0.14:6379 172.38.0.15:6379 172.38.0.16:6379 --cluster-replicas 1

