package PSR;

import java.util.ArrayList;

public class PSR {

	String [] variaveis = { "Amarelo", "Azul","Vermelho", "Verde", "Laranja",
			  "Espanhol","Ingles", "Japones", "Ukraniano", "Noruegues", 
			  "Cafe", "Agua","Suco","Leite",  "Cha",  
			  "Kool", "Winston", "Parliament", "Lucky","Chesterfield", 
			  "Cavalo", "Zebra","Caramujo","Raposa", "Cachorro" };
	
	static boolean[] variaveisVisitadas = new boolean[25];
	static boolean[][] dominios = new boolean[5][5];
	
	//Variaveis
	//Casas
	final static int Amarelo = 0;
	final static int Azul = 1;
	final static int Vermelho = 2;
	final static int Verde = 3;
	final static int Laranja = 4;
			
	//Nacionalidades
	final static int Espanhol = 5;
	final static int Ingles = 6;
	final static int Japones = 7;
	final static int Ucraniano = 8;
	final static int Noruegues = 9;
			
	//Bebidas
	final static int Cafe = 10;
	final static int Agua = 11;
	final static int Suco = 12;
	final static int Leite = 13;
	final static int Cha = 14;
	
	//Cigarros
	final static int Kool = 15;
	final static int Winston = 16;
	final static int Parliament = 17;
	final static int Lucky = 18;
	final static int Cherterfield = 19;
	
	//Animais
	final static int Cavalo = 20;
	final static int Zebra = 21;
	final static int Caramujo = 22;
	final static int Raposa = 23;
	final static int Cachorro = 24;
	
	PSR(){
		for(int i = 0; i < 25; i++) {
			variaveisVisitadas[i] = false;
		}
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++) {
				dominios[i][j] = false;
			}
		}
	}
	
	public static Variavel proximaVariavelDisponivel(){
		for(int i = 0; i < 25 ; i++) {
			if(variaveisVisitadas[i] == false) {
				return new Variavel(i,-1,i/5);
			}
		}
		return null;
	}
	
	public static ArrayList<Integer> dominioDisponivel(int tipo){
		ArrayList<Integer> dominioDisponivel = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++)
			if(dominios[tipo][i] == false) dominioDisponivel.add(i);
		return dominioDisponivel;
	}
	
	public static boolean verificaConsistencia(Estado estado, Variavel variavel){
		estado.adicionaVariavel(variavel);
		boolean resultado = verificaValidade(estado);
		estado.removeVariavel(variavel);
		if(resultado) 
			return true;
		else return false;
	}
	
	public static boolean verificaValidade(Estado estado) {
		int a;
		int b;
		// O ingles mora na casa vermelha
		a = estado.valorVariavel(Ingles);
		b = estado.valorVariavel(Vermelho);
		if(a != b && a != -1 && b != -1) return false;
		
		//O espanhol e dono do cachorro
		a = estado.valorVariavel(Espanhol);
		b = estado.valorVariavel(Cachorro);
		if(a != b && a != -1 && b != -1) return false;
		
		//O noruegues mora na primeira casa a esquerda
		a = estado.valorVariavel(Noruegues);
		if(a != 0 && a != -1) return false;
		
		//Fumam-se cigarros Kool na casa amarela
		a = estado.valorVariavel(Kool);
		b = estado.valorVariavel(Amarelo);
		if(a != b && a != -1 && b != -1) return false;
		
		//O homem que fuma cigarros Chesterfield mora na casa ao lado do homem que mora com a raposa 
		a = estado.valorVariavel(Cherterfield);
		b = estado.valorVariavel(Raposa);
		if(Math.abs(a - b) != 1 && a != -1 && b != -1) return false;
		
		//O noruegues mora ao lado da casa azul
		a = estado.valorVariavel(Noruegues);
		b = estado.valorVariavel(Azul);
		if(Math.abs(a - b) != 1 && a != -1 && b != -1) return false;
		
		//O fumante de cigarros Winston cria caramujos
		a = estado.valorVariavel(Winston);
		b = estado.valorVariavel(Caramujo);
		if(a != b && a != -1 && b != -1) return false;
		
		//O fumante de cigarros Lucky Strike bebe suco de laranja
		a = estado.valorVariavel(Lucky);
		b = estado.valorVariavel(Suco);
		if(a != b && a != -1 && b != -1) return false;
		
		//O ucraniano bebe cha
		a = estado.valorVariavel(Ucraniano);
		b = estado.valorVariavel(Cha);
		if(a != b && a != -1 && b != -1) return false;
		
		//O japones fuma cigarros Parliament
		a = estado.valorVariavel(Japones);
		b = estado.valorVariavel(Parliament);
		if(a != b && a != -1 && b != -1) return false;
		
		//Fumam-se cigarros Kool em uma casa ao lado da casa em que fica o cavalo
		a = estado.valorVariavel(Kool);
		b = estado.valorVariavel(Cavalo);
		if(Math.abs(a - b) != 1 && a != -1 && b != -1) return false;
		
		//Bebe-se cafe na casa verde
		a = estado.valorVariavel(Verde);
		b = estado.valorVariavel(Cafe);
		if(a != b && a != -1 && b != -1) return false;
		
		//A casa verde esta imediatamente a direita (a sua direita) da casa marfim
		a = estado.valorVariavel(Verde);
		b = estado.valorVariavel(Laranja);
		if(a - b != 1 && a != -1 && b != -1) return false;
		
		//Bebe-se leite na casa do meio
		a = estado.valorVariavel(Leite);
		if(a != 2 && a != -1) return false;

		return true;
	}
	
	public static void atribuirValorVariavel(Variavel variavel, Integer i) {
		dominios[variavel.tipo][i] = true;
		variavel.valor = i;
		variaveisVisitadas[variavel.id] = true;
	}
	
	public static void limparVariavel(Variavel variavel) {
		dominios[variavel.tipo][variavel.valor] = false;
		variavel.valor = -1;
		variaveisVisitadas[variavel.id] = false;
	}
	
	public static  Estado backtracking(Estado estado) {
		if( estado.variaveis.size() > 0 && estado.variaveis.size() == 25) 
			return estado;
		Variavel variavel = proximaVariavelDisponivel();
		ArrayList<Integer> dominioDisponivel = dominioDisponivel(variavel.tipo);
		Estado proxEstado;
		
		for(Integer i: dominioDisponivel) {
			atribuirValorVariavel(variavel, i);
			if(verificaConsistencia(estado, variavel)) {
				estado.adicionaVariavel(variavel);
				proxEstado = backtracking(estado);
				if(proxEstado != null) return proxEstado;
				estado.removeVariavel(variavel);
			}
			limparVariavel(variavel);
		}
		return null;
	}
	
	public static void main(String[] args){
		PSR problemaDaZebra = new PSR();
		Estado resolucao = backtracking(new Estado());
		resolucao.imprimirResultados(problemaDaZebra.variaveis);
	}
	
}
