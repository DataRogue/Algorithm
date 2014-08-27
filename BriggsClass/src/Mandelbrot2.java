import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.math.*;


public class Mandelbrot2 {
	
	private static Color[] mandelToColor = new Color[256]; 
	private static Scanner scan;
	
	/**
	 * @param args
	 */
	
	public static int mandel(Complex Z) {
	    double zx = 0.0, zy = 0.0;
	    double zx2 = 0.0, zy2 = 0.0;
	    int value = 0;
	    //Check to see how fast the number breaks out of < 4
	    while (value < 255 && zx2 + zy2 < 4.0) {
	    	// Cheaper to simply recalculate placeholder variables than make a new Complex object every time
	    	zy = 2.0 * zx * zy + Z.getImaginary(); // Create temp value for x
	    	zx = zx2 - zy2 + Z.getReal(); // Create create temp value for y
	    	// Square the two values so they're ready for calculation in in the while loop
	    	zx2 = zx * zx;
	    	zy2 = zy * zy;
	    	value++;
	    }
	    return 255-value; //Subtract it from 255 because RGB isn't additive and 255,255,255 is black. 
	  }
	
	public static void main(String[] args) {
		
		//Input Jazz
		int gridSize = 400;
		double xMin = -1.5;
		double yMin = -1.0;
		double sideLength = 400;
		double zoom = 1;
		scan = new Scanner(System.in);
		boolean userIsNotADumb = false;
		while(!userIsNotADumb)
		{
			System.out.println("Please insert integer equal to or greater than 400 for grid size: ");
			gridSize = scan.nextInt();
			System.out.println("Please insert a double for the xMin (Default is -1.5): ");
			xMin = scan.nextDouble();
			System.out.println("Please insert a double for the yMin (Default is -1.0): ");
			yMin = scan.nextDouble();
			System.out.println("Please insert a double positive value for the side value (Default is 2): ");
			sideLength = scan.nextDouble();
			if ( gridSize >= 400 || sideLength >0)
			{
				userIsNotADumb = true;
			}
			else
			{
				System.out.println("Don't be a derp, follow instructions.");
			}
		}
		
		//Since grid is 2 points long the maxes have to be min + 2.
		double xMax = xMin+sideLength;
		double yMax = yMin+sideLength;
		
		Random r = new Random();
		
		double scaledX = (xMax-xMin)/gridSize;
		double scaledY = (yMax-yMin)/gridSize;
		
		
		double cx, cy;
		
		Turtle.create(gridSize, gridSize); // Create the grid

		cx = xMin; //Keeping track of the scaled grid x
		for(int i=0; i <=gridSize; i++) // Each pixel in turtle
		{
			cy = yMin; // Keeping track of the scaled grid y
		
			for(int j=0; j<gridSize; j++)
			{	//Get mandel value, check if color already exists in an array
				//If it doesn't exist make a new one and add it in
				//If it does just call the color then plant the point
				int ci = mandel(new Complex(cx,cy));
				Color c;
				if (mandelToColor[ci]==null)
				{
					mandelToColor[ci] = new Color((int) ci, (int) ((ci*r.nextDouble())/(i/10+1)), (int) ((ci*r.nextDouble())/(j/10+1)));
				}
				c = mandelToColor[ci];
		        Turtle.setColor(c);
		        Turtle.pixel(i, j);
		        
		        cy += scaledY; //Iterate by scaled value
			}
			cx += scaledX; //Iterate by scaled value
		}
		
		
		Turtle.render();
	}
	


}
