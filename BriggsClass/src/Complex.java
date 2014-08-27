
/*************************************************************************
 *  Compilation:  javac Complex.java
 *  Execution:    java Complex
 *
 *  Data type for complex numbers.
 *
 *  The data type is "immutable" so once you create and initialize
 *  a complex object, you cannot change it. The "final" keyword
 *  when declaring re and im enforces this rule, making it a
 *  compile-time error to change the .re or .im fields after
 *  they've been initialized.
 *
 *  % java Complex
 *  a = a = 5.0 + 6.0i
 *  b = -3.0 + 4.0i
 *  c = -39.0 + 2.0i
 *  d = -39.0 + -2.0i
 *  e = 5.0
 *
 *************************************************************************/

public class Complex {
    private final double re;   // the real part
    private final double im;   // the imaginary part

    // create a new object with the given real and imaginary parts
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // return a string representation of the invoking object
    public String toString()  { return re + " + " + im + "i"; }

	public double getReal() {return re;}

	public double getImaginary() {return im;}
    
    // return the magnitude of the receiver's complex value
    public double abs() { return Math.sqrt(re*re + im*im);  }

    // return a new object whose value is (this + val)
    public Complex add(Complex val) { 
        double real = this.re + val.re;
        double imag = this.im + val.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }

    // return a new object whose value is (this - val)
    public Complex subtract(Complex val) { 
        return new Complex(re - val.re, im - val.im);
    }

    // return a new object whose value is (this * val)
    public Complex multiply(Complex val) {
        return new Complex(re * val.re - im * val.im,
                           re * val.im + im * val.re);
    }

    // return a new object whose value is the conjugate of this
    public Complex conjugate() {  return new Complex(re, -im); }

    // a static version of add
    public static Complex add(Complex a, Complex b) { 
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }

    // a static version of subtract
    public static Complex subtract(Complex a, Complex b) { 
        return new Complex(a.re - b.re, a.im - b.im);
    }


}

