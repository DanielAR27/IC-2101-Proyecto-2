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
import java.util.ArrayList;
import java.util.List;

public class Control {
    private static Control instance = null;
    private Position actualPosition = null;
    private String turnoActual = "B";
    private Tablero board;
    // Constructor
    private Control(){
        board = new Tablero();
        board.printTablero();
    }
    
    // Método que permite obtener una instancia de control, si esta no se puede obtener entonces crea una.
    public static Control getInstance(){
        if (instance == null){
            try{
                instance = cargarDatos();
            }catch (Exception e){
                System.out.println("Error: " + e.toString());
                System.out.println("Se creará un nuevo control.");
                instance = new Control();
            }
        }
        return instance;
    }
    
    public Position obtenerPosition(int x, int y){
        Position pos = new Position(x, y);
        if (!board.validPosition(pos))
            throw new IllegalArgumentException("Error: Position doesn't exist in board.");
        return pos;
        }
    
     public List<Position> getCheckPositions(Tablero t, String team){
        List <Position> rivalPositions = new ArrayList<Position>();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++){
                System.out.println(i + ", " + j);
                Piece piece = t.getPiece(new Position(i, j));
                if (piece != null){
                    System.out.println(piece);
                    if (!piece.getEquipo().equals(team)){
                            rivalPositions.addAll(piece.getMoves(board));
                    }
                }          
            }
        }
        
        return rivalPositions;
    }
    
   public int jugadorJuega(String positionBox){
       List<Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
       Position nextPos = obtenerPosition(coords.get(0), coords.get(1));
       //System.out.println("Next Position: " + nextPos);
       //if (actualPosition != null)
           //System.out.println("Actual Position: "+ actualPosition);
        if (board.sameTeam(nextPos, turnoActual)){
            actualPosition = nextPos;
            return 0;
        }else if (board.validMove(actualPosition, nextPos)){
            
             return 1;
        }else{
            return -1;
        }
    }
   
   public String getActualPositionBox(){
       String positionBox = DataVerificator.IntToLetter(actualPosition.getColumn());
       positionBox += 8 - actualPosition.getRow();
       return positionBox;
   }
   
   public String pathConstructor(int x, int y){
       Piece piece = board.getPiece(obtenerPosition(x, y));
       //System.out.println(piece);
       if (piece == null){
           return null;
       }else
           return piece.getPath();
    }
   
   public void changePlayer(String positionBox){
       List<Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
       Position nextPos = obtenerPosition(coords.get(0), coords.get(1));
       board.movePiece(actualPosition, nextPos);
       actualPosition = null;
       if (turnoActual.equals("B"))
           turnoActual = "N";
       else
           turnoActual = "B";
   }
     
    // Método para guardar los datos, recibe como parámetro un control.
    public static void guardarDatos(Control control){
        try{
            FileOutputStream file = new FileOutputStream("AjedrezData.bin");
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(control);
            stream.close();
            file.close();
            System.out.println("Se han guardado los datos exitosamente.");
        }catch(Exception e){
                System.out.println("Error: " + e);
         }
    }
    
    // Método para cargar los datos, retorna un control.
    private static Control cargarDatos() throws FileNotFoundException, IOException, ClassNotFoundException{;
        Control control;
        FileInputStream file = new FileInputStream("AjedrezData.bin");
        ObjectInputStream stream = new ObjectInputStream(file);
        control = (Control) stream.readObject();
        return control;
    }
}
