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
        if (!file.exists()) return emptyList()
        val lineas = file.readLines()
        val reservas = mutableListOf<ReservaHotel>()
        lineas.forEach{
            val partes = it.split(",")
            reservas.add(ReservaHotel.recuperarInstancia(partes[0].toInt(),partes[1],partes[2],partes[3].toInt()))
        }
        return reservas
    }

    override fun delete(entity: ReservaHotel) {
        val entidadParseada = "${entity.id},${entity.descripcion},${entity.ubicacion},${entity.numeroNoches}"

        if(file.readLines().contains(entidadParseada)){
            val lineasFiltradas = file.readLines().filter{
                it.split(",")[0].toInt() != entity.id
            }
            file.printWriter().use{ writer ->
                lineasFiltradas.forEach {
                    writer.println(it)
                }
            }
        }
        else throw IllegalArgumentException("La reserva a eliminar no se encuentra en el archivo.")
    }

}