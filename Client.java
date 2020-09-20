import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class Client extends JFrame {
	Socket socket;
	DataInputStream daIn;
	DataOutputStream daOut;

	JPanel panel;
	JTextField text;
	JButton btn;
	JTextArea pane;

	public Client() {
		try {
			socket = new Socket("127.0.0.1", 2222);
			daIn = new DataInputStream(socket.getInputStream());
			daOut = new DataOutputStream(socket.getOutputStream());

			panel = new JPanel();

			text = new JTextField();
			text.setColumns(20);
			
			pane = new JTextArea();
			pane.setPreferredSize(new Dimension(700,300));
			pane.setText(daIn.readUTF());
			btn = new JButton("sent");
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						daOut.writeUTF(text.getText());
						String str = daIn.readUTF();
						pane.append(str);
						
					}catch(Exception ex) {
						System.out.println(ex.getMessage());
					}
					
				}
			});
			
			panel.add(pane);
			panel.add(text);
			panel.add(btn);

			add(panel);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(800, 600);
			setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		Client c = new Client();
	}
}
