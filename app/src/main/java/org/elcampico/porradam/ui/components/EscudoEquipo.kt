package org.elcampico.porradam.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.elcampico.porradam.data.JornadaDePrueba
import org.elcampico.porradam.data.model.Equipo
import org.elcampico.porradam.ui.theme.PorraDamTheme

// EXTRA: el PDF recorta con CircleShape. Aquí la silueta de escudo se dibuja con
// GenericShape (mismo .clip(), otra forma). Para volver al círculo: .clip(CircleShape).
private val FormaEscudo = GenericShape { size, _ ->
    val w = size.width
    val h = size.height
    moveTo(w / 2f, 0f)
    lineTo(w, h * 0.14f)
    lineTo(w, h * 0.47f)
    cubicTo(w, h * 0.72f, w * 0.72f, h * 0.89f, w / 2f, h)
    cubicTo(w * 0.28f, h * 0.89f, 0f, h * 0.72f, 0f, h * 0.47f)
    lineTo(0f, h * 0.14f)
    close()
}

@Composable
fun EscudoEquipo(
    equipo: Equipo,
    modifier: Modifier = Modifier,
    tamano: Dp = 36.dp
) {
    Box(
        modifier = modifier
            .size(width = tamano, height = tamano * 1.12f)
            .clip(FormaEscudo)
            .background(Color(equipo.color))            // Long → Color, solo en la interfaz
            .border(1.5.dp, Color.White, FormaEscudo),
        contentAlignment = Alignment.Center
    ) {
        // Una franja blanca suave que cambia según el equipo, para que no sean todos iguales
        val franja = Color.White.copy(alpha = 0.3f)
        when (equipo.iniciales.sumOf { it.code } % 3) {
            0 -> Box(Modifier.fillMaxHeight().width(tamano * 0.2f).background(franja))
            1 -> Box(Modifier.fillMaxWidth().height(tamano * 0.18f).background(franja))
        }
        Text(
            text = equipo.iniciales,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EscudoEquipoPreview() {
    PorraDamTheme {
        Row(Modifier.padding(8.dp)) {
            EscudoEquipo(JornadaDePrueba.partidos[0].local)
            EscudoEquipo(JornadaDePrueba.partidos[1].visitante, Modifier.padding(start = 8.dp))
            EscudoEquipo(JornadaDePrueba.partidos[2].local, Modifier.padding(start = 8.dp), tamano = 56.dp)
        }
    }
}
