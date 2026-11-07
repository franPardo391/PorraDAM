package org.elcampico.porradam.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elcampico.porradam.R
import org.elcampico.porradam.ui.theme.Amarillo
import org.elcampico.porradam.ui.theme.Marcador
import org.elcampico.porradam.ui.theme.PorraDamTheme
import org.elcampico.porradam.ui.theme.TextoSuave

// Un dígito del marcador con su flecha arriba y abajo.
// No guarda nada: recibe el valor y avisa con onSumar/onRestar (el estado vive en el ViewModel).
@Composable
fun MarcadorSelector(
    goles: Int,
    onSumar: () -> Unit,
    onRestar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        // Las flechas se ven siempre; solo actúan si el número sigue entre 0 y 9
        IconButton(onClick = { if (goles < 9) onSumar() }, modifier = Modifier.size(40.dp, 28.dp)) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = stringResource(R.string.sumar_gol), tint = TextoSuave)
        }
        Box(
            modifier = Modifier
                .size(width = 46.dp, height = 54.dp)
                .background(Marcador, RoundedCornerShape(10.dp))
                .border(1.dp, Amarillo.copy(alpha = 0.35f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$goles",
                style = MaterialTheme.typography.displayLarge,
                fontSize = 38.sp,
                color = Amarillo
            )
        }
        IconButton(onClick = { if (goles > 0) onRestar() }, modifier = Modifier.size(40.dp, 28.dp)) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = stringResource(R.string.restar_gol), tint = TextoSuave)
        }
    }
}

@Preview
@Composable
private fun MarcadorSelectorPreview() {
    PorraDamTheme { MarcadorSelector(goles = 2, onSumar = {}, onRestar = {}) }
}
