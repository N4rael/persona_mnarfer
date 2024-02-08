package example.org

import java.math.RoundingMode
import java.text.DecimalFormat


class Persona(peso: Double, altura: Double) {

    var nombre: String = " "
        get() = field
        set(value) {
            require(value.trim().isNotEmpty()) { "El nombre no puede estar vacío." }
            field = value
        }
    constructor(nombre: String, peso: Double, altura: Double) : this(peso, altura) {
        this.nombre = nombre
    }

    var peso: Double = peso
        set(value) {
            require(value > 0) { "El peso no puede ser 0." }
            field = value
        }
    var altura: Double = altura
        set(value) {
            require(value > 0) { "La altura no puede ser 0." }
            field = value
        }

    private var imc: Double = 0.0
        get() = this.peso / (this.altura * this.altura)
        private set(value){
            field = value
        }
    fun obtenerIMC(): Double {
        return this.imc
    }

    fun obtenerDescImc(): String {
        return when {
            this.imc < 18.5 -> return "Peso insuficiente."
            (this.imc <= 18.5) or (this.imc <= 24.9) -> return "Peso saludable."
            (this.imc >= 25.0) or (this.imc <= 29.9) -> return "Sobrepeso."
            this.imc >= 30.0 -> return "Obesidad"
            else -> {"Ha habido un error."}
        }
    }


    fun obtenerDesc(): String {
        val imc2 = DecimalFormat("#.##")
        imc2.roundingMode = RoundingMode.DOWN
        val imcR = imc2.format(this.imc)
        val imcDesc = obtenerDescImc()

        val mediaPeso = if (pesoEncimaMedia()) { "Por encima de la media" } else { "Por debajo de la media" }
        val mediaAltura = if (alturaEncimaMedia()) { "Por encima de la media" } else { "Por debajo de la media" }
        return "${this.nombre} tiene un peso de ${this.peso} (${mediaPeso}) y una altura de ${this.altura} (${mediaAltura}), con un IMC de ${imcR} (${imcDesc})."
    }
    fun saludar(): String {

        return "Hola, ${this.nombre}"
    }

    fun alturaEncimaMedia(): Boolean {
        return when(this.altura > 1.75){
            true -> true
            false -> false
        }

    }

    fun pesoEncimaMedia(): Boolean {
        return when (this.peso > 70) {
            true -> true
            false -> false
        }
    }

}


    fun main() {
      val persona1 = Persona(76.0, 1.70)
      val persona3 = Persona("Laura",60.65, 1.65)
      val persona2 = Persona("Lidia",80.9, 1.95)

    //Persona 1
      print("Por favor añada el nombre -> ")
      persona1.nombre = readln()
        println(persona1.obtenerDesc())

      //Persona 2
      println(persona2.obtenerDesc())

      //Persona 3
      persona3.obtenerDesc()
      print("Añada el nuevo peso de ${persona3.nombre} -> ")
      persona3.peso = readln().toDouble()
      print("Añada la nueva altura de ${persona3.nombre} -> ")
      persona3.altura = readln().toDouble()
        println(persona3.obtenerDesc())
    }
