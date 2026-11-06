package org.elcampico.porradam.domain

import org.elcampico.porradam.data.model.Marcador

fun calcularPuntos(real: Marcador, porra: Marcador): Int {
    if (real == porra) return 8            // resultado exacto
    var puntos = 0
    if (real.signo() == porra.signo()) puntos += 2
    if (real.local == porra.local) puntos += 2
    if (real.visitante == porra.visitante) puntos += 2
    return puntos
}
