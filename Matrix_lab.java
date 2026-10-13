import java.util.InputMismatchException;
import java.util.Scanner;

public class Matrix_lab {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = readInt(sc);

            if (choice == 7) {
                System.out.println("Выход");
                return;
            }

            switch (choice) {
                case 1: {
                    System.out.println("Сложение. Убедитесь, что размеры матриц корректны для выполнения операции.");
                    System.out.println("1 матрица:");
                    Matrix a = readMatrix(sc);
                    if (a == null) break; 
                    System.out.println("2 матрица:");
                    Matrix b = readMatrix(sc);
                    if (b == null) break; 
                    Matrix r = a.add(b);
                    if (r != null) {
                        System.out.println("Результат:");
                        r.output();
                    }
                    break;
                }
                case 2: {
                    System.out.println("Вычитание. Убедитесь, что размеры матриц корректны для выполнения операции.");
                    System.out.println("1 матрица:");
                    Matrix a = readMatrix(sc);
                    if (a == null) break; 
                    System.out.println("2 матрица:");
                    Matrix b = readMatrix(sc);
                    if (b == null) break; 
                    Matrix r = a.difference(b);
                    if (r != null) {
                        System.out.println("Результат:");
                        r.output();
                    }
                    break;
                }
                case 3: {
                    System.out.println("Умножение. Убедитесь, что размеры матриц корректны для выполнения операции.");
                    System.out.println("1 матрица:");
                    Matrix a = readMatrix(sc);
                    if (a == null) break; 
                    System.out.println("2 матрица:");
                    Matrix b = readMatrix(sc);
                    if (b == null) break; 
                    Matrix r = a.composition(b);
                    if (r != null) {
                        System.out.println("Результат:");
                        r.output();
                    }
                    break;
                }
                case 4: {
                    System.out.println("Транспонирование");
                    Matrix a = readMatrix(sc);
                    if (a == null) break;
                    System.out.println("Результат:");
                    a.transpose().output();
                    break;
                }
                case 5: {
                    System.out.println("Определитель. Убедитесь, что размер матрицы корректен для выполнения операции.");
                    Matrix a = readMatrix(sc);
                    if (a == null) break;
                    Complex det = a.determinant();
                    if (det != null) {
                        System.out.print("Определитель: ");
                        det.output();
                    }
                    break;
                }
                case 6: {
                    System.out.println("Деление. Убедитесь, что размеры матриц корректны для выполнения операции.");
                    System.out.println("1 матрица");
                    Matrix a = readMatrix(sc);
                    if (a == null) break; 
                    System.out.println("2 матрица:");
                    Matrix b = readMatrix(sc);
                    if (b == null) break; 
                    Matrix r = a.division(b);
                    if (r != null) {
                        System.out.println("Результат:");
                        r.output();
                    }
                    break;
                }
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    public static void printMenu() {
        System.out.println("\nМеню");
        System.out.println("1. Сложить матрицы");
        System.out.println("2. Вычесть матрицы");
        System.out.println("3. Умножить матрицы");
        System.out.println("4. Транспонировать матрицу");
        System.out.println("5. Определитель матрицы");
        System.out.println("6. Разделить матрицы");
        System.out.println("7. Выход");
        System.out.print("Выбор: ");
    }

    public static int readInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число.");
                sc.nextLine();
            }
        }
    }

    public static double readDouble(Scanner sc) {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число.");
                sc.nextLine();
            }
        }
    }

    public static Matrix readMatrix(Scanner sc) {
        System.out.print("Введите кол-во строк: ");
        int rows = readInt(sc);

        System.out.print("Введите кол-во столбцов: ");
        int cols = readInt(sc);
        if(rows <= 0 || cols <= 0){
            System.out.println("Ошибка: количество строк и столбцов должно быть больше 0.");
            return null;
        }
        System.out.print("Внимание! Ввод осуществляется построчно. ");

        Matrix m = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Элемент [" + i + "][" + j + "] re: ");
                double re = readDouble(sc);

                System.out.print("Элемент [" + i + "][" + j + "] im: ");
                double im = readDouble(sc);

                m.set(i, j, new Complex(re, im));
            }
        }

        System.out.println("Введённая матрица:");
        m.output();

        return m;
    }
}

class Matrix{
    private Complex[][] matrix;
    public Matrix(int m, int n){
        this.matrix = new Complex[m][n];
    }

    public void set(int i, int j, Complex value){
        this.matrix[i][j] = value;
    }

    public Complex get(int i , int j){
        return this.matrix[i][j];
}

