package es.iesra.datos

import es.iesra.dominio.ReservaHotel
import es.iesra.dominio.ReservaVuelo
import java.io.File

class ReservaVueloDAO(private val file : File): IDAO<ReservaVuelo> {

    override fun create(entity: ReservaVuelo){
        val entidadParseada = "${entity.id},${entity.descripcion},${entity.origen},${entity.destino},${entity.horaVuelo}"

        if (file.readLines().contains(entidadParseada)) throw IllegalArgumentException("La reserva ya existe en el fichero.")
        else file.appendText(entidadParseada)

    }




    override fun read(entity: ReservaVuelo) : List<ReservaVuelo>{
        TODO("Not yet implemented")
    }

    override fun delete(entity: ReservaVuelo) {
        TODO("Not yet implemented")
    }
}