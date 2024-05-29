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
    
    @Override
    public boolean equals(Object pos){
        if (pos instanceof Position position)
            return row ==position.getRow() && col == position.getColumn();
        else
            return false;
    }
        
    @Override
    public String toString(){
        return "(" + row + ", " + col + ")";
    }
    
}
