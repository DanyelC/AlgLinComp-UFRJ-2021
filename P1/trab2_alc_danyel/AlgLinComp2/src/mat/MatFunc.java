package mat;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Classe criada com o intuito de lidar com os cálculos necessários no programa
 * @author Danyel Clinário dos Santos
 * @email danyel.clinario@poli.ufrj.br
 */

public class MatFunc {

	public static double residuo( double erro_atual, double erro_anterior) {
		return Math.abs(erro_atual - erro_anterior) / Math.abs(erro_atual);
	}

	public static Resposta jacobi(double[][] A, double tol) {
		/**
		 * Função do Método de Jacobi para cálculo de autovalores
		 * @param A é a matriz dos coeficientes do sistema
		 * @param b é a vetor de coeficientes dos termos independentes
		 * @param tol é o erro mínimo esperado para que o resultado seja considerado válido
		 * @return um objeto do tipo Resposta que armazena os autovetores
		 */

		Resposta resp = new Resposta();
		//verificar se matriz é simetrica
		for(int i=0; i<A[0].length; i++) { 											
			if (A[i][i] == 0) {
				JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Jacobi, os valores da diagonal principal devem ser diferentes de zero.");
				return null;
			}
			for(int j =0; j<A[0].length; j++) { 
				if (A[i][j] !=A[j][i]) {
					JOptionPane.showMessageDialog(null,"ERRO! - Para utilizar o método de Jacobi, a matriz precisa ser simétrica.");
					return null;
				}
			}
		}
		double[][] X = new  double[A[0].length][A[0].length];
		double[][] PInicial = new  double[A[0].length][A[0].length];
		double[][] PAtual = new  double[A[0].length][A[0].length];
		double[][] transposta = new  double[A[0].length][A[0].length];
		double[][] tmp = new  double[A[0].length][A[0].length];
		int linhaMaiorValor = 0;
		int colunaMaiorValor =0;
		double angulo =0;
		for(int i =0; i<A[0].length; i++) { 
			// ##Matriz identidade:
			X[i][i] = 1;}

		//##Controle do número de iterações
		int iteracoes = 0;
		//##Inicia P como matriz identidade
		for(int i =0; i<A[0].length; i++) { 
			for(int j =0; j<A[0].length; j++) { 
				PInicial[i][j]=X[i][j];}
		}
		while(true) {
			//##Incremento das iterações
			iteracoes += 1;
			for(int i =0; i<A[0].length; i++) { 
				for(int j =0; j<A[0].length; j++) { 
					PAtual[i][j]=PInicial[i][j];}
			}
			double maiorValor = 0;
			//##Busca pelo maior valor e sua linha/coluna:
			for(int i =0; i<A[0].length; i++) { 
				for(int j =0; j<A[0].length; j++) { 
					if (i !=j) {
						if(Math.abs(A[i][j]) > Math.abs(maiorValor)) {
							linhaMaiorValor = i;
							colunaMaiorValor = j;
							maiorValor = A[linhaMaiorValor][colunaMaiorValor];}}}}



			//##Condição de parada das iterações:
			if (Math.abs(maiorValor) <= tol) {
				ArrayList<Double> autovalores = new ArrayList<Double>();
				for(int i =0; i<A[0].length; i++) { 
					autovalores.add(A[i][i]);}
				resp.autovalor=autovalores;
				//resp.autovalor.add(autovalorAtual);
				resp.A=X;
				System.out.println("Fim das iterações!");
				for(int i =0; i<A[0].length; i++) { 
					System.out.println(resp.A[i][i]);}
				System.out.println("Autovalor:");
				for(int i =0; i<A[0].length; i++) { 
					System.out.println(resp.autovalor.get(i));}
				//print("Autovalores - Resultado:\n {i} ".format(i = autovalores))
				//print("Autovetores - Resultado:\n  {i} ".format(i = X))
				//print("Número de iterações: {i} ".format(i = iteracoes))
				//return X, autovalores;}
				return resp;}

			if (A[linhaMaiorValor][linhaMaiorValor] == A[colunaMaiorValor][colunaMaiorValor]) {
				angulo = Math.PI/4.000;
			}else {
				double fracao = 2*maiorValor/(A[linhaMaiorValor][linhaMaiorValor] - A[colunaMaiorValor][colunaMaiorValor]);
				angulo = Math.atan(fracao)/2;}

			//##Calculos dos senos e cossenos do angulo (fi)
			double senoAngulo = Math.sin(angulo);
			double cossenoAngulo = Math.cos(angulo);
			PAtual[linhaMaiorValor][colunaMaiorValor] = -senoAngulo;
			PAtual[colunaMaiorValor][linhaMaiorValor] = senoAngulo;
			PAtual[linhaMaiorValor][linhaMaiorValor] = cossenoAngulo;
			PAtual[colunaMaiorValor][colunaMaiorValor] = cossenoAngulo;

			//##Transposta da matriz PAtual:
			//transposta = Math.transpose(PAtual);
			for(int i =0; i<A[0].length; i++) { 
				for(int j =0; j<A[0].length; j++) { 
					transposta[i][j]= A[j][i];
				}
			}
			//##Multiplica as matrizes X e PAtual e salva em X
			for(int linha=0; linha<A[0].length; linha++) {
				for(int coluna=0; coluna<A[0].length; coluna++){
					double acumula_somaprod=0;
					for(int i=0; i<A[0].length; i++) {
						acumula_somaprod=acumula_somaprod+X[linha][i]*PAtual[i][coluna];}
					tmp[linha][coluna]=acumula_somaprod;}
			}
			for(int i =0; i<A[0].length; i++) { 
				for(int j =0; j<A[0].length; j++) { 
					X[i][j]= tmp[i][j];
				}
			}

			double[][] temp = new  double[A[0].length][A[0].length];

			//##multiplica transposta x A e salva em temp
			for(int linha=0; linha<A[0].length; linha++) {
				for(int coluna=0; coluna<A[0].length; coluna++){
					double acumula_somaprod=0;
					for(int i=0; i<A[0].length; i++) {
						acumula_somaprod=acumula_somaprod+transposta[linha][i]*A[i][coluna];}
					tmp[linha][coluna]=acumula_somaprod;}
			}

			for(int i =0; i<A[0].length; i++) { 
				for(int j =0; j<A[0].length; j++) { 
					temp[i][j]= tmp[i][j];
				}
			}
			//##Multiplica as matrizes temp e PAtual e salva em A
			for(int linha=0; linha<A[0].length; linha++) {
				for(int coluna=0; coluna<A[0].length; coluna++){
					double acumula_somaprod=0;
					for(int i=0; i<A[0].length; i++) {
						acumula_somaprod=acumula_somaprod+temp[linha][i]*PAtual[i][coluna];}
					tmp[linha][coluna]=acumula_somaprod;}
			}

			for(int i =0; i<A[0].length; i++) { 
				for(int j =0; j<A[0].length; j++) { 
					A[i][j]= tmp[i][j];
				}
			}
		}
	}


