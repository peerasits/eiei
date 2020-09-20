import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	Socket socket;
	DataInputStream daIn;
	DataOutputStream daOut;

	Server(Socket socket, DataInputStream daIn, DataOutputStream daOut) {
		this.socket = socket;
		this.daIn = daIn;
		this.daOut = daOut;
	}

	public void run() {

		try {
			daOut.writeUTF("Welcome");
			while (true) {
				String receive = daIn.readUTF();
				daOut.writeUTF("Server reply : " + receive);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) throws Exception {
		ServerSocket sockket_1 = new ServerSocket(2222);
		while (true) {
			Socket socket;

			try {
				socket = sockket_1.accept();
				System.out.println("New client is connect");
				DataInputStream DaIn = new DataInputStream(socket.getInputStream());
				DataOutputStream DaOut = new DataOutputStream(socket.getOutputStream());
				Server s = new Server(socket, DaIn, DaOut);
				s.start();
			} catch (IOException error) {
				System.out.println(error);
			}
		}
	}
}
