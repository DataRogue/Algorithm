
public class BullClassificationScore extends ClassificationScore {
   /*
    *  Represents the classification score for a bull. The correct
    *  ranges are 
    *  
    *  general appearance 0-40
    *  dairy character 0-30
    *  body capacity 0-30
    *  
    *  but we are not testing for input validity.
    *  
    */
   private int
      generalAppearance,   
      dairyCharacter,
      bodyCapacity;
   
   
   // default constructor
   // all members are initialized to 0 already
   public BullClassificationScore(){}

   // set the data fields to the input values
   public BullClassificationScore(int month, int day, int year, int
         gnrlAppearance, int dryCharacter, int bdyCapacity){
      super(month,day,year);
      this.generalAppearance = gnrlAppearance; 
      this.bodyCapacity = bdyCapacity;
      this.dairyCharacter = dryCharacter;
   }


   
   /*
    * Constructs a  string representation of the date and score.
    * All values are labeled.
    * 
    */
   public String toString(){
      StringBuffer temp = new StringBuffer(70);
      
      temp.append(super.toString());
      temp.append(' ');
      temp.append("GA= " + generalAppearance);
      temp.append(" DC= " + dairyCharacter);
      temp.append(" BC= " + bodyCapacity);
      temp.append(" Total= " + (generalAppearance + dairyCharacter +
      bodyCapacity));

      return temp.toString();
   }
}
