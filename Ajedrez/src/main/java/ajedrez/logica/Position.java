package ajedrez.logica;


public class Position{
    private int row;
    private int col;
    
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return col;
    }

    public void setColum(int col) {
        this.col = col;
    }
    
    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public boolean samePosition(Position pos){
        return row == pos.row && col == pos.col;
    }
        
    @Override
    public String toString(){
        return "(" + row + ", " + col + ")";
    }
    
}
