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

    void set(int i, int j, Complex value){
        this.matrix[i][j] = value;
    }

    Complex get(int i , int j){
        return this.matrix[i][j];
}

    void output(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if (this.matrix[i][j] != null){
                    System.out.print(this.matrix[i][j].re + " + " + this.matrix[i][j].im + "i   ");
                }
                else{
                    System.out.print("null  ");
                }
            }
            System.out.println();
        }
    }

    Matrix add(Matrix that){
        Matrix result = new Matrix(this.matrix.length,this.matrix[0].length);
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix[i].length; j++){
                Complex sum = this.matrix[i][j].add(that.matrix[i][j]);
                result.set(i, j, sum);
            }
        }
        return result;
    }

    Matrix difference(Matrix that){
        Matrix result = new Matrix(this.matrix.length,this.matrix[0].length);
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix[i].length; j++){
                Complex dif = this.matrix[i][j].difference(that.matrix[i][j]);
                result.set(i, j, dif);
            }
        }
        return result;
    }

    Matrix transpose(){
        Matrix result = new Matrix(this.matrix[0].length,this.matrix.length);
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix[i].length; j++){
                Complex trans = this.matrix[i][j];
                result.set(j,i,trans);
            }
        }
        return result;
    }

    Matrix composition(Matrix that){
        if (this.matrix[0].length == that.matrix.length){
            System.out.println("Нельзя умножить: размеры не совпадают");
            return null;
        }
        Matrix result = new Matrix(this.matrix.length,that.matrix[0].length);
        for(int i = 0; i < this.matrix.length; i++){
            for(int k = 0; k < that.matrix[0].length; k++){
                Complex sum = new Complex(0,0);
                for(int j = 0; j < that.matrix.length; j++){
                    Complex comp = this.matrix[i][j].composition(that.matrix[j][k]);
                    sum = sum.add(comp);
                }
                result.set(i,k,sum);
            }
        }
        return result;
    }

    Complex determinant(){
        if(this.matrix.length != this.matrix[0].length){
            System.out.println("Ошибка.Матрица не квадратная.");
            return null;
        }
        if(this.matrix.length == 1){
            return this.matrix[0][0];
        }
         if(this.matrix.length == 2){
            return (this.matrix[0][0].composition(this.matrix[1][1])).difference(this.matrix[0][1].composition(this.matrix[1][0]));
        }
        Complex det = new Complex(0,0);
        for(int j = 0; j< this.matrix.length; j++){
            Matrix minor = new Matrix(this.matrix.length - 1, this.matrix.length - 1); 
            for(int i = 1; i < this.matrix.length; i++){
                int col = 0;
                for(int k = 0; k < this.matrix.length; k++){
                    if(k !=j){
                        minor.set(i-1, col, this.matrix[i][k]);
                        col++;
                    }
                }

            }
            

            Complex term = this.matrix[0][j].composition(minor.determinant());
            if (j % 2 == 1) {
                term = new Complex(-term.re, -term.im);
            }
            det = det.add(term);
        }
        return det;

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