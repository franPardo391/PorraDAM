package org.elcampico.porradam.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.elcampico.porradam.R

// Fuentes del aula virtual (zip «tipografías»), copiadas en res/font
val Staatliches = FontFamily(
    Font(R.font.staatliches_regular, FontWeight.Normal)
)

val HostGrotesk = FontFamily(
    Font(R.font.host_grotesk_regular, FontWeight.Normal),
    Font(R.font.host_grotesk_semibold, FontWeight.SemiBold),
    Font(R.font.host_grotesk_bold, FontWeight.Bold)
)

private val base = Typography()

val Tipografia = Typography(
    displayLarge = base.displayLarge.copy(fontFamily = Staatliches),
    headlineMedium = base.headlineMedium.copy(fontFamily = Staatliches),
    titleLarge = base.titleLarge.copy(fontFamily = Staatliches),
    titleMedium = base.titleMedium.copy(fontFamily = HostGrotesk, fontWeight = FontWeight.SemiBold),
    bodyLarge = base.bodyLarge.copy(fontFamily = HostGrotesk),
    bodyMedium = base.bodyMedium.copy(fontFamily = HostGrotesk),
    labelLarge = base.labelLarge.copy(fontFamily = HostGrotesk, fontWeight = FontWeight.Bold),
    labelMedium = base.labelMedium.copy(fontFamily = HostGrotesk),
    labelSmall = base.labelSmall.copy(fontFamily = HostGrotesk, fontWeight = FontWeight.SemiBold)
)
