FROM openjdk:8-jre-alpine

ADD /fuego-de-quasar-app/build/libs/fuego-de-quasar-app.jar app.jar

ENTRYPOINT [ "sh", "-c", "java -jar -Duser.timezone=America/Mexico_City -Dnetworkaddress.cache.ttl=60 -Dnetworkaddress.cache.negative.ttl=10 /app.jar" ]

