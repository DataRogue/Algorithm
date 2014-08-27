/*************************************************************************
 *  Compilation:  javac MandelbrotExample3.java Turtle.java
 *  Execution:    java MandelbrotExample3 
 *
 *  Loads the Turtle with alternating black and white horizontal stripes,
 *  pausing briefly to show the drawing process
 *
 *************************************************************************/
import java.awt.Color;
import java.lang.*;
import java.util.Scanner;

public class MandelbrotExample3 {
   private static final Color black = new Color(0,0,0);
   private static final Color white = new Color(255,255,255);

   public static void main(String args[]) {

      Scanner stdIn = new Scanner(System.in);
    	
       
      int WIDTH  = 800;
      int HEIGHT = 500; 
      int x, y, r, g, b, size,maxsize;
       
 
 
      Turtle.create(WIDTH, HEIGHT);

      
      for (x = 0; x < WIDTH; x++){
         for (y = 0; y < HEIGHT; y++){
            // adjust the color based on the current "row",
            // given by the y index
            if (y % 50 == 0)
               if (y / 50 % 2 == 0)
                  Turtle.setColor(black);
               else
                  Turtle.setColor(white);

            // load pixel (x,y) with the current color
            // and pause for 1 ms
            Turtle.pixel(x,y);
            Turtle.pause(1);
         }
      }
            


   }
}

