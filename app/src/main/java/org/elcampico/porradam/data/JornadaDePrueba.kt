package org.elcampico.porradam.data

import org.elcampico.porradam.data.model.Equipo
import org.elcampico.porradam.data.model.Partido

object JornadaDePrueba {
    const val numero = 1
    const val cierre = "Sáb 14 nov · 16:00"

    private val madrid = Equipo("Real Madrid",  "RM",  0xFFFFFFFF)
    private val barca  = Equipo("FC Barcelona", "FCB", 0xFFA50044)

    // Cinco clásicos, alternando quién juega en casa
    val partidos = listOf(
        Partido("j1-p1", madrid, barca),
        Partido("j1-p2", barca, madrid),
        Partido("j1-p3", madrid, barca),
        Partido("j1-p4", barca, madrid),
        Partido("j1-p5", madrid, barca)
    )
}
