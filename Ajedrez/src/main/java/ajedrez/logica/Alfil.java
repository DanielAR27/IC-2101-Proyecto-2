package ajedrez.logica;

import java.util.ArrayList;
import java.util.List;

public class Alfil extends Piece{
    public Alfil(Team equipo, Position pos){
        super(equipo, pos);
    }
    @Override
    public List<Position> getMoves(Tablero t){
            List<Position> posiciones = new ArrayList<>();
            Position nextPosition;
            // Diagonal izquierda
            for (int r = actualPos.getRow() - 1, c = actualPos.getColumn() - 1; r >= 0; r--, c--){
                    nextPosition = new Position(r , c);
                    System.out.println(nextPosition);
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
            
            System.out.println("-".repeat(20));
            // Diagonal derecha:
            for (int r = actualPos.getRow() - 1, c = actualPos.getColumn() + 1; c < 8; r--, c++){
                    nextPosition = new Position(r , c);
                    System.out.println(nextPosition);
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
            
            System.out.println("-".repeat(20));
            // Diagonal izquierda inversa.
            for (int r = actualPos.getRow() + 1, c = actualPos.getColumn() - 1; r < 8; r++, c--){
                    nextPosition = new Position(r , c);
                    System.out.println(nextPosition);
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
            
            System.out.println("-".repeat(20));
             // Diagonal derecha inversa.
            for (int r = actualPos.getRow() + 1, c = actualPos.getColumn() + 1; c < 8; r++, c++){
                    nextPosition = new Position(r , c);
                    System.out.println(nextPosition);
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
        

        /*// Diagonal izquierda inversa.
        for (int r = actualPos.getRow(); r < 8; r++){
            for (int c = actualPos.getColumn(); c >= 0; c--){
                nextPosition = new Position(r, c);
                if (validCapture(t, nextPosition) || t.getPiece(nextPosition) != null)
                    posiciones.add(nextPosition);
                else
                    break;
            }
        }

        // Diagonal derecha inversa.
        for (int r = actualPos.getRow(); r < 8; r++){
            for (int c = actualPos.getColumn(); c < 8; c++){
                nextPosition = new Position(r, c);
                if (validCapture(t, nextPosition) || t.getPiece(nextPosition) != null)
                    posiciones.add(nextPosition);
                else
                    break;
            }
        } */       
            
        
        return posiciones;
    }
    
    @Override
    public String getPath(){
        if (equipo.name().equals("BLANCO"))
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\alfil_blanco.png";
        else
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\alfil_negro.png";
    }
    

   @Override
   public String toString(){
       if (equipo.name().equals("BLANCO"))
           return "AB";
       else
           return "AN";
   }    
}
