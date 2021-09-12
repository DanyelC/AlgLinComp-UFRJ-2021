package telas;
import mat.Algoritmo;
import  mat.MatFunc;
import mat.Resposta;
import mat.Utils;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.DropMode;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Tela_principal extends JFrame {
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_principal frame = new Tela_principal();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void calc(String caminhoentrada, String caminhosaida , String ordemdamatriz, String determinante, String toleranciamaxima, String metodo) throws IOException {

		Scanner input = null ;
		try{
			input = new Scanner(new File(caminhoentrada));
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERRO! - O arquivo de entrada não existe ou o caminho informado está incorreto. Informe o caminho completo do arquivo.");
			return;
		}
		int size=0;
		try{
			size = Integer.parseInt(ordemdamatriz);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERRO! - A entrada 'ordem da matriz' deve ser um número inteiro.");
			return;
		}
		double[][] a = new double[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				try{
					a[i][j] =  Double.parseDouble(input.next());
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null,"ERRO! - Revise as variáveis de entrada do programa");
					return;
				}
			}
		}
		input.close();



		//print the input matrix
		System.out.println("Matriz A lida:");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.printf("%.2f ", a[i][j]);
			}
			System.out.println();
		}
		System.out.println();


		FileWriter arquivo_saida = new FileWriter(caminhosaida);
		PrintWriter escritor = new PrintWriter(arquivo_saida);


		escritor.println("Matriz A lida:");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				escritor.printf("%.2f ", a[i][j]);
			}
			escritor.println();
		}
		escritor.println();

		switch (metodo) {
		case "jacobi": 
			double tol_max = Double.parseDouble(toleranciamaxima);
			Resposta resp2 = MatFunc.jacobi(a,tol_max);
			escritor.println("Método de Jacobi");

			double[][] matrix = a;
			Algoritmo algoritmo = new Algoritmo(tol_max, matrix);

			double[][] result = algoritmo.getEigenvalue();

			escritor.println();
			escritor.println("Autovalores");
			for (int i = 0; i <a[0].length; i++) {
				escritor.println(result[i][i]);
			}
			escritor.println();

			arquivo_saida.close();
			JOptionPane.showMessageDialog(null,"Arquivo gerado com sucesso em: "+caminhosaida);
			break;


		case "pot": 
			double tol_max_2 = Double.parseDouble(toleranciamaxima);
			Resposta resp3 =MatFunc.pot(a, tol_max_2);
			escritor.println("Método da Potência");
			escritor.println();

			escritor.println("Atualização por iteração: ");
			int contador = 0;
			boolean parou = false;
			for (int i = 0; i<resp3.erros.size();i++) {
				escritor.printf("Iteração %d: \n",i+1);
				escritor.printf("Erro: %.10f     " , resp3.erros.get(i));
				escritor.println();
				escritor.printf("Autovalor: %.2f     ", resp3.autovalor.get(i));
				escritor.println();
				escritor.printf("Autovetor: ");
				for (int j = 0; j<3; j++) {
					escritor.printf("%.2f  ", resp3.autovetor.get(i+contador));
					contador+=1;
				}
				escritor.println();
				contador-=1;
			};
			escritor.println();
			escritor.println();
			arquivo_saida.close();
			JOptionPane.showMessageDialog(null,"Arquivo gerado com sucesso em: "+caminhosaida);
			break;

		}
	}


	/**
	 * Create the frame.
	 */
	public Tela_principal() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tela_principal.class.getResource("/imagens/logo1.jpg")));
		setTitle("C\u00E1lculo de autovalores e autovetores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 420); // tamanho da tela

		JFileChooser openFileChooser;
		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(new File ("c:\\temp"));
		openFileChooser.setFileFilter(new FileNameExtensionFilter("arquivos em texto","txt"));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmManualDeInstrues = new JMenuItem("Manual de Instru\u00E7\u00F5es");
		mntmManualDeInstrues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Tela_ajuda telaajuda = new Tela_ajuda();
				telaajuda.setVisible(true);
			}
		});
		mntmManualDeInstrues.setHorizontalAlignment(SwingConstants.CENTER);
		mnAjuda.add(mntmManualDeInstrues);

		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);

		JMenuItem mntmSobreOPrograma = new JMenuItem("Sobre o programa");
		mntmSobreOPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_sobre telasobre = new Tela_sobre();
				telasobre.setVisible(true);
			}
		});
		mntmSobreOPrograma.setHorizontalAlignment(SwingConstants.CENTER);
		mnSobre.add(mntmSobreOPrograma);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(tabbedPane, BorderLayout.CENTER);


		JLabel tolmax = new JLabel("Toler\u00E2ncia m\u00E1xima (erro m\u00E1ximo)");
		tolmax.setEnabled(true);
		tolmax.setHorizontalTextPosition(SwingConstants.CENTER);
		tolmax.setHorizontalAlignment(SwingConstants.CENTER);
		tolmax.setFont(new Font("Segoe UI", Font.BOLD, 16));

		JScrollPane scrollPane_texttol = new JScrollPane();
		scrollPane_texttol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_texttol.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_texttol.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));


		JTextArea texttolmax = new JTextArea();
		scrollPane_texttol.setViewportView(texttolmax);
		texttolmax.setBorder(null);
		texttolmax.setEnabled(true);
		texttolmax.setDropMode(DropMode.INSERT);


		ActionListener iterativoActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton butjacobi = (AbstractButton) actionEvent.getSource();
				AbstractButton butpot = (AbstractButton) actionEvent.getSource();
			}
		};

		ActionListener decActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton butpot = (AbstractButton) actionEvent.getSource();
			}
		};


		JPanel panel = new JPanel();
		panel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Dados de Entrada", null, panel, null);

		JLabel lblOrdemDaMatriz = new JLabel("Ordem da matriz");
		lblOrdemDaMatriz.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblOrdemDaMatriz.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemDaMatriz.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMtodoDeSoluo = new JLabel("M\u00E9todo para c\u00E1lculo dos autovalores e autovetores");
		lblMtodoDeSoluo.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblMtodoDeSoluo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMtodoDeSoluo.setHorizontalAlignment(SwingConstants.LEFT);

		JRadioButton butpot = new JRadioButton("M\u00E9todo da Pot\u00EAncia");
		butpot.setFocusPainted(false);
		butpot.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		butpot.setActionCommand("");
		butpot.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(butpot);

		JRadioButton butjacobi = new JRadioButton("M\u00E9todo de  Jacobi");
		butjacobi.setFocusPainted(false);
		butjacobi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		butjacobi.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(butjacobi);

		butjacobi.addActionListener(iterativoActionListener);
		butpot.addActionListener(decActionListener);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);

		JLabel lblCaminhoParaA = new JLabel("Caminho para a matriz a ser analisada");
		lblCaminhoParaA.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCaminhoParaA.setHorizontalAlignment(SwingConstants.LEFT);
		lblCaminhoParaA.setFont(new Font("Segoe UI", Font.BOLD, 17));

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JTextArea textpathentrada = new JTextArea();
		scrollPane_1.setViewportView(textpathentrada);
		textpathentrada.setBorder(null);
		textpathentrada.setDropMode(DropMode.INSERT);


		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.setBackground(new Color(192, 192, 192));
		tabbedPane.addTab("Dados de Sa\u00EDda", null, panel_1, null);

		JLabel lblCaminhoParaSaida = new JLabel("Caminho para arquivo de sa\u00EDda");
		lblCaminhoParaSaida.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCaminhoParaSaida.setHorizontalAlignment(SwingConstants.LEFT);
		lblCaminhoParaSaida.setFont(new Font("Segoe UI", Font.BOLD, 17));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(new Rectangle(0, 0, 150, 200));
		ImageIcon icon = (new ImageIcon(Tela_principal.class.getResource("/imagens/Minerva_UFRJ_Oficial.png")));
		Image img = icon.getImage();
		Image imgScale = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgScale);
		lblNewLabel.setIcon(scaledIcon);

		JLabel Labelolho = new JLabel("");
		Labelolho.setVerticalAlignment(SwingConstants.TOP);
		Labelolho.setBackground(Color.WHITE);
		Labelolho.setBounds(new Rectangle(0, 0, 150, 150));
		ImageIcon icone = (new ImageIcon(Tela_principal.class.getResource("/imagens/logo1.jpg")));
		Image imge = icone.getImage();
		Image imgScaled = imge.getScaledInstance(Labelolho.getWidth(), Labelolho.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcone = new ImageIcon(imgScaled);
		Labelolho.setIcon(scaledIcone);

		JButton butPronto = new JButton("Pronto");

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap(234, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addGap(115)
						.addComponent(Labelolho)
						.addGap(200))
				.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblCaminhoParaSaida, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(butPronto)
						.addContainerGap(69, Short.MAX_VALUE))
				);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(46)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCaminhoParaSaida, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
												.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
												.addComponent(butPronto))
										.addGap(9)))
						.addGap(21)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addGap(30)
										.addComponent(Labelolho)))
						.addContainerGap())
				);

		JTextArea textpathsaida = new JTextArea();
		scrollPane_2.setViewportView(textpathsaida);
		textpathsaida.setDropMode(DropMode.INSERT);
		textpathsaida.setBorder(null);
		panel_1.setLayout(gl_panel_1);


		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(37)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblMtodoDeSoluo)
										.addGap(29)
										.addComponent(butpot)
										.addGap(18)
										.addComponent(butjacobi))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblOrdemDaMatriz)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
										.addComponent(tolmax)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(scrollPane_texttol, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addGap(29)))
						.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblCaminhoParaA, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(31)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOrdemDaMatriz, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(tolmax, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_texttol, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(28)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(35)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMtodoDeSoluo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(butpot)
								.addComponent(butjacobi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(32)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(33)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCaminhoParaA, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(8)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(41, Short.MAX_VALUE))
				);

		JTextArea textordem = new JTextArea();
		scrollPane.setViewportView(textordem);
		textordem.setBorder(null);
		textordem.setWrapStyleWord(true);
		textordem.setMinimumSize(new Dimension(5, 20));
		textordem.setMaximumSize(new Dimension(5, 20));
		textordem.setColumns(1);
		textordem.setRows(1);


		textordem.setDropMode(DropMode.INSERT);
		panel.setLayout(gl_panel);


		ActionListener determinanteActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton butpot = (AbstractButton) actionEvent.getSource();
			}
		};

		ActionListener determinante_2ActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton  butgauss= (AbstractButton) actionEvent.getSource();
				AbstractButton butjacobi = (AbstractButton) actionEvent.getSource();
			}
		};
		butjacobi.addActionListener(determinante_2ActionListener);
		butpot.addActionListener(determinanteActionListener);


		butPronto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (butpot.isSelected()) {
					try {
						calc(textpathentrada.getText(), textpathsaida.getText(), textordem.getText(), "determinante", texttolmax.getText(), "pot"  );
					} catch (IOException e1) {
						e1.printStackTrace();}
				}
				if (butjacobi.isSelected()) {
					try {
						calc(textpathentrada.getText(), textpathsaida.getText(), textordem.getText(),  "determinante", texttolmax.getText(), "jacobi"  );
					} catch (IOException e1) {
						e1.printStackTrace();}
				}

			}
		});

	}
}
