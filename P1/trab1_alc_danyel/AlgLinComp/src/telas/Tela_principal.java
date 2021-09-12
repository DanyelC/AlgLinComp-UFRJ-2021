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
	
	public static void calc(String caminhoentrada, String caminhosaida , String ordemdamatriz, String determinante, String toleranciamaxima, String metodo) throws IOException {
		
		String lastLine = "";
		Scanner input = null ;
		try{
			 input = new Scanner(new File(caminhoentrada));
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERRO! - O arquivo de entrada n�o existe ou o caminho informado est� incorreto. Informe o caminho completo do arquivo.");
			return;
		}
		int size=0;
		try{
			size = Integer.parseInt(ordemdamatriz);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERRO! - A entrada 'ordem da matriz' deve ser um n�mero inteiro.");
			return;
		}
        double[][] a = new double[size][size];
        double [] b =  new double [size];
        
        for (int i = 0; i < size; i++) {
        	for (int j = 0; j < size; j++) {
        		try{
        			a[i][j] =  Double.parseDouble(input.next());
                }
        		catch (Exception e) {
                	JOptionPane.showMessageDialog(null,"ERRO! - Revise as vari�veis de entrada do programa");
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

		//pegar a ultima linha, onde tem os bs
        BufferedReader br = null;
		try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(caminhoentrada));
            
            while ((sCurrentLine = br.readLine()) != null) {
                lastLine = sCurrentLine;}
            
        }catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"ERRO! - Revise as vari�veis de entrada do programa");
            return;
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"ERRO! - Revise as vari�veis de entrada do programa");
                return;
            }
        }
   
		System.out.print("Vetor B lido: ");
		String[] parts = lastLine.split(" ");
		for(int i = 0; i < Integer.parseInt(ordemdamatriz); i++ ) {
			double d;
			d= Double.parseDouble(parts[i]);
			b[i]= d;
			System.out.printf("%.2f ", b[i]);
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
		
         escritor.print("Vetor B lido: ");
 		String[] partes = lastLine.split(" ");
 		for(int i = 0; i < Integer.parseInt(ordemdamatriz); i++ ) {
 			double d;
 			d= Double.parseDouble(partes[i]);
 			b[i]= d;
 			escritor.printf("%.2f ", b[i]);
 		}
 		escritor.println();
 		escritor.println();
         

		switch (metodo) {
		case "declu": 	
			Resposta resp = MatFunc.declu(a, b);
			escritor.println("M�todo de decomposi��o LU");
			escritor.println();
			
			escritor.print("Vetor y: ");
			for (int i = 0; i<b.length;i++) {
				escritor.printf("%.2f ", resp.y[i]);
			};
			escritor.println();
			escritor.println();
			
			escritor.print("Vetor Reposta: ");
			for (int i = 0; i<b.length;i++) {
				escritor.printf("%.2f ", resp.x[i]);
			};
			escritor.println();
			escritor.println();
			
			escritor.println("Matriz L:");
	         for (int i = 0; i < size; i++) {
	             for (int j = 0; j < size; j++) {
	                escritor.printf("%.2f ", resp.l[i][j]);
	             }
	             escritor.println();
	         }
	         escritor.println();
	         
	         
	         escritor.println("Matriz U:");
	         for (int i = 0; i < size; i++) {
	             for (int j = 0; j < size; j++) {
	                escritor.printf("%.2f ", resp.u[i][j]);
	             }
	             escritor.println();
	         }
	         escritor.println();

			if (determinante.toLowerCase().equals("sim")){
				System.out.print("Determinante: ");
				System.out.println(resp.det);
				escritor.printf("Determinante: "+"%.2f ", resp.det);
				escritor.println();
			}
			arquivo_saida.close();
			JOptionPane.showMessageDialog(null,"Arquivo gerado com sucesso em: "+caminhosaida);
			break;
			
			
		case "deccholesky": 
			Resposta resp1 = MatFunc.deccholesky(a, b);
		
			escritor.println("M�todo de decomposi��o Cholesky");
			escritor.println();
			escritor.print("Vetor y: ");
			for (int i = 0; i<b.length;i++) {
				escritor.printf("%.2f ", resp1.y[i]);
			};
			escritor.println();
			escritor.println();
				
			escritor.print("Vetor Reposta: ");
			for (int i = 0; i<b.length;i++) {
				escritor.printf("%.2f ", resp1.x[i]);
			};
			escritor.println();
			escritor.println();
			
			
			escritor.println("Matriz L:");
	         for (int i = 0; i < size; i++) {
	             for (int j = 0; j < size; j++) {
	                escritor.printf("%.2f ", resp1.l[i][j]);
	             }
	             escritor.println();
	         }
	         escritor.println();
	         
	         
	         escritor.println("Matriz Lt:");
	         for (int i = 0; i < size; i++) {
	             for (int j = 0; j < size; j++) {
	                escritor.printf("%.2f ", resp1.u[i][j]);
	             }
	             escritor.println();
	         }
	         escritor.println();
	         
	         if (determinante.toLowerCase().equals("sim")){
					escritor.printf("Determinante: "+"%.2f ", resp1.det);
					escritor.println();
				}
	         
	         arquivo_saida.close();
			JOptionPane.showMessageDialog(null,"Arquivo gerado com sucesso em: "+caminhosaida);
			break;

			
		case "jacobi": 
			double tol_max = Double.parseDouble(toleranciamaxima);
			Resposta resp2 = MatFunc.jacobi(a, b, tol_max);
			escritor.println("M�todo iterativo de Jacobi");
			escritor.println();
				
			escritor.print("Vetor Reposta: ");
			for (int i = 0; i<b.length;i++) {
				if(resp2.erros.get(resp2.erros.size()-1).isInfinite()) {
					escritor.printf("%6.2e ", resp2.x[i]);
				}else {
				escritor.printf("%.2f ", resp2.x[i]);
				}
			};
			escritor.println();
			escritor.println();
			
			
			escritor.println("Erros por cada itera��o: ");
			for (int i = 0; i<resp2.erros.size();i++) {
				escritor.printf("Erro %d: %.10f " , i+1, resp2.erros.get(i));
				escritor.println();
			};
			escritor.println();
			escritor.println();
	         arquivo_saida.close();
			JOptionPane.showMessageDialog(null,"Arquivo gerado com sucesso em: "+caminhosaida);
			break;
			
			
		case "gauss": 
			double tol_max_2 = Double.parseDouble(toleranciamaxima);
			Resposta resp3 =MatFunc.gauss_seidel(a, b, tol_max_2);
			escritor.println("M�todo iterativo de Gauss-Seidel");
			escritor.println();
				
			escritor.print("Vetor Reposta: ");
			for (int i = 0; i<b.length;i++) {
				if(resp3.erros.get(resp3.erros.size()-1).isInfinite()) {
					escritor.printf("%6.2e ", resp3.x[i]);
				}else {
				escritor.printf("%.2f ", resp3.x[i]);
				}
			};
			escritor.println();
			escritor.println();
			
			
			escritor.println("Erros por cada itera��o: ");
			for (int i = 0; i<resp3.erros.size();i++) {
				escritor.printf("Erro %d: %.10f " , i+1, resp3.erros.get(i));
				escritor.println();
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
		setTitle("Solu\u00E7\u00E3o de Sistemas Lineares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 420); // tamanho da tela
		
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
		
		
		JLabel tolmax = new JLabel("Toler\u00E2ncia m\u00E1xima ( apenas para m\u00E9todos iterativos)");
		tolmax.setEnabled(false);
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
		texttolmax.setEnabled(false);
		texttolmax.setDropMode(DropMode.INSERT);
		
		
		ActionListener iterativoActionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton butjacobi = (AbstractButton) actionEvent.getSource();
		        AbstractButton butgauss = (AbstractButton) actionEvent.getSource();
				tolmax.setEnabled(true);
				texttolmax.setEnabled(true);
		      }
		 };
		 
		 ActionListener decActionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton butdeclu = (AbstractButton) actionEvent.getSource();
		        AbstractButton butdeccholesky = (AbstractButton) actionEvent.getSource();
				tolmax.setEnabled(false);
				texttolmax.setEnabled(false);
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
		
		JLabel lblMtodoDeSoluo = new JLabel("M\u00E9todo de solu\u00E7\u00E3o do sistema Linear");
		lblMtodoDeSoluo.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblMtodoDeSoluo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMtodoDeSoluo.setHorizontalAlignment(SwingConstants.LEFT);

		JRadioButton butdeclu = new JRadioButton("Decomposi\u00E7\u00E3o LU");
		butdeclu.setFocusPainted(false);
		butdeclu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		butdeclu.setActionCommand("");
		butdeclu.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(butdeclu);
		
		JRadioButton butdeccholesky = new JRadioButton("Decomposi\u00E7\u00E3o de Cholesky");
		butdeccholesky.setFocusPainted(false);
		butdeccholesky.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		butdeccholesky.setActionCommand("Decomposi\u00E7\u00E3o de Cholesky");
		butdeccholesky.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(butdeccholesky);
		
		JRadioButton butjacobi = new JRadioButton("Procedimento iterativo Jacobi");
		butjacobi.setFocusPainted(false);
		butjacobi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		butjacobi.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(butjacobi);
		
		JRadioButton butgauss = new JRadioButton("Procedimento iterativo Gauss-Seidel");
		butgauss.setFocusPainted(false);
		butgauss.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		butgauss.setBackground(Color.LIGHT_GRAY);
		buttonGroup.add(butgauss);
		
		butjacobi.addActionListener(iterativoActionListener);
		butgauss.addActionListener(iterativoActionListener);
		butdeclu.addActionListener(decActionListener);
		butdeccholesky.addActionListener(decActionListener);
		
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
		
		JTextArea textordem = new JTextArea();
		textordem.setBorder(null);
		textordem.setWrapStyleWord(true);
		textordem.setMinimumSize(new Dimension(5, 20));
		scrollPane.setViewportView(textordem);
		textordem.setMaximumSize(new Dimension(5, 20));
		textordem.setColumns(1);
		textordem.setRows(1);
		
		
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
					.addContainerGap(260, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(115)
					.addComponent(Labelolho)
					.addGap(200))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(12)
					.addComponent(lblCaminhoParaSaida, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(butPronto)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(67)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCaminhoParaSaida, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(butPronto))
							.addGap(9)))
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
		
		JLabel textDeterminante = new JLabel("Determinante");
		textDeterminante.setEnabled(false);
		textDeterminante.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblMtodoDeSoluo, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(butdeclu)
										.addComponent(butdeccholesky))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(butjacobi)
										.addComponent(butgauss)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblOrdemDaMatriz)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textDeterminante)
											.addGap(18)
											.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
									.addComponent(tolmax)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane_texttol, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(37))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCaminhoParaA, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOrdemDaMatriz, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(textDeterminante, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_texttol, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(tolmax, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))))
					.addGap(28)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(butdeclu)
								.addComponent(butjacobi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(butdeccholesky)
								.addComponent(butgauss)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(13)
							.addComponent(lblMtodoDeSoluo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
					.addGap(31)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCaminhoParaA, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		JTextArea textodeterminante = new JTextArea();
		textodeterminante.setEnabled(false);
		scrollPane_3.setViewportView(textodeterminante);
		

		textordem.setDropMode(DropMode.INSERT);
		panel.setLayout(gl_panel);
		
		
		ActionListener determinanteActionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton butdeclu = (AbstractButton) actionEvent.getSource();
		        AbstractButton butdeccholesky = (AbstractButton) actionEvent.getSource();
				textDeterminante.setEnabled(true);
				textodeterminante.setEnabled(true);
		      }
		 };
		 
		 ActionListener determinante_2ActionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton  butgauss= (AbstractButton) actionEvent.getSource();
		        AbstractButton butjacobi = (AbstractButton) actionEvent.getSource();
				textDeterminante.setEnabled(false);
				textodeterminante.setEnabled(false);
		      }
		 };
		 
		butgauss.addActionListener(determinante_2ActionListener);
		butjacobi.addActionListener(determinante_2ActionListener);
		butdeccholesky.addActionListener(determinanteActionListener);
		butdeclu.addActionListener(determinanteActionListener);
		 
		 
		butPronto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (butdeclu.isSelected()) {
					try {
						calc(textpathentrada.getText(), textpathsaida.getText(), textordem.getText(), textodeterminante.getText(), texttolmax.getText(), "declu"  );
					} catch (IOException e1) {
						e1.printStackTrace();}
				}
				if (butgauss.isSelected()) {
					try {
						calc(textpathentrada.getText(), textpathsaida.getText(), textordem.getText(), textodeterminante.getText(), texttolmax.getText(), "gauss"  );
					} catch (IOException e1) {
						e1.printStackTrace();}
				}
				if (butdeccholesky.isSelected()) {
					try {
						calc(textpathentrada.getText(), textpathsaida.getText(), textordem.getText(), textodeterminante.getText(), texttolmax.getText(), "deccholesky"  );
					} catch (IOException e1) {
						e1.printStackTrace();}
				}
				if (butjacobi.isSelected()) {
					try {
						calc(textpathentrada.getText(), textpathsaida.getText(), textordem.getText(), textodeterminante.getText(), texttolmax.getText(), "jacobi"  );
					} catch (IOException e1) {
						e1.printStackTrace();}
				}
				
			}
		});
		
	}
}
