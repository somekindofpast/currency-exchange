docker network rm currency-network
docker network create currency-network
docker stop currency-db
docker run -it --rm --name currency-db --network=currency-network -e POSTGRES_DB=currency_exchange_innodox -e POSTGRES_USER=currency_exchange_innodox_user -e POSTGRES_PASSWORD=currency_exchange_innodox_pass -p 5433:5432 postgres:14.3-alpine