package es.iesra.datos

import es.iesra.dominio.Reserva

interface IDAO <T : Reserva> {
    fun create(entity : T)
    fun read() : List<T>
    fun delete(entity : T)
}