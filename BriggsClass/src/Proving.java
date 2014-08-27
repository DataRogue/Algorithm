public class Proving {
    float[] proving;

    public Proving(float[] _proving) {
        proving = _proving;
    }

    public String toString() {
        String string;
        if (proving.length == 0) {
            string = "Proof: N/A \n ";
        } else {
            string = "Proof: Date =  " + (int) proving[0] + "-"
                    + (int) proving[1] + "-" + (int) proving[2] + " Dtrs =  "
                    + (int) proving[3] + " Recrds =  " + (int) proving[4]
                    + " \n AveMilk =  " + (int) proving[5] + " Ave% =  "
                    + proving[6] + " AveBf =  "
                    + ((int) (proving[5] / 100) * proving[6]) + " ExImp =  "
                    + (int) proving[7] + "\n";
        }
        return string;
    }
}
