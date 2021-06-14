package matrix_multiplication_parallel;

public class MatrixMultiplicationParallel {
	public int[][] MatrixMul(int[][] mtr1, int[][] mtr2) {

		  int[][] result = new int[mtr1.length][mtr2[0].length];
		  ParallelThreadsCreator.multiply(mtr1, mtr2, result);
		  return result;

		 }

}
