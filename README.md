# CHALLENGE-BACKEND---Java-Spring-Boot-API-
Challenge de Alkemy

Use para realizar el challenge:
  
  IDE Visual Studio Code,
  Version Java 8,
  MySQL Workbench para la base de datos,
  El archivo de properties esta configurado para conectarse al workbench con usuario y contraseña "root",
  Postman para probar los endpoints.

Lo primero que hay que hacer es crear la base de datos:

  drop database if exists `db-challenge`;
  create database if not exists `db-challenge`;

Luego iniciar la aplicacion de Spring, una vez que inicio en el workbench insertar los roles:

  insert into roles(name) values('ROLE_USER');
  insert into roles(name) values('ROLE_ADMIN');

En la carpeta del proyecto esta el archivo Script Base de Datos.sql con las sentencias.
Tambien en la carpeta esta la documentacion de Postman con los endpoints.
Abrir Postman e importar el archivo Challenge BACKEND - Java Spring Boot(API).postman_collection.json
Para probar los endpoints primero usar el POST endpoint auth/register para registrarse, luego el POST endpoint auth/login.
Este devuelve un token, para usar los demas endpoints en header poner en KEY: Authorization y en VALUE: Bearer + el token generado en el login.

Por ultimo, me falto poner las imagenes y hacer el punto 11.
