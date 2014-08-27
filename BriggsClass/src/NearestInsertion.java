/*************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 *  Compilation:  javac NearestInsertion.java
 *  Execution:    java NearestInsertion < file.txt
 *  Dependencies: Tour.java Point.java Turtle.java
 *
 *  Run nearest insertion heuristic for traveling salesperson problem
 *  and print resulting tour to standard output.
 *
 *  example: java NearestInsertion tsp1000.txt
 *
 *
 *************************************************************************/
import java.io.*;
import java.util.*;

public class NearestInsertion {

    private static final int TURTLE_GRID_SIZE = 800;

    public static void main(String[] args) {
        Scanner in;
        double maxCoordVal;
        long startTime, endTime;

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
            tour.insertNearest(p);
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
