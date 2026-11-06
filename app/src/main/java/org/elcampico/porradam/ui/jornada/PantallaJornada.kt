package org.elcampico.porradam.ui.jornada

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.elcampico.porradam.R
import org.elcampico.porradam.ui.components.FondoEstadio
import org.elcampico.porradam.ui.components.PartidoCard
import org.elcampico.porradam.ui.theme.Noche
import org.elcampico.porradam.ui.theme.VerdeCesped
import org.elcampico.porradam.ui.theme.VerdeOscuro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaJornada(
    modifier: Modifier = Modifier,
    vm: JornadaViewModel = viewModel()
) {
    Box(modifier.fillMaxSize()) {
        // Ampliación de la actividad 2.8: imagen de fondo detrás del Scaffold
        FondoEstadio(scrim = Brush.verticalGradient(listOf(Noche.copy(alpha = 0.6f), Noche.copy(alpha = 0.6f))))

        Scaffold(
            containerColor = Color.Transparent,            // deja ver la imagen
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.jornada_titulo, vm.numero),
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Noche.copy(alpha = 0.72f),
                        titleContentColor = Color.White
                    )
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = vm::guardar,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    icon = { Icon(Icons.Default.Check, contentDescription = null) },
                    text = {
                        Text(
                            stringResource(
                                if (vm.estaGuardado) R.string.jornada_guardado else R.string.jornada_guardar
                            )
                        )
                    }
                )
            },
            bottomBar = { BarraInferiorExtra() }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                vm.partidos.forEach { partido ->
                    PartidoCard(
                        partido = partido,
                        pronostico = vm.pronosticos.getValue(partido.id),
                        onCambiar = { nuevo -> vm.cambiar(partido.id, nuevo) }
                    )
                }
                Spacer(Modifier.height(72.dp))     // hueco para que el botón flotante no tape la última tarjeta
            }
        }
    }
}

// EXTRA: NavigationBar es del Tema 3. Aquí solo se dibuja; las pestañas aún no hacen nada.
@Composable
private fun BarraInferiorExtra() {
    val colores = NavigationBarItemDefaults.colors(
        selectedIconColor = VerdeOscuro,
        selectedTextColor = VerdeCesped,
        indicatorColor = VerdeCesped,
        unselectedIconColor = Color.White,
        unselectedTextColor = Color.White
    )
    NavigationBar(containerColor = Noche.copy(alpha = 0.85f)) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            colors = colores,
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.nav_jornada)) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            colors = colores,
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text(stringResource(R.string.nav_resultados)) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            colors = colores,
            icon = { Icon(Icons.Default.Star, contentDescription = null) },
            label = { Text(stringResource(R.string.nav_clasificacion)) }
        )
    }
}
