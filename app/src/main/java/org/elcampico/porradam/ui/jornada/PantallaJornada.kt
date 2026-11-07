package org.elcampico.porradam.ui.jornada

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.elcampico.porradam.R
import org.elcampico.porradam.ui.components.FondoEstadio
import org.elcampico.porradam.ui.components.PartidoCard
import org.elcampico.porradam.ui.theme.Tinta
import org.elcampico.porradam.ui.theme.Verde
import org.elcampico.porradam.ui.theme.VerdeClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaJornada(
    modifier: Modifier = Modifier,
    vm: JornadaViewModel = viewModel()
) {
    Box(modifier.fillMaxSize()) {
        // Ampliación de la actividad 2.8: imagen de fondo detrás del Scaffold
        FondoEstadio(
            scrim = Brush.verticalGradient(
                listOf(Color.Black.copy(alpha = 0.25f), Color.Black.copy(alpha = 0.45f))
            )
        )

        Scaffold(
            containerColor = Color.Transparent,            // deja ver la imagen
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(R.string.jornada_barra), style = MaterialTheme.typography.titleLarge) },
                    navigationIcon = {
                        // EXTRA: el menú lateral llega en el Tema 3; de momento no hace nada
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Menu, contentDescription = stringResource(R.string.menu))
                        }
                    },
                    actions = { PastillaPuntos(vm.puntos) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            },
            bottomBar = { BarraInferiorExtra() }
        ) { innerPadding ->
            Column(Modifier.padding(innerPadding).fillMaxSize()) {

                // Cabecera sobre la foto
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.jornada_titulo, vm.numero),
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(R.string.jornada_subtitulo),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                        Text(
                            text = stringResource(R.string.jornada_cierre, vm.cierre),
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }

                // Hoja blanca con los partidos y el botón
                Column(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxSize()
                        .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    vm.partidos.forEachIndexed { i, partido ->
                        if (i > 0) HorizontalDivider(Modifier.padding(vertical = 10.dp))
                        PartidoCard(
                            partido = partido,
                            pronostico = vm.pronosticos.getValue(partido.id),
                            onCambiar = { nuevo -> vm.cambiar(partido.id, nuevo) }
                        )
                    }
                    Button(
                        onClick = vm::guardar,
                        modifier = Modifier
                            .padding(top = 18.dp)
                            .fillMaxWidth()
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Tinta, contentColor = Color.White)
                    ) {
                        Text(
                            text = stringResource(if (vm.estaGuardado) R.string.jornada_guardado else R.string.jornada_guardar),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

// La pastilla verde de los puntos, arriba a la derecha
@Composable
private fun PastillaPuntos(puntos: Int) {
    Box(
        modifier = Modifier
            .padding(end = 12.dp)
            .background(VerdeClaro, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = stringResource(R.string.jornada_puntos, puntos),
            style = MaterialTheme.typography.labelLarge,
            color = Verde
        )
    }
}

// EXTRA: NavigationBar es del Tema 3. Aquí solo se dibuja; las pestañas aún no hacen nada.
@Composable
private fun BarraInferiorExtra() {
    NavigationBar(containerColor = Color.White) {
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
