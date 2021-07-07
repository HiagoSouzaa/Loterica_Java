package univas.vo;
import univas.vo.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class Jogo {
	
	public Jogo() {
	}
	
	public abstract int getNumerosQueMaisSairam();
	
	public abstract int getNumerosQueMenosSairam();
	
	public abstract void gerarRandomicos();
	
	public abstract void checarJogo();

	
}

