# Porra DAM · MP.3 Primer ejercicio

Francisco Pardo Hernández · PMDM · 2º DAM · IES El Campico

## 1. Pantalla Jornada repartida por capas (MP.2 paso 1)

Como en la tabla de la pantalla de login del 2.1.2. **↻** marca lo que se repite y por eso es un componente reutilizable.

| Elemento de la pantalla | Capa | Dónde está en el código | Por qué |
|---|---|---|---|
| Barra superior «Porra DAM · Jornada 1» | Presentación | `PantallaJornada` (`TopAppBar` del `Scaffold`) | Es interfaz: solo muestra un título. |
| Foto del estadio de fondo | Presentación | `res/drawable-nodpi/fondo_pantalla_hd.webp`, `FondoEstadio` | Recurso visual, no tiene lógica. |
| Tarjeta de un partido ↻ (5 por jornada) | Presentación | `ui/components/PartidoCard` | Se ve y se toca; recibe el partido y avisa de los cambios. |
| Fila de un equipo: escudo + nombre ↻ (2 por partido y otra vez en Resultados) | Presentación | `EscudoEquipo` + `ColumnaEquipo` dentro de `PartidoCard` | Componente reutilizable; convierte el `Long` del color en `Color`. |
| Selector de goles (+, número, −) ↻ (2 por partido) | Presentación | `ui/components/MarcadorSelector` | Solo pinta el número y avisa con `onSumar`/`onRestar`; no guarda nada. |
| Botón «Guardar pronósticos» | Presentación | `ExtendedFloatingActionButton` en `PantallaJornada` | La vista avisa; quien guarda es el ViewModel. |
| Pronósticos en pantalla y si ya están guardados | Presentación (estado) | `JornadaViewModel` | El ViewModel mantiene el estado y sobrevive al giro del móvil (2.1.3). |
| Colores, tipografías y textos | Presentación | `ui/theme/`, `res/font/`, `res/values/strings.xml` | Recursos de la interfaz, definidos una sola vez. |
| Barra inferior Jornada / Resultados / Clasificación | Presentación | `PantallaJornada` (EXTRA, se completa en el Tema 3) | Navegación: es interfaz. |
| Signo 1 / X / 2 de un marcador | Dominio | `Marcador.signo()` | Regla del juego, no depende de la pantalla. |
| Regla de puntuación (8 exacto; +2 signo; +2 por cada equipo con los goles acertados) | Dominio | `domain/calcularPuntos` | Lógica de negocio pura, probada con tests sin emulador. |
| Equipos (nombre, iniciales, color) | Datos | `data/model/Equipo` | El modelo no importa nada de Compose: el color es un `Long`. |
| Partido y marcador | Datos | `data/model/Partido`, `data/model/Marcador` | Clases de datos. |
| Los 5 partidos de la Jornada 1 | Datos | `data/JornadaDePrueba` | Única fuente de datos de prueba; en el Tema 3 se cambia por BD/red sin tocar el resto. |

**Comprueba.** `calcularPuntos` está en `domain/` y no dentro de un Composable porque es una regla de negocio: la usan Resultados y Clasificación, se prueba con tests sin emulador y, si mañana cambia la puntuación, se toca una sola función y los tests avisan si algo se rompe.

## 4. El partido mal calculado en el mockup de Resultados

| Partido | Real | Porra | Mockup | Según la regla |
|---|---|---|---|---|
| Atlético Madrid – Real Madrid | 2-1 | 2-1 | +8 | 8 (exacto) ✓ |
| FC Barcelona – Villarreal CF | 3-0 | 2-0 | +4 | 2 signo + 2 goles visitante = 4 ✓ |
| Sevilla FC – Real Sociedad | 1-1 | 1-0 | +2 | 2 goles local = 2 ✓ |
| **Betis – Valencia CF** | **2-1** | **1-1** | **0** | **2 goles visitante = 2 ✗** |
| Athletic Club – Celta de Vigo | 1-0 | 1-0 | +8 | 8 (exacto) ✓ |

**Betis – Valencia está mal:** no se acierta el signo (1 frente a X) ni los goles del Betis, pero sí el gol del Valencia (1 = 1), así que son **2 puntos, no 0**. Por eso el total de la jornada sería **+24** y no +22.

Test añadido en `CalcularPuntosTest`:

```kotlin
@Test fun betisValenciaDa2YNo0() =
    assertEquals(2, calcularPuntos(Marcador(2, 1), Marcador(1, 1)))
```

## 5. EscudoEquipo en el panel Design

`EscudoEquipoPreview` muestra tres equipos distintos (Atlético Marina, CF Albufera y Real Sierra). Se ve en el panel **Split/Design** al abrir `ui/components/EscudoEquipo.kt`.
