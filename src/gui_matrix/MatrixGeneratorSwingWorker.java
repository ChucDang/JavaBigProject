package gui_matrix;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class MatrixGeneratorSwingWorker {


	public void Generator(int M, int N, JPanel name, JTextField[][] jf, boolean check) {
		name.setLayout(new GridLayout(M, N));
		GridBagConstraints gc = new GridBagConstraints();
		Random rd = new Random();
		gc.gridy = 0;

		SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
			@Override
			protected Void doInBackground() throws Exception {
				for (int v = 0; v < M; v++) {
					gc.gridx = 0;
					for (int k = 0; k < N; k++) {
						jf[v][k] = new JTextField(3);
						name.add(jf[v][k], gc);
						if (check) {
							int value = rd.nextInt(10);
							jf[v][k].setText(String.valueOf(value));
						}
						gc.gridx++;

					}
					gc.gridy++;
				}
				name.setVisible(true);
				return null;
			}
		};

		worker.execute();

	}

	public void GeneratorMatrixResult(int M, int N, JPanel name, int[][] result, JTextField[][] jf) {
		M = result.length;
		N = result[0].length;
		name.setLayout(new GridLayout(M, N));
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
			@Override
			protected Void doInBackground() throws Exception {
				for (int v = 0; v < result.length; v++) {
					gc.gridx = 0;
					for (int k = 0; k < result[0].length; k++) {
						jf[v][k] = new JTextField(3);
						name.add(jf[v][k], gc);
						jf[v][k].setText(String.valueOf(result[v][k]));
						gc.gridx++;
					}
					gc.gridy++;
				}
				name.setVisible(true);
				return null;
			}
		};
		worker.execute();
	}

}