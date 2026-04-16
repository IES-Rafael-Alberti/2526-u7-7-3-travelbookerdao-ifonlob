package es.iesra.datos

import es.iesra.dominio.ReservaHotel
import java.io.File

class ReservaHotelDAO(private val file : File) : IDAO<ReservaHotel>{
    override fun create(entity: ReservaHotel){
        val entidadParseada = "${entity.id},${entity.descripcion},${entity.ubicacion},${entity.numeroNoches}"

        if (file.readLines().contains(entidadParseada)) throw IllegalArgumentException("La reserva ya existe en el fichero.")
        else file.appendText(entidadParseada)
    }

    override fun read(): List<ReservaHotel> {
        val lineas = file.readLines()
        val reservas = mutableListOf<ReservaHotel>()
        lineas.forEach{
            val partes = it.split(",")
            reservas.add(ReservaHotel.recuperarInstancia(partes[1].toInt(),partes[2],partes[3],partes[4].toInt()))
        }
        return reservas
    }

    override fun delete(entity: ReservaHotel) {
        TODO("Not yet implemented")
    }

}