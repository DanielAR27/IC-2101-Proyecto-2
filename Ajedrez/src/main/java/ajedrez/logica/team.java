package ajedrez.logica;


 enum Team {
    BLANCO,
    NEGRO;
    
     @Override
    public String toString() {
         // Método toString de Estado Compra.
        return switch (this) {
            case BLANCO->"Blanco";
            default -> "Negro";
        };
    }
}
