package models

class Auto()
{
    var modelo = ""
    var marca = ""
    var ListaCaracteristicas: MutableList<String> = mutableListOf("")
    var ListaCaracteristicasEsp: MutableList<String> = mutableListOf("")
    var ListaAutos: MutableList<String> = mutableListOf("")

    fun ensamblarAuto():MutableList<String>
    {
        var flaggola = 1
        do {
            println("Ingrese la marca que desea:")
            marca = readLine()!!
            println("Ingrese el modelo que desea:")
            modelo = readLine()!!
            var flag = 1
            var trans = ""
            println("Seleccione tipo de transmision: \n 1.- Transmision Manual \n 2.- Transmision Automatica")
            trans = readLine()!!
            /*when (trans.toInt())
      {
          1 -> ListaCaracteristicas.add("Transmision Manual")
          2 -> ListaCaracteristicas.add("Transmision Automatica")
          else -> ListaCaracteristicas.add("")
      }*/
            if (trans == "1") {
                ListaCaracteristicas.add("Transmision Manual")
            } else if (trans == "2") {
                ListaCaracteristicas.add("Transmision Automatica")
            }

            do {
                var comp = ""
                println("Seleccione mas caracteristicas: \n1.- Aire acondicionado \n2.- Frenos ABS\n3.-Bolsas de aire\n4.-Pantalla tactil\n5.-Controles Electricos")
                comp = readLine()!!
                when (comp.toInt()) {
                    1 -> ListaCaracteristicas.add("Aire acondicionado")
                    2 -> ListaCaracteristicas.add("Frenos ABS")
                    3 -> ListaCaracteristicas.add("Bolsas de Aire")
                    4 -> ListaCaracteristicas.add("Controles Electricos")
                    else -> comp = ""
                }
                println("Desea agregar otra caracteristica? s/n")
                var preg = readLine()!!
                if (preg == "s") {
                    flag = 1
                } else {
                    flag = 0
                }
            } while (flag == 1)
            do {
                var comp = ""
                println("Seleccione mas caracteristicas: \n1.- Faros de niebla\n2.- Faros Led\n3.- Asientos de piel\n4.-Rines chidos")
                comp = readLine()!!
                when (comp.toInt()) {
                    1 -> ListaCaracteristicasEsp.add("Faros de niebla")
                    2 -> ListaCaracteristicasEsp.add("Faros Led")
                    3 -> ListaCaracteristicasEsp.add("Asientos de piel")
                    4 -> ListaCaracteristicasEsp.add("Rines chidos")
                    else -> comp = ""
                }
                println("Desea agregar otra caracteristica? s/n")
                var preg = readLine()!!
                if (preg == "s") {
                    flag = 1
                } else {
                    flag = 0
                }
            } while (flag == 1)
            var cad = ""
            var cade = ""
            for (Car in ListaCaracteristicas) {
                cad = cad + Car + "\n"
            }
            for (CarE: String in ListaCaracteristicasEsp) {
                cade = cade + CarE + "\n"
            }
            ListaAutos.add("Automovil:\n Marca: ${marca}\n Modelo: ${modelo}\n Caracteristicas:\n${cad} \n Caractersiticas Especiales \n${cade}\n")
            println("Desea agregar otro auto?")
            var preggo = readLine()!!
            if (preggo == "s")
            {
                flaggola = 1
            }
            else
            {
                flaggola = 0
            }

        } while (flaggola == 1)
        return ListaAutos
    }
}