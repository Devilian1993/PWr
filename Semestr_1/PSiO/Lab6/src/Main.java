public class Main {
    public static void main(String[] args) {
        final int number_of_rows = 20;
        final int number_of_columns = 30;

        Matrix matrix = new Matrix(number_of_rows, number_of_columns, false);
        matrix.printMatrix();
        Matrix result_matrix = new Matrix(number_of_rows, number_of_columns, true);
        matrix.maxPooling(result_matrix, 2);
        result_matrix.printMatrix();
    }
}