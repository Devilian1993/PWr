public class Matrix {
    private int rows_number;
    private int cols_number;
    private int[][] matrix;
    private boolean is_result_matrix;

    Matrix(int rows_number, int cols_number, boolean is_result_matrix) {
        this.rows_number = rows_number;
        this.cols_number = cols_number;
        this.matrix = new int[rows_number][cols_number];
        this.is_result_matrix = is_result_matrix;
        fillMatrix();
    }

    public void fillMatrix() {
        for (int i = 0; i < rows_number; i++) {
            for (int j = 0; j < cols_number; j++) {
                if (!is_result_matrix) {
                    matrix[i][j] = (int) (Math.random() * 255);
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < rows_number; i++) {
            for (int j = 0; j < cols_number; j++) {
                if (matrix[i][j] != -2) {
                    System.out.printf("%3d ", matrix[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getMaxValue(int v1, int v2, int v3, int v4) {
        return Math.max(v1, Math.max(v2, Math.max(v3, v4)));
    }

    public int getMaxValue(int v1, int v2) {
        return Math.max(v1, v2);
    }

    public void maxPooling(Matrix result_matrix, int stride) {
        for (int i = 0; i < rows_number; i += stride) {
            for (int j = 0; j < cols_number; j += stride) {
                if (i + 1 >= rows_number && j + 1 >= cols_number) {
                    result_matrix.matrix[i][j] = matrix[i][j];
                } else if (i + 1 >= rows_number) {
                    result_matrix.matrix[i][j] = getMaxValue(matrix[i][j], matrix[i][j+1]);
                } else if (j + 1 >= cols_number) {
                    result_matrix.matrix[i][j] = getMaxValue(matrix[i][j], matrix[i+1][j]);
                } else {
                    result_matrix.matrix[i][j] = getMaxValue(matrix[i][j], matrix[i][j+1], matrix[i+1][j], matrix[i+1][j+1]);
                }
            }
        }
    }

}
