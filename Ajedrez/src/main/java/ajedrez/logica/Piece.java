package ajedrez.logica;

import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Serializable {
    protected Team equipo;
    protected Position actualPos;
    protected boolean moved;
    
    public Piece(Team equipo, Position position) {
        this.equipo = equipo;
        actualPos = position;
        moved = false;
    }
    
    public String getEquipo() {
        return equipo.toString();
    }
    
    public void setMoved(){
        moved = true;
    }
    
    public void setPosition(Position position) {
        actualPos = position;
    }
    
    public boolean validCapture(Tablero t, Position p) {
        if (t.validPosition(p)) {
            if (t.getPiece(p) != null) {
                if (!t.getPiece(actualPos).getEquipo().equals(t.getPiece(p).getEquipo()))
                    return true;
            }
        }
        return false;
    }
    
    public abstract List<Position> getMoves(Tablero t);
    public abstract String getPath();
    public abstract String getType();
    
}