	public static Resposta pot(double[][] A, double tol) {
		/**
		 * Função do Método da Potência
		 * @param A é a matriz dos coeficientes do sistema
		 * @param b é a vetor de coeficientes dos termos independentes
		 * @param tol é o erro mínimo esperado para que o resultado seja considerado válido
		 * @return um objeto do tipo Resposta que armazena o historico dos autovetores, autovalores e o erro associado
		 */

		Resposta resp = new Resposta();
		double autovalorAnterior = 1;
		double[] Y = new  double[A[0].length];
		double[] X = new  double[A[0].length]; 
		for (int i = 0; i < A[0].length; i++) { 
			X[i]=1;
		}
		//##Controle do número de iterações:
		int iteracoes = 0;

		while(true) {
			//##Incremento das iterações:
			iteracoes +=1;
			for (int i = 0; i < A[0].length; i++) {
				double soma = 0;
				for (int j = 0; j < A[0].length; j++) {
					soma += ((A[i][j]) * (X[j]));}
				Y[i] = soma;}
			double autovalorAtual = Y[0];
			for (int j = 0; j < A[0].length; j++) {
				Y[j] = Y[j] / autovalorAtual;}
			double residuo = Math.abs(autovalorAtual - autovalorAnterior)/Math.abs(autovalorAtual);
			resp.autovalor.add(autovalorAtual);
			for (int j = 0; j < A[0].length; j++) {
				resp.autovetor.add(Y[j]);}
			resp.erros.add(residuo);


			//##Condição de parada das iterações:
			if (residuo <= tol) {
				resp.autovalor.add(autovalorAtual);
				for (int j = 0; j < A[0].length; j++) {
					resp.autovetor.add(Y[j]);}
				resp.erros.add(residuo);
				return resp;}

			//##Novos valores para a próxima iteração:
			autovalorAnterior = autovalorAtual;
			for (int j = 0; j < A[0].length; j++) {
				X[j]=Y[j];
			}
			//X = numpy.copy(Y)}

		}
	}
}
