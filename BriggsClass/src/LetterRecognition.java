import java.util.*;
import java.io.*;
public class LetterRecognition{

   // value for letter when there is no match
   public static final char UNRECOGNIZED_LETTER = ' ';

   // size of pattern array
   public static final int PATTERN_SIZE = 10;

   // size of grid array
   public static final int GRID_SIZE = PATTERN_SIZE + 2;

/*

    To hold the pattern, its features, and the letter
    it represents.  If the pattern does not match any
    of the letter specifications, then the letter char
    is a blank.

*/

   int[][] grid;
   int massbottom, corners, tees;
   char letter;

   // default constructor
   // grid will be created as a 12x12 array of 0's
   // the integer data members are initialized to 0
   // by default already; letter is set to ' '
   public LetterRecognition(){
      grid = new int[GRID_SIZE][GRID_SIZE];
      letter = UNRECOGNIZED_LETTER;
   }

   /*
       precondition: assumes sc is not null and has a least one
          more line of text in it.

       postcondition: grid is loaded with 0's and 1's according to
          the discussion in the project description based on what
          remains in the input Scanner sc.

       YOU HAVE TO CODE THIS.
   */
   
   // Trash shitty drunken code, write new. 
   public void loadPattern(Scanner sc){
	   massbottom = 0;
	   corners = 0;
	   tees = 0;
	   String line;
	   int lineLength;
	   int length;
	   int height = PATTERN_SIZE-1;
	   boolean zeroes = false;
	   boolean finishLine = false;
	   
	   for(int i=0; i <= GRID_SIZE-1; i++)
	   {
		   grid[0][i]=0; // Creates first row of zeroes
	   }
	   
	   
	   for(int i = 0; i <= height;i++)
	   {
		   //Checks if file didn't end early
		   if(!sc.hasNextLine())
		   {
			   zeroes=true;
		   }
		   //If we're not flooding zeroes gets a new line
		   if (!zeroes)
		   {
			   line = sc.nextLine();
			   lineLength = line.length();
		   }
		   else
		   {
			   line = "";
			   lineLength=0;
		   }
		   //Checks the length of the index
		   if (lineLength >= PATTERN_SIZE)
		   {
			   length = PATTERN_SIZE-1;
		   }
		   else
		   {
			 length = lineLength-1;
			 finishLine = true;
		   }
		   grid[i+1][0]=0; //Create first row of zeroes
		   if (!zeroes){
			   for (int j = 0; j <=length; j++)
			   {
				   
				   //If $ detected flood with zeroes
				   if(line.charAt(j) =='$')
				   {
					   finishLine=true; 
					   zeroes=true;
					   grid[i+1][j+1]=0;
					   lineLength = j;
				   }
				   else if(line.charAt(j) == '*')
				   {
					   grid[i+1][j+1]=1; // If its a * then its a 1

				   }
				   else
				   {
					   grid[i+1][j+1]=0; // If its neither $ or *, then its a zero

				   }
			   }
			   grid[i+1][11]=0;
			   //If a line is short or a $ is detected finish it with zeroes
			   if(finishLine)
			   {
				   for(int j = 0; j<=(PATTERN_SIZE-(lineLength+1));j++)
				   {
					   grid[i+1][lineLength+j+1]=0; //Fills the end of a line with zeroes if it ends too soon
					  
				   }
				   finishLine = false;
			   }
		   }
		   else
		   {
			   for (int j = 0; j <=PATTERN_SIZE; j++)
			   {
				   grid[i+1][j+1]=0; //If a $ is detected flood the rest of the grid with zeroes
			   }
			   //There was a bug where for some reason the first line wouldn't get cleared of values
			   //if you didn't hit a $. I tried to fix the algo, but when I looked at the code
			   //I decided it was too scary and I didn't want to mess with it, since it kind of
			   // already worked.
			   if (i==1)
			   {
				   for (int j = 0; j <=PATTERN_SIZE; j++)
				   {
					   grid[1][j+1]=0;
				   }
			   }
		   }
		   
	   }
	   for(int i=0; i <= GRID_SIZE-1; i++)
	   {
		   grid[11][i]=0; // Creates last row of zeroes
	   }
	   
   }

   /*
       precondition: assumes the grid array is not null and is a 12x12
          array with 0's and 1's.


       postcondition: the instance specific data members, massbottom,
          corners, and tees are calculated from the current contents of
          grid according to the project specification.

       YOU HAVE TO CODE THIS.
   */
   public void extractFeatures(){
	   
	   corners = 0;
	   tees = 0;
	   massbottom = 0;
	   int previousBottom = 0;
	   for (int i =0; i<GRID_SIZE; i++)
	   {

		   for (int j = 0; j< GRID_SIZE; j++)
		   {
			   // Get massbottom
			   if(grid[i][j]==1)
			   {
				   if(previousBottom<i)
				   {
					   massbottom=0;
				   }
				   previousBottom = i;
				   massbottom++;
			   }
			   //Nested on purpose to make it easier to read
					   if(grid[i][j]==1 && grid[i-1][j-1]==0 && grid[i+1][j-1]==0 && grid[i-1][j+1]==0 && grid[i+1][j+1]==0)
					   {
						   int ones = 0;
						   //Could have used enums here, but I honestly don't
						   //know which method is more expensive. 
						   boolean opposites = false;
						   if (grid[i][j-1]==1)
						   {
							   if (grid[i][j+1]==1)
							   {
								   opposites = true;
							   }
							   ones++;
						   }
						   if (grid[i-1][j]==1)
						   {
							   if (grid[i+1][j]==1)
							   {
								   opposites = true;
							   }
							   ones++;
						   }
						   if (grid[i+1][j]==1)
						   {
							   if (grid[i-1][j]==1)
							   {
								   opposites = true;
							   }
							   ones++;
						   }
						   if (grid[i][j+1]==1)
						   {
							   if (grid[i][j-1]==1)
							   {
								   opposites = true;
							   }
							   ones++;
						   }
						   if (ones == 3)
						   {
							   j +=1;
							   tees++;
						   }
						   if (ones == 2 && !opposites)
						   {
							   j +=1;
							   corners++;
						   }
					   }
		   }  
	   }
	   
	   
   }


