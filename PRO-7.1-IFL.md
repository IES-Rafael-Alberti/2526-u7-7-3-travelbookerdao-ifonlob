# RESPUESTA A PREGUNTAS 

#### Pregunta 1 - ¿Que librería/clases has utilizado para realizar la práctica.? Comenta los métodos mas interesantes

**Librerías principales**:
- `java.time.LocalDateTime`, me ha ayudado a asignar la fecha de creación de cada reserva en un formato reconocido. 
- `kotlin.text.Regex`, gracias a esta librería he podido
- `java.io.File`, me ha permitido trabajar con persistencia de datos a través del almacenamiento de las reservas en archivos.


**Clases explicadas**:
- `ReservaVuelo` y `ReservaHotel`: poseen constructores privados en sus companion objects,por lo que para crear
instancias de estos es necesario llamar al método de clase `creaInstancia()` que actúa como una fábrica y que valida
los datos (como el formato de hora con Regex o el número de noches positivo) antes de crear el objeto.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/dominio/ReservaVuelo.kt#L34-L39

- `ReservaVueloDAO` y `ReservaHotelDAO`: implementan la interfaz genérica IDAO<T>, así como encapsulan la lógica de
"parseo" de objetos a texto y viceversa.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/datos/ReservaHotelDAO.kt#L6

- `ReservaRepository`: se encarga de la lógica de obtención de datos delegando en los diferentes DAOs.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/datos/ReservaRepository.kt#L10-L37

#### Pregunta 2 

##### Pregunta 2a - ¿Que formato le has dado a la información del fichero para guardar y recuperar la información?

He utilizado un formato de valores separados por comas (tipo CSV).
Por ejemplo, en `ReservaHotelDAO`, una reserva se guarda concatenando sus propiedades de la siguiente forma:
 >"${entity.id},${entity.descripcion},${entity.ubicacion},${entity.numeroNoches}"

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/datos/ReservaHotelDAO.kt#L8

##### Pregunta 2b - ¿Que estrategia has usado para trabajar con los ficheros?

Los archivos `reservasVuelo.txt` y `reservasHotel.txt` en el directorio raíz del proyecto, puesto que gracias a estos,
he podido utilizar un DAO específico por cada tipo de entidad para mantener la separación de responsabilidades.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/datos/ReservaVueloDAO.kt#L18


https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/datos/ReservaVueloDAO.kt#L6

##### Pregunta 2c - ¿Cómo se gestionan los errores?

A lo largo del desarrollo del código he utilizado estructuras como `require` con el fin de lanzar excepciones si se detectan
datos inválidos como en el caso de introducir un formato de hora incorrecto.

Asimismo, los DAOs lanzan `IllegalArgumentException` si se intenta crear una reserva que ya existe o eliminar una inexistente.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/datos/ReservaHotelDAO.kt#L10

Finalmente, en `ConsolaUI` se utilizan bloques `try-catch` para capturar errores de entrada del usuario o excepciones lanzadas por la lógica del programa,
evitando que la aplicación se cierre de forma inesperada.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/presentacion/ConsolaUI.kt#L38-L68

#### Pregunta 3

##### Pregunta3a - Describe la forma de acceso para leer información

Para leer información se utiliza el método `readLines()` con el fin de obtener todas las líneas del fichero, en donde
posteriormente se transforman a formato CSV a través del uso del método `split(",")` y de `recuperarInstancia()` para construir la reserva.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/datos/ReservaVueloDAO.kt#L19-L26

##### Pregunta3b - Describe la forma de acceso para escribir información

He empleado el método `appendText()` con el objetivo de añadir nuevas reservas al final del archivo, en donde previamente se 
ha transformado el objeto a su representación en cadena de texto con formato CSV.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/datos/ReservaVueloDAO.kt#L9-L14

##### Pregunta 3c - Describe la forma de acceso para actualizar información. Pon ejemplos de código

El borrado lo he llevado a cabo a través de una estrategia de filtrado, en la cual
se leen todas las líneas y se filtran todas aquellas en las cuales no coincide con el id de la reserva a eliminar,
en donde posteriormente se sobrescribe el archivo ya con la reserva asociada a ese id eliminada.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-ifonlob/blob/f0d1321daf3e73b1957db12639ed2c1d36d80b61/src/main/kotlin/es/iesra/datos/ReservaVueloDAO.kt#L27-L43