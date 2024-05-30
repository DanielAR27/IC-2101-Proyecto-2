package ajedrez.control;

import ajedrez.logica.Piece;
import ajedrez.logica.Position;
import ajedrez.logica.Tablero;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Control implements Serializable {
    private Position actualPosition;
    private String jugadasHistorial;
    private String turnoActual;
    private Tablero board;
    private int contador = 1;
    
    // Constructor
    public Control(){
        board = new Tablero();
        actualPosition = null;
        turnoActual = "B";
        jugadasHistorial = "";
    }
    
    public Tablero getTablero(){
        return board;
    }
    
    public void reiniciarJuego(){
        board = new Tablero();
        actualPosition = null;
        turnoActual = "B";
        jugadasHistorial = "";
    }
        
    public Position obtenerPosition(int x, int y){
        Position pos = new Position(x, y);
        if (!board.validPosition(pos))
            throw new IllegalArgumentException("Error: Position doesn't exist in board.");
        return pos;
        }
    
   public int jugadorJuega(String positionBox){
       List<Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
       Position nextPos = obtenerPosition(coords.get(0), coords.get(1));
       // Si la pieza es del mismo equipo que el equipo que tiene derecho a hacer la actual jugada
       // entonces se asigna como posición actual y se retorna un cero.
        if (board.sameTeam(nextPos, turnoActual)){
            actualPosition = nextPos;
            return 0;
        // En caso de que la jugada sea válida entonces se retorna un uno.
        }else if (board.validMove(actualPosition, nextPos)){
             return 1;
        // Si no se cumplen ninguno de los dos casos mencionados entonces se retorna un menos uno
        // indicando que no se puede efectuar la jugada.
        }else{
            return -1;
        }
    }
   
   // Get Plays Historial: Obtiene el historial de jugadas.
   public String getHistorialPlays(){
       return jugadasHistorial;
   }
   
   // Get Actual Position Box: Obtener la casilla del tablero en base a la posición actual.
   public String getActualPositionBox(){
       String positionBox = "";
       if (actualPosition != null){
            positionBox += DataVerificator.IntToLetter(actualPosition.getColumn());
            positionBox += 8 - actualPosition.getRow();
       }
       return positionBox;
   }
   
   
   // Path Constructor: Recibe las coordenadas de una posición y determina cual es su dirección de imagen.
   public String pathConstructor(int x, int y){
       Piece piece = board.getPiece(obtenerPosition(x, y));
       if (piece == null){
           return null;
       }else
           return piece.getPath();
    }
   
   public boolean changePlayer(String positionBox){
       List<Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
       String pieceInfo = board.getPiece(actualPosition).toString();
       String actualBox = getActualPositionBox();
       Position nextPos = obtenerPosition(coords.get(0), coords.get(1));
       boolean gameOver = board.movePiece(actualPosition, nextPos);
       actualPosition = null;
       if (gameOver == false){
            if (turnoActual.equals("B")){
                jugadasHistorial += contador + ". " + actualBox + " → " + positionBox + " [" +pieceInfo +"]" + " ".repeat(10);
                turnoActual = "N";
            }else{
                jugadasHistorial +=  actualBox + " → " + positionBox + " [" + pieceInfo +"] \n";
                turnoActual = "B";
                contador++;
            }
       }
       return gameOver;
   }
   
   public String getEquipoActual(){
       return turnoActual.equals("B")? "blanco":"negro";
   }
   
   public void printTablero(){
       board.printTablero();
   }
     
    // Método para guardar los datos, recibe como parámetro un control.
    public static boolean guardarDatos(Control control, String path){
        try{
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(control);
            stream.close();
            file.close();
            System.out.println("Se han guardado los datos exitosamente.");
            return true;
        }catch(Exception e){
                System.out.println("Error: " + e);
                return false;
         }
    }
    
    // Método para cargar los datos, retorna un control.
    public static Control cargarDatos(String path) throws FileNotFoundException, IOException, ClassNotFoundException{;
        Control control;
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream stream = new ObjectInputStream(file);
        control = (Control) stream.readObject();
        return control;
    }
}
