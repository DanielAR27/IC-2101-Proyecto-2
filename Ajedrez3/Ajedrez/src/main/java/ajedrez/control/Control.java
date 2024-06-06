package ajedrez.control;

import ajedrez.logica.Peon;
import ajedrez.logica.Piece;
import ajedrez.logica.Position;
import ajedrez.logica.Rey;
import ajedrez.logica.Tablero;
import ajedrez.logica.Torre;
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
    private String jugador1;
    private String jugador2;
    private static Control instance = null;
    private String turnoActual;
    private Tablero board;
    
    // Constructor
    private Control(){
        board = new Tablero();
        actualPosition = null;
        turnoActual = "B";
    }
    
    public static Control getInstance(){
        instance = new Control();
        return instance;
    }
    
    public Tablero getTablero(){
        return board;
    }
    
   private void changeTeam(){
       if (turnoActual.equals("B")){
           turnoActual = "N";
       }else{
           turnoActual = "B";
       }
   }    
    
    public void reiniciarJuego(){
        board = new Tablero();
        actualPosition = null;
        turnoActual = "B";
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
            if (actualPosition == null){
                actualPosition = nextPos;
                return 0;
            }else{
                Piece firstPiece = board.getPiece(actualPosition);
                Piece secondPiece = board.getPiece(nextPos);
                boolean castlingIdentified = (firstPiece instanceof Rey) && (secondPiece instanceof Torre) ||
                        (secondPiece instanceof Rey) && (firstPiece instanceof Torre);
                if (castlingIdentified){
                    System.out.println("castling identificado");
                    boolean castlingDecision = firstPiece instanceof Rey?
                            board.validCastling(firstPiece,secondPiece):
                            board.validCastling(secondPiece, firstPiece);
                    return castlingDecision? 1:-1;
                }else{
                    actualPosition = nextPos;
                    return 0;
                }                
            }
        // En caso de que la jugada sea válida entonces se retorna un uno.
        }else if (board.validMove(actualPosition, nextPos)){
            Piece piece = board.getPiece(actualPosition);
            if (piece instanceof Peon && (nextPos.getRow() == 0 || nextPos.getRow() == 7))
                return 2;
            else
                return 3;
        // Si no se cumplen ninguno de los dos casos mencionados entonces se retorna un menos uno
        // indicando que no se puede efectuar la jugada.
        }else{
            System.out.println("jugada no valida");
            return -1;
        }
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
   
    public void castlePlay(String kingBox, String rookBox, boolean towerAtStart){
           List<Integer> kingCoords = (ArrayList) DataVerificator.boxPositionValues(kingBox);
           List<Integer> rookCoords = (ArrayList) DataVerificator.boxPositionValues(rookBox);

           Position kingPos = obtenerPosition(kingCoords.get(0), kingCoords.get(1));
           Position rookPos = obtenerPosition(rookCoords.get(0), rookCoords.get(1));
           actualPosition = null;

           board.castleMove(kingPos, rookPos, towerAtStart);
           board.printTablero();
           changeTeam();
    }
    
    public boolean promotePlay(String positionBox, String type){
        List<Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);

        String pieceInfo = board.getPiece(actualPosition).toString();
        String actualBox = getActualPositionBox();
        Position nextPos = obtenerPosition(coords.get(0), coords.get(1));
        board.promotionMove(actualPosition, nextPos, turnoActual, type);
        board.printTablero();
        actualPosition = null;

        // Se verifica si hay jaque mate
        String opposingTeam = turnoActual.equals("B")? "N"  :"B";
        if (board.jaqueMate(opposingTeam)){
            return true; // Termina el juego si hay jaque mate
        }

        changeTeam();
        return false;
    }
   
    public boolean changePlayer(String positionBox){
        List<Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
        
        String pieceInfo = board.getPiece(actualPosition).toString();
        String actualBox = getActualPositionBox();
        Position nextPos = obtenerPosition(coords.get(0), coords.get(1));
        board.movePiece(actualPosition, nextPos);
        board.printTablero();
        actualPosition = null;
       
        
        // Se verifica si hay jaque mate
        String opposingTeam = turnoActual.equals("B") ? "N" : "B";
        if (board.jaqueMate(opposingTeam)) {
            return true; // Termina el juego si hay jaque mate
        }

        changeTeam();
        return false;
    }
   
   public String getEquipoActual(){
       return turnoActual.equals("B")? "blanco":"negro";
   }
   
   public String getJugadorActual(){
       return turnoActual.equals("B")? jugador1:jugador2;
   }
   
   public String getJugadorOpuesto(){
       return turnoActual.equals("B")? jugador2:jugador1;
   }
   
   public void savePlayerNames(String jugador1, String jugador2){
       this.jugador1 = jugador1;
       this.jugador2 = jugador2;
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