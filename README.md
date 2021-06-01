# Operación Fuego de Quasar
## _Challenge Mercado Libre_ ![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)

Han Solo ha sido recientemente nombrado General de l a Alianza Rebelde y busca dar un gran golpe contra el Imperio Galáctico para reavivar la llama de la resistencia. El servicio de inteligencia rebelde ha detectado un l lamado de auxilio de una nave portacarga i mperial a l a deriva en un campo de asteroides. El manifiesto de l a nave es ultra clasificado, pero se rumorea que transporta raciones y armamento para una legión entera.

[![Mercado Libre](https://http2.mlstatic.com/frontend-assets/ui-navigation/5.14.5/mercadolibre/180x180.png)](https://www.mercadolibre.com.mx/)

## Desafío

Como jefe de comunicaciones rebelde, tu misión es crear un programa en Golang que retorne la fuente y contenido del mensaje de auxilio. Para esto, cuentas con tres satélites que te permitirán triangular l a posición, ¡pero cuidado! el mensaje puede no l legar completo a cada satélite debido al campo de asteroides frente a la nave.

Posición de los satélites actualmente en servicio:

> Kenobi: [-500, -200] 🛰️:
> Skywalker: [100, -100] 🛰️:
> Sato: [500, 100] 🛰️:

## Entregables

- Código fuente en repositorio **privado** de GitHub
- Documentación que indique cómo ejecutar el programa https://www.getpostman.com/collections/cf02d2aee0cd45152d74
- Documentación del proyecto que considere importante

![image](https://user-images.githubusercontent.com/67127741/120262871-34000e80-c260-11eb-9f2d-bb72743308fe.png)

- URL en donde este hosteado el servicio https://challenge.engicoders.com/
- Contemplar buenas prácticas (tip: imaginar que estas poniendo una aplicación productiva)

## Installation

El proyecto ya tiene sus archivos de configuración en orden: application.properties, build.gradle.kts, etc.

Paso 1. Descargar la última versión del código de la rama master https://github.com/edhd8/fuego-de-quasar.git
Paso 2. En la pestaña de Gradle, ejecutar la tarea "bootJar"
Paso 3. Construir la imagen del proyecto

```sh
docker build --tag {user_docker_hub}/fuego-de-quasar:{version} .
```
Paso 4. Modificar archivo docker-compose.yml version previamente generada
Paso 5. Levantar localmente el contenedor de docker

```sh
docker-compose up -d
```

Paso 6. Probar desde Postman la ejecución de los flujos locales en el puerto 8088

## Tech

Las tecnologías empleadas en el desarrollo fueron las siguientes.

| Tecnología o herramienta | Enlace | Versión |
| ------ | ------ | ------ |
| Kotlin | https://kotlinlang.org/ | 1.3.71 |
| Docker Hub | https://hub.docker.com/ | 20.10.6 |
| Spring Boot | https://spring.io/projects/spring-boot | 2.2.4.RELEASE |
| PostgreSQL | https://www.postgresql.org/ | 13.3 |
| MarkDown | https://dillinger.io/ | |
| Git Hub | https://github.com/ | |
| AWS | https://aws.amazon.com/es/ | |

## Gitflow

![image](https://user-images.githubusercontent.com/67127741/120264051-935f1e00-c262-11eb-812a-7a65c24654c0.png)

