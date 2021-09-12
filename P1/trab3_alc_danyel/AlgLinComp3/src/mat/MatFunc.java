package mat;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Classe criada com o intuito de lidar com os cálculos necessários no programa
 * @author Danyel Clinário dos Santos
 * @email danyel.clinario@poli.ufrj.br
 */

public class MatFunc {

	public static double inter(int n, double[] x, double[] y,double xpy) {
		/**
		 * Função do Método de Lagrange para interpolação polinomial
		 * @param n é o numero de pontos lidos
		 * @param x é um vetor com os valores de x de cada ponto
		 * @param y é um vetor com os valores de y de cada ponto
		 * @return uma variavel contento o y para o ponto x desejado
		 */
		double produto= 1;
		double soma=0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					produto *= (xpy - x[j]) / (x[i] - x[j]);
				}
			}
			soma += produto * y[i];
			produto = 1;    // variavel auxiliar do somatorio, precisa voltar para 1
		}
		return soma;
	}


	public static double reg(int n, double[] x, double[] y,double xpy) {
		/**
		 * Função do Método Regressão linear
		 * @param n é o numero de pontos lidos
		 * @param x é um vetor com os valores de x de cada ponto
		 * @param y é um vetor com os valores de y de cada ponto
		 * @return uma variavel contento o y para o ponto x desejado
		 */
		double soma_x = 0 , soma_y=0 , soma_xy=0 , soma_x2 = 0;
		for (int i = 0; i < n; i++) { 
			soma_x += x[i];
			soma_y += y[i];
			soma_xy += x[i]*y[i];
			soma_x2 += Math.pow(x[i],2);
		}
		double alfa = (n*soma_xy - soma_x*soma_y)/(n*soma_x2 - Math.pow(soma_x,2));
		double beta = soma_y/n - alfa*soma_x/n;
		double resp = alfa*xpy + beta;
		System.out.println(resp);
		return resp;
	}
}

