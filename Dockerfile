FROM openjdk:8-jre-alpine

ADD /parrot-challenge-app/build/libs/parrot-challenge-app.jar app.jar

ENTRYPOINT [ "sh", "-c", "java -jar -Duser.timezone=America/Mexico_City -Dnetworkaddress.cache.ttl=60 -Dnetworkaddress.cache.negative.ttl=10 /app.jar" ]

