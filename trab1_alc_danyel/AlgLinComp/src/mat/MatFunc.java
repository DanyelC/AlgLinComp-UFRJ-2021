package mat;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Classe criada com o intuito de lidar com os cálculos necessários no programa
 * @author Danyel Clinário dos Santos
 * @email danyel.clinario@poli.ufrj.br
*/

public class MatFunc {
	
	static int contador_det = 0;
	
	
	public static boolean pivotamento(int k, double[][] A, double[] b) {
		/**
	     * Função de pivotamento necessária para algoritmos como Gauss e Decomposição LU
	     * @param A é a matriz dos coeficientes do sistema
	     * @param b é a vetor de coeficientes dos termos independentes
	     * @param k é um auxiliar para o loop, indicando a linha atual do pivo
	     * @return uma variavel booleana que indica se foi necessário fazer o pivotamento
	     */
		
		boolean troca = false; 									// inicializando a variavel como false
		double pivo = A[k][k]; 									// matriz utilizada para auxiliar no pivotamento
		int linha_pivo = k;
		for (int i = k+1; i <b.length; i++) {
			if (Math.abs(A[i][k]) > Math.abs(pivo)){
				pivo = A[i][k];
				linha_pivo = i;
				contador_det+=1; 								// se houve troca de pivo, é necessario incrementar o contador. Isso impactará no calculo do determinante
			}
		}
		if (k!= linha_pivo) {
			double[] temp = A[k]; 								// variaveis temporarias para a troca de valores
			double[] tmp = A[linha_pivo];
			A[linha_pivo] = temp;
			A[k] = tmp;
			
			double temp_2 = b[k]; 								// variaveis temporarias para a troca de valores
			double tmp_2 = b[linha_pivo];
			b[linha_pivo] = temp_2;
			b[k] = tmp_2;
			troca = true;
		}
		System.gc(); 													// Chamando garbage collector para liberar espaço
		return troca;
	}
	
