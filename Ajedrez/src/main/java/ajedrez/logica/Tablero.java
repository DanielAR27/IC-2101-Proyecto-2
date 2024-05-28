package ajedrez.logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tablero {
    List <ArrayList<Piece>> tablero;
    PieceFactory pieceFactory;
    public Tablero(){
        pieceFactory = new PieceFactory();
        tablero = createTablero();
        //printTablero();
    }
   
    private List<ArrayList<Piece>> createTablero(){
        // Se inicializa el tablero.
        List<ArrayList<Piece>> board = new  ArrayList<>();
        // Se agregan las filas al tablero.
        for (int i = 0; i < 8; i++){
            board.add(new ArrayList<>());
        }
        // Se va agregando piezas al tablero.
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                // Agregar piezas negras.
                if (i < 2){
                    // Agregar torre del equipo negro.
                    if (i == 0 && (j == 0 || j == 7))
                        board.get(i).add(pieceFactory.createPiece("T", "N", new Position(i, j)));
                    // Agregar caballo del equipo negro.
                    else if (i == 0 && (j == 1 || j == 6))
                       board.get(i).add(pieceFactory.createPiece("C", "N", new Position(i, j)));
                    // Agregar alfil del equipo negro.
                    else if (i == 0 && (j == 2 || j == 5))
                        board.get(i).add(pieceFactory.createPiece("A", "N", new Position(i, j)));
                    // Agregar rey del equipo negro.
                    else if (i == 0 && j == 3)
                        board.get(i).add(pieceFactory.createPiece("K", "N", new Position(i, j)));
                    // Agregar reina del equipo negro.
                    else if(i == 0 && j == 4)
                        board.get(i).add(pieceFactory.createPiece("Q", "N", new Position(i, j)));
                    // Agregar peon del equipo negro.
                    else
                        board.get(i).add(pieceFactory.createPiece("P", "N", new Position (i, j)));
                // Agregar piezas blancas.
                }else if(i > 5){
                    // Agregar peon del equipo blanco.
                    if (i == 6)
                         board.get(i).add(pieceFactory.createPiece("P", "B", new Position (i, j)));
                    // Agregar torre del equipo blanco.
                    else if (i == 7 && (j == 0 || j == 7))
                        board.get(i).add(pieceFactory.createPiece("T", "B", new Position (i, j)));
                    // Agregar caballo del equipo blanco.
                    else if (i == 7 && (j == 1 || j == 6))
                        board.get(i).add(pieceFactory.createPiece("C", "B", new Position (i, j)));
                    // Agregar aflfil del equpo blanco.
                    else if (i == 7 && (j == 2 || j == 5))
                        board.get(i).add(pieceFactory.createPiece("A", "B", new Position (i, j)));
                    // Agregar rey del equipo blanco.
                    else if (i == 7 && j == 3)
                        board.get(i).add(pieceFactory.createPiece("K", "B", new Position (i, j)));
                    else
                        board.get(i).add(pieceFactory.createPiece("Q", "B", new Position (i, j)));
                // Agregar espacios vacios.    
                }else{
                    board.get(i).add(null);
                }
            }
        }
        return board;
    }
    public Piece getPiece(Position p){
        return tablero.get(p.getRow()).get(p.getColumn());
    }
    
    public void movePiece(Position actualPos, Position nextPos){
        if (!validPosition(actualPos) || !validPosition(nextPos))
            throw new IllegalArgumentException("Position is not valid.");
        // Se obtiene cual sea la pieza.
        Piece piece = getPiece(actualPos);
        // Se cambia la posición de la pieza.
        piece.setPosition(nextPos);
        // Se cambia a donde estaba por null en el tablero.
        tablero.get(actualPos.getRow()).set(actualPos.getColumn(), null);
        // En la nueva posición se va a colocar la pieza en tablero.
        tablero.get(nextPos.getRow()).set(nextPos.getColumn(), piece);
    }
    
    public boolean validMove(Position actualPos, Position nextPos){
        if (actualPos == null){
            System.out.println("position is null.");
            return false;
        }else{
            Piece piece = getPiece(actualPos);
            if (piece == null){
                System.out.println("piece is null");
                return false;}
            List <Position> positions = (ArrayList) piece.getMoves(this);
            for (Position p: positions){
                if (p.samePosition(nextPos))
                    return true;
            }
            return false;
        }
    }
    
    public boolean validPosition(Position p){
        return p.getRow() >= 0 && 7 >= p.getRow() && p.getColumn() >= 0 && 7 >= p.getColumn();
    }
    
    public boolean sameTeam(Position p, String team){
        Piece piece = getPiece(p);
        if (piece == null){
            System.out.println("piece is null same team.");
            return false;
        }
        return piece.equipo.name().equals(team);
    }
    
    // Esta wea para el jaque.
    public boolean checkAvailable(List <Position> positions){
        for (Position p: positions){
            if (getPiece(p) instanceof Rey){
                return true;
            }
        }
        return false;
    }
    
    public void printTablero(){
        for(int i = 0; i < 8; i++){
            System.out.println(tablero.get(i));
        }
        System.out.println("");
    }

}
