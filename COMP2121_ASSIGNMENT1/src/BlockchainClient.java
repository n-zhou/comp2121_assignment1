import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;

public class BlockchainClient {

	public static void main(String[] args){

		if (args.length != 2) {
			return;
		}

		String serverName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		BlockchainClient bcc = new BlockchainClient();
		Socket socket = null;
		try {
			socket = new Socket(serverName, portNumber);
			bcc.clientHandler(socket.getInputStream(), socket.getOutputStream());
		}
		catch(Exception e) {
			e.getMessage();
		}
		finally {
			reallyClose(socket);
		}
		
		// implement your code here.
	}

	public void clientHandler(InputStream serverInputStream, OutputStream serverOutputStream) {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(serverInputStream));
		PrintWriter outWriter = new PrintWriter(serverOutputStream, true);

		Scanner sc = new Scanner(System.in);
		try {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				outWriter.println(line);
				if(line.equals("cc"))
					return;
				while(!inputReader.ready()) {}
				while(inputReader.ready()) {
					System.out.println(inputReader.readLine());
				}
			}
		}
		catch(Exception e) {
			e.getMessage();
		}
	}

	// implement helper functions here if you need any.
	public static void reallyClose(Socket socket) {
		try {
			socket.close();
		} catch (Exception e) {}
	}
}
