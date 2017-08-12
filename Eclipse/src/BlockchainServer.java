import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockchainServer {

	private Blockchain blockchain;

	public BlockchainServer() {
		blockchain = new Blockchain();
	}

	// getters and setters
	public void setBlockchain(Blockchain blockchain) {
		this.blockchain = blockchain;
	}

	public Blockchain getBlockchain() {
		return blockchain;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			return;
		}
		System.out.println("Wooh");
		int portNumber = Integer.parseInt(args[0]);
		BlockchainServer bcs = new BlockchainServer();
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(portNumber);
			System.out.println("whoa1");
			socket = serverSocket.accept();
			System.out.println("whoa2");
			bcs.serverHandler(socket.getInputStream(), socket.getOutputStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			reallyClose(socket);
			reallyClose(serverSocket);
		}
		// implement your code here.
	}

	public void serverHandler(InputStream clientInputStream, OutputStream clientOutputStream) {

		BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientInputStream));
		PrintWriter outWriter = new PrintWriter(clientOutputStream, true);
		System.out.println("---");
		// implement your code here.
		try {
			while(inputReader.ready()) {
				System.out.println("pl,s");
				String line = inputReader.readLine();
				System.out.println(line);
				outWriter.print(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// implement helper functions here if you need any.
	public static void reallyClose(ServerSocket serve) {
		try {
			serve.close();
		} catch (Exception e) {}
	}
	
	public static void reallyClose(Socket socket) {
		try {
			socket.close();
		} catch (Exception e) {}
	}
}
