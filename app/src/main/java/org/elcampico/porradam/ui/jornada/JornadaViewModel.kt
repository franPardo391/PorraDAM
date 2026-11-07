package org.elcampico.porradam.ui.jornada

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.elcampico.porradam.data.JornadaDePrueba
import org.elcampico.porradam.data.model.Marcador

// Como ContadorViewModel: el estado vive fuera de Compose y sobrevive al girar el móvil.
class JornadaViewModel : ViewModel() {

    val numero = JornadaDePrueba.numero
    val cierre = JornadaDePrueba.cierre
    val puntos = 0                  // los puntos llegan con Resultados (Tema 3)
    val partidos = JornadaDePrueba.partidos

    // Lo que se ve en los selectores (id del partido → marcador)
    var pronosticos by mutableStateOf(partidos.associate { it.id to Marcador() })
        private set                 // la pantalla lo lee, no lo cambia

    // Lo último que se guardó con el botón (null si aún no se ha guardado)
    var guardados by mutableStateOf<Map<String, Marcador>?>(null)
        private set

    val estaGuardado: Boolean
        get() = guardados == pronosticos

    fun cambiar(idPartido: String, marcador: Marcador) {
        pronosticos = pronosticos + (idPartido to marcador)
    }

    fun guardar() {
        guardados = pronosticos
    }
}
