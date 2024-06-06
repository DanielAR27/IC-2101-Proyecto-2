
package ajedrez.control;
import java.util.ArrayList;
import java.util.List;

public class DataVerificator {
   public static  String IntToLetter(int letterValue){
       return switch(letterValue){
           case 0->"A";
           case 1->"B";
           case 2->"C";
           case 3->"D";
           case 4->"E";
           case 5->"F";
           case 6->"G";
           default->"H";
       };
   }
 
   public static List<Integer> boxPositionValues(String valueBox){
       ArrayList<Integer> values = new ArrayList<>();
       values.add(8 - Integer.parseInt(valueBox.substring(1, 2)));
       values.add(DataVerificator.LetterToInt(valueBox.substring(0, 1)));
       return values;
   }
     
   public static int LetterToInt(String letterValue){
        return switch(letterValue){
           case "A"->0;
           case "B"->1;
           case "C"->2;
           case "D"->3;
           case "E"->4;
           case "F"->5;
           case "G"->6;
           default->7;
       };      
   }
   
   public static String LetterFromBlack(String valueBox){       
       String column = valueBox.substring(0, 1);
       String row = valueBox.substring(1, 2);
       
       
       
       
       switch(column){
           case "A"->column = "H";
           case "B"->column = "G";
           case "C"->column = "F";
           case "D"->column = "E";
           case "E"->column = "D";
           case "F"->column = "C";
           case "G"->column = "B";
           default->column = "A";
       }
       
       switch(row){
           case "1"->row = "8";
           case "2"->row = "7";
           case "3"->row = "6";
           case "4"->row = "5";
           case "5"->row = "4";
           case "6"->row = "3";
           case "7"->row = "2";
           default -> row = "1";
       }
       
       return column + row;
       
   }
   
  
}
