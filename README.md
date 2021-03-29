Для использования базы из compose файла необходимо создать сеть в docker 

```bash
docker network create --subnet 172.18.0.0/16 dev_network
```

Сборка контейнера (из корня)

```bash

docker build -t crud-application/supervisor-backend:1.0.0 .
```

При необходимости cоздание и запуск контейнера из докера (предварительно должна существовать сеть docker network create --subnet 172.18.0.0/16 dev_network)

```bash
docker create --name="supervisor" --network="dev_network" -p 8132:8132 -t crud-application/supervisor-backend:1.0.0
```
