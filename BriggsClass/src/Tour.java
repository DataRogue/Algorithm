import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Tour {
    List<Point> pointList = new ArrayList<Point>();
    private double distance = 0;

    public Tour() // create an empty tour
    {

    }

    public void show() // print the tour to standard output
    {
        ListIterator<Point> listIt = pointList.listIterator();
        while (listIt.hasNext()) {
            System.out.println(listIt.next().toString());
        }
    }

    public void draw() // draw the tour using Turtle graphics, including the
                       // return edge
    {
        if (!pointList.isEmpty()) {
            ListIterator<Point> listIt = pointList.listIterator();
            Point lastPoint = listIt.next();
            while (listIt.hasNext()) {
                Point tempPoint = listIt.next();
                lastPoint.drawTo(tempPoint);
                lastPoint = tempPoint;
            }
            lastPoint.drawTo(pointList.get(0));
        }
    }

    public double distance() // return the total distance of the tour
    {
        return distance
                + pointList.get(0).distanceTo(
                        pointList.get(pointList.size() - 1));
    }

    public void insertSmallest(Point p)// insert p using the smallest net
                                       // increase heuristic
    {
        // Like insert neartest but simply checks for distance
        if (pointList.isEmpty()) {
            pointList.add(p);
        } else {
            ListIterator<Point> listIt = pointList.listIterator();
            double winningDistance = p.distanceTo(pointList.get(0));
            int winIndx = 0;
            while (listIt.hasNext()) {
                Point tempPoint = listIt.next();
                double testDistance = tempPoint.distanceTo(p);
                if (testDistance < winningDistance) {
                    winningDistance = p.distanceTo(tempPoint);
                    winIndx = listIt.previousIndex() + 1;
                }
            }
            distance += winningDistance;
            pointList.add(winIndx, p);
        }
    }

    public void insertNearest(Point p) // insert p using the nearest neighbor
                                       // heuristic
    {
        if (pointList.isEmpty()) // Checks to see if first point added
        {
            pointList.add(p);
        } else {
            ListIterator<Point> listIt = pointList.listIterator();
            double winningDistance = 999999999999999999999999.9; // Set easy to
                                                                 // beat
                                                                 // distance
            int winIndx = 0;
            while (listIt.hasNext()) // Loops through list
            {
                Point tempPoint = listIt.next();
                if (p.distanceTo(tempPoint) < winningDistance) {
                    winningDistance = p.distanceTo(tempPoint);
                    winIndx = listIt.previousIndex() + 1;
                }
            }
            distance += winningDistance;
            pointList.add(winIndx, p);
        }
    }

    public double getDistance(List<Point> inputList) {
        // Simple method that loops through list and gets the total distance
        double returnDistance = 0;
        Point lastPoint = null;
        ListIterator<Point> inputIt = inputList.listIterator();
        lastPoint = inputIt.next();
        while (inputIt.hasNext()) {
            Point testPoint = inputIt.next();
            returnDistance += lastPoint.distanceTo(testPoint);
            lastPoint = testPoint;
        }
        return returnDistance;
    }

    public void nodeReplacement() {
        // Variable declartation
        // All duplications exist to avoid writing over a list
        // I'm looping without restarting the iteration
        List<Point> returnList = pointList;
        List<Point> testList = returnList;
        ListIterator<Point> primaryIt = returnList.listIterator();
        ListIterator<Point> secondaryIt = returnList.listIterator();
        double newDistance;
        int primary, secondary;
        Point primaryPoint;
        Point secondaryPoint;
        while (primaryIt.hasNext()) {

            primaryPoint = primaryIt.next();
            primary = primaryIt.previousIndex();

            // I have no idea why, but for some reason the momement
            // the stack enters the while loop whatever value primary
            // was previously changes to zero.
            System.out.println(primary);
            while (secondaryIt.hasNext()) {
                System.out.println(primary);
                secondaryPoint = secondaryIt.next();
                secondary = secondaryIt.previousIndex();

                testList.set(secondary, primaryPoint);
                testList.set(primary, secondaryPoint);
                newDistance = getDistance(testList);
                if (newDistance < distance) {
                    // create new list and restart the
                    distance = newDistance;
                    pointList = returnList;
                    returnList = testList;
                    primaryIt = returnList.listIterator();
                    secondaryIt = returnList.listIterator();
                    break;
                } else {
                    // reset list
                    testList.set(primary, primaryPoint);
                    testList.set(secondary, secondaryPoint);
                }

            }

            System.out.println(primaryIt.hasNext());
        }
    }
}
