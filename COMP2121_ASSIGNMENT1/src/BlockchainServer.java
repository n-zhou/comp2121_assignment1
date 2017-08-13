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
	static Socket socket;

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
		int portNumber = Integer.parseInt(args[0]);
		BlockchainServer bcs = new BlockchainServer();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNumber);
			while(true){
				socket = serverSocket.accept();
				bcs.serverHandler(socket.getInputStream(), socket.getOutputStream());
				socket.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		catch(Exception e) {
			//e.getMessage();
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
		// implement your code here.
		try {
			String line;
			while((line = inputReader.readLine()) != null) {
				if(line.equals("cc")) {
					return;
				}
				if(line.equals("pb")) {
					outWriter.println(blockchain.toString());
					continue;
				}
				if(!line.startsWith("tx")) {
					outWriter.println("Error\n");
					continue;
				}
				if(blockchain.addTransaction(line) != 0)
					outWriter.println("Accepted\n");
				else
					outWriter.println("Rejected\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		finally {
			reallyClose(outWriter);
			reallyClose(clientOutputStream);
			reallyClose(inputReader);
			reallyClose(clientInputStream);
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

	public static void reallyClose(InputStream is) {
		try {
			is.close();
		} catch (Exception e) {}
	}

	public static void reallyClose(OutputStream os) {
		try {
			os.close();
		} catch (Exception e) {}
	}

	public static void reallyClose(BufferedReader bufferedReader) {
		try {
			bufferedReader.close();
		} catch(Exception e) {}

	}

	public static void reallyClose(PrintWriter pw) {
		try {
			pw.close();
		} catch(Exception e) {}
	}
}
