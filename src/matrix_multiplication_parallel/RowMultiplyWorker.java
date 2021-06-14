package matrix_multiplication_parallel;

public class RowMultiplyWorker implements Runnable{

	private int[][] result;
	private int[][] matrix1;
	private int[][] matrix2;
	private int row;

	public RowMultiplyWorker(int[][] result, int[][] matrix1, int[][] matrix2, int row) {
		this.result = result;
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.row = row;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < matrix2[0].length; i++) {// Vòng lặp cho từng phần tử trong hàng của ma trận 2.
			result[row][i] = 0;
			for (int j = 0; j < matrix1[row].length; j++) {// Vòng lặp từng phần tử trong hàng của ma trận 1.
				result[row][i] += matrix1[row][j] * matrix2[j][i];// Gán giá trị nhân vào ma trận kết quả result
			}
		}
	}
}
