package univas.vo;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JogoMegaSena extends Jogo {

	ArrayList 	jogosTotais 	= new ArrayList<>();
	ArrayList 	data 			= new ArrayList<>();
	String[] 	numerosQtd;
	Scanner 	sc 				= new Scanner(System.in);
	int[] 		numeros 		= new int[6];

	Map<Integer, Integer> map 	= new HashMap<Integer, Integer>();

	public JogoMegaSena() {
		csvFile();
	}

	public void csvFile() {

		//Alterar o diretório para o arquivo CSV da máquina
		File csvFile = new File("C:\\mega.csv");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e1) {
			System.out.println("Não foi possível fazer a leitura do arquivo CSV.");
			System.out.println("Verifique o diretório de origem.");
		}
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				String[] count = line.split(",");
				data.add(count[1]);
				jogosTotais.add(
						count[2] + " " + count[3] + " " + count[4] + " " + count[5] + " " + count[6] + " " + count[7]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encotrado : \n" + e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBounds : \n " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO erro: \n" + e.getMessage());
		}
	}

	public void getQtdSaidasNumeros() {
		int cont = 0;

		for (int i = 01; i < 61; i++) {
			for (int j = 0; j < jogosTotais.size(); j++) {

				String numerosJogos = jogosTotais.get(j).toString();

				String[] jogos = new String[5];

				jogos = numerosJogos.split(" ");

				String num = Integer.toString(i).format("%02d", i);

				for (int k = 0; k < 5; k++) {

					if (jogos[k].equals(num)) {
						cont++;
					}
				}
			}

			map.put(i, cont);
			cont = 0;
		}

		/*
		 * Método abaixo realiza a organização do map pelo value.
		 */

		Map<Integer, Integer> sorted = map.entrySet().stream().sorted(comparingByValue())
				.collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

		String mapAsString = sorted.keySet().stream().map(key -> "O número " + key + " saiu " + sorted.get(key) + " vezes!")
				.collect(Collectors.joining(", ", "{", "}"));

		mapAsString = mapAsString.replace("{", "").replace("}", "");

		numerosQtd = mapAsString.split(", ");

	}

	@Override
	public int getNumerosQueMaisSairam() {

		getQtdSaidasNumeros();

		System.out.println("Números mais saídos da Megasena! :)");

		for (int i = 1; i < 7; i++) {
			System.out.println(numerosQtd[numerosQtd.length - i]);
		}
		return 0;
	}

	@Override
	public int getNumerosQueMenosSairam() {

		getQtdSaidasNumeros();

		System.out.println("Números menos saídos da Megasena! :(");

		for (int i = 1; i < 7; i++) {
			System.out.println(numerosQtd[i]);
		}
		return 0;
	}

	@Override
	public void gerarRandomicos() {
		int[] sorteio = new int[6];
        
        for (int i = 0; i < 6; i++) {
            int sorteado;
            boolean repetido;

            do {
		repetido = false;
                sorteado = (int) (Math.random()*60)+1; 
                
                for (int j = 0; j < sorteio.length; j++) {
                    if (sorteio[j] == sorteado) {
                    	repetido = true;
                    }
                }
            } while(repetido);
            if(!repetido){
            	sorteio[i] = sorteado;
            }
            
        }
      
       System.out.print("Numeros Aleatorios para a Megasena = ");
        
      for(int i = 0;i < 6;i++) {
    	 System.out.print(sorteio[i]);
    	  if (i < 5) {
    		  System.out.print(" - ");
    	  }
    	    
      }
        System.out.print("\n\n");

		
	}

	@Override
	public void checarJogo() {
		

		System.out.println("Insira seus numeros: ");
         for (int i = 0; i < 6; i++) {
			
			int sorteado;
			boolean repetido;
			do {
			
				repetido = false;
				System.out.println("Digite o " + (i + 1) + "º número.");
				sorteado = sc.nextInt();	
				if(sorteado < 1 || sorteado >60 ) {
					System.out.println("Numero invalido sorteio cancelado !!!!");
					return;
				}
				
				for(int j = 0;j < numeros.length;j++) {
					if(numeros[j] == sorteado) {
						repetido = true;
					}
				}
				if(repetido) {
					System.out.println(" Numero ja digitado!!!!  \n Por Favor digite um novo numero :");
				}
			}while(repetido);
			
			numeros[i] = sorteado;
		}
		
		ArrayList<Integer> acertos = new ArrayList<>();

		String[] sorteioStr = new String[6];

		for (int i = 0; i < 6; i++) {
			sorteioStr[i] = Integer.toString(numeros[i]).format("%02d", numeros[i]);
		}

		int cont = 0;

		for (int i = 0; i < jogosTotais.size(); i++) {

			String numerosJogos = jogosTotais.get(i).toString();

			String[] jogos = new String[6];

			jogos = numerosJogos.split(" ");

			for (int j = 0; j < sorteioStr.length; j++) {

				for (int k = 0; k < jogos.length; k++) {

					if (sorteioStr[j].contains(jogos[k])) {
						cont++;
					}

				}
			}
			acertos.add(cont);
			cont = 0;
		}

		int numAcertos = 6;
		boolean stop = true;

		while (stop) {
			for (int i = 0; i < acertos.size(); i++) {
				if (acertos.get(i).equals(numAcertos)) {
					System.out.println(
							"Voce acertaria " + numAcertos + " numeros no jogo nº " + (i + 1) + " em " + data.get(i));
					System.out.println("Numeros sorteados: " + jogosTotais.get(i));
					System.out.println("");
					stop = false;
				}
			}
			numAcertos--;
		}

	}

}
