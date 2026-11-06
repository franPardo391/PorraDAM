package org.elcampico.porradam.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import org.elcampico.porradam.R

// La foto del aula virtual a pantalla completa, con un velo (scrim) para que se lea el texto.
@Composable
fun FondoEstadio(scrim: Brush, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.fondo_pantalla_hd),
            contentDescription = null,                 // decorativa
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Box(Modifier.matchParentSize().background(scrim))
    }
}
