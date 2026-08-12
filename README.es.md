<div align="center">

  <img src="Ajedrez3/Ajedrez/src/main/resources/chess_icon_game.png" alt="Chess Logo" width="150" />

  **Juego de Ajedrez - Proyecto Académico de POO**

  Una experiencia clásica de ajedrez llevada al escritorio. Gestiona partidas, realiza movimientos especiales y domina el tablero con una interfaz gráfica intuitiva.

  🌍 *Ahora con soporte para cambio de idioma dinámico (Español/Inglés)*
  
  [🇬🇧 Read in English](README.md)

  <br />

  ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
  ![Swing](https://img.shields.io/badge/Swing-5382A1?style=for-the-badge&logo=java&logoColor=white)
  ![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

  <br />

  [Funcionalidades](#-funcionalidades) · [Arquitectura](#-arquitectura-del-sistema) · [Inicio Rápido](#-inicio-rápido) · [Demostración](#-demostración)

</div>

<br />

---

<br />

## ♟️ Acerca del Proyecto

Este es un proyecto desarrollado para el curso de **Programación Orientada a Objetos**. Consiste en un juego de ajedrez completamente funcional creado con **Java**. 

Este proyecto fue desarrollado con el uso de patrones de diseño de software (como el patrón MVC, Singleton y Factory). Desde movimientos estándar hasta lógicas como el **enroque**, la **promoción de peones** y la **captura al paso**, todo integrado en una interfaz visual (GUI) construida sobre **Java Swing**.

<br />

## ✨ Funcionalidades

<table>
<tr>
<td width="50%">

### ⚔️ Jugabilidad Clásica
- **Validación estricta** de movimientos legales para cada pieza.
- Soporte completo para **enroque** (corto y largo).
- Reglas especiales: **Captura al paso** y **promoción de peón**.
- Algoritmo de detección precisa de **Jaque** y **Jaque Mate**.

</td>
<td width="50%">

### 💾 Gestión de Partida
- Sistema de **guardado y carga** (persistencia de estado).
- Opciones estratégicas: declarar **tablas** o **rendirse**.
- Panel lateral con registro de **piezas capturadas**.
- Interfaz gráfica con resaltado inteligente de casillas.
- 🌍 **Cambio de idioma dinámico**: Cambia la interfaz entre Español e Inglés en tiempo real.

</td>
</tr>
</table>

<br />

---

<br />

## 🏗️ Arquitectura del Sistema

El proyecto sigue una arquitectura **Modelo-Vista-Controlador (MVC)** adaptada, separando la lógica pesada del juego de la renderización de la interfaz de usuario.

```text
IC-2101-Proyecto-2/
│
├── Ajedrez3/Ajedrez/
│   ├── src/main/java/ajedrez/
│   │   ├── control/                  # Lógica de control y validación
│   │   │   ├── Control.java          # Controlador principal
│   │   │   └── DataVerificator.java  # Utilidades
│   │   │
│   │   ├── interfaz/                 # Vistas (Java Swing)
│   │   │   ├── Ajedrez.java          # Ventana principal del tablero
│   │   │   ├── LoadMatch.java        # Menú de carga
│   │   │   └── SaveMatch.java        # Menú de guardado
│   │   │
│   │   └── logica/                   # Modelo (Reglas y piezas)
│   │       ├── Tablero.java          # Gestión del estado de las casillas
│   │       ├── Piece.java            # Clase base para las piezas
│   │       └── PieceFactory.java     # Creador de piezas
│   │
│   ├── src/main/resources/           # Assets (Imágenes, íconos)
│   └── pom.xml                       # Dependencias (Maven)
└── assets/                            # Demostraciones y documentación
```

<br />

### 🧩 Patrones de Diseño Implementados

| Patrón | Descripción y Uso en el proyecto |
|:---|:---|
| **Singleton** | Se utiliza en la clase `Control` para asegurar que solo exista una única instancia global que coordine la partida y el turno actual. |
| **Factory Method** | Implementado en `PieceFactory` para delegar y centralizar la creación de las distintas piezas (Rey, Reina, Peón, etc.) según su tipo. |
| **MVC** | Separación de responsabilidades entre las vistas en Swing (`interfaz`), las reglas de negocio (`logica`) y el mediador (`control`). |

<br />

---

<br />

## 🚀 Inicio Rápido

### Prerrequisitos

Para ejecutar y compilar este proyecto, necesitas tener instalados:

| Software | Versión recomendada |
|:---|:---|
| [Java JDK](https://adoptium.net/) | `v17` o superior |
| [Apache Maven](https://maven.apache.org/) | `v3.6` o superior |

<br />

<details>
<summary><strong>📦 1. Compilar desde el código fuente</strong></summary>

<br />

Para compilar el proyecto y empaquetarlo en un ejecutable portátil, abre una terminal en el directorio donde se encuentra el `pom.xml`:

```bash
cd Ajedrez3/Ajedrez
mvn clean package
```

Esto descargará las dependencias necesarias y generará el archivo `Ajedrez-1.0-SNAPSHOT-jar-with-dependencies.jar` en la carpeta `target/`.

</details>

<details>
<summary><strong>🎮 2. Ejecutar la versión compilada (JAR)</strong></summary>

<br />

Una vez que tengas el proyecto empaquetado, puedes ejecutar directamente el archivo `.jar`. Esto abrirá el splash screen seguido del juego completo.

```bash
cd Ajedrez3/Ajedrez/target
java -jar Ajedrez-1.0-SNAPSHOT-jar-with-dependencies.jar
```
*(💡 En entornos Windows, también puedes simplemente hacer doble clic sobre el archivo `.jar`)*

</details>

<br />

---

<br />

## 📸 Demostración

A continuación, un vistazo a la pantalla de carga principal del juego:

<div align="center">
  <img src="Ajedrez3/Ajedrez/src/main/resources/splash_screen.png" alt="Splash Screen" width="650" />
</div>

<br />

### Pantalla de Inicio
<div align="center">
  <img src="assets/menu_home.png" alt="Menú de Inicio" width="650" />
</div>

<br />

### Movimientos Especiales y Reglas

**Enroque Corto y Largo**  
Para realizar un enroque en este juego, es necesario darle clic primero al **Rey** y posteriormente darle clic a la **Torre** con la que se desea enrocar. El juego calculará si la ruta está libre y si se cumplen las condiciones para realizar la jugada automáticamente.
<div align="center">
  <img src="assets/enroque_corto.gif" alt="Enroque Corto" width="500" />
  <br/><br/>
  <img src="assets/enroque_largo.gif" alt="Enroque Largo" width="500" />
</div>

<br />

**Captura al Paso**  
<div align="center">
  <img src="assets/captura_al_paso.gif" alt="Captura al Paso" width="500" />
</div>

<br />

**Promoción de Peón**  
Cuando un peón alcanza el extremo opuesto del tablero, se despliega un menú que permite al jugador elegir entre promoverlo a Reina, Torre, Caballo o Alfil.
<div align="center">
  <img src="assets/promocion_peon.gif" alt="Promoción de Peón" width="500" />
</div>

<br />

### Jaque y Jaque Mate

**Jaque**  
El sistema de validación del juego impide realizar movimientos que expongan al propio Rey (movimientos "suicidas"). Si se está en jaque, el juego no permitirá avanzar hasta que se realice un movimiento válido que defienda al Rey.
<div align="center">
  <img src="assets/jaque.gif" alt="Jaque" width="500" />
</div>

<br />

**Jaque Mate**  
<div align="center">
  <img src="assets/jaque_mate.gif" alt="Jaque Mate" width="500" />
</div>

<br />

### Gestión de Partidas

**Guardado y Carga de Partida**  
Al guardar una partida, se abrirá un selector de archivos. Es **muy importante** seleccionar la ruta deseada y agregar manualmente la extensión `.bin` al final del nombre (por ejemplo, `partida_01.bin`) para asegurar que el archivo se guarde y se pueda cargar correctamente.

**Guardado de Partida**  
<div align="center">
  <img src="assets/save_game.png" alt="Guardado de Partida" width="500">
</div>

<br />

**Carga de Partida**
<div align="center">
  <img src="assets/load_game.png" alt="Carga de Partida" width="500" />
</div>

<br />

**Tablas y Rendirse**  

**Tablas**  
<div align="center">
  <img src="assets/tablas.png" alt="Tablas" width="500">
</div>

<br />

**Rendirse**
<div align="center">
  <img src="assets/rendirse.png" alt="Rendirse" width="500" />
</div>

### Registro y Utilidades

**Panel de Piezas Capturadas**  
<div align="center">
  <img src="assets/piezas_capturadas.gif" alt="Piezas Capturadas" width="500" />
</div>

---

<br />

## 📄 Licencia y Créditos

Este proyecto se distribuye bajo la licencia **MIT**. Desarrollado por **Daniel Alemán** y **Luis Meza** como proyecto académico para el curso de **Programación Orientada a Objetos (POO)** en el **Instituto Tecnológico de Costa Rica (TEC)**.