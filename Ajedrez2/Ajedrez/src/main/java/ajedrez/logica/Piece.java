package ajedrez.logica;

import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Serializable {
    protected Team equipo;
    protected Position actualPos;
    
    public Piece(Team equipo, Position pos) {
        this.equipo = equipo;
        this.actualPos = pos;
    }
    
    public String getEquipo() {
        return equipo.toString();
    }
    
    public Position getPosition() {
        return actualPos;
    }
    
    public void setPosition(Position pos) {
        this.actualPos = pos;
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
    
    public abstract String getType(); // Añadir este método abstracto
}
