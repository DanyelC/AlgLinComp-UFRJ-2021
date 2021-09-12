package telas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;

public class Tela_ajuda extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_ajuda frame = new Tela_ajuda();
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
	public Tela_ajuda() {
		setResizable(false);
		//setResizable(false);
		setTitle("Ajuda");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tela_ajuda.class.getResource("/imagens/logo1.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 450);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		
		JLabel ajudalabel = new JLabel();
		ajudalabel.setVerticalTextPosition(SwingConstants.TOP);
		ajudalabel.setVerticalAlignment(SwingConstants.TOP);
		//ajudalabel.setBounds(new Rectangle(100, 100, 400, 300));
		ajudalabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ajudalabel.setToolTipText("");
		ajudalabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ajudalabel.setHorizontalTextPosition(SwingConstants.CENTER);
		ajudalabel.setHorizontalAlignment(SwingConstants.CENTER);
		ajudalabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ajudalabel.setText("<html> O programa gerado \u00E9 capaz de resolver sistemas lineares quadrados da<br>forma Ax=b utilizando os quatro m\u00E9todos aprendidos em aula:<br>\r\n- Decomposi\u00E7\u00E3o LU - permite c\u00E1lculo do determinante <br>\r\n- Decomposi\u00E7\u00E3o de Cholesky - permite c\u00E1lculo do determinante <br>\r\n- Procedimento Iterativo Jacobi <br>\r\n- Procedimento Iterativo Gauss-Seidel <br>\r\n<br>\r\n\r\nOs inputs gerais do programa s\u00E3o:<br>\r\n- A ordem da matriz A a ser analisada<br>\r\n- O m\u00E9todo de solu\u00E7\u00E3o desejado<br>\r\n- O caminho para o arquivo txt contendo a matriz A e o vetor b a serem <br>analisados.<br>\r\n<br>\r\nO caminho para o arquivo txt onde os outputs do programa ser\u00E3o escritos, <br>como a descri\u00E7\u00E3o da matriz A e vetor b lidos, determinante (se requisitado <br> e poss\u00EDvel) e vetor resposta, por exemplo.\r\n\r\nPara que o programa funcione, <br>  basta preencher as entradas corretamente e pressionar o bot\u00E3o \"pronto\" <br>  na guia \"dados de sa\u00EDda\".<br> \r\n<br>\r\nOBS: <br> \r\n- Para requisitar o determinante da matriz A, preencha o campo <br>  \"Determinante\" com a palavra \"sim\" (sem aspas). <br> \r\n- O programa espera um \".\" para ler casas decimais.  Por exemplo, escreva  <br> \"0.5\" e n\u00E3o \"0,5\" (sem aspas).   <br>\r\n- \u00C9 recomendado preencher o caminho completo para os arquivos  de  <br> entrada e saida. Exemplo: \"C:\\Users\\Myuser\\Desktop\\teste_entrada.txt\"  <br>\r\n- Exemplo de preenchimento de arquivo txt para entrada: Os primeiros  <br> elementos representam a matriz A, enquanto os pr\u00F3ximos elementos  <br> representam o vetor b.  <br>\r\n<br>\r\n3 1 -1  <br>\r\n1 3 1.1  <br>\r\n1 4 3 <br>\r\n <br>\r\n1 2 1.5 \r\n</html>");
		JScrollPane scroll = new JScrollPane(ajudalabel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		scroll.setBounds(0, 0, 485, 413);
		scroll.setMinimumSize(new Dimension(500, 450));
		
		
		
		getContentPane().add(scroll);
		
		
		
		
		
		
		
		
		
		
	}
}
