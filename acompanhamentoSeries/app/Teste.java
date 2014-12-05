import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import models.Episodio;
import models.Serie;
import models.Temporada;


public abstract class Teste {

	public static void main(String[] args) throws IOException {
		File csv = new File("seriesFinalFile.csv");
		BufferedReader br = new BufferedReader(new FileReader(csv)); 
		String line = br.readLine();
		String[] linha = line.split(",");
		
		String serieLinha;
		int temporadaLinha;
		String episodioLinha;
		
		String serieAtualNome = linha[0];
		int temporadaAtualNum = 1;
		int epLinhaNum = Integer.parseInt(linha[2]);
		String episodioAtual = linha[3];
		
		Serie serieAtual = new Serie(serieAtualNome);
		Temporada temporadaAtual = new Temporada(1);
		Episodio ep = new Episodio(episodioAtual, temporadaAtual, epLinhaNum);
		temporadaAtual.addEpisodio(ep);
		serieAtual.addTemporada(temporadaAtual);
		
		System.out.println(serieAtualNome + " adicionada ao BD");

		while((line = br.readLine()) != null){
			linha = line.split(",");
			
			if(linha.length >= 4){
				serieLinha = linha[0];
				temporadaLinha = Integer.parseInt(linha[1]);
				epLinhaNum = Integer.parseInt(linha[2]);
				episodioLinha = linha[3];
				
				System.out.println(serieAtualNome + " " + serieLinha + " " + temporadaLinha + " " + epLinhaNum + " " + episodioLinha);
			
				if(serieLinha.equals(serieAtualNome)){
					if(temporadaLinha == temporadaAtualNum){
						if(!episodioLinha.equals(episodioAtual)){
							serieAtual.addEpisodio(new Episodio(episodioLinha, temporadaAtual, temporadaAtualNum));
							episodioAtual = episodioLinha;
						}
					}
					else{
						temporadaAtual = new Temporada(temporadaLinha);
						serieAtual.addTemporada(temporadaAtual);
						serieAtual.addEpisodio(new Episodio(episodioLinha, temporadaAtual, temporadaLinha));
						temporadaAtualNum = temporadaLinha;
						
						if(!episodioLinha.equals(episodioAtual)){
							serieAtual.addEpisodio(new Episodio(episodioLinha, temporadaAtual, temporadaAtualNum));
							episodioAtual = episodioLinha;
						}
					}
				}
				else{
					serieAtualNome = serieLinha;
					temporadaAtualNum = 1;
					episodioAtual = episodioLinha;
					serieAtual = new Serie(serieAtualNome);
					temporadaAtual = new Temporada(1);
					ep = new Episodio(episodioAtual, temporadaAtual, epLinhaNum);
					temporadaAtual.addEpisodio(ep);
					serieAtual.addTemporada(temporadaAtual);
					
					System.out.println(serieLinha + " adicionada ao BD");
				}
			}
		}
		br.close();

	}

}
