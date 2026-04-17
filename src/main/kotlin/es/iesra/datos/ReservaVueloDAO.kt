package es.iesra.datos

import es.iesra.dominio.ReservaVuelo
import java.io.File

class ReservaVueloDAO(private val file : File): IDAO<ReservaVuelo> {


    override fun create(entity: ReservaVuelo){
        val entidadParseada = "${entity.id},${entity.descripcion},${entity.origen},${entity.destino},${entity.horaVuelo}"

        if (file.readLines().contains(entidadParseada)) throw IllegalArgumentException("La reserva ya existe en el fichero.")
        else file.appendText(entidadParseada)
    }


    override fun read(): List<ReservaVuelo> {
        if (!file.exists()) return emptyList()
        val lineas = file.readLines()
        val reservas = mutableListOf<ReservaVuelo>()
        lineas.forEach{
            val partes = it.split(",")
            reservas.add(ReservaVuelo.recuperarInstancia(partes[0].toInt(),partes[1],partes[2],partes[3],partes[4]))
        }
        return reservas
    }

    override fun delete(entity: ReservaVuelo) {
        val entidadParseada = "${entity.id},${entity.descripcion},${entity.origen},${entity.destino},${entity.horaVuelo}"

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