# Challenge Literalura

Este challenge usa una api para obtener informacion de libros y autores, estos son guardados en una base de datos postgres, y con esa informacion se realizan varias consultas.
https://github.com/MiguelSanchezG/Challenge-Literalura


## Tabla de contenido

- [Instalación](#instalación)
- [Uso](#uso)

  
## Instalación
1. Para este caso no es realmente necesaria una instalacion, se puede simplemente copiar el repositorio en la carpeta de tu preferencia:
   ```bash
   git clone https://github.com/MiguelSanchezG/Challenge-Literalura.git
2. puede verificar los archivos y el código abriendo el proyecto con IntelliJ o el IDE de tu preferencia.
3. Es necesario tener pgAdmin, esto para manejar la base de datos.

## Uso

1. Primero es necesario crear la base de datos, esto para evitar algun error al compilar la aplicacion. solo hay que ir al pgAdmin, luego se da click derecho a la opcion postgreSQL17, esto muestra un pequeño menu, donde seleccionaremos Create, y seleccionamos Database.

![CreandoBase](./imagenes/creandoBase.jpg)

2. Nos saltara una pequeña ventana, y simplemente pondremos el nombre, que en este caso seria literalura y le damos a save

![nombreBase](./imagenes/nombreBase.jpg)

3. tambien se muestran ciertas consultas que pueden servir, para reiniciar la base de datos de cero, y sobretodo verificar informacion

![algunosComandos](./imagenes/algunosComandos.jpg)

4. por ultimo vamos a abrir el proyecto con nuestra IDE de preferencia, en este caso se usa IntelliJ, luego buscamos el archivo application.properties, esto para definir cierta informacion necesaria, en este caso, para la prueba, se puede reemplazar.
   +{DB_HOST} por localhost:8080
   +{DB_NAME} por literalura
   +{DB_USER} por el usuario que definimos en al iniciar por primera vez el pgAdmin
   +{DB_PASSWORD} por la contraseña que definimos con ese nombre

![configuracionDB](./imagenes/configuracionDB.jpg)

5. Ahora volvemos a las carpetas del proyecto y buscamos la clase LiteraluraApplication

![claseProyecto](./imagenes/claseProyecto.jpg)

6. Entonces le daremos click derecho en la clase y buscamos la opcion que dice Run

![iniciarApp](./imagenes/iniciarApp.jpg)

7. y ahi empezara la app con las siguientes 6 opciones.

![menu](./imagenes/menu.jpg)

8. La primera opcion nos da la opcion de poner el nombre del libro que deseamos buscar, y nos muestra su informacion relacionada.

![Consulta1](./imagenes/Consulta1.jpg)
![Resultado1](./imagenes/Resultado1.jpg)

9. la segunda opcion nos muestra todos los libros que llevamos guardando

![resultado2](./imagenes/resultado2.jpg)

10. la tercera nos muestra los autores y sus libros

![resultado3](./imagenes/resultado3.jpg)

11. con la cuarta se busca con la fecha proporcionada que autores estaban vivos en esa epoca

![Consulta4](./imagenes/Consulta4.jpg)
![resultado4](./imagenes/resultado4.jpg)

12. con la quinta opcion se busca segun el idioma

![Consulta5](./imagenes/Consulta5.jpg)
![resultado5](./imagenes/resultado5.jpg)

13. y la ultima opcion cerraria la app

![cerrando](./imagenes/cerrando.jpg)
