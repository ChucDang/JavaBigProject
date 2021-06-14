package gui_matrix;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MatrixApp{

	private JFrame mainFrame;
	public MatrixApp() {
		mainFrame = new JFrame("Chương trình nhân hai ma trận nhiều chiều");
		JLabel lbltitle = new JLabel("BÀI TẬP LỚN NHÂN HAI MA TRẬN NHIỀU CHIỀU DO NHÓM 1 THỰC HIỆN");
		mainFrame.getContentPane().add(new Matrix_UI(), BorderLayout.CENTER);
		
		mainFrame.add(lbltitle, BorderLayout.NORTH);
		mainFrame.setSize(1100,400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new MatrixApp();
	}

	

}
