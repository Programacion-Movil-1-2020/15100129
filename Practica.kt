import javafx.beans.binding.When

fun main()
{
    var flaggola:Int = 1
    var ListaComputadoras: MutableList<String> = mutableListOf("")
    while (flaggola == 1)
    {
        //DECLARACION DE VARIABLES
        var computadora = ""
        var procesador = ""
        var ram = ""
        var discoDuro = ""
        var tarjetaGrafica = ""
        var obj:String? = "" //MENU DE COMPUTADORA
        println("1-Armar equipo \n2-Ver equipos armados \n3-Salir")
        obj = readLine()!!
        when (obj.toInt()) {

            1 ->
            {
                var flag: Int = 0 // VARIABLE BANDERA
                do // CICLO DO WHILE
                {
                    //PARTE DEL PROCESADOR
                    var proc: String? = ""
                    println("Escoja un procesador: \n1-intel Core i3 \n2-intel Core i4 \n3-intel Core i5\n4-n/a")
                    proc = readLine()!!
                    when (proc.toInt()) {
                        1 -> procesador = "i3 "//println("i3")
                        2 -> procesador = "i4"//println("i4")
                        3 -> procesador = "i5 "//println("i5")
                        else -> procesador ="n/a"
                    }
                    // PARTE MEMORIA RAM
                    var ramo: String? = ""
                    println("Escoja la memoria ram: \n1-DDR1 4 Gig \n2-DDR3 8 Gig  \n3-DDR4 16 Gig\n4-n/a")
                    ramo = readLine()!!
                    when (proc.toInt()) {
                        1 -> ram = "DDR1 4 G" //println("DDR1 4 G")
                        2 -> ram = "DDR3 8 G" //println("DDR3 8 G")
                        3 -> ram = "DDR4 16 G" //println("DDR4 16 G")
                        else -> ram = "n/a"
                    }
                    // DISCO DURO
                    var disco: String? = ""
                    println("Escoja tipo de disco duro: \n1-1T SATA \n2-1T SCSI  \n3-500 Gig SOLIDO\n4-n/a")
                    disco = readLine()!!
                    when (disco.toInt()) {
                        1 -> discoDuro = "1T SATA"//println("1T SATA")
                        2 -> discoDuro = "1T SCSI"//println("1T SCSI")
                        3 -> discoDuro = "500 G SOLIDO"//println("500 G SOLIDO")
                        else -> discoDuro = "n/a"
                    }
                    // TARJETA GRAFICA
                    var grapho: String? = ""
                    println("Escoja tarjeta grafica: \n1-AMD Radeon RX 580 \n2-MSI NVIDIA GeForce RTX 2060  \n3-ASUS NVIDIA GeForce RTX 2080\n4-n/a")
                    grapho = readLine()!!
                    when (grapho.toInt()) {
                        1 -> tarjetaGrafica = "AMD Radeon RX 580"//println("1T SATA")
                        2 -> tarjetaGrafica = "MSI NVIDIA GeForce RTX 2060"//println("1T SCSI")
                        3 -> tarjetaGrafica = "ASUS NVIDIA GeForce RTX 2080"//println("500 G SOLIDO")
                        else -> tarjetaGrafica = "n/a"
                    }
                    //CONCATENACION DE COMPUTADORA
                    computadora = "Tipo Procesador: "+procesador +" Ram: "+ ram +" DiscoDuro: "+discoDuro+" Tarjeta Grafica: "+tarjetaGrafica
                    //AGREGANDO COMPUTADORAS A LA LISTA
                    ListaComputadoras.add(computadora)
                    var Preg: String? = ""
                    println("Desea hacer otra computadora? s/n")
                    Preg = readLine()!!
                    if (Preg == "s")
                        flag = 1
                    else
                        flag = 0
                } while (flag == 1) //Fin del do
            }
            2 ->
            {
                // LISTA DE COMPUTADORAS
                for ((index:Int,comput:String) in ListaComputadoras.withIndex())
                {
                    println("${index} :${comput}")
                }
            }
            else ->
            {
                //SALIDA DEL PROGRAMA
                flaggola = 0
                println("SALIENDO")
            }
        }
    }

}