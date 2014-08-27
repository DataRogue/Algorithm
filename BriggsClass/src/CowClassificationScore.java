
public class CowClassificationScore extends ClassificationScore {
   /*
    *  Represents the classification score for a cow. The correct
    *  ranges are 
    *  
    *  general appearance 0-30
    *  dairy character 0-20
    *  body capacity 0-20
    *  mammary system 0-30
    *  
    *  but we are not testing for input validity.
    *  
    */
   private int
      generalAppearance,   
      dairyCharacter,
      bodyCapacity,
      mammarySystem;
   
   
   // default constructor
   // all members are initialized to 0 already
   public CowClassificationScore(){}

   public CowClassificationScore(int month, int day, int year, int
         gnrlAppearance, int dryCharacter, int bdyCapacity, int mmrySystem){
      super(month,day,year);
      this.generalAppearance= gnrlAppearance; 
      this.bodyCapacity= bdyCapacity;
      this.dairyCharacter= dryCharacter;
      this.mammarySystem= mmrySystem;
   }

   /*
    * Constructs a  string representation of the date and score.
    * All values are labeled.
    * 
    */
   public String toString(){
      StringBuffer temp= new StringBuffer(70);
      
      temp.append(super.toString());
      temp.append(' ');
      temp.append("GA= " + generalAppearance);
      temp.append(" DC= " + dairyCharacter);
      temp.append(" BC= " + bodyCapacity);
      temp.append(" MS= " + mammarySystem);
      temp.append(" Total= " + (generalAppearance + dairyCharacter +
      bodyCapacity + mammarySystem));

      return temp.toString();
   }
}
