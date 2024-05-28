
package ajedrez.logica;

import java.util.ArrayList;
import java.util.List;

public class Rey extends Piece{
    public Rey(Team equipo, Position pos){
        super(equipo, pos);
    }
    
    @Override
    public List<Position> getMoves(Tablero t){
        List<Position> posiciones = new ArrayList<>();
        Position nextPosition;
        
        for (int r = actualPos.getRow() - 1; r <= actualPos.getRow() + 1; r++){
            for(int c = actualPos.getColumn() - 1; c <= actualPos.getColumn() + 1; c++){
                nextPosition = new Position(r, c);
                if (t.validPosition(nextPosition)){
                    if (validCapture(t, nextPosition) || t.getPiece(nextPosition) == null)
                        posiciones.add(nextPosition);
                }
            }
        }            
        return posiciones;
    }
    
    @Override
    public String getPath(){
        if (equipo.name().equals("BLANCO"))
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\rey_blanco.png";
        else
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\rey_negro.png";
    }

   @Override
   public String toString(){
       if (equipo.name().equals("BLANCO"))
           return "KB";
       else
           return "KN";
   }    
}
