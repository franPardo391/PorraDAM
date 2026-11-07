package org.elcampico.porradam.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.elcampico.porradam.data.JornadaDePrueba
import org.elcampico.porradam.data.model.Equipo
import org.elcampico.porradam.data.model.Marcador
import org.elcampico.porradam.data.model.Partido
import org.elcampico.porradam.ui.theme.GrisCaja
import org.elcampico.porradam.ui.theme.PorraDamTheme

// Un partido: la fila del local encima de la del visitante, cada una con su selector.
@Composable
fun PartidoCard(
    partido: Partido,
    pronostico: Marcador,
    onCambiar: (Marcador) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        FilaEquipo(
            equipo = partido.local,
            goles = pronostico.local,
            onSumar = { onCambiar(pronostico.copy(local = pronostico.local + 1)) },
            onRestar = { onCambiar(pronostico.copy(local = pronostico.local - 1)) }
        )
        FilaEquipo(
            equipo = partido.visitante,
            goles = pronostico.visitante,
            onSumar = { onCambiar(pronostico.copy(visitante = pronostico.visitante + 1)) },
            onRestar = { onCambiar(pronostico.copy(visitante = pronostico.visitante - 1)) }
        )
    }
}

// La «fila de un equipo» que se repite dos veces en cada partido (paso 1 del MP.2)
@Composable
fun FilaEquipo(
    equipo: Equipo,
    goles: Int,
    onSumar: () -> Unit,
    onRestar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(GrisCaja, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            EscudoEquipo(equipo, tamano = 32.dp)
        }
        Text(
            text = equipo.nombre,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 14.dp),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1
        )
        MarcadorSelector(goles = goles, onSumar = onSumar, onRestar = onRestar)
    }
}

@Preview(showBackground = true)
@Composable
private fun PartidoCardPreview() {
    PorraDamTheme {
        PartidoCard(JornadaDePrueba.partidos[0], Marcador(2, 1), onCambiar = {}, Modifier.padding(16.dp))
    }
}
