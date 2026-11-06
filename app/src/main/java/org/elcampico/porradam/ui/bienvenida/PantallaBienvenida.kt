package org.elcampico.porradam.ui.bienvenida

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.elcampico.porradam.R
import org.elcampico.porradam.ui.components.FondoEstadio
import org.elcampico.porradam.ui.theme.Papel
import org.elcampico.porradam.ui.theme.PorraDamTheme
import org.elcampico.porradam.ui.theme.Tinta
import org.elcampico.porradam.ui.theme.VerdeCesped

// 0 · Bienvenida. Según el MP.1 es del Tema 3 (ampliación): aquí solo la parte visual.
@Composable
fun PantallaBienvenida(
    onEntrar: () -> Unit,
    modifier: Modifier = Modifier
) {
    // EXTRA: los campos de texto no salen en el Tema 2. rememberSaveable sí (ejercicio Tres contadores).
    var alias by rememberSaveable { mutableStateOf("") }
    var codigo by rememberSaveable { mutableStateOf("") }

    Box(modifier.fillMaxSize()) {
        FondoEstadio(
            scrim = Brush.verticalGradient(
                0.3f to Color.Transparent,
                0.55f to Color.Black.copy(alpha = 0.55f),
                1f to Color.Black.copy(alpha = 0.88f)
            )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .systemBarsPadding()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row {
                Text(stringResource(R.string.titulo_porra), style = MaterialTheme.typography.displayLarge, color = Color.White)
                Text(" " + stringResource(R.string.titulo_dam), style = MaterialTheme.typography.displayLarge, color = VerdeCesped)
            }
            Text(
                text = stringResource(R.string.bienvenida_subtitulo),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.88f)
            )

            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Papel)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CampoExtra(alias, { alias = it }, stringResource(R.string.bienvenida_alias))
                    CampoExtra(codigo, { codigo = it }, stringResource(R.string.bienvenida_codigo))
                    Spacer(Modifier.height(4.dp))
                    Button(
                        onClick = onEntrar,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Tinta, contentColor = Color.White)
                    ) {
                        Text(stringResource(R.string.bienvenida_entrar), style = MaterialTheme.typography.labelLarge)
                        Spacer(Modifier.width(8.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Composable
private fun CampoExtra(valor: String, onCambio: (String) -> Unit, etiqueta: String) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(etiqueta) },
        supportingText = { Text(stringResource(R.string.extra)) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PantallaBienvenidaPreview() {
    PorraDamTheme { PantallaBienvenida(onEntrar = {}) }
}
