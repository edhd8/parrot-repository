FROM openjdk:8-jre-alpine

RUN mkdir /code
COPY fuego-de-quasar-app/build/libs /code

ENTRYPOINT [ "sh", "-c", "java -jar -Duser.timezone=America/Mexico_City -Dnetworkaddress.cache.ttl=60 -Dnetworkaddress.cache.negative.ttl=10 /code/fuego-de-quasar-app-boot.jar" ]