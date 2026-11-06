package org.elcampico.porradam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.elcampico.porradam.ui.bienvenida.PantallaBienvenida
import org.elcampico.porradam.ui.jornada.PantallaJornada
import org.elcampico.porradam.ui.theme.PorraDamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PorraDamTheme {
                // Cambio de pantalla muy simple; la navegación de verdad llega en el Tema 3.
                // rememberSaveable: al girar el móvil se sigue en la misma pantalla.
                var enJornada by rememberSaveable { mutableStateOf(false) }
                if (enJornada) {
                    PantallaJornada()
                } else {
                    PantallaBienvenida(onEntrar = { enJornada = true })
                }
            }
        }
    }
}
