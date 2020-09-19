package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CorredoresThread extends Thread {

	private int idCorredor;
	private static int ordem;
	private Semaphore semaforo;
	private static int saindo;

	public CorredoresThread(int idCorredor, Semaphore semaforo) {
		this.idCorredor = idCorredor;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		PessoaAndando();
		try {
			semaforo.acquire();
			Porta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			Sair();
		}
	}

	private void PessoaAndando() {
		int disTotal = (int) (Math.random() * 201);
		int disPercorrida = 0;
		Random deslocamento = new Random();
		int valor;
		int tempo = 100;
		while (disPercorrida < disTotal) {
			valor = deslocamento.nextInt(3) + 4;
			disPercorrida += valor;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("#" + idCorredor + " já andou " + disPercorrida + "m.");
		}
		ordem++;
		System.out.println("#" + idCorredor + " foi o " + ordem + "o. a chegar");
	}

	private void Porta() {

		int tempo = (int) ((Math.random() * 1001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void Sair() {
		saindo++;
		System.out.println("#" + idCorredor + " foi o " + saindo + "o. a abrir e cruzar a porta.");
	}
}