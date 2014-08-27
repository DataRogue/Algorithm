public abstract class Animal {

    // registration number, name, date of birth, gender,
    // sire registration number, dam registration number,
    // / classification score(which may be lacking is a specific animal).
    // You should minimally define a toString method to convert the common
    // data to a String, getters for the sire and dam registration numbers,
    // and a constructor for the common fields.

    protected String regNum;
    protected String name;
    protected int[] dateOfBirth;
    protected boolean genderMale;
    protected String sireRegNum;
    protected String damRegNum;
    protected int[] classScore;

    public String toString() {

        String _string = "Registration number:  " + regNum + "  \n ";
        _string += "Name:  " + name + "  \n ";
        _string += "Born:  " + dateOfBirth[0] + " -" + dateOfBirth[1] + "-"
                + dateOfBirth[2] + " \n ";
        if (classScore.length == 0) {
            _string += "Classification: N/A \n ";
        } else {
            _string += "Classification: Date = " + classScore[0] + "/"
                    + classScore[1] + "/" + classScore[2] + " GA= "
                    + classScore[3] + " DC= " + classScore[4] + " BC= "
                    + classScore[5] + " \n ";
        }
        return _string;
    }

    public String getSire() {
        return sireRegNum;
    }

    public String getDam() {
        return damRegNum;
    }
}
