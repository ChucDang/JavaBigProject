package gui_matrix;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/*
 * This class is responsible for showing UI
 * */
import matrix_multiplication_parallel.MatrixMultiplicationParallel;



public class Matrix_UI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel matrix1 = new JPanel();//Bảng điều khiển của ma trận 1
	private JPanel matrix2 = new JPanel();//Bảng điều khiển của ma trận 2

	private JPanel gMatrix1 = new JPanel();//Panel chứa ma trận 1
	private JPanel gMatrix2 = new JPanel();//Panel chứa ma tr
	private JPanel matrixResult = new JPanel();//Panel chứa ma trận kết quả

	private JScrollPane pane1 = new JScrollPane();//Các Scollpane tương ứng cho mỗi panel ma trận
	private JScrollPane pane2 = new JScrollPane();
	private JScrollPane pane3 = new JScrollPane();

	private JTextField tfRow1 = new JTextField(5);//Các JTextField nhập dòng và cột
	private JTextField tfCol1 = new JTextField(5);
	private JTextField tfRow2 = new JTextField(5);
	private JTextField tfCol2 = new JTextField(5);

	private JButton btnCreate1 = new JButton("Tạo ma trận 1");
	private JButton btnCreate2 = new JButton("Tạo ma trận 2");
	private JButton btnMultiply = new JButton("Nhân 2 ma trận");
	private JButton btnResult = new JButton("Ma trận kết quả");
	private JButton btnReset = new JButton("Tạo mới");
	private JButton btnRead1 = new JButton("Đọc từ file");
	private JButton btnRead2 = new JButton("Đọc từ file");

	private JButton btnCancel1 = new JButton("Hủy");
	private JButton btnCancel2 = new JButton("Hủy");

	private JButton btnGet1 = new JButton("Nhận giá trị");
	private JButton btnGet2 = new JButton("Nhận giá trị");

	private JCheckBox checkBox1 = new JCheckBox("Random");
	private JCheckBox checkBox2 = new JCheckBox("Random");

	
	private static int[][] mtr1, mtr2, mtrResult;//Biến lưu giá trị của mảng tương ứng với các ma trận
	private JTextField matrix11[][], matrix22[][], matrix33[][];//Mảng các JTextField thể hiện giá trị của ma trận lên giao diện
	public static int row1, col1, row2, col2, row3, col3;//Biến chứa giá trị hàng và cột của các ma trận
	static boolean flagCreate1 = false;
	
	/*
	 * Jpanel chính
	 * */
	public Matrix_UI() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1.0;

		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(4, 1, 4, 1);

		matrix1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Nhập dữ liệu cho ma trận 1"));
		add(matrix1, gc);
		matrix1.add(inputPane1(), gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 1.0;
		matrix2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Nhập dữ liệu cho ma trận 2"));
		add(matrix2, gc);
		matrix2.add(inputPane2(), gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weighty = 1.0;
		pane1.setViewportView(gMatrix1);

		add(pane1, gc);
		gMatrix1.setVisible(false);
		gMatrix1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ma trận 1"));

		gc.gridx = 1;
		gc.gridy = 1;
		gc.weighty = 1.0;
		pane2.setViewportView(gMatrix2);
		add(pane2, gc);
		gMatrix2.setVisible(false);
		gMatrix2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ma trận 2"));

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.BOTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		add(resultPane(), gc);

		gc.gridx = 0;
		gc.gridy = 3;
		gc.weighty = 1;
		pane3.setViewportView(matrixResult);
		add(pane3, gc);
		matrixResult.setVisible(false);
		matrixResult.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ma trận kết quả"));
	}

	/*
	 * Panel chứa bảng điều khiển của ma trận 1
	 * */
	protected JPanel inputPane1() {
		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(new LineBorder(Color.CYAN));
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(3, 2, 3, 2);
		jp.add(new JLabel("Nhập số hàng:"), gc);

		gc.gridx++;
		jp.add(tfRow1, gc);

		gc.gridx++;
		jp.add(new JLabel("Nhập số cột:"), gc);

		gc.gridx++;
		jp.add(tfCol1, gc);

		gc.gridx++;
		jp.add(checkBox1, gc);
		checkBox1.addItemListener(new ButtonHandler());

		gc.gridx++;
		jp.add(btnCreate1, gc);
		btnCreate1.setBackground(new Color(200, 221, 242));
		btnCreate1.addActionListener(new ButtonHandler());

		gc.gridx = 0;
		gc.gridy = 1;
		jp.add(btnCancel1, gc);
		btnCancel1.addActionListener(new ButtonHandler());
		btnCancel1.setEnabled(false);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 2;
		jp.add(btnRead1, gc);
		btnRead1.addActionListener(new ButtonHandler());
		
		gc.gridx = 3;
		gc.gridy = 1;
		gc.gridwidth = 2;
		jp.add(btnGet1, gc);
		btnGet1.addActionListener(new ButtonHandler());
		btnGet1.setEnabled(false);

		

		return jp;
	}

	/*
	 * Panel chứa bảng điều khiển của ma trận 2
	 * */
	private JPanel inputPane2() {
		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(new LineBorder(Color.CYAN));
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(3, 2, 3, 2);
		jp.add(new JLabel("Nhập số hàng:"), gc);

		gc.gridx++;
		jp.add(tfRow2, gc);
		tfRow2.setEnabled(false);

		gc.gridx++;
		jp.add(new JLabel("Nhập số cột:"), gc);

		gc.gridx++;
		jp.add(tfCol2, gc);

		gc.gridx++;
		jp.add(checkBox2, gc);
		checkBox2.addItemListener(new ButtonHandler2());

		gc.gridx++;
		jp.add(btnCreate2, gc);
		btnCreate2.setBackground(new Color(200, 221, 242));
		btnCreate2.addActionListener(new ButtonHandler2());

		gc.gridx = 0;
		gc.gridy = 1;
		jp.add(btnCancel2, gc);
		btnCancel2.addActionListener(new ButtonHandler2());
		btnCancel2.setEnabled(false);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 2;
		jp.add(btnRead2, gc);
		btnRead2.addActionListener(new ButtonHandler());
		
		gc.gridx = 3;
		gc.gridy = 1;
		gc.gridwidth = 2;
		jp.add(btnGet2, gc);
		btnGet2.addActionListener(new ButtonHandler2());
		btnGet2.setEnabled(false);

		return jp;
	}

	/*
	 * Panel chứa bảng điều khiển của ma trận kết quả
	 * */
	private JPanel resultPane() {
		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(new LineBorder(Color.CYAN));
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(4, 1, 4, 1);
		jp.add(btnMultiply,gc);
		btnMultiply.addActionListener(new ButtonHandlerResult());
		
		gc.gridx++;
		jp.add(btnResult, gc);
		btnResult.addActionListener(new ButtonHandlerResult());
		btnResult.setEnabled(false);

		gc.gridx++;
		jp.add(btnReset, gc);
		btnReset.addActionListener(new ButtonHandlerResult());

		return jp;
	}

	private boolean isEmptyFields1() {
		return (tfRow1.getText().trim().isEmpty() || tfCol1.getText().trim().isEmpty());
	}

	private boolean isEmptyFields2() {
		return (tfRow2.getText().trim().isEmpty() || tfCol2.getText().trim().isEmpty());
	}
	

	/*
	 * Lớp xử lý sự kiện cho bảng điều khiển của ma trận 1
	 */
	private class ButtonHandler implements ActionListener, ItemListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnCreate1) {
				if (isEmptyFields1()) {
					JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ số dòng và cột");
					return;
				} else {
					try {
						
						int row1 = Integer.parseInt(tfRow1.getText().trim());
						int col1 = Integer.parseInt(tfCol1.getText().trim());
						matrix11 = new JTextField[row1][col1];
						tfRow1.setEnabled(false);
						tfCol1.setEnabled(false);
						MatrixGeneratorSwingWorker mg1 = new MatrixGeneratorSwingWorker();
						mg1.Generator(row1, col1, gMatrix1, matrix11, checkBox1.isSelected());
						
						btnCreate1.setEnabled(false);
						tfRow2.setText(tfCol1.getText().trim());
						tfRow2.setEnabled(false);
						btnCancel1.setEnabled(true);
						btnGet1.setEnabled(true);
						flagCreate1 = true;
						
					} catch (Exception ex) {
						tfRow1.setText("");
						tfCol1.setText("");
						JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên");
						return;
					}

				}
			}
			if (e.getSource() == btnCancel1) {

				Cancel(tfRow1, tfCol1, gMatrix1, true);
				Cancel(tfRow2, tfCol2, gMatrix2, true);
				btnCreate1.setEnabled(true);

			}
			if (e.getSource() == btnRead1) {
				
//				Cancel(tfRow1, tfCol1, gMatrix1, true);
//				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//				int returnValue = jfc.showOpenDialog(null);
//				if (returnValue == JFileChooser.APPROVE_OPTION) {
//					File selectedFile = jfc.getSelectedFile();
//					String path = selectedFile.getAbsolutePath();
//					System.out.println(selectedFile.getAbsolutePath());
//					FileRead_Write fr = new FileRead_Write();
//					try {
//						Matrix_UI.mtr1 = fr.ReadData(path);
//					} catch (IOException e1) {
//
//						e1.printStackTrace();
//					}
//					int row = Matrix_UI.mtr1.length;
//					int col = Matrix_UI.mtr1[0].length;
//					matrix11 = new JTextField[row][col];
//					MatrixGeneratorSwingWorker mg = new MatrixGeneratorSwingWorker();
//					mg.GeneratorMatrixResult(row,col,gMatrix1, Matrix_UI.mtr1, matrix11);
//					JOptionPane.showMessageDialog(null, "Đọc file thành công");
//					btnGet1.setEnabled(true);
//					Matrix_UI.flagCreate1 = false;
//					tfRow1.setText(String.valueOf(row));
//					tfCol1.setText(String.valueOf(col));
//					tfRow2.setText(String.valueOf(col));
//					tfRow1.setEnabled(false);
//					tfCol1.setEnabled(false);

				//}
			}
			if (e.getSource() == btnGet1) {
				if (Matrix_UI.flagCreate1 == true) {
					Matrix_UI.row1 = Integer.parseInt(tfRow1.getText().trim());
					Matrix_UI.col1 = Integer.parseInt(tfCol1.getText().trim());
				} else {
					Matrix_UI.row1 = Matrix_UI.mtr1.length;
					Matrix_UI.col1 = Matrix_UI.mtr1[0].length;
				}
				try {
					int i, j;
					Matrix_UI.mtr1 = new int[Matrix_UI.row1][Matrix_UI.col1];
					for (i = 0; i < Matrix_UI.row1; i++) {
						for (j = 0; j < Matrix_UI.col1; j++) {
							if (matrix11[i][j].getText().trim().isEmpty()) {
								matrix11[i][j].setText("0");
								Matrix_UI.mtr1[i][j] = 0;
							} else {
								Matrix_UI.mtr1[i][j] = Integer.parseInt(matrix11[i][j].getText().trim());
							}
							matrix11[i][j].setEnabled(false);
						}
					}
					btnCreate1.setEnabled(false);
					btnGet1.setEnabled(false);
					btnCancel1.setEnabled(false);
					btnCancel2.setEnabled(true);
					btnGet2.setEnabled(true);
					btnRead1.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Đã nhận giá trị của ma trận 1 thành công");
					return;
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên");
					return;
				}

			}

		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == checkBox1) {
				if (checkBox1.isSelected())
					;
				return;
			}

		}

	}

	private class ButtonHandler2 implements ActionListener, ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == checkBox2) {
				if (checkBox2.isSelected())
					return;
			}

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCreate2) {
				if (isEmptyFields1()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số dòng và cột cho ma trận 1 trước");
					return;
				}
				if (isEmptyFields2()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ số dòng và cột");
					return;
				}
				if (Integer.parseInt(tfRow2.getText().trim()) != Integer.parseInt(tfCol1.getText().trim())) {
					JOptionPane.showMessageDialog(null,
							"Số dòng của ma trận 2 phải bằng với số cột của ma trận 1");
					return;
				}

				else {
					try {
						row2 = Integer.parseInt(tfRow2.getText().trim());
						col2 = Integer.parseInt(tfCol2.getText().trim());
						matrix22 = new JTextField[row2][col2];
						tfRow2.setEnabled(false);
						tfCol2.setEnabled(false);
						MatrixGeneratorSwingWorker mg2 = new MatrixGeneratorSwingWorker();
						mg2.Generator(row2, col2, gMatrix2, matrix22, checkBox2.isSelected());

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên");
						return;
					}
				}
			}
			if (e.getSource() == btnCancel2) {

				Cancel(tfRow2, tfCol2, gMatrix2, false);
				btnCreate2.setEnabled(true);

			}
			if(e.getSource() == btnRead2) {
				
			}
			if (e.getSource() == btnGet2) {
				if (Integer.parseInt(tfRow2.getText().trim()) != Integer.parseInt(tfCol1.getText().trim())) {
					JOptionPane.showMessageDialog(null,
							"Số dòng của ma trận 2 phải bằng với số cột của ma trận 1");
					return;
				}
				
				try {
					Matrix_UI.row2 = Integer.parseInt(tfRow2.getText().trim());
					Matrix_UI.col2 = Integer.parseInt(tfCol2.getText().trim());
					int i, j;
					Matrix_UI.mtr2 = new int[Matrix_UI.row2][Matrix_UI.col2];

					for (i = 0; i < Matrix_UI.row2; i++) {
						for (j = 0; j < Matrix_UI.col2; j++) {
							if (matrix22[i][j].getText().trim().isEmpty()) {
								matrix22[i][j].setText("0");
								Matrix_UI.mtr2[i][j] = 0;
							} else {
								Matrix_UI.mtr2[i][j] = Integer.parseInt(matrix22[i][j].getText().trim());
							}
							matrix22[i][j].setEnabled(false);
						}
					}
					btnCreate2.setEnabled(false);
					btnResult.setEnabled(true);
					btnCancel2.setEnabled(false);
					btnGet2.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Đã nhận giá trị của ma trận 2 thành công");
					return;
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên vào ma trận 2");
					return;
				}
			}

		}

	}

	public void Cancel(JTextField row, JTextField col, JPanel name, boolean check) {
		if(check==true) {
			row.setText("");
		}
		col.setText("");
		row.setEnabled(true);
		col.setEnabled(true);
		name.setVisible(false);
		name.removeAll();

	}

	private class ButtonHandlerResult implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnResult) {			
				Matrix_UI.row3 = Matrix_UI.mtrResult.length;
				Matrix_UI.col3 = Matrix_UI.mtrResult[0].length;
				matrix33 = new JTextField[Matrix_UI.row3][Matrix_UI.col3];
				MatrixGeneratorSwingWorker mg = new MatrixGeneratorSwingWorker();
				mg.GeneratorMatrixResult(Matrix_UI.row3,Matrix_UI.col3,matrixResult, Matrix_UI.mtrResult, matrix33);
				btnResult.setEnabled(false);
			}
			if (e.getSource() == btnMultiply) {
				try {
					Matrix_UI.mtrResult = new int[Matrix_UI.mtr1.length][Matrix_UI.mtr2[0].length];
					MatrixMultiplicationParallel mf = new MatrixMultiplicationParallel();
					Matrix_UI.mtrResult = mf.MatrixMul(Matrix_UI.mtr1, Matrix_UI.mtr2);
					System.out.println("mtrkq");
					for (int i = 0; i < Matrix_UI.mtr1.length; i++) {
						for (int j = 0; j < Matrix_UI.mtr2[0].length; j++) {
							System.out.print(Matrix_UI.mtrResult[i][j] + "   Nhan");

						}
					}
					JOptionPane.showMessageDialog(null, "Đã nhân xong");
					return;
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhận giá trị của ma trận trước");
					return;
				}
			}
			if (e.getSource() == btnReset) {
				Cancel(tfRow1, tfCol1, gMatrix1,true);
				btnCreate1.setEnabled(true);
				Cancel(tfRow2, tfCol2, gMatrix2,true);
				btnCreate2.setEnabled(true);
				matrixResult.setVisible(false);
				btnRead1.setEnabled(true);
				matrixResult.removeAll();
				
			}

		}

	}

}