package univas.vo;

import java.io.IOException;

public class FactoryJogo {
	
	//Impedindo de dar o new na classe.
	private FactoryJogo() {};
	

	public static Jogo getJogo(TipoJogo tipoJogo){
	
		if( tipoJogo == TipoJogo.MEGA ) {
			return new JogoMegaSena();
		}
		if( tipoJogo == TipoJogo.QUINA ) {
			return new JogoQuina();
		}
		if( tipoJogo ==TipoJogo.LOTO ) {
			return new JogoLotoFacil();
		}
		
		System.out.println("Tipo de jogo não foi encontrado");
		return null;
	}

}

