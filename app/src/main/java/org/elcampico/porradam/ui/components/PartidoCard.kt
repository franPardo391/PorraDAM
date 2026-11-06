package org.elcampico.porradam.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.elcampico.porradam.data.JornadaDePrueba
import org.elcampico.porradam.data.model.Equipo
import org.elcampico.porradam.data.model.Marcador
import org.elcampico.porradam.data.model.Partido
import org.elcampico.porradam.ui.theme.PorraDamTheme

// Tarjeta de un partido (diseño B): escudo y nombre a cada lado, marcador en el centro.
@Composable
fun PartidoCard(
    partido: Partido,
    pronostico: Marcador,
    onCambiar: (Marcador) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ColumnaEquipo(partido.local, Modifier.weight(1f))
            MarcadorSelector(
                goles = pronostico.local,
                onSumar = { onCambiar(pronostico.copy(local = pronostico.local + 1)) },
                onRestar = { onCambiar(pronostico.copy(local = pronostico.local - 1)) }
            )
            Text(
                text = "–",
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            MarcadorSelector(
                goles = pronostico.visitante,
                onSumar = { onCambiar(pronostico.copy(visitante = pronostico.visitante + 1)) },
                onRestar = { onCambiar(pronostico.copy(visitante = pronostico.visitante - 1)) }
            )
            ColumnaEquipo(partido.visitante, Modifier.weight(1f))
        }
    }
}

// La «fila de un equipo» que se repite dos veces en cada partido (paso 1 del MP.2)
@Composable
private fun ColumnaEquipo(equipo: Equipo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        EscudoEquipo(equipo)
        Text(
            text = equipo.nombre,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}

@Preview
@Composable
private fun PartidoCardPreview() {
    PorraDamTheme {
        PartidoCard(JornadaDePrueba.partidos[0], Marcador(2, 1), onCambiar = {})
    }
}
