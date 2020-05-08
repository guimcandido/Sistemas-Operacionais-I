package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

public class Main {
	public static void main(String[] args) {
		
		int permissoes = 4;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for (int idCarro = 0; idCarro < 10; idCarro++) {
			Thread tCarro = new ThreadCarro(idCarro, semaforo);
			tCarro.start();
		}
	}
}