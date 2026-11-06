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
}
