package org.elcampico.porradam.data.model

data class Equipo(
    val nombre: String,
    val iniciales: String,
    val color: Long          // 0xFFB3261E: el modelo no depende de Compose
)
