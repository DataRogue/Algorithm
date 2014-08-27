public abstract class ClassificationScore {
    /*
     * The superclass to hold classification scores for both genders of
     * Holsteins.
     * 
     * It has integer fields for the year, month and day of the score. The
     * representation scheme is
     * 
     * year : some positive value >= 1700 month : 1-12 for January to December
     * day : 1-31 for the day of the month
     * 
     * such that the three together represent a valid date. We are not testing
     * for input validity, however.
     */
    private int scoreMonth, scoreDay, scoreYear;

    // default constructor
    protected ClassificationScore() {
    }

    /**
     * Set the data members with the given values.
     */
    protected ClassificationScore(int month, int day, int year) {
        scoreMonth = month;
        scoreDay = day;
        scoreYear = year;
    }

    // construct a labeled string in the month/day/year format
    public String toString() {
        return "Date= " + scoreMonth + '-' + scoreDay + '-' + scoreYear;
    }

    // test the constructors and toString methods
    public static void main(String[] args) {

        ClassificationScore perfectCow = new CowClassificationScore(1, 2, 2010,
                30, 20, 20, 30), aveCow = new CowClassificationScore(3, 4,
                2011, 10, 11, 12, 13), perfectBull = new BullClassificationScore(
                5, 6, 2012, 40, 30, 30), aveBull = new BullClassificationScore(
                7, 8, 2013, 9, 19, 29);

        System.out.println("Pefect Cow\n" + perfectCow);
        System.out.println("Average Cow\n" + aveCow);
        System.out.println("Pefect Bull\n" + perfectBull);
        System.out.println("Average Bull\n" + aveBull);

    }

}
