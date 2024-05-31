package ajedrez.logica;

import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Serializable {
    protected Team equipo;
    protected Position actualPos;
    protected boolean moved;
    
    public Piece (Team equipo, Position pos){
        this.equipo = equipo;
        this.actualPos = pos;
        moved = false;
    }
    
    public String getEquipo(){
        return equipo.toString();
    }
    
    public Position getPosition(){
        return actualPos;
    }
    
    public boolean getMoveStatus(){
        return moved;
    }
    
    public void setMoved(){
        moved = true;
    }
    
    public void setPosition(Position pos){
        this.actualPos = pos;
    }
    
    public boolean validCapture(Tablero t, Position p){
        if (t.validPosition(p)){
            //System.out.println("is valid.");
            if (t.getPiece(p) != null){
                //System.out.println("piece is not null: "+ t.getPiece(p));
                if (!t.getPiece(actualPos).getEquipo().equals(t.getPiece(p).getEquipo()))
                 return true;
            }
        }
        //System.out.println("piece is null dawg");
        return false;
    }
    
    public abstract List<Position> getMoves(Tablero t);
    public abstract String getPath();
}
