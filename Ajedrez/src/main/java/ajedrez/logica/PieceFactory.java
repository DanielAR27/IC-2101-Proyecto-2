package ajedrez.logica;

public class PieceFactory {
    public static Piece createPiece(String type, String team, Position pos){
        if (team.equals("N")){
            return switch (type) {
                case "P" -> new Peon(Team.NEGRO, pos);
                case "T" -> new Torre(Team.NEGRO, pos);
                case "C" -> new Caballo(Team.NEGRO, pos);
                case "A" -> new Alfil(Team.NEGRO, pos);
                case "K" -> new Rey(Team.NEGRO, pos);
                default -> new Reina(Team.NEGRO, pos);
            };
        }else{
            return switch (type) {
                case "P" -> new Peon(Team.BLANCO, pos);
                case "T" -> new Torre(Team.BLANCO, pos);
                case "C" -> new Caballo(Team.BLANCO, pos);
                case "A" -> new Alfil(Team.BLANCO, pos);
                case "K" -> new Rey(Team.BLANCO, pos);
                default -> new Reina(Team.BLANCO, pos);
         };
       }
    }
}
    
