docker stop $(docker ps -aq)
docker compose rm
docker compose up > docker.log &
sleep 10
./gradlew flywayClean
./gradlew flywayMigrate
./gradlew generateJooq
./gradlew bootRun
