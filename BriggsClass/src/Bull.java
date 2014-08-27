public class Bull extends Animal {

    Proving proving;

    public Bull(String _regNum, String _name, int[] _dateOfBirth,
            boolean _genderMale, String _sireRegNum, String _damRegNum,
            int[] _classScore, float[] _proving) {
        regNum = _regNum;
        name = _name;
        dateOfBirth = _dateOfBirth;
        genderMale = _genderMale;
        sireRegNum = _sireRegNum;
        damRegNum = _damRegNum;
        classScore = _classScore;
        proving = new Proving(_proving);
    }

    public String toString() {
        String string = super.toString();
        if (classScore.length != 0) {
            string += "Total= "
                    + (classScore[3] + classScore[4] + classScore[5] + " \n ");
        }
        string += proving.toString();
        return string;
    }
}
