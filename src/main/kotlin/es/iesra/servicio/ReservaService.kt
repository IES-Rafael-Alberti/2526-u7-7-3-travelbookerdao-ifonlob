package es.iesra.servicio

import es.iesra.datos.IReservaRepository
import es.iesra.dominio.ReservaHotel
import es.iesra.dominio.ReservaVuelo

/**
 * Implementación concreta de IReservaService.
 * Depende de la abstracción IReservaRepository, no de una implementación concreta.
 */
class ReservaService(private val repositorio: IReservaRepository) : IReservaService {

    override fun crearReservaVuelo(descripcion: String, origen: String, destino: String, horaVuelo: String) {
        val nuevoId = repositorio.obtenerSiguienteId()
        val reservaVuelo = ReservaVuelo.creaInstancia(nuevoId, descripcion,origen, destino, horaVuelo)
        repositorio.agregar(reservaVuelo)
    }

    override fun crearReservaHotel(descripcion: String, ubicacion: String, numeroNoches: Int) {
        val nuevoId = repositorio.obtenerSiguienteId()
        val reservaHotel = ReservaHotel.creaInstancia(nuevoId, descripcion,ubicacion, numeroNoches)
        repositorio.agregar(reservaHotel)
    }

    override fun listarReservas() = repositorio.obtenerTodas()
}