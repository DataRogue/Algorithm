
public class Point {
	public double selfX;
	public double selfY;
	public Point(double x, double y)   // constructor
	{
		selfX =x;
		selfY =y;
	}
	public String toString()              // for display
	{
		return "The X coordinate is: " + selfX +", and they Y coordinate is:" + selfY;
	}
	public void draw()                    // draw Point using Turtle graphics
	{
		Turtle.fly(selfX, selfY);
		Turtle.spot(1);
	}
	public void drawTo(Point other)       // draw an edge from this to other in the Turtle
	{
		Turtle.fly(selfX, selfY);
		Turtle.go(other.selfX, other.selfY);
	}
	public double distanceTo(Point other) // return the Euclidean distance between this and other
	{
		return Math.sqrt(((other.selfX - selfX)*(other.selfX - selfX)) + ((other.selfY - selfY)*(other.selfY - selfY)));
	}
}
