package matrix_multiplication_parallel;
import java.util.ArrayList;
import java.util.List;

public class ParallelThreadsCreator {

	public static void multiply(int[][] matrix1, int[][] matrix2, int[][] result) {
		List<Thread> threads = new ArrayList<>();
		int rows1 = matrix1.length;
		for (int i = 0; i < rows1; i++) {// Vòng lặp cho từng dòng ma trận, mỗi dòng gán với một luồng nhân RowMultiWorker.
			RowMultiplyWorker task = new RowMultiplyWorker(result, matrix1, matrix2, i);
			Thread thread = new Thread(task);
			thread.start();
			threads.add(thread);
			if (threads.size() % 10 == 0) {// Tạo list 10 luồng, khi chúng được thực thi xong thì mới thêm luồng mới
				waitForThreads(threads);
			}
		}
	}

	private static void waitForThreads(List<Thread> threads) {
		for (Thread thread : threads) {
			try {
				thread.join();//đảm bảo các Thread thực thi và kết thúc đúng theo thứ tự mà chúng đã được khởi tạo.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		threads.clear();
	}
}