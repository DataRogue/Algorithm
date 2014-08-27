/*************************************************************************
 *  Compilation:  javac MandelbrotExample1.java Turtle.java Complex.java
 *  Execution:    java MandelbrotExample1
 *
 *  Loads the Turtle grid with some randomly colored rectangles.
 *  You have to close the Turtle window to stop the program.
 *
 *
 *************************************************************************/
import java.awt.Color;
import java.lang.*;
import java.util.Scanner;

public class MandelbrotExample1 {
   public static void main(String args[]) {

      Scanner stdIn = new Scanner(System.in);
       
      int WIDTH  = 512;
      int HEIGHT = 512; 
      int x, y, r, g, b, size,maxsize;
       
      System.out.println("To stop the program, you will need to "
      + "close the Turtle window.");
      System.out.println("Enter the maximum size of a square ");
      maxsize = stdIn.nextInt();
 
      Turtle.create(WIDTH, HEIGHT);

      while (true){
         // get random pixel coordinates
         x = (int) (Math.random() * WIDTH);
	   y = (int) (Math.random() * HEIGHT);
        	
         // get a random color
         r = (int) (Math.random() * 256);
         g = (int) (Math.random() * 256);
         b = (int) (Math.random() * 256);

         // get a random size
         size = (int) (Math.random() * maxsize);
                    	
         // assign the pen to the new color
         Turtle.setColor(new Color(r,g,b));
        	
         // move pen to the pixel
         Turtle.fly(x,y);
        	
         // draw the rectangle
         Turtle.spot(size,size);
         // wait 5 milliseconds
         Turtle.pause(5);
      }        
   }
}
