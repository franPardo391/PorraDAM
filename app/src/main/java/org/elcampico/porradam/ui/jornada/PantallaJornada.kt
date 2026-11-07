package org.elcampico.porradam.ui.jornada

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.elcampico.porradam.R
import org.elcampico.porradam.ui.components.FondoEstadio
import org.elcampico.porradam.ui.components.PartidoCard
import org.elcampico.porradam.ui.theme.Amarillo
import org.elcampico.porradam.ui.theme.Marcador
import org.elcampico.porradam.ui.theme.Noche

@Composable
fun PantallaJornada(
    modifier: Modifier = Modifier,
    vm: JornadaViewModel = viewModel()
) {
    Box(modifier.fillMaxSize()) {
        // Ampliación de la actividad 2.8: imagen de fondo detrás del Scaffold
        FondoEstadio(
            scrim = Brush.verticalGradient(
                listOf(Color.Black.copy(alpha = 0.2f), Noche.copy(alpha = 0.85f))
            )
        )

        Scaffold(
            containerColor = Color.Transparent,            // deja ver la imagen
            topBar = { Cabecera(vm.numero, vm.cierre, vm.puntos) },
            bottomBar = {
                Column {
                    Button(
                        onClick = vm::guardar,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Amarillo, contentColor = Marcador)
                    ) {
                        if (vm.estaGuardado) {
                            Icon(Icons.Default.Check, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                        }
                        Text(
                            text = stringResource(if (vm.estaGuardado) R.string.jornada_guardado else R.string.jornada_guardar),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    BarraInferiorExtra()
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 14.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                vm.partidos.forEach { partido ->
                    PartidoCard(
                        partido = partido,
                        pronostico = vm.pronosticos.getValue(partido.id),
                        onCambiar = { nuevo -> vm.cambiar(partido.id, nuevo) }
                    )
                }
            }
        }
    }
}

// Cabecera sobre la foto: nombre de la app, puntos, jornada y fecha de cierre
@Composable
private fun Cabecera(numero: Int, cierre: String, puntos: Int) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(start = 18.dp, end = 18.dp, top = 10.dp, bottom = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.jornada_barra).uppercase(),
                style = MaterialTheme.typography.titleLarge,
                letterSpacing = 3.sp,
                color = Color.White.copy(alpha = 0.85f),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(R.string.jornada_puntos, puntos),
                style = MaterialTheme.typography.labelLarge,
                color = Marcador,
                modifier = Modifier
                    .background(Amarillo, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }
        Text(
            text = stringResource(R.string.jornada_titulo, numero),
            style = MaterialTheme.typography.displayMedium,
            color = Color.White,
            modifier = Modifier.padding(top = 6.dp)
        )
        Text(
            text = stringResource(R.string.jornada_subtitulo),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.8f)
        )
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.DateRange, contentDescription = null, tint = Amarillo, modifier = Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.jornada_cierre, cierre),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}

// EXTRA: NavigationBar es del Tema 3. Aquí solo se dibuja; las pestañas aún no hacen nada.
@Composable
private fun BarraInferiorExtra() {
    NavigationBar(containerColor = Noche) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.nav_jornada)) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text(stringResource(R.string.nav_resultados)) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Star, contentDescription = null) },
            label = { Text(stringResource(R.string.nav_clasificacion)) }
        )
    }
}
