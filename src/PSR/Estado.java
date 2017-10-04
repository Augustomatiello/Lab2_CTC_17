package PSR;

import java.util.HashMap;

public class Estado {
	HashMap<Integer, Variavel> variaveis;
	
	Estado(){
		variaveis = new HashMap<Integer, Variavel>();
	}
	
	public int valorVariavel(int idVariavel) {
		if(variaveis.containsKey(idVariavel))
			return variaveis.get(idVariavel).valor;
		return -1;
	}

	public void adicionaVariavel(Variavel variavel) {
		variaveis.put(variavel.id, variavel);
	}

	public void removeVariavel(Variavel variavel) {
		variaveis.remove(variavel.id);
	}

	public void imprimirResultados(String[] variavel) {
		int valor = 0;
		while(valor != 5){
			System.out.println("Casa: " + valor);
			for(int i = 0; i < 25; i++){
				if(variaveis.get(i).valor == valor){
					System.out.println(variavel[variaveis.get(i).id]);
				}
			}
			System.out.println(" ");
			valor ++;
		}
	}
}
