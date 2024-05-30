package ajedrez.logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tablero implements Serializable{
    private List <ArrayList<Piece>> tablero;
    
    private List <Position> whitePositions;
    private List <Position> blackPositions;

    public Tablero(){
        whitePositions = new ArrayList<>();
        blackPositions = new ArrayList<>();
        tablero = setTablero();
    }
       
    private List<ArrayList<Piece>> setTablero(){
        // Se inicializa el tablero.
        List<ArrayList<Piece>> board = new  ArrayList<>();
        
        // Se agregan las filas al tablero.
        for (int i = 0; i < 8; i++){
            board.add(new ArrayList<>());
        }
        // Se va agregando piezas al tablero.
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Position piecePos = new Position (i, j);
                // Agregar piezas negras.
                if (i < 2){
                    // Agregar torre del equipo negro.
                    if (i == 0 && (j == 0 || j == 7))
                        board.get(i).add(PieceFactory.createPiece("T", "N", piecePos));
                    // Agregar caballo del equipo negro.
                    else if (i == 0 && (j == 1 || j == 6))
                       board.get(i).add(PieceFactory.createPiece("C", "N", piecePos));
                    // Agregar alfil del equipo negro.
                    else if (i == 0 && (j == 2 || j == 5))
                        board.get(i).add(PieceFactory.createPiece("A", "N", piecePos));
                    // Agregar rey del equipo negro.
                    else if (i == 0 && j == 3)
                        board.get(i).add(PieceFactory.createPiece("K", "N", piecePos));
                    // Agregar reina del equipo negro.
                    else if(i == 0 && j == 4)
                        board.get(i).add(PieceFactory.createPiece("Q", "N", piecePos));
                    // Agregar peon del equipo negro.
                    else
                        board.get(i).add(PieceFactory.createPiece("P", "N", piecePos));
                 // Se agrega la posición a las posiciones de piezas negras.
                 blackPositions.add(piecePos);
                // Agregar piezas blancas.
                }else if(i > 5){
                    // Agregar peon del equipo blanco.
                    if (i == 6)
                         board.get(i).add(PieceFactory.createPiece("P", "B", piecePos));
                    // Agregar torre del equipo blanco.
                    else if (i == 7 && (j == 0 || j == 7))
                        board.get(i).add(PieceFactory.createPiece("T", "B", piecePos));
                    // Agregar caballo del equipo blanco.
                    else if (i == 7 && (j == 1 || j == 6))
                        board.get(i).add(PieceFactory.createPiece("C", "B", piecePos));
                    // Agregar aflfil del equpo blanco.
                    else if (i == 7 && (j == 2 || j == 5))
                        board.get(i).add(PieceFactory.createPiece("A", "B", piecePos));
                    // Agregar rey del equipo blanco.
                    else if (i == 7 && j == 3)
                        board.get(i).add(PieceFactory.createPiece("K", "B", piecePos));
                    else
                        board.get(i).add(PieceFactory.createPiece("Q", "B", piecePos));
                 // Se agrega la posición a las posiciones de piezas blancas.
                 whitePositions.add(piecePos);
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
        
    public boolean movePiece(Position actualPos, Position nextPos){
        if (!validPosition(actualPos) || !validPosition(nextPos))
            throw new IllegalArgumentException("Position is not valid.");
        // Se obtiene cual sea la pieza.
        Piece piece = getPiece(actualPos);
        // Si es del equipo blanco entonces se elimina la casilla anterior y se agrega la nueva a la lista.
        if ("B".equals(piece.getEquipo())){
            whitePositions.remove(actualPos);
            whitePositions.add(nextPos);
        // El mismo caso para el equipo negro.
        }else{
            blackPositions.remove(actualPos);
            blackPositions.add(nextPos);
        }
        // Se cambia la posición de la pieza.
        piece.setPosition(nextPos);
        // Se obtiene la pieza de la siguiente posicion.
        Piece nextPiece = getPiece(nextPos);
        // En caso de que la pieza no sea un espacio vacío entonces se eliminará de la lista correspondiente
        // y esto equivale a una "captura".
        if (nextPiece != null){
            if ("B".equals(piece.getEquipo()))
                whitePositions.remove(nextPos);
            else
                blackPositions.remove(nextPos);
        }
        // Se cambia a donde estaba por null en el tablero.
        tablero.get(actualPos.getRow()).set(actualPos.getColumn(), null);
        // En la nueva posición se va a colocar la pieza en tablero.
        tablero.get(nextPos.getRow()).set(nextPos.getColumn(), piece);
        System.out.println("Posiciones blancas: " + whitePositions);
        System.out.println("Posiciones negras: " + blackPositions);
        return nextPiece instanceof Rey;
    }
            
    public List<Position> getSecurePositions(List<Position> kingPositions, Position actualPosition){
       List<Position> checkPositions = new ArrayList<>();
        // Se obtiene la pieza.
        Piece piece = getPiece(actualPosition);
       // Se pregunta cual lista tomar en base al equipo de la pieza del rey.
       ArrayList<Position> iterable =  piece.getEquipo().equals("B")? (ArrayList) blackPositions: (ArrayList) whitePositions;
        
        // Primero se obtienen las posiciones que pueden resultar en Jaque.
        for (Position p : kingPositions){
            // Se obtiene la pieza en donde se va a probar.
            Piece comingPiece = getPiece(p);   
            // Se cambia a donde estaba por null en el tablero.
           tablero.get(actualPosition.getRow()).set(actualPosition.getColumn(), null);
           // En la nueva posición se va a colocar la pieza en tablero.
           tablero.get(p.getRow()).set(p.getColumn(), piece);
           printTablero();
           // Se pregunta cual lista tomar en base al equipo de la pieza del rey.
            for (int i = 0; i < iterable.size(); i++){
                Position enemyPosition = iterable.get(i);
                System.out.println(enemyPosition);
                Piece enemyPiece = getPiece(enemyPosition);
                System.out.println(enemyPiece);
                List<Position> enemyPositions = enemyPiece.getMoves(this);
                System.out.println("ENEMY POSITIONS: " + enemyPositions);
                System.out.println(enemyPositions.contains(p));
                System.out.println("-".repeat(20));
                
                if (enemyPositions.contains(p) && !checkPositions.contains(p))
                    checkPositions.add(p);
            }
         // Se cambia a donde estaba de regreso a la pieza original.
           tablero.get(actualPosition.getRow()).set(actualPosition.getColumn(), piece);
           // La pieza en donde se probó también se va a regresar a su valor original.
           tablero.get(p.getRow()).set(p.getColumn(), comingPiece);            
        }
        
        System.out.println("Posiciones de jaque: " + checkPositions);
        kingPositions.removeAll(checkPositions);
        return kingPositions;
        
 }
    
    public boolean validMove(Position actualPos, Position nextPos){
        if (actualPos == null){
            //System.out.println("position is null.");
            return false;
        }else{
            Piece piece = getPiece(actualPos);
            if (piece == null){
                //System.out.println("piece is null");
                return false;}
            else if (piece instanceof Rey){
                List <Position> positions = (ArrayList) getSecurePositions(piece.getMoves(this), actualPos);
                for (Position p: positions){
                    if (p.equals(nextPos))
                    return true;
                }
                return false;
            }else{
                List <Position> positions = (ArrayList) piece.getMoves(this);
                System.out.println(positions);
                for (Position p: positions){
                    if (p.equals(nextPos))
                        return true;
                }
                return false;
            }
        }
    }
    
    public boolean validPosition(Position p){
        return p.getRow() >= 0 && 7 >= p.getRow() && p.getColumn() >= 0 && 7 >= p.getColumn();
    }
    
    public boolean sameTeam(Position p, String team){
        Piece piece = getPiece(p);
        if (piece == null){
            return false;
        }
        return piece.equipo.toString().equals(team);
    }
        
    public void printTablero(){
        for(int i = 0; i < 8; i++){
            System.out.println(tablero.get(i));
        }
        System.out.println("");
    }

}