   /*
       precondition: assumes the massbottom, corners, and tees 
          data members have been correctly calculated from the 
          current contents of the grid.

       postcondition: letter is assigned either the UNRECOGNIZED_LETTER
         value if the features do not match any of the letter features,
         or the letter whose features are matched.

       YOU HAVE TO CODE THIS.
   */
   public void classifyLetter(){
	   
	   //I would've done a state machine, but JAVA
	   //doesn't have native support for it
	  if(massbottom == 2 && corners == 2 && tees ==2)
	  {
		  letter = 'A';
	  }
	  else if (massbottom >= 2 && corners == 4 && tees ==2)
	  {
		  letter='B';
	  }
	  else if (massbottom >= 2 && corners == 2 && tees ==0)
	  {
		  letter='C';
	  }
	  else if (massbottom >= 2 && corners == 2 && tees ==1)
	  {
		  letter='E';
	  }
	  else if (massbottom == 1 && corners == 1 && tees ==1)
	  {
		  letter='F';
	  }
	  else if (massbottom >= 2 && corners == 3 && tees ==1)
	  {
		  letter='G';
	  }
	  else if (massbottom == 2 && corners == 0 && tees ==2)
	  {
		  letter='H';
	  }
	  else if (massbottom == 1 && corners == 0 && tees ==0)
	  {
		  letter='I';
	  }
	  else if (massbottom >= 2 && corners == 1 && tees ==0)
	  {
		  letter='L';
	  }
	  else if (massbottom == 2 && corners == 2 && tees ==1)
	  {
		  letter='P';
	  }
	  else if (massbottom == 1 && corners == 3 && tees ==1)
	  {
		  letter='M';
	  }
	  else if (massbottom >= 2 && corners == 4 && tees ==0)
	  {
		  letter='S';
	  }
	  else if (massbottom == 1 && corners == 0 && tees ==1)
	  {
		  letter='T';
	  }
	  else if (massbottom == 1 && corners == 2 && tees ==1)
	  {
		  letter='Y';
	  }
	   else
	   {
		   letter = UNRECOGNIZED_LETTER;
	   }
   }


   // getter functions for the massbottom, tees, corners, and
   // the matching letter
   public int getMassbottom(){ return massbottom;}
   public int getCorners(){ return corners;}
   public int getTees(){ return tees;}
   public char getLetter(){ return letter;}

   /*

       pre: grid is not null

       post: grid is not modified its full contents(all 12 rows of 12 columns)
          has been printed to the screen, line by line

       YOU MUST CODE THIS.

   */
   public void printPatternToScreen(){
	   for (int i = 0; i <= GRID_SIZE-1; i++)
	   { 
		   for (int j = 0; j <= GRID_SIZE-1; j++)
		   {
			   System.out.print(grid[i][j]);
		   }
		   System.out.println();
	   }
	   
   

   }

   /*

       pre: grid, massbottom, corners, tees, and letter are all 
          consistently loaded;
          patternNum indicates which number pattern this is from the
          input file

       post: the values of massbottom, corners, tees, and letter are
          reported to standard out labeled with patternNum

   */
   public void reportResultsToStdout(int patternNum){

      System.out.println("\nResults for pattern# " + patternNum + "\n");
      printPatternToScreen();
      System.out.println("\n   Massbottom = " + massbottom
      + "\n   Num of Corners = " + corners +
      "\n   Num of Tees = " + tees);

      System.out.print("\n   These feature values ");
      if (letter == LetterRecognition.UNRECOGNIZED_LETTER)
         System.out.println("do not match any letter.");
      else
         System.out.println("match " + letter);
   }

   /*
      This main can be used to obtain your output.

      It sets up a Scanner instance from a command line argument,
      creates a LetterPattern instance, and repeatedly loads the 
      LetterPattern instance from the Scanner and
      analyzes it.  It displays the results to the standard out.

   */
   public static void main(String[] args){
	   
	  
      Scanner src;
      LetterRecognition lp = new LetterRecognition();
      int
         patternNumber = 1;

      if (args.length > 0){
         try{
  
            src = new Scanner(new File(args[0]));

            while (src.hasNextLine()){
               lp.loadPattern(src);
               lp.extractFeatures();
               lp.classifyLetter();
               lp.reportResultsToStdout(patternNumber);
               patternNumber++;
            }
         }
         catch(NullPointerException e){
            System.out.println("The file name may have been a null string.\nProgram Terminating." + e);
         }
         catch(FileNotFoundException e){
            System.out.println("No file with the name " + args[0]
            + "\nProgram terminating." + e);
         }
      }
      else // args is empty
         System.out.println("You must supply the input file name on"
         + " the command line.");
   }
}
