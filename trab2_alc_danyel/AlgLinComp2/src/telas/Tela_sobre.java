package telas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class Tela_sobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_sobre frame = new Tela_sobre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_sobre() {
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tela_sobre.class.getResource("/imagens/logo1.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JLabel ajudalabel = new JLabel();
		//ajudalabel.setBounds(new Rectangle(100, 100, 400, 300));
		ajudalabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ajudalabel.setToolTipText("");
		ajudalabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ajudalabel.setHorizontalTextPosition(SwingConstants.CENTER);
		ajudalabel.setHorizontalAlignment(SwingConstants.CENTER);
		ajudalabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ajudalabel.setText("<html> Programa desenvolvido pelo aluno Danyel Clin\u00E1rio dos Santos, <br>utilizando o pacote de desenvolvimento Java 11. O Programa <br> foi desenvolvido para a segunda avalia\u00E7\u00E3o da disciplina \u00C1lgebra <br> Linear Computacional, ministrada pelo Professor Lu\u00EDs Volnei  <br> Sudati Sagrilo, no ano de 2021, no per\u00EDodo 2020.2 da Faculdade <br>Federal do Rio de Janeiro - UFRJ</html>");
		JScrollPane scroll = new JScrollPane(ajudalabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		scroll.setBounds(0, 0, 485, 413);
		scroll.setMinimumSize(new Dimension(500, 450));
		
		
		
		getContentPane().add(scroll);
		
		
	}

}
