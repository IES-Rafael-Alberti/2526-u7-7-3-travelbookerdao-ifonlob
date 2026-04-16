package es.iesra.datos

import es.iesra.dominio.ReservaVuelo
import java.io.File

class ReservaVueloDAO(private val file : File): IDAO<ReservaVuelo> {
    override fun create(entity: ReservaVuelo) = file.appendText("${entity.id},${entity.descripcion},${entity.origen},${entity.destino},${entity.horaVuelo}")

    override fun read(entity: ReservaVuelo) : List<ReservaVuelo>{
        TODO("Not yet implemented")
    }

    override fun delete(entity: ReservaVuelo) {
        TODO("Not yet implemented")
    }
}