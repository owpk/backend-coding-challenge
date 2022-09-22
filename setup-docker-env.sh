docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.17.6

docker run --name geonames-db -p 5432:5432 -e POSTGRES_PASSWORD=postgres --rm -d postgres
sleep 6
./gradlew flywayMigrate
./gradlew generateJooq