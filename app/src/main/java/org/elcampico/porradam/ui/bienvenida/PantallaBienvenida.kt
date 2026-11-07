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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elcampico.porradam.R
import org.elcampico.porradam.ui.components.FondoEstadio
import org.elcampico.porradam.ui.theme.Amarillo
import org.elcampico.porradam.ui.theme.Marcador
import org.elcampico.porradam.ui.theme.Noche
import org.elcampico.porradam.ui.theme.PorraDamTheme
import org.elcampico.porradam.ui.theme.TextoSuave

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
                1f to Noche.copy(alpha = 0.95f)
            )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .systemBarsPadding()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row {
                Text(stringResource(R.string.titulo_porra), style = MaterialTheme.typography.displayLarge, color = Color.White)
                Text(" " + stringResource(R.string.titulo_dam), style = MaterialTheme.typography.displayLarge, color = Amarillo)
            }
            Text(
                text = stringResource(R.string.bienvenida_subtitulo),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.85f)
            )

            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Noche.copy(alpha = 0.92f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Campo(stringResource(R.string.bienvenida_alias), stringResource(R.string.bienvenida_alias_pista), Icons.Default.Person, alias) { alias = it }
                    Campo(stringResource(R.string.bienvenida_codigo), stringResource(R.string.bienvenida_codigo_pista), Icons.Default.AccountCircle, codigo) { codigo = it }
                }
            }

            Button(
                onClick = onEntrar,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Amarillo, contentColor = Marcador)
            ) {
                Text(stringResource(R.string.bienvenida_entrar), style = MaterialTheme.typography.titleLarge, letterSpacing = 2.sp)
                Spacer(Modifier.width(10.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
        }
    }
}

// Etiqueta pequeña encima y el campo con su icono (EXTRA)
@Composable
private fun Campo(etiqueta: String, pista: String, icono: ImageVector, valor: String, onCambio: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(etiqueta, style = MaterialTheme.typography.labelMedium, color = TextoSuave, letterSpacing = 1.sp)
        OutlinedTextField(
            value = valor,
            onValueChange = onCambio,
            placeholder = { Text(pista, color = TextoSuave) },
            leadingIcon = { Icon(icono, contentDescription = null, tint = TextoSuave) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Amarillo,
                unfocusedBorderColor = TextoSuave.copy(alpha = 0.5f),
                cursorColor = Amarillo
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PantallaBienvenidaPreview() {
    PorraDamTheme { PantallaBienvenida(onEntrar = {}) }
}
