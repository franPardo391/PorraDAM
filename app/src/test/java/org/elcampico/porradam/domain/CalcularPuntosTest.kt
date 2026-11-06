package org.elcampico.porradam.domain

import org.elcampico.porradam.data.model.Marcador
import org.junit.Assert.assertEquals
import org.junit.Test

class CalcularPuntosTest {
    @Test fun resultadoExactoDa8() =
        assertEquals(8, calcularPuntos(Marcador(2, 1), Marcador(2, 1)))

    @Test fun signoYGolesDeUnEquipoDan4() =
        assertEquals(4, calcularPuntos(Marcador(3, 0), Marcador(2, 0)))

    @Test fun soloGolesDeUnEquipoDa2() =
        assertEquals(2, calcularPuntos(Marcador(1, 1), Marcador(1, 0)))

    @Test fun fallarTodoDa0() =
        assertEquals(0, calcularPuntos(Marcador(0, 2), Marcador(1, 0)))

    // MP.3 ejercicio 4: en el mockup de Resultados, Betis 2-1 Valencia con porra 1-1
    // aparece con 0 puntos. Se acierta el gol del Valencia (1), así que son 2 puntos.
    @Test fun betisValenciaDa2YNo0() =
        assertEquals(2, calcularPuntos(Marcador(2, 1), Marcador(1, 1)))
}
