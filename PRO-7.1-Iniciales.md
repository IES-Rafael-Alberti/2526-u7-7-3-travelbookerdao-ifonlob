# RESPUESTA A PREGUNTAS 

#### Pregunta 1 - ¿Que librería/clases has utilizado para realizar la práctica.? Comenta los métodos mas interesantes

**Librerías principales**:
- `java.time.LocalDateTime`, me ha ayudado a asignar la fecha de creación de cada reserva en un formato reconocido. 
- `kotlin.text.Regex`, gracias a esta librería he podido
- `java.io.File`, me ha permitido trabajar con persistencia de datos a través del almacenamiento de las reservas en archivos.


**Clases explicadas**:
- `ReservaVuelo` y `ReservaHotel`: poseen constructores privados en sus companion objects,por lo que para crear
instancias de estos es necesario llamar al método de clase `creaInstancia()` que actúa como una fábrica y que valida
los datos ((como el formato de hora con Regex o el número de noches positivo) antes de crear el objeto.
- `ReservaVueloDAO` y `ReservaHotelDAO`: implementan la interfaz genérica IDAO<T>, así como encapsulan la lógica de
"parseo" de objetos a texto y viceversa.
- `ReservaRepository`: se encarga de la lógica de obtención de datos delegando en los diferentes DAOs.

#### Pregunta 2 

##### Pregunta 2a - ¿Que formato le has dado a la información del fichero para guardar y recuperar la información?

He utilizado un formato de valores separados por comas (tipo CSV).
Por ejemplo, en `ReservaHotelDAO`, una reserva se guarda concatenando sus propiedades de la siguiente forma:
 >"${entity.id},${entity.descripcion},${entity.ubicacion},${entity.numeroNoches}"

##### Pregunta 2b - ¿Que estrategia has usado para trabajar con los ficheros?

Los archivos `reservasVuelo.txt` y `reservasHotel.txt` en el directorio raíz del proyecto, puesto que gracias a estos,
he podido utilizar un DAO específico por cada tipo de entidad para mantener la separación de responsabilidades.

##### Pregunta 2c - ¿Cómo se gestionan los errores?

A lo largo del desarrollo del código he utilizado estructuras como `require` con el fin de lanzar excepciones si se detectan
datos inválidos como en el caso de introducir un formato de hora incorrecto.

Asimismo, los DAOs lanzan `IllegalArgumentException` si se intenta crear una reserva que ya existe o eliminar una inexistente.

Finalmente, en `ConsolaUI` se utilizan bloques `try-catch` para capturar errores de entrada del usuario o excepciones lanzadas por la lógica del programa,
evitando que la aplicación se cierre de forma inesperada.

#### Pregunta 3

##### Pregunta3a - Describe la forma de acceso para leer información

Para leer información se utiliza el método `readLines()` con el fin de obtener todas las líneas del fichero.
