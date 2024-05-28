package ajedrez.logica;

import java.util.List;

public abstract class Piece {
    protected Team equipo;
    protected Position actualPos;
    
    public Piece (Team equipo, Position pos){
        this.equipo = equipo;
        this.actualPos = pos;
    }
    
    public String getEquipo(){
        return equipo.name();
    }
    
    public Position getPosition(){
        return actualPos;
    }
    
    public void setPosition(Position pos){
        this.actualPos = pos;
    }
    
    public boolean validCapture(Tablero t, Position p){
        if (t.validPosition(p)){
            System.out.println("is valid.");
            if (t.getPiece(p) != null){
                System.out.println("piece is not null: "+ t.getPiece(p));
                if (!t.getPiece(actualPos).getEquipo().equals(t.getPiece(p).getEquipo()))
                 return true;
            }
        }
        System.out.println("piece is null dawg");
        return false;
    }
    
    public abstract List<Position> getMoves(Tablero t);
    public abstract String getPath();
}
