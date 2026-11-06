package org.elcampico.porradam.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// La app siempre va sobre la foto del estadio, así que usa un solo esquema.
// Sin dynamicColor: si no, Android 12+ ignora nuestros colores (Tema 2).
private val Esquema = lightColorScheme(
    primary = VerdeCesped,
    onPrimary = VerdeOscuro,
    background = Noche,
    surface = Papel,
    onSurface = Tinta,
    onSurfaceVariant = Gris
)

@Composable
fun PorraDamTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Esquema,
        typography = Tipografia,
        content = content
    )
}
