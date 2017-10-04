package funcao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;



public class Funcao {
	double x;
	double y;
	double f;
	double delta = 0.05;
	double tempInicial = 300000;
	double alpha = 0.99;
	double tempFinal = 0.00000002;
	int High = 10;
	ArrayList<Ponto> pontosMaximos = new ArrayList<Ponto>();	

	private double calculaF(double x, double y){
		f = 4*Math.exp(-x*x - y*y) + Math.exp(-(x-5)*(x-5) - (y-5)*(y-5))+
				Math.exp(-(x+5)*(x+5) - (y-5)*(y-5)) + Math.exp(-(x-5)*(x-5) - (y+5)*(y+5))+
				Math.exp(-(x+5)*(x+5) - (y+5)*(y+5));
		return f;
	}
	
	private void tempera(){
		
		double temp = tempInicial;
		Random r = new Random();
		
		x = (r.nextDouble() - 0.5)*High;
		y = (r.nextDouble() - 0.5)*High;
		f = calculaF(x,y);
		while(temp > tempFinal){
			
			// primeiro avalia x crescente
			double xp = x + delta;
			double dif = calculaF(xp,y) - f;
			if (dif > 0){
				x = xp;
			}
			else {
				Ponto aux = new Ponto(x,y);
				aux.setaF(f);
				pontosMaximos.add(aux);
				double gama = r.nextDouble();
				if(gama < Math.exp(-(dif)/temp)){
					x = xp;
				}
			}
			
			// depois avalia x decrescente
			xp = x - delta;
			 dif = calculaF(xp,y) - f;
			if (dif > 0){
				x = xp;
			}
			else {
				Ponto aux = new Ponto(x,y);
				aux.setaF(f);
				pontosMaximos.add(aux);
				double gama = r.nextDouble();
				if(gama < Math.exp(-(dif)/temp)){
					x = xp;
				}
			}
			
			// depois avalia y crescente
			double yp = y + delta;
			dif = calculaF(x,yp) - f;
			if (dif > 0){
				y = yp;
			}
			else {
				Ponto aux = new Ponto(x,y);
				aux.setaF(f);
				pontosMaximos.add(aux);
				double gama = r.nextDouble();
				if(gama < Math.exp(-(dif)/temp)){
					y = yp;
				}
			}
			// depois avalia y decrescente
			yp = y - delta;
			dif = calculaF(x,yp) - f;
			if (dif > 0){
				y = yp;
			}
			else {
				Ponto aux = new Ponto(x,y);
				aux.setaF(f);
				pontosMaximos.add(aux);
				double gama = r.nextDouble();
				if(gama < Math.exp(-(dif)/temp)){
					y = yp;
				}
			}
			
			f = calculaF(x,y);
			temp = temp*alpha;
		}
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Funcao func = new Funcao();
		func.tempera();
		Ponto max = new Ponto(0,0);
		if (func.pontosMaximos.size() == 0){
			System.out.println("Não foi encontrado um máximo, o valor final de f foi " + func.f + " para x:" 
					+ func.x +" e y:" + func.y);
		}
		else{
			for(int i = 0; i < func.pontosMaximos.size(); i ++){
				if(func.pontosMaximos.get(i).f > max.f){
					max.x = func.pontosMaximos.get(i).x;
					max.y = func.pontosMaximos.get(i).y;
					max.f = func.pontosMaximos.get(i).f;
				}
			}
		}
		System.out.println("O valor máximo da função f " + max.f + " para x: " + max.x + " e y: " + max.y);
	}
	
}