	 public static double[] subsretro(double[][] A, double[] b) {
		 /**
		     * Função de substituição retroativa usada em algoritmos como Decomposição LU e Cholesky
		     * @param A é a matriz dos coeficientes do sistema, na Decomposição LU, representa a matriz U, por exemplo
		     * @param b é a vetor de coeficientes dos termos independentes,  na Decomposição LU, representa o vetor y, por exemplo
		     * @return vetor resposta x
		     */

		 double[] x = new double[b.length];
		 //Substituições Retroativas
		 for (int i = (b.length-1); i >-1; i--) {
			 double Somatorio = 0;
			 for (int j = i+1; j <b.length; j++) {
				 Somatorio= Somatorio+ (A[i][j] * x[j]);
			 }
			 x[i] = (b[i]-Somatorio)/A[i][i];
		 }
		System.gc(); 																			// Chamando garbage collector para liberar espaço
		return x;
	 }
	 
	 
	 public static double[] subssuc(double[][] A, double[] b) {
		 /**
		     * Função de substituição sucessiva usada em algoritmos como Decomposição LU e Cholesky
		     * @param A é a matriz dos coeficientes do sistema, na Decomposição LU, representa a matriz L, por exemplo
		     * @param b é a vetor de coeficientes dos termos independentes,  na Decomposição LU, representa o vetor b, por exemplo
		     * @return vetor resposta x
		     */

		 double[] x = new double[b.length];
		//Substituições Sucessivas
	    	for (int i = 0; i <b.length; i++) {
	    		double Somatorio = 0;
	    		for (int j = 0; j <i; j++) {
	    			Somatorio= Somatorio+ (A[i][j] * x[j]);
	    		}
	    		x[i] = (b[i]-Somatorio)/A[i][i];
	    	}
	    	System.gc(); 																			// Chamando garbage collector para liberar espaço
	    	return x;
	 }
 
/*	 public static Resposta gauss_det(double[][] A, double[] b) {
		 double[] x = new double[b.length];
		 Resposta resp = new Resposta();
		 
		 for(int k=0; k<b.length-1;k++) { //passando por cada etapa
			 for(int i =k+1;i<b.length; i++) { // passando por cada linha
				 
				 boolean troca = pivotamento(k,A,b);
				//System.out.print("houve troca");
				//System.out.println(troca);
				 double m = (-1)*(A[i][k]/A[k][k]);
				 for (int j= k+1; j<b.length; j++ ) { // atualiza linha i, olhando cada coluna j
					 A[i][j]= m * A[k][j] + A[i][j];
				 }
				 b[i] = m * b[k] + b[i]; // atualizando vetor b
				 A[i][k] = 0; // por fim zera o elemento
			 }
		 }
		 //calculo do det antes de chamar subsretro. se det ==0, erro!
		 double det = 1;
		 for (int i = 0; i<b.length; i++) {
			 det*=A[i][i];
		 }
		 det= det * (Math.pow(-1, contador_det));
		 if(det != 0 ) {
			 x = subsretro(A, b); // agora temos uma matriz triagular superior
			 resp.b = x;
			 resp.det = det;
			 return resp;
		 }else {
			 resp.b = new double[1];
			 resp.det = det;
			 //System.out.println("ERRO - SISTEMA SINGULAR");
			 return resp;
		 }
	 } */
	
	 
	 	public static double norma_vet(double[] v, double[] x) {
		 /**
		     * Função para calculo da norma dos vetores para métodos iterativos
		     * @param v vetor atual
		     * @param x é o vetor anterior
		     * @return norma dos vetores
		     */
	 		double max_num=0; 				// variavel que guarda o valor máximo do numerador
	 		double max_den=0;					// variavel que guarda o valor máximo do denominador
	 		for(int i =0; i<x.length; i++) {
	 			double num = Math.abs(v[i]-x[i]);
	 			if(num>max_num) {
	 				max_num= num;
	 			}
	 			double den = Math.abs(v[i]);
	 			if(den>max_den) {
	 				max_den= den;
	 			}
	 		}
	 		if( max_num==0 || max_den==0) {
				 JOptionPane.showMessageDialog(null,"ERRO - DIVISÃO POR ZERO");
				 return 0;
	 		}
	 		return max_num/max_den;
	 	}
	 
	 	
	 	
	 	
	    public static Resposta declu(double[][] A ,double[] b) {
	    	/**
		     * Função de Decomposição LU
		     * @param A é a matriz dos coeficientes do sistema
		     * @param b é a vetor de coeficientes dos termos independentes
		     * @return um objeto do tipo Resposta que armazena a matriz A, o vetor b, a matriz L, a matriz U, o vetor x e o determinante da matriz lida.
		     */
	    	
	    	Resposta resp = new Resposta(); 										// criando objeto resposta
	    	double[][] L= new double [A[0].length][A[0].length]; 		// criando matriz L
	    	for(int i =0; i< A[0].length; i++) { 										//transformando L em matriz identidade
		    			L[i][i]=1;
	    	}
	    	for(int k=0; k<A[0].length-1;k++) { 									//passando por cada etapa
				 for(int i =k+1;i<A[0].length; i++) { 								// passando por cada linha
					 boolean troca = pivotamento(k,A,b);
					 double m = (-1)*(A[i][k]/A[k][k]); 								//fator m importante para L
					 L[i][k]=-m;
					 for (int j= k+1; j<A[0].length; j++ ) { 							// atualiza linha i, olhando cada coluna j
						 A[i][j]= m * A[k][j] + A[i][j];
					 }
					 A[i][k] = 0; 																	// por fim zera o elemento
				 }
			 }
	    	double det = 1;
			 for (int i = 0; i<L[0].length; i++) { 
				 det*=A[i][i]; 																		// É a matriz "U" nesse caso
			 }
			 resp.det = det;
			 if(det != 0 && !(Double.isNaN(det))) { 								// se o determinante for diferente de 0 e válido ( não é NaN), segue o algoritmo
				;
			 }else {
				 JOptionPane.showMessageDialog(null,"ERRO - SISTEMA SINGULAR");
			 }
			 
	    	resp.l=L;
	    	resp.u=A;
	    	
	    	double[] y = subssuc(resp.l, b);
	    	double[] x = subsretro(resp.u, y);
	    	resp.x = x;
	    	resp.b = x;
	    	resp.y=y;
	    	System.out.print("Vetor resposta : ");
	    	for (int i = 0; i<resp.x.length; i++) {
	    		System.out.print(resp.x[i]+" ");
	    	}
	    	System.out.println();
	    	System.out.println("Acabou a Resposta ae: ");
	    	System.gc(); 																			// Chamando garbage collector para liberar espaço
	    	return resp;
	    }
	    	
	    	
	    
	    
	    public static Resposta deccholesky(double[][] A, double[] b) {
	    	/**
		     * Função de Decomposição de Cholesky
		     * @param A é a matriz dos coeficientes do sistema
		     * @param b é a vetor de coeficientes dos termos independentes
		     * @return um objeto do tipo Resposta que armazena a matriz A, o vetor b, a matriz L, a matriz Lt, o vetor x e o determinante da matriz lida.
		     */
	    	
	    	Resposta resp = new Resposta(); 										// criando objeto resposta
	    	
	    	//verificar se matriz é simetrica
	    	for(int i=0; i<A[0].length; i++) { 											//passando por cada etapa
	    		 if (A[i][i] <= 0) {
					 JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Cholesky, a matriz precisa ser simétrica positiva definida.");
					 return null;
	    		 }
				 for(int j =0; j<A[0].length; j++) { 
						 if (A[i][j] !=A[j][i]) {
							 JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Cholesky, a matriz precisa ser simétrica positiva definida.");
							 return null;
					 }
				 }
	    	}
	    	// criando matriz L e Lt que serão preenchidas em seguida
	    	double[][] L = new double[A[0].length][A[0].length]; 		
	    	double[][] Lt = new double[A[0].length][A[0].length];
	    	
	    	//algoritmo para a criação da matriz L
	    	for (int i = 0; i < A[0].length; i++) { 	 
				for (int k = 0; k < (i + 1); k++) {
					double somatorio = 0;					 								// variavel usada para o somatório necessário
					for (int j = 0; j < k; j++) {
						somatorio += L[i][j] * L[k][j];
					}
					L[i][k] = (i == k) ? Math.sqrt(A[i][i] - somatorio)
							: (1.0 / L[k][k] * (A[i][k] - somatorio));
				}
			}

	    		for(int i =0; i< A[0].length; i++) { 									//copiando L para Lt de forma que não apenas referenciem o mesmo espaço de memória	
		    		for(int j =0; j< A[0].length; j++) {
		    			Lt[i][j] = L[j][i];
			    	}
		    	}
	    																									// Preenchendo Lt como a matriz transposta de L
	    	for(int i =0; i< A[0].length; i++) {
	    		for(int j =0; j< A[0].length; j++) {
	    			Lt[i][j] = L[j][i];
		    	}
	    	}
	    	
	    	System.gc(); 																			// Chamando garbage collector para liberar espaço
	    	
	    	//Verificando se a matriz é positiva definida
	    	double teste_det=1;
	    	for(int i =0; i< A[0].length; i++) {
	    		teste_det*=L[i][i];
	    	}
	    	if (teste_det<=0 || Double.isNaN(teste_det)) {
	    		 JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Cholesky, a matriz precisa ser simétrica positiva definida.");
				 return null;
	    	}
	    	
	    	teste_det=1;
	    	for(int i =0; i< A[0].length; i++) {
	    		teste_det*=Lt[i][i];
	    		
	    	}
	    	if (teste_det<=0 || Double.isNaN(teste_det)) {
	    		 JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Cholesky, a matriz precisa ser simétrica positiva definida.");
				 return null;
	    	}else {
	    		resp.det=teste_det*teste_det; 											// passando o determinante para o objeto resposta
	    	}
	    	
	    	resp.l=L;
	    	resp.u=Lt;
	    	
	    	double[] y = subssuc(resp.l, b);												// passando L e b para a função de substutuição sucessiva
	    	double[] x = subsretro(resp.u, y);											// passando Lt e y para a função de substutuição retroativa
	    	resp.x = x;
	    	resp.b = x;
	    	resp.y=y;
	    	System.out.print("Vetor resposta : ");
	    	for (int i = 0; i<resp.x.length; i++) {
	    		System.out.print(resp.x[i]+" ");
	    	}
	    	System.out.println();
	    	System.out.println("Acabou a Resposta ae: ");
	    	System.gc(); 																			// Chamando garbage collector para liberar espaço
	    	return resp;
	    }
	    
	    
	    public static Resposta jacobi(double[][] A, double[] b, double tol) {
	    	/**
		     * Função do Método Iterativo de Jacobi
		     * @param A é a matriz dos coeficientes do sistema
		     * @param b é a vetor de coeficientes dos termos independentes
		     * @param tol é o erro mínimo esperado para que o resultado seja considerado válido
		     * @return um objeto do tipo Resposta que armazena o vetor x e um ArrayList com o historico de erros durante as iterações
		     */
	    	
	    	
	    	Resposta resp = new Resposta();
	    	boolean warning=false; // variavel que alerta o sistema sobre possivel não convergencia
	    	//verificar se matriz é simetrica
	    	for(int i=0; i<A[0].length; i++) { 											
	    		 if (A[i][i] == 0) {
					 JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Jacobi, os valores da diagonal principal devem ser diferentes de zero.");
					 return null;
	    		 }
				 /*for(int j =0; j<A[0].length; j++) { 
						 if (A[i][j] !=A[j][i]) {
							 JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Jacobi, a matriz precisa ser simétrica.");
							 return null;
					 }
				 }*/
	    	}
	    	
	    	double aii = 0;
	    	double aij = 0;
	    	//verificar se pode não convergir
	    	for(int i =0; i<A[0].length; i++) { 
	    		aii=A[i][i];
	    		 for(int j =0; j<A[0].length; j++) {
	    			 if (i!=j) {
	    			 aij+=A[i][j];
	    			 }
	    		 }
	    		 if (aij>aii) {
	    			 JOptionPane.showMessageDialog(null,"Aviso! - A matriz não respeita o critério das linhas. Logo, sua convergência não é garantida. As iterações serão limitadas a 10.000");
	    			 warning = true;
	    			 break;
	    		 }
	    	}
	    	
	    	
	    	double[] x = new double[A[0].length];
	    	double[] v = new double[A[0].length];
	    	
	    	// Preparação da matriz A e do vetor b
	    	 for(int i =0; i<A[0].length; i++) { 
	    		 for(int j =0; j<A[0].length; j++) { 
	    			 if(i != j) {
	    				 A[i][j]=A[i][j]/A[i][i];
	    			 }
	    		 }
	    		 b[i]=b[i]/A[i][i];
	    		 x[i]=b[i];  //preparando "vetor chute"
	    	 }
	    	 ArrayList <Double>norma = new ArrayList<Double>(); // lista para salvar o historico de erro
	    	 boolean convergiu = false;
	    	 if(warning == false) {
		    	 while(!convergiu) {
		    		 for(int i =0; i<A[0].length; i++) { // preparando o somatorio
		    			 double somatorio = 0;
		    			 for(int j =0; j<A[0].length; j++) {
		    				 if(i!=j) {
		    					 somatorio +=A[i][j] * x[j];  //utilizando a aproximação da iteração anterior
		    				 }
		    			 }
		    			 v[i]= b[i] - somatorio; // isso calcula a aproximação atual
		    		 }
		    		 double erro = norma_vet(v, x);
		    		 norma.add(erro); // salvando o histórico de erro
	    	    	 for(int i =0; i<A[0].length; i++) {
		    			 x[i]=v[i];
		    		 }
		    		 if (erro<= tol) {
		    			 convergiu = true;
		    			 resp.x=x;
		    	    	 resp.erros = norma;
		    	    	 return resp;
		    		 }
		    	 }
	    	 }else {
	    		 for(int k =0; k<10000; k++) {
		    		 for(int i =0; i<A[0].length; i++) { // preparando o somatorio
		    			 double somatorio = 0;
		    			 for(int j =0; j<A[0].length; j++) {
		    				 if(i!=j) {
		    					 somatorio +=A[i][j] * x[j];  //utilizando a aproximação da iteração anterior
		    				 }
		    			 }
		    			 v[i]= b[i] - somatorio; // isso calcula a aproximação atual
		    		 }
		    		 double erro = norma_vet(v, x);
		    		 norma.add(erro); // salvando o histórico de erro
	    	    	 for(int i =0; i<A[0].length; i++) {
		    			 x[i]=v[i];
		    		 }
	    	    	 resp.x=v;
	    	    	 
		    		 if (erro<= tol) {
		    			 resp.x=v;
		    	    	 resp.erros = norma;
		    	    	 return resp;
		    		 }
		    		 
		    		 if (Double.isNaN(erro) || Double.isInfinite(erro) ) {
		    	    	 resp.erros = norma;
		    	    	 return resp;
		    		 }
		    	 }
	    	 }
	    	resp.x=v;
	    	resp.erros = norma;
	    	return resp;
	    }
	    
	    
	    public static Resposta gauss_seidel(double[][] A, double[] b, double tol) {
	    	/**
		     * Função do Método Iterativo de Gauss-Seidel
		     * @param A é a matriz dos coeficientes do sistema
		     * @param b é a vetor de coeficientes dos termos independentes
		     * @param tol é o erro mínimo esperado para que o resultado seja considerado válido
		     * @return um objeto do tipo Resposta que armazena o vetor x e um ArrayList com o historico de erros durante as iterações
		     */
	    	
	    	Resposta resp = new Resposta();
	    	boolean warning=false; // variavel que alerta o sistema sobre possivel não convergencia
	    	//verificar se matriz é simetrica
	    	for(int i=0; i<A[0].length; i++) { 											
	    		 if (A[i][i] == 0) {
					 JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Gauss-Seidel, os valores da diagonal principal devem ser diferentes de zero.");
					 return null;
	    		 }
				/* for(int j =0; j<A[0].length; j++) { 
						 if (A[i][j] !=A[j][i]) {
							 JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Jacobi, a matriz precisa ser simétrica.");
							 return null;
					 }
				 }*/
	    	}
	    	
	    	double aii = 0;
	    	double aij = 0;
	    	//verificar se pode não convergir
	    	for(int i =0; i<A[0].length; i++) { 
	    		aii=A[i][i];
	    		 for(int j =0; j<A[0].length; j++) {
	    			 if (i!=j) {
	    			 aij+=A[i][j];
	    			 }
	    		 }
	    		 if (aij>aii) {
	    			 JOptionPane.showMessageDialog(null,"Aviso! - A matriz não respeita o Critério das linhas. Logo, sua convergência não é garantida. As iterações serão limitadas a 10.000");
	    			 warning = true;
	    			 break;
	    		 }
	    	}
	    	
	    	
	    	double[] x = new double[A[0].length];
	    	double[] v = new double[A[0].length];
	    	
	    	// Preparação da matriz A e do vetor b
	    	 for(int i =0; i<A[0].length; i++) { 
	    		 for(int j =0; j<A[0].length; j++) { 
	    			 if(i != j) {
	    				 A[i][j]=A[i][j]/A[i][i];
	    			 }
	    		 }
	    		 b[i]=b[i]/A[i][i];
	    	 }
	    	 ArrayList <Double>norma = new ArrayList<Double>(); // lista para salvar o historico de erro
	    	 boolean convergiu = false;
	    	 if(warning == false) {
		    	 while(!convergiu) {
		    		 for(int i =0; i<A[0].length; i++) { // preparando o somatorio
		    			 double somatorio = 0;
		    			 for(int j =0; j<A[0].length; j++) {
		    				 if(i!=j) {
		    					 somatorio +=A[i][j] * x[j];  //utilizando a aproximação da iteração anterior
		    				 }
		    			 }
		    			 x[i]= b[i] - somatorio; // isso calcula a aproximação atual
		    		 }
		    		 double erro = norma_vet(x, v);
		    		 norma.add(erro); // salvando o histórico de erro
	    	    	 for(int i =0; i<A[0].length; i++) {
		    			 v[i]=x[i];
		    		 }
		    		 if (erro<= tol) {
		    			 convergiu = true;
		    			 resp.x=x;
		    	    	 resp.erros = norma;
		    	    	 return resp;
		    		 }
		    	 }
	    	 }else {
	    		 for(int k =0; k<10000; k++) {
		    		 for(int i =0; i<A[0].length; i++) { // preparando o somatorio
		    			 double somatorio = 0;
		    			 for(int j =0; j<A[0].length; j++) {
		    				 if(i!=j) {
		    					 somatorio +=A[i][j] * x[j];  //utilizando a aproximação da iteração anterior
		    				 }
		    			 }
		    			 x[i]= b[i] - somatorio; // isso calcula a aproximação atual
		    		 }
		    		 double erro = norma_vet(x, v);
		    		 norma.add(erro); // salvando o histórico de erro
	    	    	 for(int i =0; i<A[0].length; i++) {
		    			 v[i]=x[i];
		    		 }
	    	    	 resp.x=x;
	    	    	 
		    		 if (erro<= tol) {
		    			 resp.x=x;
		    	    	 resp.erros = norma;
		    	    	 return resp;
		    		 }
		    		 
		    		 if (Double.isNaN(erro) || Double.isInfinite(erro) ) {
		    	    	 resp.erros = norma;
		    	    	 return resp;
		    		 }
		    	 }
	    	 }
	    	resp.x=x;
	    	resp.erros = norma;
	    	return resp;
	    }


	}
