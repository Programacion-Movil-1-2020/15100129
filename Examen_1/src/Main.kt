import com.sun.tools.javac.Main
import models.Auto

fun main()
{
    var cont = -1
    for ((index:Int,i) in Auto().ensamblarAuto().withIndex())
    {
        println("Numero de auto: ${index} ${i}")
        cont += 1
    }
    print("Cantidad de autos:${cont}")
}