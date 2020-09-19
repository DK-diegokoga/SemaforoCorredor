package view;
import java.util.concurrent.Semaphore;
import controller.CorredoresThread;
public class Principal {

	public static void main(String[] args) {
		
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		
		for(int idCorredor = 0; idCorredor < 4; idCorredor++) {
		Thread corredor = new CorredoresThread(idCorredor,semaforo);
		corredor.start();
		}	
	}
}
