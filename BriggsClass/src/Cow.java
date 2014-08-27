public class Cow extends Animal {
    LactationRecord lactation;

    public Cow(String _regNum, String _name, int[] _dateOfBirth,
            boolean _genderMale, String _sireRegNum, String _damRegNum,
            int[] _classScore, float[][] _lactation) {
        regNum = _regNum;
        name = _name;
        dateOfBirth = _dateOfBirth;
        genderMale = _genderMale;
        sireRegNum = _sireRegNum;
        damRegNum = _damRegNum;
        classScore = _classScore;
        lactation = new LactationRecord(_lactation);
    }

    public String toString() {
        String string = super.toString();
        if (classScore.length != 0) {
            string += "MS ="
                    + classScore[6]
                    + " Total= "
                    + (classScore[3] + classScore[4] + classScore[5]
                            + classScore[6] + " \n ");
        }
        string += lactation.toString();
        return string;
    }
}
