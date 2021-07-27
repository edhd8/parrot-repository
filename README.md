# Parrot 
## _Backend Coding Challenge_ ![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)

Se tiene que crear una API que será consumida por un conjunto de clientes
(móviles y web). El API será consumida por una base de 1000 usuarios con uso constante para la
creación de órdenes. Tiene que contener los siguientes servicios web, almacenando los datos
necesarios para su correcto funcionamiento.

[![Parrot Software](https://pos.parrotsoftware.io/wp-content/uploads/2021/06/Parrot_Illustration_BannerP-1.gif)](https://pos.parrotsoftware.io/)

## Requerimientos de negocio

Creación de **usuarios**: Los usuarios no se podrán modificar una vez creados.
- Email (Llave única)
- Nombre
- No es necesario un password para el manejo de usuarios

(Opcional) Obtener credenciales para consulta de todos los servicios que
dependan del usuario. Todos los servicios web relacionados a un usuario
deben estar protegidos por algún estándar de Autenticación.

Crear **órdenes** para un usuario: Las ordenes serán creadas por él mismo.
- Nombre del cliente externo quien pidió la orden
- Precio total de la orden
- Lista de productos que conforman la orden

Las órdenes deben contener la lista de **productos** que la conforman. Los
productos son creados por el usuario al mismo tiempo que se crea la orden.
Productos con el mismo nombre serán considerados como iguales.
- Nombre del producto
- Precio unitario
- Cantidad

Reporte de productos vendidos.
- Filtrado por fecha (inicio y fin)
- Ordenado por producto de mayor a menor vendido
- Las columnas necesarias para el reporte son nombre del producto,
  cantidad total y precio total

Entidades de la base de datos:

> User 👤 table name: [users], user is a reservate word.
> 
> Order 📦 table name: [order]
>
> Product Catalog 💻 table name: [product_catalog]

![image info](D:\Aplicaciones\Parrot Challenge\diagrams\smartphone.png)

## Entregables

- Código fuente en repositorio **público** de GitHub https://github.com/edhd8/parrot-challenge
- Documentación sobre cómo ejecutar el programa https://www.getpostman.com/collections/29b354016d3c1b7cf323
![image info](http://www.plantuml.com/plantuml/png/ZP2zQYin48NxUOgfxlKjzcw_HR0n9gG8CVcfbrbfOWkq8qKQXHZZfyeZvCMY9SGcO4CQIHpfpCUdULPAMaOFVxUgCNTj6pt9bFmtOMmplnGMmurrj4aq0b3H86KSE06eKy-uY0uJPyK6dpAdNFUxPAV37rodO8lH310hnnaNIHINth5elrZV_8ZNb5BGVhabxrastDy-W2MbWJ9Z_Bgq0y2fZitLkLQ7rwl73YUZAhHtiOzbyCxGeIsPKxkVrbvev0ESgMDJnnvv7MPwvSbiDjLMKgrt_m38Lv52OWEonv8B9HTmHvWPMKnXqKIugR4b6tx3MRqPZiPzl0kQKDl1aoLWiG37LzXSKlQ45Uagx1jABHydNkIVFhD39y_XddCCadbM5UULNKR_2G00)
  
## Installation

El proyecto ya tiene sus archivos de configuración en orden: application.properties, build.gradle.kts, etc. Si se desea desplegar en ambiente productivo realizar los pasos 1, 6-10, los pasos 2-5 son opcionales si se desea ejecutar el proyecto en ambiente local. El paso 11 es común en ambos ambientes.

- Paso 1. Descargar la última versión del código de la rama develop https://github.com/edhd8/parrot-challenge.git
- Paso 2. Compilar el proyecto con Ctrl+F9, correspondiente a la opción 'build project' esperando el resultado
```sh
BUILD SUCCESS
```
- Paso 3. Configurar la ejecución del proyecto de la siguiente forma:
  ![image info](D:\Aplicaciones\Parrot Challenge\diagrams\run configuration parrot.png)
- Paso 4. Instalar postgreSQL y crear BD **db_parrot** de manera local
- Paso 5. Si se desea ejecutar en ambiente local simplemente dar click en Run 'Application'
- Paso 6. En la pestaña de Gradle, ejecutar la tarea "bootJar" dentro de la capeta build
- Paso 7. Si se va a desplegar en ambiente productivo, construir la imagen del proyecto

```sh
docker build --tag {user_docker_hub}/parrot-challenge:{version} .
```
- Paso 8. Subir la imagen a Docker Hub

```sh
docker push {user_docker_hub}/parrot-challenge:{version}
```
![image info](D:\Aplicaciones\Parrot Challenge\diagrams\docker.png)

- Paso 9. Modificar archivo docker-compose.yml version previamente generada
- Paso 10. Levantar el contenedor de docker

![image](https://user-images.githubusercontent.com/67127741/120374666-73ba0b00-c2df-11eb-9542-799b192960ca.png)

```sh
docker-compose up -d
```

- Paso 11. Probar desde Postman la ejecución de los flujos

![image info](D:\Aplicaciones\Parrot Challenge\diagrams\postman.png)

![image](https://user-images.githubusercontent.com/67127741/124666499-bf7a4a00-de73-11eb-9b42-4043a98a82fe.png)

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