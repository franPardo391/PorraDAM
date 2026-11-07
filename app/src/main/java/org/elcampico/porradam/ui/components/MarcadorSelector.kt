package org.elcampico.porradam.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import org.elcampico.porradam.R
import org.elcampico.porradam.ui.theme.GrisCaja
import org.elcampico.porradam.ui.theme.PorraDamTheme

// Selector de goles de un equipo: el número en una cajita y, a su derecha, flecha arriba / abajo.
// No guarda nada: recibe el valor y avisa con onSumar/onRestar (el estado vive en el ViewModel).
private val Gris = androidx.compose.ui.graphics.Color(0xFF757575)

@Composable
fun MarcadorSelector(
    goles: Int,
    onSumar: () -> Unit,
    onRestar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 44.dp, height = 40.dp)
                .background(GrisCaja, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "$goles", style = MaterialTheme.typography.titleLarge)
        }
        // Las flechas se ven siempre; solo actúan si el número sigue entre 0 y 9
        Column {
            IconButton(onClick = { if (goles < 9) onSumar() }, modifier = Modifier.size(28.dp, 22.dp)) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = stringResource(R.string.sumar_gol), tint = Gris)
            }
            IconButton(onClick = { if (goles > 0) onRestar() }, modifier = Modifier.size(28.dp, 22.dp)) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = stringResource(R.string.restar_gol), tint = Gris)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MarcadorSelectorPreview() {
    PorraDamTheme { MarcadorSelector(goles = 2, onSumar = {}, onRestar = {}) }
}
