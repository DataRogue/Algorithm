import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Database {
    private static TreeMap<String, Animal> cowMap = new TreeMap<String, Animal>();

    public Database(Scanner _inptFile) {
        while (_inptFile.hasNext()) {
            // Scanner is gonna scan, Parser is gonna parse
            // I also like array, A LOT. So if it can be, it will be
            // stored in an array.
            String regNum = _inptFile.next();
            String sireRegNum = _inptFile.next();
            String damRegNum = _inptFile.next();
            boolean isMale;
            if (_inptFile.next().charAt(0) == 'm') {
                isMale = true;
            } else {
                isMale = false;
            }
            int date[] = new int[3];
            date[0] = _inptFile.nextInt();
            date[1] = _inptFile.nextInt();
            date[2] = _inptFile.nextInt();
            _inptFile.nextLine();
            String name = _inptFile.nextLine();
            int classData[] = parseClass(_inptFile, isMale);

            // MoeData is the lactation proving stuff, similar logic thus
            // similar code
            // Declaring things as null because they get filled out later in
            // if-else statements
            // so that the compiler doesn't freak out.
            float hasMoeData = _inptFile.nextInt();
            float moeDataM[] = null;
            float moeDataF[][] = null;
            if (hasMoeData != -1) {
                // Check gender and stuff
                if (isMale == true) {
                    moeDataM = parseMoeDataM(_inptFile, hasMoeData);
                } else {
                    moeDataF = parseMoeDataF(_inptFile, hasMoeData);
                }
            } else {

                moeDataM = new float[0];
                moeDataF = new float[0][0];
            }

            if (isMale == true) {
                cowMap.put(regNum, new Bull(regNum, name, date, isMale,
                        sireRegNum, damRegNum, classData, moeDataM));
            } else {

                cowMap.put(regNum, new Cow(regNum, name, date, isMale,
                        sireRegNum, damRegNum, classData, moeDataF));
            }
        }

    }

    public static void printPedigree(String _command) {
        // Makes a blank line, keeps track of current generation
        // (If the assignment was any larger I would have used
        // recursive algorithm to get as many generations as I
        // could.)
        System.out.println("");
        String tempGrand = _command;
        String tempChild;
        String temp;

        if (cowMap.containsKey(tempGrand)) {
            System.out.println("Data for the animal ");
            System.out.println(cowMap.get(tempGrand).toString());

            ;
            tempChild = cowMap.get(tempGrand).getDam();
            if (cowMap.containsKey(tempChild)) {
                System.out.println("Data for the Dam ");
                System.out.println(cowMap.get(tempChild).toString());

                temp = cowMap.get(tempChild).getDam();
                if (cowMap.containsKey(temp)) {
                    System.out.println("Data for the Dam's Dam ");
                    System.out.println(cowMap.get(temp).toString());
                } else {
                    System.out.println("No data is avalible");
                }
                temp = cowMap.get(tempChild).getSire();
                if (cowMap.containsKey(temp)) {
                    System.out.println("Data for the animal Dam's Sire ");
                    System.out.println(cowMap.get(temp).toString());
                } else {
                    System.out.println("No data is avalible");
                }
            } else {
                System.out.println("No data is avalible");
            }

            temp = cowMap.get(tempGrand).getSire();
            if (cowMap.containsKey(tempChild)) {
                System.out.println("Data for the Sire ");
                System.out.println(cowMap.get(tempChild).toString());

                temp = cowMap.get(tempChild).getDam();
                if (cowMap.containsKey(temp)) {
                    System.out.println("Data for the Sire's Dam ");
                    System.out.println(cowMap.get(temp).toString());
                } else {
                    System.out.println("No data is avalible");
                }

                temp = cowMap.get(tempChild).getSire();
                if (cowMap.containsKey(temp)) {
                    System.out.println("Data for the Sire's Sire ");
                    System.out.println(cowMap.get(temp).toString());
                } else {
                    System.out.println("No data is avalible");
                }
            } else {
                System.out.println("No data is avalible");
            }

        } else {
            System.out.println("No Animal data is avalible");
        }

    }

    public static int[] parseClass(Scanner _inptFile, boolean isMale) {
        // Returns a classArray. If data doesn't exist array size will be zero.
        int hasClass = _inptFile.nextInt();
        int classArray[];
        if (hasClass > 0) {

            if (isMale == true) {
                // Month, Day, Year, General appearance, Dairy Character, and
                // Body Capacity
                classArray = new int[6];
            } else {
                // Month, Day, Year, General appearance, Dairy Character, Body
                // Capacity, and Mammary System
                classArray = new int[7];
            }
            classArray[0] = hasClass;
            for (int i = 1; i <= classArray.length - 1; i++) {
                classArray[i] = _inptFile.nextInt();
            }

        } else {
            classArray = new int[0];
        }
        return classArray;
    }

    public static float[] parseMoeDataM(Scanner _inptFile, float hasMoeData) {
        float moeDataM[] = new float[8];
        moeDataM[0] = hasMoeData;
        for (int i = 1; i <= moeDataM.length - 1; i++) {
            moeDataM[i] = _inptFile.nextFloat();
        }
        return moeDataM;
    }

    public static float[][] parseMoeDataF(Scanner _inptFile, float hasMoeData) {
        // This part essentially scans in data and pumps it into a list until
        // it hits a -1. Then using the length of the list divided by six
        // and rounded up. It creates a 2D array to store the data.
        // And transcribes the data from the list so its easier to sort through.
        // I know this method is a bit more expensive than the dirty solution,
        // but this protects against missing data errors that would crash
        // the program.
        ArrayList<Float> moeDataFTemp = new ArrayList<Float>();
        moeDataFTemp.add(hasMoeData);
        float dataFloat;
        while (true) {
            dataFloat = _inptFile.nextFloat();
            if (dataFloat < 0) {
                break;
            }
            moeDataFTemp.add(dataFloat);
        }
        int arrayLength = (int) Math.ceil(((double) moeDataFTemp.size() / 5));
        float moeDataF[][] = new float[arrayLength][5];
        {
            for (int i = 0; i <= arrayLength - 1; i++) {
                for (int j = 0; j <= 4; j++) {
                    if (moeDataFTemp.isEmpty()) {
                        break;
                    }
                    moeDataF[i][j] = moeDataFTemp.get(0);
                    moeDataFTemp.remove(0);
                }
            }
        }
        return moeDataF;
    }

}
