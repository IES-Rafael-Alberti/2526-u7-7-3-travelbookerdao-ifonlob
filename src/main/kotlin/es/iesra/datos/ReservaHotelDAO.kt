package es.iesra.datos

import es.iesra.dominio.ReservaHotel
import es.iesra.dominio.ReservaVuelo
import java.io.File

class ReservaHotelDAO(private val file : File) : IDAO<ReservaHotel>{
    override fun create(entity: ReservaHotel) = file.appendText("${entity.id},${entity.descripcion},${entity.ubicacion},${entity.numeroNoches}")

    override fun read(): List<ReservaHotel> {
        val lineas = file.readLines()
        val reservas = mutableListOf<ReservaHotel>()
        lineas.map{
            val partes = it.split(",")
            reservas.add(ReservaHotel.recuperar(partes[1],partes[2],partes[3].toInt()))
        }
    }

    override fun delete(entity: ReservaHotel) {
        TODO("Not yet implemented")
    }

}