package telas;
import  mat.MatFunc;
import mat.Resposta;

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

	public static void calc(String caminhoentrada, String caminhosaida , String npontos_string, String xpy_string, String metodo) throws IOException {
		String lastLine = "";
		Scanner input = null ;
		try{
			input = new Scanner(new File(caminhoentrada));
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERRO! - O arquivo de entrada não existe ou o caminho informado está incorreto. Informe o caminho completo do arquivo.");
			return;
		}
		int size=0;
		try{
			size = Integer.parseInt(npontos_string);
			if(size==1) {
				JOptionPane.showMessageDialog(null,"ERRO! - É necessário, no mínimo, dois pontos para realizar a interporlação.");
				return;
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERRO! - A entrada 'número de pontos' deve ser um número inteiro.");
			return;
		}
		double[] x = new double[size];
		double[] y = new double[size];

		for (int i = 0; i < size; i++) {
			try{
				x[i]=  Double.parseDouble(input.next());
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null,"ERRO! - Revise as variáveis de entrada do programa");
				return;
			}
		}

		//pegar a ultima linha, onde tem os ys
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(caminhoentrada));

			while ((sCurrentLine = br.readLine()) != null) {
				lastLine = sCurrentLine;}

			//System.out.println(lastLine);
		}catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"ERRO! - Revise as variáveis de entrada do programa");
			return;
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"ERRO! - Revise as variáveis de entrada do programa");
				return;
			}
		}

		//printa o vetor lido
		System.out.print("Vetor x lido:" );
		for (int i = 0; i < size; i++) {
			System.out.printf("%.2f ", x[i]);
		}
		System.out.println();
		System.out.println();

		//printa o vetor lido
		System.out.print("Vetor y lido: ");
		String[] parts = lastLine.split(" ");
		for(int i = 0; i < Integer.parseInt(npontos_string); i++ ) {
			y[i]=Double.parseDouble(parts[i]);
			System.out.printf("%.2f ", y[i]);
		}
		System.out.println();

		input.close();

		FileWriter arquivo_saida = new FileWriter(caminhosaida);
		PrintWriter escritor = new PrintWriter(arquivo_saida);


		escritor.println("Pontos lidos (x,y):");
		for (int i = 0; i < size; i++) {
			escritor.printf("(%.2f, %.2f)", x[i],y[i]);
			escritor.println();
		}
		//escritor.close(); 

		double xpy = Double.parseDouble(xpy_string);

		switch (metodo) {
		case "inter": 
			double resp = MatFunc.inter(size,x,y,xpy);
			escritor.println("Método de interpolação");
			escritor.printf("Valor de y para %f: %f", xpy, resp);
			arquivo_saida.close();
			JOptionPane.showMessageDialog(null,"Arquivo gerado com sucesso em: "+caminhosaida);
			break;


		case "reg": 
			double resp1 =MatFunc.reg(size,x,y,xpy);
			escritor.printf("Valor de y para %f: %f", xpy, resp1);
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
		setTitle("C\u00E1lculo do valor aproximado de uma fun\u00E7\u00E3o");
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


		JLabel tolmax = new JLabel("Coordenada do ponto que se deseja calcular o valor de y");
		tolmax.setEnabled(true);
		tolmax.setHorizontalTextPosition(SwingConstants.CENTER);
		tolmax.setHorizontalAlignment(SwingConstants.CENTER);
		tolmax.setFont(new Font("Segoe UI", Font.BOLD, 16));

		JScrollPane scrollPane_texttol = new JScrollPane();
		scrollPane_texttol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_texttol.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_texttol.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));


		JTextArea xpy = new JTextArea();
		scrollPane_texttol.setViewportView(xpy);
		xpy.setBorder(null);
		xpy.setEnabled(true);
		xpy.setDropMode(DropMode.INSERT);


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

		JLabel lblOrdemDaMatriz = new JLabel("N\u00FAmero de pontos");
		lblOrdemDaMatriz.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblOrdemDaMatriz.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrdemDaMatriz.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMtodoDeSoluo = new JLabel("M\u00E9todo para c\u00E1lculo do valor aproximado");
		lblMtodoDeSoluo.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblMtodoDeSoluo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMtodoDeSoluo.setHorizontalAlignment(SwingConstants.LEFT);

		JRadioButton butinter = new JRadioButton("Interpola\u00E7\u00E3o (M\u00E9todo Lagrange)");
		butinter.setFocusPainted(false);
		butinter.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		butinter.setActionCommand("");
		butinter.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(butinter);

		JRadioButton butreg = new JRadioButton("Regress\u00E3o multilinear");
		butreg.setFocusPainted(false);
		butreg.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		butreg.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(butreg);

		butreg.addActionListener(iterativoActionListener);
		butinter.addActionListener(decActionListener);

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
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(lblCaminhoParaA, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(lblOrdemDaMatriz)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
																.addComponent(tolmax)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(scrollPane_texttol, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
														.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
																.addComponent(lblMtodoDeSoluo)
																.addGap(29)
																.addComponent(butinter)
																.addGap(18)
																.addComponent(butreg)))))
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addComponent(separator, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(19)
										.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)))
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
						.addGap(18)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(42)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMtodoDeSoluo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(butinter)
								.addComponent(butreg, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(34)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(34)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCaminhoParaA, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(8)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(41, Short.MAX_VALUE))
				);

		JTextArea npontos = new JTextArea();
		scrollPane.setViewportView(npontos);
		npontos.setBorder(null);
		npontos.setWrapStyleWord(true);
		npontos.setMinimumSize(new Dimension(5, 20));
		npontos.setMaximumSize(new Dimension(5, 20));
		npontos.setColumns(1);
		npontos.setRows(1);


		npontos.setDropMode(DropMode.INSERT);
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
		butreg.addActionListener(determinante_2ActionListener);
		butinter.addActionListener(determinanteActionListener);


		butPronto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (butinter.isSelected()) {
					try {
						calc(textpathentrada.getText(), textpathsaida.getText(), npontos.getText(), xpy.getText(), "inter"  );
					} catch (IOException e1) {
						e1.printStackTrace();}
				}
				if (butreg.isSelected()) {
					try {
						calc(textpathentrada.getText(), textpathsaida.getText(), npontos.getText(), xpy.getText(), "reg"  );
					} catch (IOException e1) {
						e1.printStackTrace();}
				}

			}
		});

	}
}
