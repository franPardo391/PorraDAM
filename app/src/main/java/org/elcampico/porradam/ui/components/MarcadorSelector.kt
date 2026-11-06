package org.elcampico.porradam.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elcampico.porradam.ui.theme.PorraDamTheme

// Selector de goles de un equipo: + arriba, el número y − abajo.
// No guarda nada: recibe el valor y avisa con onSumar/onRestar (el estado vive en el ViewModel).
@Composable
fun MarcadorSelector(
    goles: Int,
    onSumar: () -> Unit,
    onRestar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        // Los botones se ven siempre; solo actúan si el número sigue entre 0 y 9
        BotonGol(simbolo = "+", onClick = { if (goles < 9) onSumar() })
        Text(text = "$goles", style = MaterialTheme.typography.headlineMedium)
        BotonGol(simbolo = "−", onClick = { if (goles > 0) onRestar() })
    }
}

@Composable
private fun BotonGol(simbolo: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.size(width = 48.dp, height = 32.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text = simbolo, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun MarcadorSelectorPreview() {
    PorraDamTheme { MarcadorSelector(goles = 2, onSumar = {}, onRestar = {}) }
}
