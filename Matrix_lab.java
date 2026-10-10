import java.util.Scanner;
class Matrix_Lab {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Complex a = new Complex(1,2);
        Complex b = new Complex(3,4);
        Complex c = a.add(b);




      }

}
class Matrix{
    Complex[][] matrix;
    Matrix(int m, int n){
        this.matrix = new Complex[m][n];
    }
}
class Complex{
    double re;
    double im;

    Complex(double re, double im) {
    this.re = re;
    this.im = im;
}

void output() {
    System.out.println(this.re + " + " + this.im + "i");
}
    Complex add(Complex that){
        return new Complex(
            this.re + that.re,
            this.im + that.im
        );
    }

    Complex difference(Complex that){
        return new Complex(
            this.re - that.re,
            this.im - that.im
        );
    }
    Complex composition(Complex that){
        return new Complex(
            this.re * that.re - this.im * that.im ,
            this.re * that.im + that.re * this.im
        );
    }
    double modul(){
        return 
            Math.sqrt(this.re * this.re + this.im * this.im);
    }

    Complex division(Complex that){
        double denominator = that.modul();
        if (denominator == 0) {
            System.out.println("Деление на ноль!");
            return new Complex(0, 0);
        }
        Complex inversion = that.conjugation();
        Complex numerator = this.composition(inversion);
        Complex result = new Complex(numerator.re / (denominator*denominator) , numerator.im / (denominator*denominator));
        return result;
    }

    Complex conjugation(){
        return new Complex(
            this.re,
            -this.im
        );
    }
}