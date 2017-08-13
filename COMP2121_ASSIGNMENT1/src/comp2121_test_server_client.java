import static org.junit.Assert.*;
import java.util.concurrent.Semaphore;

import org.junit.Test;

public class comp2121_test_server_client {

	static Semaphore sem = new Semaphore(0); 
	
	private class T extends Thread{
		
		private int mode;
		
		public T(int mode) {
			this.mode = mode;
		}
		
		@Override
		public void run() {
			if(mode == 0) {
				String args[] = {"8333"};
				BlockchainServer.main(args);
				sem.release();
			}
			else {
				try {
					sem.acquire();
				}
				catch (InterruptedException e) {}
				String args[] = {"localhost", "8333"};
				BlockchainClient.main(args);
			}
		}
	}
	
	@Test(timeout=1000)
	public void test(){
		try {
			Thread t1 = new T(0);
			t1.start();
			Thread t2 = new T(1);
			t2.start();
			t2.join();
			t1.join();
		}
		catch (Exception e) {}
		
	}

}
