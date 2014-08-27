import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.math.*;


public class Mandelbrot3 {
	
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
	
	public static Color[][] drawMandel(int _gridSize, double _xCenter, double _yCenter, double _sideLength, double _zoom)
	{
		Random r = new Random();
		
		
		// Variable setup
		int gridSize = _gridSize;
		double xCenter = _xCenter;
		double yCenter = _yCenter;
		double sideLength = _sideLength;
		double zoom = _zoom;
		
		//Setup return array
		Color returnArray[][] = new Color[gridSize+1][gridSize+1];
		
		//Initial xMin/yMin setup
		double xMin = xCenter - (sideLength)/(2);
		double yMin = yCenter - (sideLength)/(2);
		
		
		//Offset for zoom
		yMin *= sideLength/(zoom*2);
		xMin *= sideLength/(zoom*2);
		
		//Since grid is 2 points long the maxes have to be min + 2.
		double xMax = xMin+2;
		double yMax = yMin+2;
		
		//Scale XY to imaginary plane
		double scaledX = (xMax-xMin)/(gridSize*zoom);
		double scaledY = (yMax-yMin)/(gridSize*zoom);
		
		double cx, cy;

		cx = xMin; //Keeping track of the scaled grid x
		for(int i=0; i <=gridSize; i++) // Each pixel in turtle
		{
			cy = yMin; // Keeping track of the scaled grid y
		
			for(int j=0; j<=gridSize; j++)
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
		        returnArray[i][j] = c;

		        
		        cy += scaledY; //Iterate by scaled value
			}
			cx += scaledX; //Iterate by scaled value
		}
		return returnArray;
	}
	


	
	public static void main(String[] args) {
		
		//Input Jazz
		int gridSize = 400;
		double xCenter = -0.5;
		double yCenter = 0;
		double sideLength = 2;
		double zoom = 1;
		scan = new Scanner(System.in);
		boolean userIsNotADumb = false;
		double xOffset = -1.5;
		double yOffset = 0;
		int times = 10;

		while(!userIsNotADumb)	
		{
			//Demand user input and make sure values are valid
			System.out.println("Please insert integer equal to or greater than 400 for grid size: ");
			gridSize = scan.nextInt();
			System.out.println("Please insert a double for the xCenter (Default is -0.5): ");
			xCenter = scan.nextDouble();
			System.out.println("Please insert a double for the yCenter (Default is 0): ");
			yCenter = scan.nextDouble();
			System.out.println("Please insert a double positive value for the side value (Default is 2): ");
			sideLength = scan.nextDouble();
			System.out.println("Please insert a double value equal to or greater than 1 for the zoom (Default is 1): ");
			zoom = scan.nextDouble();
			System.out.println("Please insert a double value for the X offset (Default is -1.5): ");
			xOffset = scan.nextDouble();
			System.out.println("Please insert a double value for the Y offset (Default is 0): ");
			yOffset =  scan.nextDouble();
			System.out.println("Please insert a positive integar for frames in movie (Default is 10): ");
			times = scan.nextInt();
			if ( gridSize >= 400 || sideLength >0 || zoom >= 1 || times >= 1)
			{
				userIsNotADumb = true;
			}
			else
			{
				System.out.println("Don't be a derp, follow instructions.");
			}
		}
		Turtle.create(gridSize, gridSize); // Create the grid
		
		//Array for all the frames
		Color framesArray[][][] = new Color[times+1][gridSize+1][gridSize+1];
		//Pre-render all frames
		for(int i = 0; i<=times;i++)
		{
			Color pixelArray[][] = drawMandel(gridSize, xCenter+(xOffset*i), yCenter+(yOffset*i), sideLength, zoom+(zoom*i));
			framesArray[i] = pixelArray;
		}
		//Animate ze movie
		for(int i = 0; i <= times;i++)
		{
			//Create array via method call
			
			for (int j = 0; j <= gridSize; j++)
			{
				for (int h = 0; h <= gridSize; h++)
				{
					Turtle.setColor(framesArray[i][j][h]);
					Turtle.pixel(j, h);
				}
			}
			Turtle.pause(200);
			Turtle.render();
		}
	}
}
