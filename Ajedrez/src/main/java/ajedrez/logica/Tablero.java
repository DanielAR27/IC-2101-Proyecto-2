package ajedrez.logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tablero implements Serializable {
    private List<ArrayList<Piece>> tablero;
    private List<Position> whitePositions;
    private List<Position> blackPositions;

    public Tablero() {
        whitePositions = new ArrayList<>();
        blackPositions = new ArrayList<>();
        tablero = setTablero();
    }

    private List<ArrayList<Piece>> setTablero() {
        List<ArrayList<Piece>> board = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<>());
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position piecePos = new Position(i, j);
                if (i < 2) {
                    if (i == 0 && (j == 0 || j == 7))
                        board.get(i).add(PieceFactory.createPiece("T", "N", piecePos));
                    else if (i == 0 && (j == 1 || j == 6))
                        board.get(i).add(PieceFactory.createPiece("C", "N", piecePos));
                    else if (i == 0 && (j == 2 || j == 5))
                        board.get(i).add(PieceFactory.createPiece("A", "N", piecePos));
                    else if (i == 0 && j == 3)
                        board.get(i).add(PieceFactory.createPiece("Q", "N", piecePos));
                    else if (i == 0 && j == 4)
                        board.get(i).add(PieceFactory.createPiece("K", "N", piecePos));
                    else
                        board.get(i).add(PieceFactory.createPiece("P", "N", piecePos));
                    blackPositions.add(piecePos);
                } else if (i > 5) {
                    if (i == 6)
                        board.get(i).add(PieceFactory.createPiece("P", "B", piecePos));
                    else if (i == 7 && (j == 0 || j == 7))
                        board.get(i).add(PieceFactory.createPiece("T", "B", piecePos));
                    else if (i == 7 && (j == 1 || j == 6))
                        board.get(i).add(PieceFactory.createPiece("C", "B", piecePos));
                    else if (i == 7 && (j == 2 || j == 5))
                        board.get(i).add(PieceFactory.createPiece("A", "B", piecePos));
                    else if (i == 7 && j == 3)
                        board.get(i).add(PieceFactory.createPiece("Q", "B", piecePos));
                    else
                        board.get(i).add(PieceFactory.createPiece("K", "B", piecePos));
                    whitePositions.add(piecePos);
                } else {
                    board.get(i).add(null);
                }
            }
        }
        return board;
    }

    public Piece getPiece(Position p) {
        return tablero.get(p.getRow()).get(p.getColumn());
    }
    
    public void setPiece(Position p, Piece piece){
        tablero.get(p.getRow()).set(p.getColumn(), piece);
    }
    
    public void replacePosition(String team, Position beforePos, Position afterPos){
        if ("B".equals(team)) {
            whitePositions.remove(beforePos);
            whitePositions.add(afterPos);
        } else {
            blackPositions.remove(beforePos);
            blackPositions.add(afterPos);
        }        
    }

    public boolean movePiece(Position actualPos, Position nextPos) {
        if (!validPosition(actualPos) || !validPosition(nextPos))
            throw new IllegalArgumentException("Position is not valid.");

        Piece piece = getPiece(actualPos);
        if (piece == null)
            throw new IllegalArgumentException("No piece at actual position.");
        
        Piece nextPiece = getPiece(nextPos);
        replacePosition(piece.getEquipo(), actualPos, nextPos);

        piece.setPosition(nextPos);
        if (nextPiece != null) {
            if ("B".equals(piece.getEquipo()))
                blackPositions.remove(nextPos);
            else
                whitePositions.remove(nextPos);
        }
        
        setPiece(actualPos, null);
        setPiece(nextPos, piece);
        
        return nextPiece instanceof Rey;
    }
    
    public boolean validCastling(Piece king, Piece rook){

        // Verifica que las piezas no se hayan movido anteriormente.
        if (king.moved || rook.moved)
            return false;
        
        int row = king.actualPos.getRow();
                
        int start = king.actualPos.getColumn();
        int stop = rook.actualPos.getColumn();
        int iterator = stop == 0? -1:1;
        for (int i = start + iterator; i < stop; i += iterator){
            Position p = new Position(row, i);
            if (getPiece(p) != null)
                return false;
        }
        
        int kingColumn = iterator == -1? start -2 : start + 2;
        int rookColumn = iterator == -1? stop + 3 : stop - 2;
        
        Position nextKingPos = new Position(row, kingColumn);
        Position nextRookPos = new Position(row, rookColumn);
        
       Tablero simulatedBoard = simulateMove(king.actualPos, nextKingPos);
        simulatedBoard = simulatedBoard.simulateMove(rook.actualPos, nextRookPos);
        
        System.out.println("Resultado del castling: " + !simulatedBoard.jaque(king.getEquipo()));
        // Retorna True si no hay jaque y False si hay jaque.
        return !simulatedBoard.jaque(king.getEquipo());
    }

    public void castleMove(Position kingPos, Position rookPos, boolean towerAtStart){
        Position nextKingPos;
        Position nextRookPos;
        
        System.out.println("castleMove");
        
        Piece king = getPiece(kingPos);
        Piece rook = getPiece(rookPos);
        

        if (towerAtStart){
            nextKingPos = new Position(kingPos.getRow(), kingPos.getColumn() - 2);
            nextRookPos = new Position(rookPos.getRow(), rookPos.getColumn() +3);          
        }else{
            nextKingPos = new Position(kingPos.getRow(), kingPos.getColumn() + 2);
            nextRookPos = new Position(rookPos.getRow(), rookPos.getColumn() - 2);  
        }
        
        // Se reemplaza en la lista de posiciones del mismo equuipo con la posición del rey.
        replacePosition(king.getEquipo(), kingPos, nextKingPos);
        king.setPosition(nextKingPos);
        king.setMoved();
        
        // Se reemplaza en la lista de posiciones del mismo equipo pero con la posición de la torre.
        replacePosition(rook.getEquipo(), rookPos, nextRookPos);
        rook.setPosition(nextRookPos);
        rook.setMoved();
        
        setPiece(kingPos, null);
        setPiece(nextKingPos, king);
 
        setPiece(rookPos, null);
        setPiece(nextRookPos, rook);        
  
    }    

    public boolean validMove(Position actualPos, Position nextPos) {
        if (actualPos == null) {
            return false;
        } else {
            Piece piece = getPiece(actualPos);
            if (piece == null) {
                return false;
            } else {
                List<Position> positions = piece.getMoves(this);
                if (positions.contains(nextPos)) {
                    System.out.println("Contiene la posición.");
                    Tablero simulatedBoard = simulateMove(actualPos, nextPos);
                    if (!simulatedBoard.jaque(piece.getEquipo())) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    public boolean validPosition(Position p) {
        return p.getRow() >= 0 && p.getRow() <= 7 && p.getColumn() >= 0 && p.getColumn() <= 7;
    }

    public boolean sameTeam(Position p, String team) {
        Piece piece = getPiece(p);
        if (piece == null) {
            return false;
        }
        return piece.getEquipo().equals(team);
    }

    public void printTablero() {
        for (int i = 0; i < 8; i++) {
            System.out.println(tablero.get(i));
        }
        System.out.println("");
    }

    public boolean jaque(String team) {
        Position kingPosition = null;
        List<Position> enemyPositions = team.equals("B") ? blackPositions : whitePositions;
        System.out.println(enemyPositions);

        for (Position pos : team.equals("B") ? whitePositions : blackPositions) {
            Piece piece = getPiece(pos);
            if (piece instanceof Rey) {
                kingPosition = pos;
                break;
            }
        }

        if (kingPosition == null) {
            throw new IllegalStateException("No king found for team " + team);
        }

        for (Position pos : enemyPositions) {
            Piece enemyPiece = getPiece(pos);
            if (enemyPiece != null) {
                List<Position> enemyMoves = enemyPiece.getMoves(this);
                if (enemyMoves.contains(kingPosition)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean jaqueMate(String team) {
        if (!jaque(team)) {
            return false;
        }

        List<Position> teamPositions = team.equals("B") ? whitePositions : blackPositions;

        for (Position pos : teamPositions) {
            Piece piece = getPiece(pos);
            if (piece != null) {
                List<Position> moves = piece.getMoves(this);
                for (Position move : moves) {
                    Tablero simulatedBoard = simulateMove(pos, move);
                    if (!simulatedBoard.jaque(team)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Tablero simulateMove(Position actualPos, Position nextPos) {
        Tablero simulatedBoard = cloneBoard();

        Piece piece = simulatedBoard.getPiece(actualPos);
        Piece nextPiece = simulatedBoard.getPiece(nextPos);

        if ("B".equals(piece.getEquipo())) {
            simulatedBoard.whitePositions.remove(actualPos);
            simulatedBoard.whitePositions.add(nextPos);
        } else {
            simulatedBoard.blackPositions.remove(actualPos);
            simulatedBoard.blackPositions.add(nextPos);
        }

        piece.setPosition(nextPos);
        simulatedBoard.tablero.get(actualPos.getRow()).set(actualPos.getColumn(), null);
        simulatedBoard.tablero.get(nextPos.getRow()).set(nextPos.getColumn(), piece);

        if (nextPiece != null) {
            if ("B".equals(piece.getEquipo())) {
                simulatedBoard.blackPositions.remove(nextPos);
            } else {
                simulatedBoard.whitePositions.remove(nextPos);
            }
        }

        return simulatedBoard;
    }

    private Tablero cloneBoard() {
        Tablero newBoard = new Tablero();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position pos = new Position(i, j);
                Piece piece = this.getPiece(pos);
                if (piece != null) {
                    newBoard.tablero.get(i).set(j, PieceFactory.createPiece(piece.getType(), piece.getEquipo(), pos));
                } else {
                    newBoard.tablero.get(i).set(j, null);
                }
            }
        }

        newBoard.whitePositions = new ArrayList<>(this.whitePositions);
        newBoard.blackPositions = new ArrayList<>(this.blackPositions);

        return newBoard;
    }
}
