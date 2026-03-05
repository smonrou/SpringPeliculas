# Repositorio para entrega de laboratorio No.3

- Nombre: Saúl Andrés Monroy Marroquín
- Carnet: 202140974

Diseñar una pequeña base de datos en MySQL con una tabla que contenga información sobre
películas. Además desarrolle una API RESTful con Spring Boot, que acceda a los registros de la
tabla. La conexión con base de datos deberá realizarse exclusivamente por JDBCTemplate.

## Pruebas de funcionamiento

A continuación se muestra la tabla mostrada en el enunciado del laboratorio pero con la URL
funcional, solo para pegar en Thunder Client.

| Método | Ruta | Descripción | Código Http |
| :---: | :---: | :---: | :---: |
| GET | <http://localhost:8080/api/peliculas?page=3&size=20> | Lista Paginada | 200 |
| GET | <http://localhost:8080/api/peliculas/buscar?nombre=Escuadrón> | Búsqueda por Nombre | 200 |
| GET | <http://localhost:8080/api/peliculas/count> | Total de Registros | 200 |
| POST | <http://localhost:8080/api/peliculas> | Crea películas | 201 400 |
| PUT | <http://localhost:8080/api/peliculas/3> | Actualiza Película{id=3} | 200 404 |
| DELETE | <http://localhost:8080/api/peliculas/100> | Elimina Película{id=3} | 200 404 |

## Video

<https://youtu.be/c921vG8atZI>

## Recordatorio

Script SQL se encuentra en **src\main\resources\SQL\dbPelicula.sql**.

Recodar ir a aplication.properties y **Cambiar el puerto, usuario y contraseña.**

## Aclaración

Los inserts del Script SQL fueron generados a través de AI con la finalidad de tener muchos datos para pruebas.

### Fecha de Entrega: 6 de marzo de 2026
