package mat;

import java.util.ArrayList;

public class Resposta{
    public double[] b;
    public ArrayList<Double> erros = new ArrayList<Double>();
    public ArrayList<Double> autovalor = new ArrayList<Double>();
    public ArrayList<Double> autovetor = new ArrayList<Double>();
    public double[] x;
    public double[] y;
    public double[][] l;
    public double[][] A;
    public double[][] u; // serve como u ou Lt, dependendo do método
}
