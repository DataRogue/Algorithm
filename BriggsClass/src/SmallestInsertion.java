/*************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 *  Compilation:  javac SmallestInsertion.java
 *  Execution:    java SmallestInsertion  file.txt
 *  Dependencies: Tour.java Point.java Turtle.java
 *
 *  Run smallest insertion heuristic for traveling salesperson problem
 *  and plot results in Turtle graphics.
 *
 *  example: java SmallestInsertion  tsp1000.txt
 *
 *************************************************************************/
import java.io.*;
import java.util.*;

public class SmallestInsertion {

    private static final int TURTLE_GRID_SIZE = 800;

    public static void main(String[] args) {

        Scanner in;
        double maxCoordVal;

        try {
            if (args.length != 1) {
                System.out
                        .println("Must provide a filename as argument to program ");
                return;
            }
            in = new Scanner(new FileReader(args[0]));
        } catch (Exception e) {
            System.out.println("Scanner creation threw an exception.\n" + e);
            return;
        }
        long startTime, endTime;

        Turtle.create(TURTLE_GRID_SIZE, TURTLE_GRID_SIZE);

        // get dimensions
        int W = in.nextInt();
        int H = in.nextInt();

        maxCoordVal = (W > H ? W : H);
        // set the scale of the window; the scale is used to convert a
        // a coordinate value c to a Turtle pixel index by
        // index = (int) (c * scale)
        Turtle.setScale(TURTLE_GRID_SIZE / maxCoordVal);

        // read in data and run nearest insertion heuristic
        Tour tour = new Tour();
        startTime = System.currentTimeMillis();
        while (in.hasNextDouble()) {
            double x = in.nextDouble();
            double y = in.nextDouble();
            Point p = new Point(x, y);
            tour.insertSmallest(p);
        }
        endTime = System.currentTimeMillis();

        // print results
        tour.draw();
        System.out.println("Tour distance = " + tour.distance());
        System.out.println("It took " + (endTime - startTime)
                / ((double) 60000) + " minutes to find the tour.");

        tour.show();
        System.out.println(tour.distance());

    }
}
