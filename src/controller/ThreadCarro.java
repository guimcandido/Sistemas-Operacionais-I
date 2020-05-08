package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {

	private int idCarro;
	private int sentido;// 0=NORTE,1=LESTE,2=SUL,3=OESTE
	private Semaphore semaforo;

	public ThreadCarro(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
		this.sentido = (int) (Math.random() * 4);
	}

	public String sentidoString(int sentido) {
		if (sentido == 0) {
			return "norte";
		} else if (sentido == 1) {
			return "leste";
		} else if (sentido == 2) {
			return "sul";
		} else {
			return "oeste";
		}
	}

	public String sentidoContrarioString(int sentido) {
		if (sentido == 2) {
			return "norte";
		} else if (sentido == 3) {
			return "leste";
		} else if (sentido == 1) {
			return "sul";
		} else {
			return "oeste";
		}
	}

	public void run() {
		System.out.println("[ >> ] #" + idCarro + " segue no sentido " + sentidoString(this.sentido) + ".");
		carroAndando();
		try {
			semaforo.acquire();
			carroEstacionado();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			carroSaindo();
		}
	}

	private void carroAndando() {
		int distanciaTotal = (int) ((Math.random() * 501) + 1500);
		int distanciaPercorrida = 0;
		int deslocamento = 100;
		int tempo = 30;
		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[ >| ] #" + idCarro + " chegou no semáforo " + sentidoContrarioString(this.sentido) + ".");
	}

	private void carroEstacionado() {
		System.out.println(
				"[ || ] #" + idCarro + " está aguardando no semáforo " + sentidoContrarioString(this.sentido) + ".");
	}

	private void carroSaindo() {
		System.out.println(
				"[ |> ] #" + idCarro + " atravessou o cruzamento seguindo para " + sentidoString(this.sentido) + ".");
	}
}
