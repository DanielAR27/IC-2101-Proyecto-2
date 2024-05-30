package ajedrez.logica;

import java.util.ArrayList;
import java.util.List;

public class Torre extends Piece {
    public Torre(Team equipo, Position pos){
        super(equipo, pos);
    }
    
    @Override
    public List<Position> getMoves(Tablero t){
        List<Position> posiciones = new ArrayList<>();
        Position nextPosition;
        
        // Arriba
        for (int r = actualPos.getRow() - 1; r >= 0; r--){
                    nextPosition = new Position(r, actualPos.getColumn());
                    if (t.validPosition(nextPosition)){ // Posición válida.
                        if (t.getPiece(nextPosition) == null) // Espacio vacío.
                            posiciones.add(nextPosition);
                        else if(validCapture(t, nextPosition)){ // Posición es pieza enemiga.
                            posiciones.add(nextPosition);
                            break;
                        }else{ // Posición es pieza del mismo equipo.
                            break;
                        }
                    }else{ // Posición no válida.
                        break;
                    }            
        }
        
 
        // Abajo
        for (int r = actualPos.getRow() + 1; r < 8; r++){
                    nextPosition = new Position(r, actualPos.getColumn());
                    if (t.validPosition(nextPosition)){
                        if (t.getPiece(nextPosition) == null)
                            posiciones.add(nextPosition);
                        else if(validCapture(t, nextPosition)){
                            posiciones.add(nextPosition);
                            break;
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }            
        }
        
        //Izquierda
        for (int c = actualPos.getColumn() - 1; c >= 0; c--){
                    nextPosition = new Position(actualPos.getRow(), c);
                    if (t.validPosition(nextPosition)){
                        if (t.getPiece(nextPosition) == null)
                            posiciones.add(nextPosition);
                        else if(validCapture(t, nextPosition)){
                            posiciones.add(nextPosition);
                            break;
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }            
        }
        
        // Derecha
        for (int c = actualPos.getColumn() + 1; c < 8; c++){
                    nextPosition = new Position(actualPos.getRow(), c);
                    if (t.validPosition(nextPosition)){
                        if (t.getPiece(nextPosition) == null)
                            posiciones.add(nextPosition);
                        else if(validCapture(t, nextPosition)){
                            posiciones.add(nextPosition);
                            break;
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }            
        }        
        
        return posiciones;
    }
    
    @Override
    public String getType() {
        return "T"; // Torre
    }
    
    @Override
    public String getPath(){
        if (equipo.toString().equals("B"))
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\torre_blanca.png";
        else
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\torre_negra.png";
    }

  @Override
   public String toString(){
       if (equipo.name().equals("BLANCO"))
           return "TB";
       else
           return "TN";
   }
}
