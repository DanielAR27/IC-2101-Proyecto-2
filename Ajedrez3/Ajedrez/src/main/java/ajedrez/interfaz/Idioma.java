package ajedrez.interfaz;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase Singleton que maneja los textos y el estado del idioma en todo el juego.
 */
public class Idioma {
    
    private static Idioma instance;
    private boolean enEspanol;
    
    private Map<String, String> textosEspanol;
    private Map<String, String> textosIngles;
    
    private Idioma() {
        enEspanol = true; // Idioma por defecto
        textosEspanol = new HashMap<>();
        textosIngles = new HashMap<>();
        inicializarTextos();
    }
    
    public static Idioma getInstance() {
        if (instance == null) {
            instance = new Idioma();
        }
        return instance;
    }
    
    public boolean isEnEspanol() {
        return enEspanol;
    }
    
    public void setEnEspanol(boolean enEspanol) {
        this.enEspanol = enEspanol;
    }
    
    public String get(String clave) {
        if (enEspanol) {
            return textosEspanol.getOrDefault(clave, clave);
        } else {
            return textosIngles.getOrDefault(clave, clave);
        }
    }
    
    private void inicializarTextos() {
        // --- Interfaz Principal (Ajedrez.java) ---
        textosEspanol.put("title_ajedrez", "Juego de Ajedrez");
        textosIngles.put("title_ajedrez", "Chess Game");
        
        textosEspanol.put("btn_empezar", "Empezar Partida");
        textosIngles.put("btn_empezar", "Start Game");
        
        textosEspanol.put("btn_guardar", "Guardar");
        textosIngles.put("btn_guardar", "Save");
        
        textosEspanol.put("btn_cargar", "Cargar Partida");
        textosIngles.put("btn_cargar", "Load Match");
        
        textosEspanol.put("btn_rendirse", "Rendirse");
        textosIngles.put("btn_rendirse", "Surrender");
        
        textosEspanol.put("btn_tablas", "Tablas");
        textosIngles.put("btn_tablas", "Tie");
        
        textosEspanol.put("btn_capturadas", "Ver capturadas");
        textosIngles.put("btn_capturadas", "View captured");
        
        textosEspanol.put("lbl_jugador_actual", "Jugador actual:");
        textosIngles.put("lbl_jugador_actual", "Current player:");
        
        textosEspanol.put("lbl_equipo_actual", "Equipo actual:");
        textosIngles.put("lbl_equipo_actual", "Current team:");
        
        textosEspanol.put("lbl_casilla_seleccionada", "Casilla seleccionada:");
        textosIngles.put("lbl_casilla_seleccionada", "Selected box:");
        
        textosEspanol.put("lbl_piezas_tablero", "Piezas del Tablero:");
        textosIngles.put("lbl_piezas_tablero", "Board Pieces:");
        
        // --- Equipos ---
        textosEspanol.put("blanco", "Blanco");
        textosIngles.put("blanco", "White");
        textosEspanol.put("negro", "Negro");
        textosIngles.put("negro", "Black");
        
        // --- Botones Genéricos ---
        textosEspanol.put("btn_si", "S\u00ed");
        textosIngles.put("btn_si", "Yes");
        textosEspanol.put("btn_no", "No");
        textosIngles.put("btn_no", "No");
        textosEspanol.put("btn_aceptar", "Aceptar");
        textosIngles.put("btn_aceptar", "OK");
        
        // --- Mensajes de Confirmación (Ajedrez.java) ---
        textosEspanol.put("msg_cambiar_idioma", "\u00bfEst\u00e1 seguro de cambiar su idioma?");
        textosIngles.put("msg_cambiar_idioma", "Are you sure you want to change the language?");
        textosEspanol.put("title_cambiar_idioma", "Cambiar Idioma");
        textosIngles.put("title_cambiar_idioma", "Change Language");
        
        textosEspanol.put("msg_seguro_rendirse", "\u00bfEst\u00e1 seguro el jugador {player} de rendirse?");
        textosIngles.put("msg_seguro_rendirse", "Is player {player} sure to surrender?");
        textosEspanol.put("title_advertencia", "Advertencia");
        textosIngles.put("title_advertencia", "Warning");
        
        textosEspanol.put("msg_abandono", "Abandono");
        textosIngles.put("msg_abandono", "Surrender");
        
        textosEspanol.put("msg_ganador", "El jugador {player} ha ganado la partida.");
        textosIngles.put("msg_ganador", "Player {player} has won the match.");
        
        textosEspanol.put("msg_desea_tablas", "Jugador {player} \u00bfDesea hacer tablas?");
        textosIngles.put("msg_desea_tablas", "Player {player} do you want a tie?");
        
        textosEspanol.put("msg_tablas_aceptadas", "Ambos jugadores han empatado la partida.");
        textosIngles.put("msg_tablas_aceptadas", "Both players have tied the match.");
        textosEspanol.put("title_tablas", "Tablas");
        textosIngles.put("title_tablas", "Tie");
        
        textosEspanol.put("msg_volver_jugar", "\u00bfDesea volver a jugar otra partida?");
        textosIngles.put("msg_volver_jugar", "Do you want to play another match?");
        textosEspanol.put("title_fin_juego", "Fin del Juego");
        textosIngles.put("title_fin_juego", "Game Over");
        
        textosEspanol.put("msg_pos_invalida", "La posici\u00f3n no es v\u00e1lida, intente de nuevo.");
        textosIngles.put("msg_pos_invalida", "Invalid position, try again.");
        textosEspanol.put("title_notificacion", "Notificaci\u00f3n");
        textosIngles.put("title_notificacion", "Notification");
        
        // --- Tooltips Banderas ---
        textosEspanol.put("tooltip_ingles", "Switch to English");
        textosIngles.put("tooltip_ingles", "Switch to English");
        textosEspanol.put("tooltip_espanol", "Cambiar a Espa\u00f1ol");
        textosIngles.put("tooltip_espanol", "Cambiar a Espa\u00f1ol");
        
        // --- Ventana PlayerNames ---
        textosEspanol.put("lbl_nombres_jugadores", "Nombres de los Jugadores");
        textosIngles.put("lbl_nombres_jugadores", "Player Names");
        
        textosEspanol.put("lbl_jugador_1", "Jugador #1 (equipo blanco)");
        textosIngles.put("lbl_jugador_1", "Player #1 (white team)");
        
        textosEspanol.put("lbl_jugador_2", "Jugador #2 (equipo negro)");
        textosIngles.put("lbl_jugador_2", "Player #2 (black team)");
        
        textosEspanol.put("btn_listo", "Listo");
        textosIngles.put("btn_listo", "Ready");
        
        textosEspanol.put("msg_nombres_vacios", "No ha ingresado el nombre para uno de los jugadores.");
        textosIngles.put("msg_nombres_vacios", "You have not entered a name for one of the players.");
        textosEspanol.put("title_error", "Error");
        textosIngles.put("title_error", "Error");
        
        // --- Ventana PromotePawn ---
        textosEspanol.put("title_promocion", "Promoci\u00f3n de Pe\u00f3n");
        textosIngles.put("title_promocion", "Pawn Promotion");
        
        textosEspanol.put("lbl_seleccionar_pieza", "Seleccione una pieza");
        textosIngles.put("lbl_seleccionar_pieza", "Select a piece");
        
        textosEspanol.put("btn_reina", "Reina");
        textosIngles.put("btn_reina", "Queen");
        
        textosEspanol.put("btn_torre", "Torre");
        textosIngles.put("btn_torre", "Rook");
        
        textosEspanol.put("btn_caballo", "Caballo");
        textosIngles.put("btn_caballo", "Knight");
        
        textosEspanol.put("btn_alfil", "Alfil");
        textosIngles.put("btn_alfil", "Bishop");
        
        // --- Ventana SaveMatch / LoadMatch ---
        textosEspanol.put("title_guardar", "Guardar Partida");
        textosIngles.put("title_guardar", "Save Match");
        
        textosEspanol.put("title_cargar", "Cargar Partida");
        textosIngles.put("title_cargar", "Load Match");
        
        textosEspanol.put("lbl_ruta_directorio", "Ruta de directorio:");
        textosIngles.put("lbl_ruta_directorio", "Directory path:");
        
        textosEspanol.put("lbl_nombre_archivo", "Nombre del archivo:");
        textosIngles.put("lbl_nombre_archivo", "File name:");
        
        textosEspanol.put("btn_abrir_dir", "Abrir Directorio");
        textosIngles.put("btn_abrir_dir", "Open Directory");
        
        textosEspanol.put("btn_abrir_archivo", "Abrir Archivo");
        textosIngles.put("btn_abrir_archivo", "Open File");
        
        // --- Ventana Capturadas ---
        textosEspanol.put("title_capturadas", "Capturadas");
        textosIngles.put("title_capturadas", "Captured Pieces");
    }
    
    // Helper para mostrar mensajes (OK / Aceptar) en el idioma correcto
    public static void mostrarMensaje(java.awt.Component parent, String mensaje, String titulo, int messageType) {
        Idioma idioma = Idioma.getInstance();
        Object[] options = { idioma.get("btn_aceptar") };
        javax.swing.JOptionPane.showOptionDialog(
            parent, mensaje, titulo,
            javax.swing.JOptionPane.DEFAULT_OPTION,
            messageType,
            null, options, options[0]
        );
    }
}
