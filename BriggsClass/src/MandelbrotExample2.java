import java.awt.Color;
/**
 * 
 *  Compilation:  javac MandelbrotExample2.java Turtle.java Complex.java
 *  Execution:    java MandelbrotExample2
 *
 *  Fills the pixels of the Turtle grid with some randomly selected colors.
 *  
 *  You can use this source code as a basis for your adaptations.
 *  
 *  The region of the complex plane (-1.5, -1.0) to (0.5,1.0) is a good 
 *  region for Mandelbrot set, so we hardwire it here.  You will need
 *  to read in values for the lower left corner coordinates and the
 *  grid side length.
 *  
 *
 **/
public class MandelbrotExample2 {
   public static void main(String args[]) {

      int WIDTH  = 512;
      int HEIGHT = 512;
      int r,
          g,
          b;
        
      Turtle.create(WIDTH, HEIGHT);

      double xMax, xMin;
      double yMax, yMin;
      // the coordinates of the "current" pixel cell's
      // lower left corner
      double Cx, Cy;

      // Setup for the Mandelbrot calculations. Notice that we 
      // are incrementing across the pixel locations in the screen 
      // at the same time we are incrementing across the Complex 
      // points for the mandelbrot calculations. 
        

      // set the coordinates of the lower left and upper right corners
      // of the grid
      xMin = -1.5; xMax = 0.5;
      yMin = -1.0; yMax = 1.0;
        

      // calculate the pixel width
	double deltaX = (xMax - xMin)/WIDTH;
      // calculate the pixel height
      double deltaY = (yMax - yMin)/HEIGHT;
        
      // each pass through the outer loop draws one column of pixels
      // set current x coordinate to the minimum
      Cx = xMin;
      for (int i = 0; i< WIDTH;i++){
         // set y coordinate to the minimum value
         Cy = yMin;    

         // iterate up the pixel column
         for (int j = 0; j < WIDTH; j++){
            // Replace most of the following code with your calls to the 
     		// mandel function. Color the pixel based on the calculation  
 
     		r = (int) (Math.random() * 256);
          	g = (int) (Math.random() * 256);
           	b = (int) (Math.random() * 256);
        	
           	Turtle.setColor(new Color(r,g,b));
            // color the pixel
           	Turtle.pixel(i,j);
                 
           	Cy += deltaY;
         }
        
         Cx += deltaX;
         // to see the Turtle draw, uncomment the pause
         // Turtle.pause(10);
      }
      Turtle.render();      
   }  
}
