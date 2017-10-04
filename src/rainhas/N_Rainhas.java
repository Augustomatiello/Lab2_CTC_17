package rainhas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class N_Rainhas {
	

		public int n;
		int[] posicoesRainhas;
		char[][] tabuleiro;
		
		public N_Rainhas(int qtdQueens) {
			n = qtdQueens;
			posicoesRainhas = new int[n];
			tabuleiro = new char[n][n];
		}

		public void HillClimbing() {
			long start = System.currentTimeMillis();
			boolean climb = true;
			while(climb) {
				posicoesRainhas = geraPosicoes();
				imprimeTabuleiro();
				int minLocal = calcularHeuristica();
				
				for(int coluna = 0; coluna < n; coluna++) {
					for(int linha = 0; linha < n; linha++) {
						if(linha != posicoesRainhas[coluna]) {
							int linhaAtual = posicoesRainhas[coluna];
							posicoesRainhas[coluna] = linha;
							int heuristica = calcularHeuristica();
							if(heuristica < minLocal) {
								minLocal = heuristica;
							}
							else posicoesRainhas[coluna] = linhaAtual;
						}
					}
				}
				if (calcularHeuristica() == 0)
					climb = false;
			}
			long elapsedTime = System.currentTimeMillis()-start;
			System.out.println("Resultados para " + n + " rainhas:\n");
			imprimeTabuleiro();
			System.out.println("Tempo total: "+ elapsedTime +" ms");
		}
		
		
		
		private int[] geraPosicoes() {

			List<Integer> randomPos = new ArrayList<Integer>();

			Random r = new Random();
			for (int i = 0; i < n; i++) {
				randomPos.add(r.nextInt(8));
			}

			int[] randomPosicoes = new int[n];

			for (int i = 0; i < randomPos.size(); i++) {
				randomPosicoes[i] = randomPos.get(i);
			}

			return randomPosicoes;
		}
		
		
		private void atualizaTabuleiro(){
			for(int coluna = 0; coluna < posicoesRainhas.length; coluna ++){
				for(int linha = 0; linha < posicoesRainhas.length; linha++){
					if(linha == posicoesRainhas[coluna]){
						tabuleiro[linha][coluna] = 'R';
					}
					else tabuleiro[linha][coluna]= '-';
				}
			}
		}
		
		
		public void imprimeTabuleiro() {
			
			atualizaTabuleiro();
			
			for(int i = 0; i < posicoesRainhas.length; i++){
				System.out.print(posicoesRainhas[i] +  " ");
			}
			System.out.println(" ");
			
			for (int coluna = 0; coluna < n; coluna++) {
				for (int linha = 0; linha < n; linha++) {
				System.out.print(tabuleiro[coluna][linha] + " ");	
				}
				System.out.println(" ");
			}
			System.out.println(" ");
		}
		
		public int calcularHeuristica() {
			int conflitos = 0;
			int linha1;
			int linha2;
			int coluna1;
			int coluna2;
			for(int i = 0; i < n; i++) {
				for(int j = i+1; j < n; j++) {
					linha1 = posicoesRainhas[i];
					linha2 = posicoesRainhas[j];
					coluna1 = i;
					coluna2 = j;
					if(linha1 == linha2){ 
						conflitos++;
					}
					else if (coluna1 == coluna2){
						conflitos++;
					}
					else if(Math.abs(linha1-linha2) == Math.abs(coluna1-coluna2)) {
						conflitos++;
					}		
				}
			}
			return conflitos;
		}

	
		public static void main(String[] args) {
			N_Rainhas rainhas = new N_Rainhas(25);
			rainhas.HillClimbing();
		}
		
}

