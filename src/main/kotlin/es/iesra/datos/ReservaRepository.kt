package es.iesra.datos

import es.iesra.dominio.Reserva
import es.iesra.dominio.ReservaHotel
import es.iesra.dominio.ReservaVuelo

/**
 * Implementación en memoria del repositorio de reservas.
 */
class ReservaRepository(private val daoVuelos : IDAO<ReservaVuelo>, private val daoHotel : IDAO<ReservaHotel>) : IReservaRepository {

    override fun obtenerTodas(): List<Reserva> = daoVuelos.read().union(daoHotel.read()).toList()

    override fun agregar(reserva: Reserva){
        when(reserva){
            is ReservaHotel -> return daoHotel.create(reserva)
            is ReservaVuelo -> return daoVuelos.create(reserva)
            else -> ""
        }
    }

    override fun eliminar(reserva: Reserva){
        when(reserva){
            is ReservaHotel -> return daoHotel.delete(reserva)
            is ReservaVuelo -> return daoVuelos.delete(reserva)
            else -> ""
        }
    }

    override fun encontrarPorId(id: Int): Reserva? = obtenerTodas().find{}
}