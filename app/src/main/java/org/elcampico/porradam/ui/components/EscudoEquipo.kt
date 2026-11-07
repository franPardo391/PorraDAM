package org.elcampico.porradam.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.elcampico.porradam.R
import org.elcampico.porradam.data.JornadaDePrueba
import org.elcampico.porradam.data.model.Equipo
import org.elcampico.porradam.ui.theme.PorraDamTheme

// Todos los equipos usan la misma imagen: res/drawable/escudo.
// (El PDF dibuja un círculo con el color y las iniciales; aquí se cambia por una imagen.)
@Composable
fun EscudoEquipo(
    equipo: Equipo,
    modifier: Modifier = Modifier,
    tamano: Dp = 36.dp
) {
    Image(
        painter = painterResource(R.drawable.escudo),
        contentDescription = equipo.nombre,
        contentScale = ContentScale.Fit,
        modifier = modifier.size(tamano)
    )
}

@Preview(showBackground = true)
@Composable
private fun EscudoEquipoPreview() {
    PorraDamTheme {
        Row(Modifier.padding(8.dp)) {
            EscudoEquipo(JornadaDePrueba.partidos[0].local)
            EscudoEquipo(JornadaDePrueba.partidos[1].visitante, Modifier.padding(start = 8.dp), tamano = 56.dp)
        }
    }
}