    public void output(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if (this.matrix[i][j] != null){
                    System.out.print(this.matrix[i][j].getRe() + " + " + this.matrix[i][j].getIm() + "i   ");
                }
                else{
                    System.out.print("null  ");
                }
            }
            System.out.println();
        }
    }

    public Matrix add(Matrix that){
        if (this.matrix.length != that.matrix.length ||
            this.matrix[0].length != that.matrix[0].length) {
            System.out.println("Ошибка: размеры не совпадают");
            return null;
        }

        Matrix result = new Matrix(this.matrix.length,this.matrix[0].length);
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix[i].length; j++){
                Complex sum = this.matrix[i][j].add(that.matrix[i][j]);
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public Matrix difference(Matrix that){
        if (this.matrix.length != that.matrix.length ||
            this.matrix[0].length != that.matrix[0].length) {
            System.out.println("Ошибка: размеры не совпадают");
            return null;
        }
        Matrix result = new Matrix(this.matrix.length,this.matrix[0].length);
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix[i].length; j++){
                Complex dif = this.matrix[i][j].difference(that.matrix[i][j]);
                result.set(i, j, dif);
            }
        }
        return result;
    }

    public Matrix transpose(){
        Matrix result = new Matrix(this.matrix[0].length,this.matrix.length);
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix[i].length; j++){
                Complex trans = this.matrix[i][j];
                result.set(j,i,trans);
            }
        }
        return result;
    }

    public Matrix composition(Matrix that){
        if (this.matrix[0].length != that.matrix.length){
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

    public Complex determinant(){
        if (matrix.length == 0) {
            return new Complex(1, 0);
        }
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
                term = new Complex(-term.getRe(), -term.getIm());
            }
            det = det.add(term);
        }
        return det;
    }

    public Matrix division(Matrix that){
        if(this.matrix.length != this.matrix[0].length || that.matrix.length != that.matrix[0].length ){
            System.out.println("Ошибка.Матрица не квадратная.");
            return null;
        }
        Complex det = that.determinant();
        if (det.modul() < 1e-9){
            System.out.println("Деление невозможно , определитель делителя вырожденный");
            return null;
        }
        that = that.transpose();
        Matrix allid_matrix = new Matrix(that.matrix.length, that.matrix[0].length);
        for(int i =0; i < that.matrix.length; i++){
            for(int j =0; j < that.matrix[i].length; j++){
                Matrix minor = new Matrix(that.matrix.length - 1, that.matrix.length - 1); 
                int row = 0;
                int col = 0;
                for(int k = 0; k<that.matrix.length;k++){
                    for(int l = 0; l < that.matrix.length; l++ ){
                        if ((i != k) && (j != l)){
                            minor.set(row,col,that.matrix[k][l]);
                            if (col < that.matrix.length - 2){
                                col++;
                            }
                            else{
                                row++;
                                col = 0;
                            }
                        }
                    }
                }

                Complex term = minor.determinant();
                if ((((that.matrix.length - i - 1) + (that.matrix.length - j - 1)) % 2 == 1)) {
                    term = new Complex(-term.getRe(), -term.getIm());
                }
                allid_matrix.set(i,j,term);
            }
        }
        Complex one = new Complex(1,0);
        det = one.division(det);
        for (int i = 0; i < allid_matrix.matrix.length; i++) {
            for (int j = 0; j < allid_matrix.matrix.length; j++) {
                Complex val = allid_matrix.matrix[i][j].composition(det);
                allid_matrix.set(i, j, val);
            }
        }
        return this.composition(allid_matrix);  
    }
    }

    
class Complex{
    private double re;
    private double im;

    public Complex(double re, double im) {
    this.re = re;
    this.im = im;
}

    public void output() {
        System.out.println(this.re + " + " + this.im + "i");
    }

    public Complex add(Complex that){
        return new Complex(
            this.re + that.re,
            this.im + that.im
        );
    }

    public Complex difference(Complex that){
        return new Complex(
            this.re - that.re,
            this.im - that.im
        );
    }

    public Complex composition(Complex that){
        return new Complex(
            this.re * that.re - this.im * that.im ,
            this.re * that.im + that.re * this.im
        );
    }
    public double modul(){
        return 
            Math.sqrt(this.re * this.re + this.im * this.im);
    }

    public Complex division(Complex that){
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

    public Complex conjugation(){
        return new Complex(
            this.re,
            -this.im
        );
    }
    public double getRe() { return re; }
    public double getIm() { return im; }
}